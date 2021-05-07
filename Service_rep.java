import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.LinkedList;
public class Service_rep {
    public static void main(String[]args) throws IOException, ClassNotFoundException{
        LinkedList<Ticket> tickets  = new LinkedList<Ticket>();
        Scanner sc = new Scanner(System.in);
        
        // establish connection here
        // this must be opened first to accept client
        ServerSocket server = new ServerSocket(6666);

        System.out.print("Name: ");
        String name = sc.nextLine();
        int choice = 1;

        while(choice != 0){
            menu();
            choice = sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 0: // exit
                    break;
                case 1:  // connect with customer
                    System.out.println("Waiting for connection...");
                    Socket client = server.accept();
                    System.out.println("Connection established with customer.\n");
                    try {         
                        /**
                         * initialize input & output
                         * for objects
                         */
                        InputStream inputStream = client.getInputStream();
                        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                        OutputStream outputStream = client.getOutputStream();
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

                        // customer name
                        String client_name = (String) objectInputStream.readObject();

                        // customer request
                        int req = 1;
                        while(req != 0){
                            System.out.println("\nWaiting for customer request...");
                            req = (int) objectInputStream.readObject();
                            switch(req){
                                case 1: // create tickets
                                    System.out.println("Customer is creating a ticket...");
                                    Ticket currentTicket = (Ticket) objectInputStream.readObject();
                                    currentTicket.assignTNumber(tickets.size()+1);
                                    tickets.add(currentTicket);
                                    System.out.println("\nNew ticket received");
                                    currentTicket.viewTicket();
                    
                                    while(currentTicket.getTicketStatus() == false){
                    
                                        // from client
                                        Message fromClient = (Message) objectInputStream.readObject();
                                        if(fromClient.getContent().equalsIgnoreCase("RESOLVED")){
                                            currentTicket.resolveTicket();
                                            System.out.println("\nTicket resolved.");
                                            break;
                                        }
                                        fromClient.printMessage();
                    
                                        // response to client
                                        System.out.print("\nYou: ");
                                        String response = sc.nextLine();
                                        Message toClient = new Message(name, response);
                                        objectOutputStream.writeObject(toClient);        
                                    }
                                    break;
                                case 2: // view tickets
                                    System.out.println("Customer requesting to view tickets...");
                                    objectOutputStream.writeObject(getClientTickets(tickets, client_name));
                                    System.out.println("Tickets sent to customer...");
                                    break;
                                case 0: // logout
                                    System.out.println("\nCustomer has disconnected.");
                                    break;
                                default:
                                    System.out.println("Error: invalid request from user.");
                                    break;
                            }
                        }
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                case 2: // view tickets;
                    checkTickets(tickets);
                default:
                    break;
            }
        }
        server.close();
        System.out.println("Logging out...");
    }
    
    private static void menu(){
        System.out.println("--------------------------------");
        System.out.println("Menu");
        System.out.println("[0] Quit");
        System.out.println("[1] Connect with a Customer");
        // System.out.println("[1] Accept tickets");
        System.out.println("[2] View previous ticket");
        System.out.print("\nChoice: ");
    }

    private static void checkTickets(LinkedList<Ticket> tickets){
        System.out.println("--------------------------------");
        System.out.println("Resolve tickets: " + tickets.size());
        for(int i = 0; i < tickets.size(); i++){
            Ticket oldTicket = tickets.get(i);
            System.out.println("  ------------------------------");
            oldTicket.viewTicket();
        }
    }

    private static Ticket[] getClientTickets(LinkedList<Ticket> tickets, String name){
        LinkedList<Ticket> subList = new LinkedList<Ticket>();
        for(int i = 0; i < tickets.size(); i++){
            Ticket oldTicket = tickets.get(i);
            if(oldTicket.getClientName().equalsIgnoreCase(name)){
                subList.add(oldTicket);
            }
        }
        Object[] arr_temp = subList.toArray();

        Ticket[] arr = new Ticket[arr_temp.length];
        for(int i =0; i < arr.length; i++) {
            arr[i] = (Ticket) arr_temp[i];
        }
        return arr;
    }
}
