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

    public Ticket(String cn, String t){
        this.ticket_number = 0;
        this.client_name = cn;
        this.rep_name = "";
        this.topic = t;

        // default 
        this.isResolved = false;
    }

    public void assignTNumber(int i){
        this.ticket_number = i;
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

    public Boolean getTicketStatus(){
        return this.isResolved;
    }
}