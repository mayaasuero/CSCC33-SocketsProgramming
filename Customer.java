public class Customer {
    public static void main(String[]args){
        Message sample = new Message("maya","hello");
        System.out.println(sample.getSender());
        System.out.println(sample.getContent());
        System.out.println(sample.getDate());
        System.out.println(sample.getTime());
    }
    
}

