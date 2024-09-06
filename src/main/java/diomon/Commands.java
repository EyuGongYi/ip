package diomon;

public class Commands {
    private boolean canExit;
    public enum Types {
        TODO,
        DEADLINE,
        EVENT,
        LIST,
        MARK,
        UNMARK,
        BYE,
        HELP,
        DELETE,
    }

    public Commands() {
        this.canExit = false;
    }

    public boolean isCanExitExit() {
        return canExit;
    }

    public static Types checkType(String command) {
        for(Types t : Types.values()) {
            if (t.name().equalsIgnoreCase(command)) return t;
        }
        throw new RuntimeException();
    }

    public void runCommand(Types t, String input, TaskList taskList) {

        if (input == null) {
            switch (t) {
            case LIST:
                runList(taskList);
                break;
            case BYE:
                runBye();
                break;
            case HELP:
                runHelp();
                break;
            default:
                throw new RuntimeException("Missing argument/ Function not implemented");
            }
        } else {
            switch (t) {
            case TODO:
                runTodo(taskList, input);
                break;
            case DEADLINE:
                runDeadline(taskList, input);
                break;
            case EVENT:
                runEvent(taskList, input);
                break;
            case MARK:
                runMark(taskList, input);
                break;
            case UNMARK:
                runUnmark(taskList, input);
                break;
            case DELETE:
                runDelete(taskList, input);
                break;
            default:
                throw new RuntimeException("Unknown argument/ Function not implemented yet");
                }
            }
    }

    public void run(String input, TaskList taskList) {
        // Process input
        String[] inputArray = input.split(" ", 2);
        String inputCommand = inputArray[0];
        String inputContent = inputArray.length == 1 ? null: inputArray[1];
        try {
            runCommand(checkType(inputCommand), inputContent, taskList);
        } catch (RuntimeException e){
            System.out.println("Command don't exist. Please retry. Type 'Help' for help");
        }

    }

    //Command Logic
    public void runTodo(TaskList taskList, String input) {
        Task newTask = Task.of(input, Task.TaskType.TODO);
        taskList.add(newTask);
        System.out.printf("New Diomon.Task: [%s] has been added.\n", newTask);
        System.out.print(taskList);
    }
    public void runDeadline(TaskList taskList, String input) {
        try {
            Task newTask = Task.of(input, Task.TaskType.DEADLINE);
            taskList.add(newTask);
            System.out.printf("New Diomon.Task: [%s] has been added.\n", newTask);
            System.out.print(taskList);
        } catch (Exception e) {
            System.out.println("Incorrect/ missing details given");
        }

    }
    public void runEvent(TaskList taskList, String input) {
        try {
            Task newTask = Task.of(input, Task.TaskType.EVENT);
            taskList.add(newTask);
            System.out.printf("New Diomon.Task: [%s] has been added.\n", newTask);
            System.out.print(taskList);
        } catch (Exception e) {
            System.out.println("Incorrect/ missing details given");
        }
    }
    public void runList(TaskList taskList) {
        System.out.println("Diomon.TaskList:");
        System.out.print(taskList);
    }
    public void runMark(TaskList taskList, String input) {
        try {
            int i = Integer.parseInt(input);
            System.out.printf("Diomon.Task %d: [%s] has been marked", i, taskList.get(i - 1));
            taskList.mark( i- 1);
        } catch (NumberFormatException e) {
            System.out.println("Param given for marking a task is wrong, please try again");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bound, please try again");
        }
    }
    public void runUnmark(TaskList taskList, String input) {
        try {
            int i = Integer.parseInt(input);
            System.out.printf("Diomon.Task %d: [%s] has been unmarked\n", i, taskList.get(i - 1));
            taskList.unmark( i- 1);
        } catch (NumberFormatException e) {
            System.out.println("Param given for unmarking a task is wrong, please try again");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index out of bound");
        }
    }
    public void runBye() {
        String byeMessage = "Bye. Hope to see you again soon!";
        System.out.println(byeMessage);
        this.canExit = true;
    }
    public void runHelp() {
        String helpMessage = "Diomon.Commands:\n-TODO\n-DEADLINE\n-EVENT\n-LIST\n-MARK\n-UNMARK\n-BYE\n-HELP";
        System.out.print(helpMessage);
    }
    public void runDelete(TaskList taskList, String input) {
        try {
            int i = Integer.parseInt(input);
            System.out.printf("Diomon.Task %d: [%s] has been deleted", i, taskList.get(i - 1));
            taskList.remove( i- 1);
        } catch (NumberFormatException e) {
            System.out.println("Param given for marking a task is wrong, please try again");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bound, please try again");
        }
    }
}
