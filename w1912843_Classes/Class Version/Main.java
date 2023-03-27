import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.Arrays;

public class Main {
    public static ArrayList<String[]> waitingList = new ArrayList<>();
    public static ArrayList<String> waitingList_FirstName = new ArrayList<String>();
    public static ArrayList<String> waitingList_SecondName = new ArrayList<String>();
    public static ArrayList<String> waitingList_VehicleNo = new ArrayList<String>();
    public static ArrayList<Integer> waitingList_NoOfLiters = new ArrayList<Integer>();
    public static int count_1 = 0;
    public static int count_2 = 0;
    public static int count_3 = 0;
    public static int count_4 = 0;
    public static int count_5 = 0;
    public static String[][] queue_1 = new String[6][4];
    public static String[][] queue_2 = new String[6][4];
    public static String[][] queue_3 = new String[6][4];
    public static String[][] queue_4 = new String[6][4];
    public static String[][] queue_5 = new String[6][4];
    public static int[] min = new int[5];

    public static int total = 6600;

    public static int fuel, queue1, queue2, queue3, queue4, queue5;


    public static void main(String[] args) {
        FuelQueue queue_1 = new FuelQueue();
        FuelQueue queue_2 = new FuelQueue();
        FuelQueue queue_3 = new FuelQueue();
        FuelQueue queue_4 = new FuelQueue();
        FuelQueue queue_5 = new FuelQueue();
        FuelQueue[] queueList = {queue_1, queue_2, queue_3, queue_4, queue_5};

        System.out.println("\n----------------------------WELCOME----------------------------------");
        loop:
        while (true) {
            System.out.println("""
                    \n100 or VFQ: View all Fuel Queues.
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
            choice = (choice.toUpperCase());

            switch (choice) {
                case "100":
                case "VFQ":
                    System.out.println("\n------------------------All the queues-----------------------------\n");
                    ViewAllQueues(queueList);
                    break;
                case "101":
                case "VEQ":
                    System.out.println("\n------------------------All the empty queues-----------------------------\n");
                    ViewAllEmptyQueues(queueList);
                    break;
                case "102":
                case "ACQ":
                    System.out.println("\n------------------------Adding customer to a queue-----------------------------\n");
                    AddCustomerToQueue(queueList);
                    break;
                case "103":
                case "RCQ":
                    System.out.println("\n------------Removing a customer from a Queue. (From a specific location)--------------\n");
                    RemoveCustomerFromSpecificLocation(queueList);
                    break;
                case "104":
                case "PCQ":
                    System.out.println("\n------------------------Removing a served customer-----------------------------\n");
                    RemoveAServedCustomer(queueList);
                    break;
                case "105":
                case "VCS":
                    System.out.println("\n------------------------Customers sorted in alphabetical order-----------------------------\n");
                    SortInAlphabeticalOrder(queueList);
                    break;
                case "106":
                case "SPD":
                    System.out.println("\n------------------------Storing Program Data into file-----------------------------\n");
                    try {
                        StoreProgramDataToFile(queueList);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "107":
                case "LPD":
                    System.out.println("\n------------------------Loading Program Data from file-----------------------------\n");
                    LoadProgramDataFromFile(queueList);
                    break;
                case "108":
                case "STK":
                    System.out.println("\n------------------------Remaining Fuel Stock-----------------------------\n");
                    break;
                case "109":
                case "AFS":
                    System.out.println("\n------------------------Adding Fuel Stock-----------------------------\n");
                    break;
                case "110":
                case "IFQ":
                    System.out.println("Queue 1 : RS." + queue1 * 430);
                    System.out.println("Queue 2 : RS." + queue2 * 430);
                    System.out.println("Queue 3 : RS." + queue3 * 430);
                    System.out.println("Queue 4 : RS." + queue4 * 430);
                    System.out.println("Queue 5 : RS." + (queue5 * 430) + "\n");
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

    public static void ViewAllQueues(FuelQueue[] queueList) {
        for (int i = 0; i < 5; i++) {
            System.out.println("------------------------------Queue " + (i + 1) + "------------------------------");
            for (int j = 0; j < 6; j++) {
                System.out.println("******Customer " + (j + 1) + "*******");
                if (queueList[i].Passengers[j].getFirstName() == null) {
                    System.out.println("1) First Name: Empty!");
                } else {
                    System.out.println("1) First Name: " + queueList[i].Passengers[j].getFirstName());
                }
                if (queueList[i].Passengers[j].getSecondName() == null) {
                    System.out.println("2) Second Name: Empty!");
                } else {
                    System.out.println("2) Second Name: " + queueList[i].Passengers[j].getSecondName());
                }
                if (queueList[i].Passengers[j].getVehicleNo() == null) {
                    System.out.println("3) Vehicle No: Empty!");
                } else {
                    System.out.println("3) Vehicle No: " + queueList[i].Passengers[j].getVehicleNo());
                }
                System.out.println("4) No. of Litres required: " + queueList[i].Passengers[j].getNoOfLitres() + "\n");
            }
        }
    }


    public static void ViewAllEmptyQueues(FuelQueue[] queueList) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                if (queueList[i].Passengers[i].getFirstName() == null) {
                    System.out.println("Customer " + (j + 1) + " of queue " + (i + 1) + " is empty!");
                }
            }
            System.out.println("\n");
        }
    }

    public static void AddCustomerToQueue(FuelQueue[] queueList) {
        int count = 0;
        int temp = 0;
        min[0] = count_1;
        min[1] = count_2;
        min[2] = count_3;
        min[3] = count_4;
        min[4] = count_5;
        boolean x = true;
        //then bubble sort is done to  arrange the array from the  minimum count value to the largest
        while (x) {
            if (min[0] > min[1]) {
                temp = min[0];
                min[0] = min[1];
                min[1] = temp;
            }
            if (min[1] > min[2]) {
                temp = min[2];
                min[2] = min[1];
                min[1] = temp;
            }
            if (min[2] > min[3]) {
                temp = min[2];
                min[2] = min[3];
                min[3] = temp;

            }
            if (min[3] > min[4]) {
                temp = min[3];
                min[3] = min[4];
                min[4] = temp;
            }
            count++;
            if (25 == count) {
                x = false;
            }

        }
        Scanner input = new Scanner(System.in);
        System.out.print("1) Enter your first name: ");
        String firstName = input.nextLine();
        System.out.print("2) Enter your second name: ");
        String secondName = input.nextLine();
        System.out.print("3) Enter your vehicle number: ");
        String vehicleNo = null;
        vehicleNo = input.nextLine();
        int noOfLitres = 0;
        try {
            System.out.print("4) Enter the number of litres required: ");
            noOfLitres = input.nextInt();
            {
            }
        } catch (Exception e) {
            System.out.println("Invalid Input!");
        }

        String[] Passengers = {firstName, secondName, vehicleNo, String.valueOf(noOfLitres)};
        if (queueList[4].Passengers[5].getFirstName() != null) {
            waitingList.add(Passengers);
        }
        if (count_1 == min[0]) {

            for (int i = 0; i < 6; i++) {
                if (queueList[0].Passengers[i].getFirstName() == null) {
                    assert queueList[0].Passengers[i] != null;
                    queueList[0].Passengers[i].setFirstName(firstName);

                    queueList[0].Passengers[i].setSecondName(secondName);
                    queueList[0].Passengers[i].setVehicleNo(vehicleNo);
                    queueList[0].Passengers[i].setNoOfLitres(noOfLitres);
                    queue_1[i][0] = (queueList[0].Passengers[i].getFirstName());
                    queue_1[i][1] = (queueList[0].Passengers[i].getSecondName());
                    queue_1[i][2] = (queueList[0].Passengers[i].getVehicleNo());
                    queue_1[i][3] = String.valueOf((queueList[0].Passengers[i].getNoOfLitres()));
                    count_1++;
                    break;
                }
            }
        } else if (count_2 == min[0]) {
            for (int i = 0; i < 6; i++) {
                if (queueList[1].Passengers[i].getFirstName() == null) {
                    assert queueList[1].Passengers[i] != null;
                    queueList[1].Passengers[i].setFirstName(firstName);

                    queueList[1].Passengers[i].setSecondName(secondName);
                    queueList[1].Passengers[i].setVehicleNo(vehicleNo);
                    queueList[1].Passengers[i].setNoOfLitres(noOfLitres);
                    queue_2[i][0] = (queueList[1].Passengers[i].getFirstName());
                    queue_2[i][1] = (queueList[1].Passengers[i].getSecondName());
                    queue_2[i][2] = (queueList[1].Passengers[i].getVehicleNo());
                    queue_2[i][3] = String.valueOf((queueList[1].Passengers[i].getNoOfLitres()));
                    count_2++;
                    break;
                }
            }

        } else if (count_3 == min[0]) {
            for (int i = 0; i < 6; i++) {
                if (queueList[2].Passengers[i].getFirstName() == null) {
                    assert queueList[2].Passengers[i] != null;
                    queueList[2].Passengers[i].setFirstName(firstName);
                    queueList[2].Passengers[i].setSecondName(secondName);
                    queueList[2].Passengers[i].setVehicleNo(vehicleNo);
                    queueList[2].Passengers[i].setNoOfLitres(noOfLitres);
                    queue_3[i][0] = (queueList[2].Passengers[i].getFirstName());
                    queue_3[i][1] = (queueList[2].Passengers[i].getSecondName());
                    queue_3[i][2] = (queueList[2].Passengers[i].getVehicleNo());
                    queue_3[i][3] = String.valueOf((queueList[2].Passengers[i].getNoOfLitres()));
                    count_3++;
                    break;
                }
            }

        } else if (count_4 == min[0]) {
            for (int i = 0; i < 6; i++) {
                if (queueList[3].Passengers[i].getFirstName() == null) {
                    assert queueList[3].Passengers[i] != null;
                    queueList[3].Passengers[i].setFirstName(firstName);
                    queueList[3].Passengers[i].setSecondName(secondName);
                    queueList[3].Passengers[i].setVehicleNo(vehicleNo);
                    queueList[3].Passengers[i].setNoOfLitres(noOfLitres);
                    queue_4[i][0] = (queueList[3].Passengers[i].getFirstName());
                    queue_4[i][1] = (queueList[3].Passengers[i].getSecondName());
                    queue_4[i][2] = (queueList[3].Passengers[i].getVehicleNo());
                    queue_4[i][3] = String.valueOf((queueList[3].Passengers[i].getNoOfLitres()));
                    count_4++;
                    break;
                }
            }

        } else if (count_5 == min[0]) {
            for (int i = 0; i < 6; i++) {
                if (queueList[4].Passengers[i].getFirstName() == null) {
                    assert queueList[4].Passengers[i] != null;
                    queueList[4].Passengers[i].setFirstName(firstName);
                    queueList[4].Passengers[i].setSecondName(secondName);
                    queueList[4].Passengers[i].setVehicleNo(vehicleNo);
                    queueList[4].Passengers[i].setNoOfLitres(noOfLitres);
                    queue_5[i][0] = (queueList[4].Passengers[i].getFirstName());
                    queue_5[i][1] = (queueList[4].Passengers[i].getSecondName());
                    queue_5[i][2] = (queueList[4].Passengers[i].getVehicleNo());
                    queue_5[i][3] = String.valueOf((queueList[4].Passengers[i].getNoOfLitres()));
                    count_5++;
                    break;
                }
            }


        } else {
            System.out.println("Wrong");
        }

    }


    public static void RemoveAServedCustomer(FuelQueue[] queueList) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the queue number: ");
        int queno = input.nextInt();
        if (queno == 1 || queno == 2 || queno == 3 || queno == 4 || queno == 5) {
            fuel = fuel - (queueList[queno - 1].Passengers[0].getNoOfLitres());

            if (queno == 1) {
                queue1 = +(queueList[queno - 1].Passengers[0].getNoOfLitres());
            } else if (queno == 2) {
                queue2 = +(queueList[queno - 1].Passengers[0].getNoOfLitres());
            } else if (queno == 3) {
                queue3 = +(queueList[queno - 1].Passengers[0].getNoOfLitres());
            } else if (queno == 4) {
                queue4 = +(queueList[queno - 1].Passengers[0].getNoOfLitres());
            } else {
                queue5 = +(queueList[queno - 1].Passengers[0].getNoOfLitres());
            }
        }

        queueList[queno - 1].Passengers[0].setFirstName(null);
        queueList[queno - 1].Passengers[0].setSecondName(null);
        queueList[queno - 1].Passengers[0].setVehicleNo(null);
        queueList[queno - 1].Passengers[0].setNoOfLitres(0);
        for (int i = 0; i < 5; i++) {
            queueList[queno - 1].Passengers[i].setFirstName(queueList[queno - 1].Passengers[i + 1].getFirstName());
            queueList[queno - 1].Passengers[i].setSecondName(queueList[queno - 1].Passengers[i + 1].getSecondName());
            queueList[queno - 1].Passengers[i].setVehicleNo(queueList[queno - 1].Passengers[i + 1].getVehicleNo());
            queueList[queno - 1].Passengers[i].setNoOfLitres(queueList[queno - 1].Passengers[i + 1].getNoOfLitres());
        }
        queueList[queno - 1].Passengers[5].setFirstName(null);
        queueList[queno - 1].Passengers[5].setSecondName(null);
        queueList[queno - 1].Passengers[5].setVehicleNo(null);
        queueList[queno - 1].Passengers[5].setNoOfLitres(0);

        for (int i = 0; i < 6; i++) {
            if (queueList[queno - 1].Passengers[i].getFirstName() == null && waitingList_FirstName.size() != 0) {
                queueList[queno - 1].Passengers[i].setFirstName(waitingList_FirstName.get(0));
                queueList[queno - 1].Passengers[i].setSecondName(waitingList_SecondName.get(0));
                queueList[queno - 1].Passengers[i].setVehicleNo(waitingList_VehicleNo.get(0));
                queueList[queno - 1].Passengers[i].setNoOfLitres(waitingList_NoOfLiters.get(0));
                waitingList_FirstName.remove(0);
                waitingList_SecondName.remove(0);
                waitingList_VehicleNo.remove(0);
                waitingList_NoOfLiters.remove(0);
                break;
            } else {
                System.out.println("Invalid Input! Renter...");
            }

        }
    }
        public static void StoreProgramDataToFile (FuelQueue[]queueList) throws IOException {
            BufferedWriter writer = new BufferedWriter(new FileWriter("myFile.txt", false));

            for (int i = 0; i < 5; i++) {
                writer.write("-------------Queue " + (i + 1) + "--------------");
                writer.newLine();
                for (int j = 0; j < 6; j++) {
                    writer.write("******Customer " + (j + 1) + "*******");
                    writer.newLine();

                    if (queueList[i].Passengers[j].getFirstName() == null) {
                        writer.write("1) First Name: Empty!");
                        writer.newLine();
                    } else {
                        writer.write("1) First Name: " + queueList[i].Passengers[j].getFirstName());
                        writer.newLine();
                    }
                    if (queueList[i].Passengers[j].getSecondName() == null) {
                        writer.write("2) Second Name: Empty!");
                        writer.newLine();
                    } else {
                        writer.write("2) Second Name: " + queueList[i].Passengers[j].getSecondName());
                        writer.newLine();
                    }
                    if (queueList[i].Passengers[j].getVehicleNo() == null) {
                        writer.write("3) Vehicle No: Empty!");
                        writer.newLine();
                    } else {
                        writer.write("3) Vehicle No: " + queueList[i].Passengers[j].getVehicleNo());
                        writer.newLine();
                    }
                    writer.write("4) No. of Litres required: " + queueList[i].Passengers[j].getNoOfLitres() + "\n");
                    writer.newLine();
                }
                writer.flush();
            }
            System.out.println("Data Entered in to the file successfully!");
        }


        public static void LoadProgramDataFromFile (FuelQueue[]queueList){

            try {
                BufferedReader reader = new BufferedReader(new FileReader("myFile.txt"));

                int data = reader.read();
                while (data != -1) {
                    System.out.print((char) data);
                    data = reader.read();

                }
                reader.close();
            } catch (Exception e) {
                System.out.println("Can't find this file");
            }
        }
        public static void SortInAlphabeticalOrder (FuelQueue[]queueList){
            System.out.println("Sorted queue 01");
            String[] hold;
            for (int i = 0; i < 6; i++) {
                for (int j = i + 1; j < 6; j++) {
                    if (queue_1[i][0] == null || queue_1[j][0] == null) {
                        continue;
                    } else {
                        if (Arrays.toString(queue_1[i]).compareTo(Arrays.toString(queue_1[j])) > 0) {
                            hold = queue_1[i];
                            queue_1[i] = queue_1[j];
                            queue_1[j] = hold;
                        }
                    }
                }
            }

            for (int i = 0; i < 6; i++) {
                System.out.println("Customer" + (i + 1) + ": " + Arrays.toString(queue_1[i]));
            }

            System.out.println("Sorted queue 02");
            String[] hold2;
            for (int i = 0; i < 6; i++) {
                for (int j = i + 1; j < 6; j++) {
                    if (queue_2[i][0] == null || queue_2[j][0] == null) {
                        continue;
                    } else {
                        if (Arrays.toString(queue_2[i]).compareTo(Arrays.toString(queue_2[j])) > 0) {
                            hold2 = queue_2[i];
                            queue_2[i] = queue_2[j];
                            queue_2[j] = hold2;
                        }
                    }
                }

            }
            //print after sort
            for (int i = 0; i < 6; i++) {
                System.out.println("Customer" + (i + 1) + ":-" + Arrays.toString(queue_2[i]));

            }
            //for pump3
            System.out.println("Sorted queue 03");
            String[] hold3;
            for (int i = 0; i < 6; i++) {
                for (int j = i + 1; j < 6; j++) {
                    if (queue_3[i][0] == null || queue_3[j][0] == null) {
                        continue;
                    } else {
                        if (Arrays.toString(queue_3[i]).compareTo(Arrays.toString(queue_3[j])) > 0) {
                            hold3 = queue_3[i];
                            queue_3[i] = queue_3[j];
                            queue_3[j] = hold3;
                        }
                    }
                }
            }

            for (int i = 0; i < 6; i++) {
                System.out.println("Customer" + (i + 1) + ":-" + Arrays.toString(queue_3[i]));

            }

            System.out.println("Sorted queue 04");
            String[] hold4;
            for (int i = 0; i < 6; i++) {
                for (int j = i + 1; j < 6; j++) {
                    if (queue_4[i][0] == null || queue_4[j][0] == null) {
                        continue;
                    } else {
                        if (Arrays.toString(queue_4[i]).compareTo(Arrays.toString(queue_4[j])) > 0) {
                            hold4 = queue_4[i];
                            queue_4[i] = queue_4[j];
                            queue_4[j] = hold4;
                        }
                    }
                }
            }
            //print after sort
            for (int i = 0; i < 6; i++) {
                System.out.println("Customer " + (i + 1) + ":-" + Arrays.toString(queue_4[i]));

            }
            //for pump5
            System.out.println("Sorted queue 05");
            String[] hold5;
            for (int i = 0; i < 6; i++) {
                for (int j = i + 1; j < 6; j++) {
                    if (queue_5[i][0] == null || queue_5[j][0] == null) {
                        continue;
                    } else {
                        if (Arrays.toString(queue_5[i]).compareTo(Arrays.toString(queue_5[j])) > 0) {
                            hold5 = queue_5[i];
                            queue_5[i] = queue_5[j];
                            queue_5[j] = hold5;
                        }
                    }
                }

            }
            //print after sort
            for (int i = 0; i < 6; i++) {
                System.out.println("Customer" + (i + 1) + ":-" + Arrays.toString(queue_5[i]));
            }
        }
        public static void AddFuelStock () {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter the number of litres of fuel you want to add to stock: ");
            int add = input.nextInt();
        }

        public static void RemoveCustomerFromSpecificLocation (FuelQueue[]queueList){
            int queNo = 0;
            int cusNo = 0;
            Scanner input = new Scanner(System.in);
            System.out.print("Enter the queue number(1,2,3,4,5): ");
            queNo = input.nextInt();
            System.out.print("Enter the customer location you want to remove(1,2,3,4,5,6): ");
            cusNo = input.nextInt() - 1;
            if (queueList[queNo - 1].Passengers[cusNo - 1].getFirstName() == null) {
                System.out.println("Sorry! There is no customer in the specified location");
            } else {
                System.out.println("Customer" + cusNo + "of Queue " + queNo + "has been removed");
                if (queNo - 1 == 0) {
                    count_1 -= 1;
                } else if (queNo - 1 == 1) {
                    count_2 -= 1;
                } else if (queNo - 1 == 2) {
                    count_3 -= 1;
                } else if (queNo - 1 == 3) {
                    count_4 -= 1;
                } else if (queNo - 1 == 4) {
                    count_5 -= 1;
                }
                queueList[queNo - 1].Passengers[cusNo - 1].setFirstName(null);
                queueList[queNo - 1].Passengers[cusNo - 1].setSecondName(null);
                queueList[queNo - 1].Passengers[cusNo - 1].setVehicleNo(null);
                queueList[queNo - 1].Passengers[cusNo - 1].setNoOfLitres(0);
                for (int i = cusNo; i < 6; i++) {
                    queueList[queNo - 1].Passengers[i - 1].setFirstName(queueList[queNo - 1].Passengers[i].getFirstName());
                    queueList[queNo - 1].Passengers[i - 1].setSecondName(queueList[queNo - 1].Passengers[i].getSecondName());
                    queueList[queNo - 1].Passengers[i - 1].setVehicleNo(queueList[queNo - 1].Passengers[i].getVehicleNo());
                    queueList[queNo - 1].Passengers[i - 1].setNoOfLitres(queueList[queNo - 1].Passengers[i].getNoOfLitres());
                }
                queueList[queNo - 1].Passengers[5].setFirstName(null);
                queueList[queNo - 1].Passengers[5].setSecondName(null);
                queueList[queNo - 1].Passengers[5].setVehicleNo(null);
                queueList[queNo - 1].Passengers[5].setNoOfLitres(0);
            }
        }
    }



