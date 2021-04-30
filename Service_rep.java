public class Service_rep {
    private String name;

    public static void main(String[]args){
        //establish connection here

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
    
}
