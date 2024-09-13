package diomon;

import diomon.command.Command;
import diomon.parser.Parser;
import diomon.ui.Ui;

import java.util.Scanner;

/**
 * The {@code Main} class is the entry point for the diomon.Diomon application, which manages a task list.
 * It handles user input, interacts with the storage to save and load tasks, and executes commands.
 */
public class Diomon {
    private Storage storage;
    private Command command;
    private TaskList taskList;
    private Ui ui;

    /**
     * Prints a greeting message when the application starts.
     */
    private static void greeting() {
        String greetingMessage = "________________________________________________________________\nHello! I'm diomon.Diomon\nWhat do you need recorded?\n________________________________________________________________\n";
        System.out.print(greetingMessage);
    }

    /**
     * The main loop of the application.
     * It loads tasks from storage, listens for user input, executes commands, and saves tasks before exiting.
     */
    private void run() {
        // Initialise instance
        storage = new Storage("data/data.txt");
        Parser parser = new Parser();
        Scanner scanner = new Scanner(System.in);
        taskList = new TaskList(storage.load());
        ui = new Ui();
        while (true) {
            String input = scanner.nextLine();
            command = Parser.parse(input);
            command.execute(taskList, ui, storage);
            System.out.println(command.getResponse());
            if (command.isCanExit()) {
                storage.save(taskList.toStorageString());
                break;
            }
        }
    }

    public String getResponse(String input) {
        try {
            storage = new Storage("data/data.txt");
            Scanner scanner = new Scanner(System.in);
            taskList = new TaskList(storage.load());
            ui = new Ui();
            command = Parser.parse(input);
            command.execute(taskList, ui, storage);
            return command.getResponse();
        } catch (RuntimeException e){
            return e.getMessage();
        }
    }

    /**
     * The main method to start the diomon.Diomon application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Diomon().run();
    }
}
