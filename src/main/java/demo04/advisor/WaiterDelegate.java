package demo04.advisor;

public class WaiterDelegate {
    private Waiter waiter;

    public void waiterService(String clientName) {
        waiter.greetTo(clientName);
        waiter.serverTo(clientName);
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }
}
