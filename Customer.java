/**
 * 
 */

import java.io.*;
import java.net.*;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Customer {
    public static void main(String[]args) throws IOException, ClassNotFoundException{
        try {
            // establish connection here
            
            /**
             * for basic text
             */
            // InputStreamReader ist = new InputStreamReader(client.getInputStream(),"UTF-8");
            // OutputStreamWriter ost = new OutputStreamWriter(client.getOutputStream(),"UTF-8");
            // BufferedReader buffR = new BufferedReader(ist);
            // BufferedWriter buffW = new BufferedWriter(ost);

            Scanner sc = new Scanner(System.in);
            
            System.out.print("Name: ");
            String name = sc.nextLine();


            int choice = 1;
            while(choice != 0){
                menu();
                choice = sc.nextInt();
                sc.nextLine();
                switch(choice){
                    case 0: //exit
                        break;
                    case 1: // new ticket

                        // connection must be opened at service_rep first
                        System.out.println("Connecting to server...");
                        Socket client = new Socket("localhost", 6666); 
            
            
                        /**
                         * for objects
                         */
                        OutputStream outputStream = client.getOutputStream();
                        InputStream inputStream = client.getInputStream();
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);        

                        System.out.println("connection established\n");

                        
                        // boolean isResolved = false;
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
                                client.close();
                            }
                        }
                        break;
                    case 2: // view ticket
    
                }
            }
            System.out.println("Logging out...");

        } catch (SocketException se) {
            System.out.println("Error. Connection to server was closed.");
        } 
    }


    private static void menu(){
        System.out.println("--------------------------------");
        System.out.println("Menu");
        System.out.println("[0] Quit");
        System.out.println("[1] New ticket");
        // System.out.println("[2] View tickets");
        System.out.print("\nChoice: ");
    }

    private static Ticket createTicket(Scanner sc, String name){
        System.out.print("Topic: ");
        String topic = sc.nextLine();
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

