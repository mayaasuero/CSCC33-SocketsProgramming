/**
 * 
 */

 import java.util.Scanner;

public class Customer {
    private static String name;
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        int choice = 1;
        while(choice != 0){
            menu();
            choice = sc.nextInt();

            switch(choice){
                case 1: // new ticket
                    createTicket(sc);
                case 2: // view ticket

            }
        }
        System.out.println("Logging out...");
    }


    private static void menu(){
        System.out.println("--------------------------------");
        System.out.println("Menu");
        System.out.println("[0] Quit");
        System.out.println("[1] New ticket");
        System.out.println("[2] View ticket");
        System.out.print("\nChoice: ");
    }

    private static void createTicket(Scanner sc){
        System.out.print("Topic: ");
        String topic = sc.nextLine();
        System.out.print("Description: ");
        String message = sc.nextLine();

        Message issue = new Message(name, message);
        Ticket ticket = new Ticket(1,name,topic);
    }
    
    private static void respond(Scanner sc, Ticket ticket){
        System.out.print("Description: ");
        String message = sc.nextLine();

        Message issue = new Message(name, message);
        ticket.appendMessage(issue);
    }

}

