import java.util.Scanner;

public class Okay {
    public static void main(String[] args) {

        // This is for getting user input
        Scanner input = new Scanner(System.in);

        // Max number of tasks we can have
        final int MAX = 5;

        // Arrays (like boxes) to store task details
        String[] list = new String[MAX]; // Where we store task names
        boolean[] check = new boolean[MAX]; // Marks if a task is done (true = done, false = not done)
        String[] must = new String[MAX]; // Stores priority of tasks (High, Medium, Low)
        String[] end = new String[MAX]; // Stores the deadline (when the task needs to be finished)
        String[] notif = new String[MAX]; // Stores any reminder message
        int choice = 0; // Stores the user's choice of action

        // Keep showing the menu until the user decides to stop
        while (choice != 5) {
            // These count how many tasks are in the list and how many are completed
            int taskCount = 0;
            int doneTask = 0;

            // Go through all tasks and count how many are there and how many are done
            for (int i = 0; i < MAX; i++) {
                if (list[i] != null) taskCount++; // If the task is not empty, count it
                if (check[i]) doneTask++; // If the task is marked as done, count it
            }

            // Show the number of tasks and completed tasks
            System.out.println();
            System.out.println("Current Status: ");
            System.out.println("Total tasks: " + taskCount);
            System.out.println("Completed tasks: " + doneTask);

            // Show the menu options for the user to choose from
            System.out.println("Type 1 to add a new task.");
            System.out.println("Type 2 to update a task.");
            System.out.println("Type 3 to delete a task.");
            System.out.println("Type 4 to see your tasks.");
            System.out.println("Type 5 to exit the program.");

            // Ask the user what they want to do
            System.out.print("Select an option: ");
            choice = input.nextInt();  // Get the user's choice
            input.nextLine();  // Clear any extra input

            // Check if there are any tasks before allowing updates or deletions
            boolean Tasks = false;
            for (String task : list) {
                if (task != null) {
                    Tasks = true; // Found at least one task
                    break;
                }
            }

            // If there are no tasks, show a message and ask them to add one
            if (!Tasks && (choice == 2 || choice == 3)) {
                System.out.println("No tasks yet. Please add a task first.");
                continue;
            }

            // Do the action based on the user's choice
            switch (choice) {
                case 1: // If user chooses to add a new task
                    System.out.println("Enter the task name:");
                    for (int i = 0; i < MAX; i++) {
                        if (list[i] == null) { // Find an empty spot for the new task
                            System.out.print("Task " + (i + 1) + ": ");
                            list[i] = input.nextLine(); // Store the task name

                            // Ask for extra details about the task
                            System.out.print("Enter the priority (High, Medium, Low): ");
                            must[i] = input.nextLine();
                            System.out.print("Enter the deadline (format: YYYY-MM-DD): ");
                            end[i] = input.nextLine();
                            System.out.print("Enter a reminder (optional): ");
                            notif[i] = input.nextLine();

                            check[i] = false; // The task is not done yet
                            break; // Exit the loop after adding the task
                        }
                    }
                    break;

                case 2: // If user chooses to update an existing task
                    System.out.println("Enter the task number to update (1 to " + MAX + "): ");
                    int UP = input.nextInt(); // Get the task number
                    input.nextLine(); // Clear extra input
                    if (UP >= 1 && UP <= MAX && list[UP - 1] != null) { // Check if task exists
                        System.out.println("Current task: " + list[UP - 1]);

                        // Ask the user if they want to change the task or mark it as done
                        System.out.print("Do you want to revise or mark the task as complete? (revise/complete): ");
                        String okay = input.nextLine();

                        if (okay.equalsIgnoreCase("revise")) { // If they want to revise
                            System.out.print("Enter new task name: ");
                            list[UP - 1] = input.nextLine(); // Change task name

                            // Ask for updated details
                            System.out.print("Enter new priority (High, Medium, Low): ");
                            must[UP - 1] = input.nextLine();
                            System.out.print("Enter new deadline (format: YYYY-MM-DD): ");
                            end[UP - 1] = input.nextLine();
                            System.out.print("Enter a new reminder (optional): ");
                            notif[UP - 1] = input.nextLine();

                            System.out.println("Task updated: " + list[UP - 1]);
                        } else if (okay.equalsIgnoreCase("complete")) { // If they want to mark it as complete
                            check[UP - 1] = true;
                            System.out.println("Task marked as complete: " + list[UP - 1]);
                        } else {
                            System.out.println("Invalid choice. No changes made.");
                        }
                    } else {
                        System.out.println("Invalid task number or task not found.");
                    }
                    break;

                case 3: // If user chooses to delete a task
                    boolean D = false;
                    while (!D) {
                        System.out.println("Enter the task number to delete (1 to " + MAX + "): ");
                        int ekis = input.nextInt(); // Get task number to delete
                        input.nextLine(); // Clear extra input
                        if (ekis >= 1 && ekis <= MAX && list[ekis - 1] != null) { // Check if task exists
                            System.out.println("Current task: " + list[ekis - 1]);
                            System.out.print("Are you sure you want to delete this task? (yes/no): ");
                            String confirmation = input.nextLine();

                            if (confirmation.equalsIgnoreCase("yes")) { // If yes, delete the task
                                // Remove the task and clear all its details
                                list[ekis - 1] = null;
                                check[ekis - 1] = false;
                                must[ekis - 1] = null;
                                end[ekis - 1] = null;
                                notif[ekis - 1] = null;
                                System.out.println("Task " + ekis + " deleted.");
                                D = true;
                            } else {
                                System.out.println("Task not deleted.");
                                D = true;
                            }
                        } else {
                            System.out.println("Invalid task number. Please enter a valid number.");
                        }
                    }
                    break;

                case 4: // If user wants to see their tasks
                    System.out.println("Your To-Do List:");
                    boolean all = false;
                    for (int i = 0; i < MAX; i++) {
                        if (list[i] != null) { // Show tasks that are not empty
                            System.out.print((i + 1) + ". " + list[i]);
                            if (check[i]) {
                                System.out.println(" [Completed]"); // Mark task as done
                            } else {
                                System.out.println(" [Pending]"); // Mark task as not done yet
                            }
                            System.out.println("Priority: " + must[i]);
                            System.out.println("Deadline: " + end[i]);
                            System.out.println("Reminder: " + notif[i]);
                            all = true;
                        }
                    }

                    // If no tasks exist, show a message
                    if (!all) {
                        System.out.println("No tasks available.");
                    } else {
                        System.out.println("\nType 1 to see only completed tasks or 2 to view all tasks.");
                        int view = input.nextInt(); // Ask if they want to see completed or all tasks
                        input.nextLine(); // Clear input

                        if (view == 1) { // If they want to see only completed tasks
                            System.out.println("Completed tasks:");
                            boolean Done = false;
                            for (int i = 0; i < MAX; i++) {
                                if (list[i] != null && check[i]) { // Show only completed tasks
                                    System.out.println((i + 1) + ". " + list[i]);
                                    Done = true;
                                }
                            }
                            if (!Done) {
                                System.out.println("No completed tasks yet.");
                            }
                        } else {
                            System.out.println("Viewing all tasks.");
                        }
                    }
                    break;

                case 5: // Exit the program
                    System.out.println("Exiting the program...");
                    break;

                default: // If user enters an invalid option
                    System.out.println("Invalid option. Please try again.");
            }
        }

        // Close the scanner to avoid wasting memory
        input.close();
    }
}
