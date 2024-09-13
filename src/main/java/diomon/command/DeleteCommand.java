package diomon.command;

import diomon.Storage;
import diomon.TaskList;
import diomon.ui.Ui;

public class DeleteCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int i = Integer.parseInt(input);
            setResponse(String.format("Task %d: %s has been deleted", i, tasks.get(i - 1)));
            tasks.remove( i- 1);
        } catch (NumberFormatException e) {
            setResponse("Param given for marking a task is wrong, please try again");
        } catch (IndexOutOfBoundsException e) {
            setResponse("Index out of bound, please try again");
        }
    }
}
