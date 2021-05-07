/**
 * 
 */

import java.io.*;
import java.net.*;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.LinkedList;

public class Customer {
    public static void main(String[]args) throws IOException, ClassNotFoundException{
        try {
            Scanner sc = new Scanner(System.in);
            
            System.out.print("Name: ");
            String name = sc.nextLine();


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

            System.out.println("Connection established\n");

            objectOutputStream.writeObject(name);


            int choice = 1;
            while(choice != 0){
                menu();
                choice = sc.nextInt();
                sc.nextLine();
                objectOutputStream.writeObject(choice);

                switch(choice){
                    case 0: //exit
                        client.close();
                        break;
                    case 1: // new ticket
                       
                        // boolean isResolved = false;
                        Ticket openTicket = createTicket(sc,name);
                        objectOutputStream.writeObject(openTicket);

                        while(openTicket.getTicketStatus() == false){
                            System.out.print("\nYou: ");
                            String content = sc.nextLine();
                            Message msg = new Message(name, content);
                            objectOutputStream.writeObject(msg);

                            // from client
                            
                            // buffW.write(msg.toString());
                            // buffW.newLine();
                            // buffW.flush();

                            // System.out.println("Helpdesk: "+ buffR.readLine());                            
                            if(content.equalsIgnoreCase("RESOLVED")){
                                openTicket.resolveTicket();
                                break;
                                //
                            }

                            Message fromServer = (Message) objectInputStream.readObject();
                            fromServer.printMessage();
                        }
                        break;
                    case 2: // view ticket
                        Ticket arr[] = (Ticket[]) objectInputStream.readObject();
                        if(arr.length > 0){
                            for(int i = 0; i < arr.length; i++){
                                arr[i].viewTicket();
                            }
                        } else{
                            System.out.println("No previous tickets.");
                        }
                        break;    
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
        System.out.println("[2] View tickets");
        System.out.print("\nChoice: ");
    }

    private static Ticket createTicket(Scanner sc, String name){
        System.out.print("Topic: ");
        String topic = sc.nextLine();
        Ticket ticket = new Ticket(name,topic);
        return ticket;
    }

    private static void viewTicket(){

    }
}

