import java.util.Queue;

public abstract class User {
    private String name;
    private Queue<Order> orders;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
