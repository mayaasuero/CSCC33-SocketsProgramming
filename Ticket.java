/**
 * 
 */

 import java.util.LinkedList;

public class Ticket{
    private int ticket_number;
    private String client_name;
    private String rep_name;
    private boolean isResolved;
    private LinkedList<Message> conversation;

    public Ticket(int tn, String cn, String rp){
        this.ticket_number = tn;
        this.client_name = cn;
        this.rep_name = rp;

        // default 
        this.isResolved = false;
        this.conversation = new LinkedList<Message>();
    }

    public void viewTicket(int tn){
        
    }

    public void resolveTicket(int tn){
        
    }
}