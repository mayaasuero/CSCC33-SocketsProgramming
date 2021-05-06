import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
public class Service_rep {
    public static void main(String[]args) throws IOException, ClassNotFoundException{
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
                case 1:  // accept tickets
                    System.out.println("waiting for connection...");
                    Socket client = server.accept();
                    System.out.println("connection established\n");
                    try {
                        System.out.println("waiting for new ticket...");
        
                        /**
                         * initialize input & output
                         * for objects
                         */
                        InputStream inputStream = client.getInputStream();
                        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                        OutputStream outputStream = client.getOutputStream();
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        
                        Ticket currentTicket = (Ticket) objectInputStream.readObject();
                        System.out.println("\nNew ticket received");
                        currentTicket.viewTicket();
        
                        while(currentTicket.getTicketStatus() == false){
        
                            // from client
                            Message fromClient = (Message) objectInputStream.readObject();
                            fromClient.printMessage();
        
                            // response to client
                            System.out.print("\nYou: ");
                            String response = sc.nextLine();
                            Message toClient = new Message(name, response);
                            objectOutputStream.writeObject(toClient);
        
                            // String msgClient = buffR.readLine();
                            // System.out.println("Client: "+ msgClient);
                            // System.out.print("Reply: ");
        
                            // String responds = sc.nextLine();
                            // buffW.write(responds);
                            // buffW.newLine();
                            // buffW.flush();
        
                            if(fromClient.getContent().equalsIgnoreCase("RESOLVED")){
                                currentTicket.resolveTicket();
                                client.close();
                            }
        
                        }
                        //While(server is up)
        
                        
                        //Ticket newTicket = (Ticket)objectInputStream.readObject();
                        //newTicket.viewTicket();
                        // while loop (conversation with client until resolved)
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                default:
                    break;
            }
        }
        
        // while(a == 1){
            
        //     a = sc.nextInt();
        // }
        server.close();



        // Scanner sc = new Scanner(System.in);
        // String name = sc.next();
        // int choice = 1;
        // while(choice != 0){
        //     menu();
        //     choice = sc.nextInt();

        //     switch(choice){
        //         case 1: // new ticket
                    
        //         case 2: // view ticket

        //     }
        // }

        System.out.println("Logging out...");
    }
    
    private static void menu(){
        System.out.println("--------------------------------");
        System.out.println("Menu");
        System.out.println("[0] Quit");
        System.out.println("[1] Accept tickets");
        // System.out.println("[2] View a ticket");
        System.out.print("\nChoice: ");
    }

    private static void checkTickets(){
        //check list of tickets
    }

    private static void respond(){

    }

    private static void closeTicket(){

    }

}
