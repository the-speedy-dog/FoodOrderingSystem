import java.util.Queue;
import java.util.Scanner;

public abstract class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
