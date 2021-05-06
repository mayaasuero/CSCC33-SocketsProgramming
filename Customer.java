/**
 * 
 */

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Customer {
    public static void main(String[]args) throws IOException, ClassNotFoundException{
        try {
            // establish connection here
            // connection must be opened at service_rep first
            Socket client = new Socket("localhost", 6666); 


            /**
             * for objects
             */
            OutputStream outputStream = client.getOutputStream();
            InputStream inputStream = client.getInputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            
            /**
             * for basic text
             */
            // InputStreamReader ist = new InputStreamReader(client.getInputStream(),"UTF-8");
            // OutputStreamWriter ost = new OutputStreamWriter(client.getOutputStream(),"UTF-8");
            // BufferedReader buffR = new BufferedReader(ist);
            // BufferedWriter buffW = new BufferedWriter(ost);

            System.out.println("connection established\n");
            Scanner sc = new Scanner(System.in);
            
            System.out.print("Name: ");
            String name = sc.nextLine();


            int choice = 1;
            while(choice != 0){
                menu();
                choice = sc.nextInt();
                sc.nextLine();
                switch(choice){
                    case 1: // new ticket
                        
                        boolean isResolved = false;
                        Ticket openTicket = createTicket(sc,name);
                        objectOutputStream.writeObject(openTicket);

                        while(openTicket.getTicketStatus() == false){
                            System.out.print("\nYou: ");
                            String content = sc.nextLine();
                            Message msg = new Message(name, content);
                            objectOutputStream.writeObject(msg);

                            // from client
                            Message fromClient = (Message) objectInputStream.readObject();
                            fromClient.printMessage();
                            // buffW.write(msg.toString());
                            // buffW.newLine();
                            // buffW.flush();

                            // System.out.println("Helpdesk: "+ buffR.readLine());                            
                            if(content.equalsIgnoreCase("RESOLVED")){
                                openTicket.resolveTicket();
                            }
                        }
                        break;
                    case 2: // view ticket
    
                }
            }
            System.out.println("Logging out...");

            client.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
    }


    private static void menu(){
        System.out.println("--------------------------------");
        System.out.println("Menu");
        System.out.println("[0] Quit");
        System.out.println("[1] New ticket");
        System.out.println("[2] View tickets");
        System.out.print("\nChoice: ");
    }

    private static Ticket createTicket(Scanner sc, String name){
        System.out.print("Topic: ");
        String topic = sc.nextLine();
        // System.out.print("Description: ");
        // String message = sc.nextLine();

        // Message issue = new Message(name, message);
        Ticket ticket = new Ticket(1,name,topic);
        return ticket;
    }
    
    private static void respond(Scanner sc, Ticket ticket, String name){
        System.out.print("Description: ");
        String message = sc.nextLine();

        Message issue = new Message(name, message);
        ticket.appendMessage(issue);
    }

    private static void viewTicket(){

    }
}

