package ChatbotRan;

import java.util.ArrayList;
import java.util.Scanner;

public class Ran {
    ArrayList<Task> tasks;

    public static void main(String[] args) {


        Ran chatbot = new Ran();
        chatbot.run();
    }

    public Ran() {
        this.tasks = new ArrayList<>();
    }

    public void run() {
        System.out.println("Hello. I am ");
        String art = "__________                \n" +
                "\\______   \\_____    ____  \n" +
                " |       _/\\__  \\  /    \\ \n" +
                " |    |   \\ / __ \\|   |  \\\n" +
                " |____|_  /(____  /___|  /\n" +
                "        \\/      \\/     \\/ ";
        System.out.println(art);
        System.out.println("What would you like to do today?");
        boolean running = true;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("____________________________________________________________");
            String line = sc.nextLine();
            int space = line.indexOf(' ');
            String command = space == -1 ? line : line.substring(0, space);
            switch (command) {
                case "mark":
                    Task task = this.handleTaskNo(line, space);
                    if (task != null) {
                        task.setCompleted(true);
                        System.out.println("Alright. I have marked this task as complete: ");
                        System.out.println(task);
                    }
                    break;
                case "unmark":
                    task = this.handleTaskNo(line, space);
                    if (task != null) {
                        task.setCompleted(false);
                        System.out.println("If that's the case, I'll set that task as incomplete.");
                        System.out.println(task);
                    }
                    break;
                case "deadline":
                    Deadline deadline = Deadline.parse(line, space);
                    this.addTask(deadline);
                    break;
                default:
                    switch (line) {
                        case "bye":
                            running = false;
                            break;
                        case "list":
                            if (tasks.isEmpty()) {
                                System.out.println("You haven't got any tasks.");
                            } else {
                                for (int i = 0; i < tasks.size(); i++) {
                                    System.out.println("Task " + (i + 1) + ":" + tasks.get(i));
                                }
                            }
                            break;
                        default:
                            System.out.println("I didn't understand that.");
                    }


            }
        } while (running);

        System.out.println("Goodbye, please return soon.");
    }


    private Task handleTaskNo(String line, int space) {
        if (space == -1) {
            System.out.println("Missing task number");
            return null;
        }
        Integer taskNo = parseNumber(line, space);
        if (taskNo == null || taskNo < 1) {
            System.out.println("Invalid task number.");
        } else if (taskNo > tasks.size()) {
            System.out.println("No task by that number.");
        } else {
            return tasks.get(taskNo - 1);
        }
        return null;
    }
    private void addTask(Task task) {
        if (task != null) {
            System.out.println("I've added this task to the list: ");
            System.out.println(task);
            System.out.println("There are now "+tasks.size()+" tasks in the list");
        }
    }

    private static Integer parseNumber(String line, int spacePos) {
        try {
            return Integer.parseInt(line.substring(spacePos + 1));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}

