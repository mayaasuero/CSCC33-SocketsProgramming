/**
 * 
 */

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Customer {
    public static void main(String[]args) throws IOException{
        try {
            // establish connection here
            // connection must be opened at service_rep first
            Socket client = new Socket("localhost", 6666); 
            OutputStream outputStream = client.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            
            InputStreamReader ist = new InputStreamReader(client.getInputStream());
            OutputStreamWriter ost = new OutputStreamWriter(client.getOutputStream());
            BufferedReader buffR = new BufferedReader(ist);
            BufferedWriter buffW = new BufferedWriter(ost);
            // Ticket ticket = new Ticket(1, "Maya", "sample");
            System.out.println("connection established");
            Scanner sc = new Scanner(System.in);
            System.out.println("Name: ");
            String name = sc.next();
            int choice = 1;
            while(choice != 0){
                menu();
                choice = sc.nextInt();
                sc.next();
                switch(choice){
                    case 1: // new ticket
                        
                        boolean isResolved = false;
                        while(isResolved==false){
                            //Ticket openTicket = createTicket(sc,name);
                            //objectOutputStream.writeObject(openTicket);
                            String msg = sc.nextLine();
                            buffW.write(msg);
                            buffW.newLine();
                            buffW.flush();

                            System.out.println("Helpdesk: "+ buffR.readLine());
                            System.out.print("You: ");

                            if(msg.equalsIgnoreCase("RESOLVED")){
                                isResolved=true;
                            }
                        }
                        
                    case 2: // view ticket
    
                }
            }
            System.out.println("Logging out...");

            client.close();
        } catch (UnknownHostException e) {
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
        System.out.print("Description: ");
        String message = sc.nextLine();

        Message issue = new Message(name, message);
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

