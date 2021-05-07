/**
 * 
 */

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;

public class Message implements Serializable{
    private String sender;
    private String content;
    private LocalDate date;
    private LocalTime time;

    public Message(String sn, String data){
        this.sender = sn;
        this.content = data;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    public String getSender(){
        return this.sender;
    }

    public String getContent(){
        return this.content;
    }

    public void printMessage(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm");
        System.out.println("--------------------------------");
        System.out.println("Time & Date: " + this.time.format(formatter).toString() + " " + this.date.toString());
        System.out.println("From : " + this.getSender());
        System.out.println("Message: " + getContent());
    }
}
