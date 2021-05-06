/**
 * 
 */

import java.util.LinkedList;
import java.io.Serializable;

public class Ticket implements Serializable{
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

    public void viewTicket(){
        System.out.println("  Ticket #: "+this.ticket_number);
        System.out.println("  Client: "+this.client_name);
        System.out.println("  Topic: "+this.topic);
        if(this.isResolved == true){
            System.out.println("  Status: Closed");
        } else{
            System.out.println("  Status: Open");
        }
    }

    public void resolveTicket(){
        this.isResolved = true;        
    }

    public void appendMessage(Message msg){
        conversation.add(msg);
    }

    public Boolean getTicketStatus(){
        return this.isResolved;
    }
}