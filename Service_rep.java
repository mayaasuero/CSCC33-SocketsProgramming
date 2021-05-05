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
        System.out.println("waiting for connection...");
        Socket client = server.accept();
        System.out.println("connection established");
        try {
            System.out.println("waiting for new ticket...");
            InputStreamReader ist = new InputStreamReader(client.getInputStream(), "UTF-8");
            OutputStreamWriter ost = new OutputStreamWriter(client.getOutputStream(),"UTF-8");
            BufferedReader buffR = new BufferedReader(ist);
            BufferedWriter buffW = new BufferedWriter(ost);

            boolean isResolved = false;
            while(isResolved==false){
                String msgClient = buffR.readLine();
                System.out.println("Client: "+ msgClient);
                System.out.print("Reply: ");

                String responds = sc.nextLine();
                buffW.write(responds);
                buffW.newLine();
                buffW.flush();

                if(msgClient.equalsIgnoreCase("RESOLVED")){
                    isResolved=true;
                }

            }
            //While(server is up)
            //InputStream inputStream = client.getInputStream();
            //ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            
            //Ticket newTicket = (Ticket)objectInputStream.readObject();
            //newTicket.viewTicket();
            // while loop (conversation with client until resolved)
            server.close();
            client.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



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
        // System.out.println("Logging out...");
    }
    
    private static void menu(){
        System.out.println("--------------------------------");
        System.out.println("Menu");
        System.out.println("[0] Quit");
        System.out.println("[1] List tickets");
        System.out.println("[2] View a ticket");
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
