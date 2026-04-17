import java.io.*;
import java.util.*;

public class ContactManager {

    static final String FILE_NAME = "contacts.txt";
    static Scanner sc = new Scanner(System.in);

    
    public static void addContact() throws IOException {
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();

        FileWriter fw = new FileWriter(FILE_NAME, true);
        fw.write(name + "," + phone + "\n");
        fw.close();

        System.out.println("Contact added successfully");
    }

 
    public static void viewContacts() throws IOException {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No contacts found.");
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
        String line;

        System.out.println("\nContact List:");
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            System.out.println("Name: " + data[0] + " | Phone: " + data[1]);
        }
        br.close();
    }

    public static void searchContact() throws IOException {
        System.out.print("Enter name to search: ");
        String name = sc.nextLine();

        BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
        String line;
        boolean found = false;

        while ((line = br.readLine()) != null) {
            if (line.toLowerCase().startsWith(name.toLowerCase() + ",")) {
                System.out.println("Found: " + line);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Contact not found.");
        }

        br.close();
    }

    public static void deleteContact() throws IOException {
        System.out.print("Enter name to delete: ");
        String name = sc.nextLine();

        File inputFile = new File(FILE_NAME);
        File tempFile = new File("temp.txt");

        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

        String line;
        boolean found = false;

        while ((line = br.readLine()) != null) {
            if (line.toLowerCase().startsWith(name.toLowerCase() + ",")) {
                found = true;
                continue; 
            }
            bw.write(line + "\n");
        }

        br.close();
        bw.close();

        inputFile.delete();
        tempFile.renameTo(inputFile);

        if (found) {
            System.out.println("Contact deleted successfully!");
        } else {
            System.out.println("Contact not found.");
        }
    }

    public static void main(String[] args) throws IOException {

        while (true) {
            System.out.println("\n===== Contact Manager =====");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Search Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");

            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addContact();
                    break;
                case 2:
                    viewContacts();
                    break;
                case 3:
                    searchContact();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}