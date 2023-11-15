package javasebasic.reflection;

import javasebasic.annotation.MyAnnotation;

@MyAnnotation(value = "hello pay class")
public class Pay {
    private String channel;

    Pay(){
        this.channel = "default";
    }

    Pay(String channel){
        this.channel = channel;
    }

    @MyAnnotation(value = "hello pay method")
    public void pay(int money){
        System.out.println("Pay $" +money+ " to seller.");
    }
}
