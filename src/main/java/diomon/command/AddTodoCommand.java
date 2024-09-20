package diomon.command;

import diomon.Storage;
import diomon.exception.MissingInputException;
import diomon.parser.Parser;
import diomon.task.Task;
import diomon.task.TaskList;

public class AddTodoCommand extends AddCommand{
    public AddTodoCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        try {
            if (input == null) {
                throw new MissingInputException();
            }
            Task newTask = Parser.parseTodo(input);
            tasks.add(newTask);
            setResponse(String.format("Task: ( %s ) has been added.", newTask));
        } catch (RuntimeException e) {
            throw new RuntimeException("Something went wrong, please check your input");
        }
    }
}
