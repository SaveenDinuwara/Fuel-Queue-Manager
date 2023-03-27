import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayVersion {


    static int  remainingFuel = 0;
    public static void main(String[] args)  {


        String[] queue1 = new String[6];
        String[] queue2 = new String[6];
        String[] queue3 = new String[6];
        String[] sortedQueue = new String[6];
        int totalFuel = 6600;
        loop:
        while (true) {
            System.out.println("""

                    100 or VFQ: View all Fuel Queues.
                    101 or VEQ: View all Empty Queues.
                    102 or ACQ: Add customer to a Queue.
                    103 or RCQ: Remove a customer from a Queue. (From a specific location)
                    104 or PCQ: Remove a served customer.
                    105 or VCS: View Customers Sorted in alphabetical order. (Do not use library sort routine)
                    106 or SPD: Store Program Data into file.
                    107 or LPD: Load Program Data from file.
                    108 or STK: View Remaining Fuel Stock.
                    109 or AFS: Add Fuel Stock.
                    999 or EXT: Exit the Program.
                    """);

            Scanner input = new Scanner(System.in);
            System.out.print("\nChoose your preferred option\n>>>");
            String choice = input.nextLine();

            switch (choice) {
                case "100":
                case "VFQ":
                    System.out.println("\n------------------------All the queues-----------------------------\n");
                    ViewAllQueues(queue1, queue2, queue3);
                    break;
                case "101":
                case "VEQ":
                    System.out.println("\n------------------------All the empty queues-----------------------------\n");
                    ViewAllEmptyQueues(queue1, queue2, queue3);
                    break;
                case "102":
                case "ACQ":
                    System.out.println("\n------------------------Adding customer to a queue-----------------------------\n");
                    System.out.print("Enter the customer name: ");
                    String customerName = input.next();
                    System.out.print("Enter the queue number(1/2/3): ");
                    int queueNo = input.nextInt();

                    AddCustomerToQueue(queue1, queue2, queue3, queueNo, customerName, totalFuel );
                    ViewAllQueues(queue1, queue2, queue3);
                    break;
                case "103":
                case "RCQ":
                    System.out.println("\n------------Removing a customer from a Queue. (From a specific location)--------------\n");
                    System.out.print("Enter the queue number(1/2/3): ");
                    queueNo = input.nextInt();
                    System.out.print("Enter the location you want to remove the customer(1/2/3/4/5/6):");
                    int removeLoc = input.nextInt();

                    RemoveACustomer(queue1, queue2, queue3, queueNo, removeLoc, totalFuel );
                    break;
                case "104":
                case "PCQ":
                    System.out.println("\n------------------------Removing a served customer-----------------------------\n");
                    System.out.print("Enter the queue number(1/2/3): ");
                    queueNo = input.nextInt();
                    System.out.print("Enter the location you want to remove the customer(1/2/3/4/5/6):");
                    removeLoc = input.nextInt();

                    RemoveAServedCustomer(queue1, queue2, queue3, queueNo, removeLoc);
                    break;
                case "105":
                case "VCS":
                    System.out.println("\n------------------------Customers sorted in alphabetical order-----------------------------\n");
                    SortedInAlphabeticalOrder(queue1, queue2, queue3, sortedQueue);
                    break;
                case "106":
                case "SPD":
                    System.out.println("\n------------------------Storing Program Data into file-----------------------------\n");
                    try {
                        StoreProgramDataToFile(queue1, queue2, queue3);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "107":
                case "LPD":
                    System.out.println("\n------------------------Loading Program Data from file-----------------------------\n");
                    LoadProgramDataFromFile(  queue1,   queue2,   queue3);
                    break;
                case "108":
                case "STK":
                    System.out.println("\n------------------------Remaining Fuel Stock-----------------------------\n");
                    System.out.println("Remaining fuel = " + remainingFuel);
                    break;
                case "109":
                case "AFS":
                    System.out.println("\n------------------------Adding Fuel Stock-----------------------------\n");
                    System.out.println("Enter the amount you want to add in Litres: ");
                    int add = input.nextInt();
                    remainingFuel += add;
                    break;
                case "999":
                case "EXT":
                    System.out.println("Thanks!");
                    break loop;
                default:
                    System.out.println("Invalid Input! Please retry...");
            }
        }
    }

    public static void ViewAllQueues(String[] queue1, String[] queue2, String[] queue3) {
        System.out.println("\nQueue 1 --> " + Arrays.toString(queue1));
        System.out.println("Queue 2 --> " + Arrays.toString(queue2));
        System.out.println("Queue 3 --> " + Arrays.toString(queue3));

    }

    public static void ViewAllEmptyQueues(String[] queue1, String[] queue2, String[] queue3) {
        System.out.println("------------------------Queue 1-----------------------------\n");
        for (int i = 0; i < queue1.length; i++) {
            if (queue1[i] == null) {
                System.out.println(i + 1 + ". Empty");
            } else {
                System.out.println(i + 1 + ". " + queue1[i]);
            }
        }

        System.out.println("------------------------Queue 2-----------------------------\n");
        for (int i = 0; i < queue2.length; i++) {
            if (queue2[i] == null) {
                System.out.println(i + 1 + ". Empty");
            } else {
                System.out.println(i + 1 + ". " + queue2[i]);
            }
        }
        System.out.println("------------------------Queue 3-----------------------------\n");
        for (int i = 0; i < queue3.length; i++) {
            if (queue3[i] == null) {
                System.out.println(i + 1 + ". Empty");
            } else {
                System.out.println(i + 1 + ". " + queue3[i]);
            }
        }

    }

    public static void AddCustomerToQueue(String[] queue1, String[] queue2, String[] queue3, int queueNo, String customerName, int totalFuel ) {

        //while (true) {
        if (queueNo == 1) {
            for (int i = 0; i < queue1.length; i++) {
                if (queue1[i] == null) {
                    queue1[i] = customerName;
                    System.out.println("\n" + customerName + " was successfully added to the queue 1!");
                    remainingFuel = totalFuel - 10;
                    break;
                } else {
                    if (i < 6) {
                        continue;
                    } else {
                        System.out.println("This queue is filled. Try another queue!");
                    }
                }
            }
        } else if (queueNo == 2) {
            for (int i = 0; i <= queue2.length; i++) {
                if (queue2[i] == null) {
                    queue2[i] = customerName;
                    System.out.println("\n" + customerName + " was successfully added to the queue 2!");
                    remainingFuel = totalFuel - 10;
                    break;
                } else if (i < 6) {
                    continue;
                } else {
                    System.out.println("This queue is filled. Try another queue!");
                }
            }
        } else if (queueNo == 3) {
            for (int i = 0; i < queue3.length; i++) {
                if (queue3[i] == null) {
                    queue3[i] = customerName;
                    System.out.println("\n" + customerName + " was successfully added to the queue 3!");
                    remainingFuel = totalFuel - 10;
                    break;
                } else {
                    if (i < 6) {
                        continue;
                    } else {
                        System.out.println("This queue is filled. Try another queue!");
                    }
                }
            }
        } else {
            System.out.println("Invalid Input! Renter...");
        }
    }

    public static void RemoveACustomer(String[] queue1, String[] queue2, String[] queue3, int queueNo, int removeLoc, int totalFuel ) {
        switch (queueNo) {
            case 1:
                for (int i = 0; i < queue1.length; i++) {
                    if (i == removeLoc - 1) {
                        queue1[i] = null;
                        System.out.println("Customer successfully removed!");
                        remainingFuel = totalFuel + 10;
                        break;
                    } else {
                        System.out.println("Location you entered is invalid!");
                    }
                }
                break;

            case 2:
                for (int i = 0; i < queue2.length; i++) {
                    if (i == removeLoc - 1) {
                        queue2[i] = null;
                        System.out.println("Customer successfully removed!");
                        remainingFuel = totalFuel + 10;
                        break;
                    } else {
                        System.out.println("Location you entered is invalid!");
                    }
                }
                break;

            case 3:
                for (int i = 0; i < queue3.length; i++) {
                    if (i == removeLoc - 1) {
                        queue3[i] = null;
                        System.out.println("Customer successfully removed!");
                        remainingFuel = totalFuel + 10;
                        break;
                    } else {
                        System.out.println("Location you entered is invalid!");
                    }
                }
                break;

            default:
                System.out.println("Invalid Input! Reenter...");
        }

    }

    public static void RemoveAServedCustomer(String[] queue1, String[] queue2, String[] queue3, int queueNo, int removeLoc) {
        switch (queueNo) {
            case 1:
                for (int i = 0; i < queue1.length; i++) {
                    if (i == removeLoc - 1) {
                        queue1[i] = null;
                        System.out.println("Customer successfully removed!");
                        break;
                    } else {
                        System.out.println("Location you entered is invalid!");
                    }
                }
                break;

            case 2:
                for (int i = 0; i < queue2.length; i++) {
                    if (i == removeLoc - 1) {
                        queue2[i] = null;
                        System.out.println("Customer successfully removed!");
                        break;
                    } else {
                        System.out.println("Location you entered is invalid!");
                    }
                }
                break;

            case 3:
                for (int i = 0; i < queue3.length; i++) {
                    if (i == removeLoc - 1) {
                        queue3[i] = null;
                        System.out.println("Customer successfully removed!");
                        break;
                    } else {
                        System.out.println("Location you entered is invalid!");
                    }
                }
                break;

            default:
                System.out.println("Invalid Input! Reenter...");
        }

    }

    public static void SortedInAlphabeticalOrder(String[] queue1, String[] queue2, String[] queue3, String[] sortedQueue) {
        String temp;
        sortedQueue = queue1;
        for (int i = 0; i < sortedQueue.length; i++) {   //Holds each element

            for (int j = i + 1; j < sortedQueue.length; j++) {  //Check for remaining elements
                if (sortedQueue[i] == null || sortedQueue[j] == null) {
                    continue;
                } else {
                    //compares each element of the array to all the remaining elements
                    if (sortedQueue[i].compareTo(sortedQueue[j]) > 0) {
                        //swapping array elements
                        temp = sortedQueue[i];
                        sortedQueue[i] = sortedQueue[j];
                        sortedQueue[j] = temp;
                    }
                }
            }

            //prints the sorted array in alphabetical order
        }
        System.out.println("Queue 1 --> " + Arrays.toString(sortedQueue));

        sortedQueue = queue2;
        for (int i = 0; i < sortedQueue.length; i++) {   //Holds each element

            for (int j = i + 1; j < sortedQueue.length; j++) {  //Check for remaining elements
                if (sortedQueue[i] == null || sortedQueue[j] == null) {
                    continue;
                } else {
                    //compares each element of the array to all the remaining elements
                    if (sortedQueue[i].compareTo(sortedQueue[j]) > 0) {
                        //swapping array elements
                        temp = sortedQueue[i];
                        sortedQueue[i] = sortedQueue[j];
                        sortedQueue[j] = temp;
                    }
                }
            }

            //prints the sorted array in alphabetical order
        }
        System.out.println("Queue 2 --> " + Arrays.toString(sortedQueue));

        sortedQueue = queue3;
        for (int i = 0; i < sortedQueue.length; i++) {   //Holds each element

            for (int j = i + 1; j < sortedQueue.length; j++) {  //Check for remaining elements
                if (sortedQueue[i] == null || sortedQueue[j] == null) {
                    continue;
                } else {
                    //compares each element of the array to all the remaining elements
                    if (sortedQueue[i].compareTo(sortedQueue[j]) > 0) {
                        //swapping array elements
                        temp = sortedQueue[i];
                        sortedQueue[i] = sortedQueue[j];
                        sortedQueue[j] = temp;
                    }
                }
            }

            //prints the sorted array in alphabetical order
        }
        System.out.println("Queue 3 --> " + Arrays.toString(sortedQueue));

    }

    public static void StoreProgramDataToFile(String[] queue1, String[] queue2, String[] queue3) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter("myFile.txt", false));
        writer.write("\nQueue 1 --> " + Arrays.toString(queue1));
        writer.newLine();
        writer.write("\nQueue 2 --> " + Arrays.toString(queue2));
        writer.newLine();
        writer.write("\nQueue 3 --> " + Arrays.toString(queue3));
        writer.newLine();
        writer.flush();
        System.out.println("Data Entered in to the file successfully!");
    }

    public static void LoadProgramDataFromFile(String[] queue1, String[] queue2, String[] queue3){


        try{
            BufferedReader reader = new BufferedReader(new FileReader("myFile.txt"));

            int data = reader.read();
            while(data != -1){
                System.out.print((char)data);
                data=reader.read();

            }
            reader.close();
        }catch(Exception e){
            System.out.println("Can't find this file");
        }


    }
}
