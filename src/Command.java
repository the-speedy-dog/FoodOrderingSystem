import java.util.ArrayList;
import java.util.function.Consumer;

public class Command {
    private ArrayList<String> names;
    private String description;
    private Consumer<String[]> action;

    public Command(
        ArrayList<String> names,
        String description,
        Consumer<String[]> action
    ) {
        this.names = names;
        this.description = description;
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void printDetails() {
        for (int i = 0; i < names.size(); i++) {
            System.out.printf(
                "%s%s",
                names.get(i),
                i == names.size()-1 ? "\n" : ", "
            );
        }
        System.out.println(" - " + description);
    }

    public boolean includes(String name) {
        for (String n : names) {
            if (n.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void run(String... params) {
        action.accept(params);
    }
}
