import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Handler {
    ArrayList<Command> commands = new ArrayList<>();
    private Scanner scnr; 
    private boolean running;

    public Handler(Scanner scnr) {
        this.scnr = scnr;
        running = true;

        // add help command here so it appears first
        commands.add(
            new Command(
                new ArrayList<String>(Arrays.asList("help", "h")),
                "List available commands",
                obj -> help()
            )
        );
    }

    private void help() {
        for (Command cmd : commands) {
            cmd.printDetails();
        }
    }

    private void quit() {
        running = false;
    }

    public void loop() {

        // add quit command here so it appears last
        commands.add(
            new Command(
                new ArrayList<String>(Arrays.asList("quit", "q")),
                "Quits out of process",
                obj -> quit()
            )
        );

        String input;
        String command;
        String[] args;

        while (running) {
            System.out.print("javadash > ");
            input = scnr.nextLine();
            args = input.split(" ");
            command = args[0];
            args = Arrays.copyOfRange(args, 1, args.length);

            for (Command cmd : commands) {
                if (cmd.includes(command)) {
                    cmd.run(args);
                }
            }
        }
    }
}
