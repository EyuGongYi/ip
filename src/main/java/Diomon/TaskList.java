package Diomon;

import java.util.ArrayList;

/**
 * The {@code TaskList} class manages a collection of {@link Task} objects.
 * It provides methods for adding, removing, marking, and unmarking tasks.
 * It also allows conversion of tasks to string representations suitable for display and storage.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructs a {@code TaskList} with the given tasks.
     *
     * @param args One or more {@code Task} objects to initialize the list with.
     */
    public TaskList(Task... args) {
        this.tasks = new ArrayList<Task>();
        for (Task task : args) {
            this.add(task);
        }
    }

    /**
     * Adds a task to the list.
     *
     * @param t The task to be added.
     */
    public void add(Task t) {
        this.tasks.add(t);
    }

    /**
     * Removes a task from the list based on its index.
     *
     * @param i The index of the task to be removed.
     */
    public void remove(int i) {
        this.tasks.remove(i);
    }

    /**
     * Retrieves a task from the list based on its index.
     *
     * @param i The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int i) {
        return this.tasks.get(i);
    }

    /**
     * Marks the task at the specified index as completed.
     *
     * @param i The index of the task to be marked as completed.
     */
    public void mark(int i) {
        this.tasks.get(i).mark();
    }

    /**
     * Marks the task at the specified index as not completed.
     *
     * @param i The index of the task to be marked as incomplete.
     */
    public void unmark(int i) {
        this.tasks.get(i).unmark();
    }

    /**
     * Returns a string representation of the task list for display.
     * Each task is numbered and shows its type icon, status icon, and description.
     *
     * @return A string containing all tasks in a human-readable format.
     */
    @Override
    public String toString() {
        String temp = "";
        for (int i = 0; i < tasks.size(); i++){
            Task t = tasks.get(i);
            temp = temp.concat(String.format("%d-> [%s][%s] %s\n", i + 1, t.getTypeIcon(), t.getStatusIcon(), t));
        }
        return temp;
    }

    /**
     * Converts the task list to a string representation suitable for storage.
     * Each task is formatted as a string and separated by a newline.
     *
     * @return A string containing all tasks formatted for storage.
     */
    public String toStorageString() {
        String temp ="";
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            temp = temp.concat(String.format("%s\n", t.toStorageString()));
        }
        return temp;
    }
}
