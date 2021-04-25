/**
 * 
 */

 import java.util.LinkedList;

public class Ticket{
    private int ticket_number;
    private String client_name;
    private String rep_name;
    private String topic;
    private boolean isResolved;
    private LinkedList<Message> conversation;

    public Ticket(int tn, String cn, String t){
        this.ticket_number = tn;
        this.client_name = cn;
        this.rep_name = "";
        this.topic = t;

        // default 
        this.isResolved = false;
        this.conversation = new LinkedList<Message>();
    }

    public void viewTicket(int tn){
        
    }

    public void resolveTicket(){
        this.isResolved = true;        
    }

    public void appendMessage(Message msg){
        conversation.add(msg);
    }
}