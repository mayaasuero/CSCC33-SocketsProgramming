/**
 * 
 */

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Message {
    private String sender;
    private String content;
    private LocalDate date;
    private LocalTime time;
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

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

    public String getDate(){
        return this.date.toString();
    }

    public String getTime(){
        return this.time.format(timeFormatter);
    }

    public void printMessage(){
        System.out.println("Time & Date: " + this.getTime() + " " + this.getDate());
        System.out.println("From : " + this.getSender());
        System.out.println(getContent());
        System.out.println("--------------------------------");
    }
}
