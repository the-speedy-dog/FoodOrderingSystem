import java.util.ArrayList;
import java.util.function.Consumer;

public class Command {
    private ArrayList<String> names;
    private ArrayList<ArrayList<String>> modifiers;
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
        modifiers = new ArrayList<>();
    }

    public Command(
        ArrayList<String> names,
        ArrayList<ArrayList<String>> modifiers,
        String description,
        Consumer<String[]> action
    ) {
        this.names = names;
        this.modifiers = modifiers;
        this.description = description;
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void printDetails() {
        // Print out command's aliases
        for (int i = 0; i < names.size(); i++) {
            System.out.printf(
                "%s%s",
                names.get(i),
                i == names.size()-1 ? " " : ", "
            );
        }
        // Print out modifiers and their options in brackets
        for (int i = 0; i < modifiers.size(); i++) {
            ArrayList<String> mod = modifiers.get(i);
            System.out.print("<");
            for (int j = 0; j < mod.size(); j++) {
                System.out.printf(
                    "%s%s",
                    mod.get(j),
                    j == mod.size()-1 ? "" : ", "
                );
            }
            System.out.printf(">%s", i == modifiers.size()-1 ? "" : " ");
        }
        System.out.println("\n - " + description);
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
        try{
            action.accept(params);
        } catch (Exception e) {
            System.out.println("Invalid Arguments!");
            printDetails();
        }
    }
}
