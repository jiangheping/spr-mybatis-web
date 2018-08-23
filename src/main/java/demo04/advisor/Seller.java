package demo04.advisor;

public class Seller {
    /**
     * 和Waiter类中的同名的方法,目的是为了验证仅仅织入了Waiter类中的greetTo方法
     */
    public void greetTo(String name) {
        System.out.println("Seller Greet to " + name);
    }
}
