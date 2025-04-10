import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHandlingUtility {
    private Scanner scanner;

    public FileHandlingUtility() {
        scanner = new Scanner(System.in);
    }

    public void showMainMenu() {
        while (true) {
            System.out.println("\n=== File Management System ===");
            System.out.println("1. Create File");
            System.out.println("2. Read File");
            System.out.println("3. Update/Write to File");
            System.out.println("4. Delete File");
            System.out.println("5. Exit");
            
            System.out.print("Enter your choice (1-5): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createFile();
                    break;
                case "2":
                    readFile();
                    break;
                case "3":
                    updateFile();
                    break;
                case "4":
                    deleteFile();
                    break;
                case "5":
                    System.out.println("Thank you for using File Management System!");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private void createFile() {
        System.out.println("\n=== File Creation Options ===");
        System.out.println("1. Create file in current directory");
        System.out.println("2. Create file in custom location");
        System.out.println("3. Back to main menu");
        
        System.out.print("Enter your choice (1-3): ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                createFileInCurrentDirectory();
                break;
            case "2":
                createFileInCustomLocation();
                break;
            case "3":
                return;
            default:
                System.out.println("Invalid choice! Please try again.");
        }
    }

    private void createFileInCurrentDirectory() {
        System.out.println("Enter the file name: ");
        String fileName = scanner.nextLine();
        
        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println("File created successfully: " + file.getAbsolutePath());
            } else {
                System.out.println("File already exists!");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            e.printStackTrace();
        }
    }

    private void createFileInCustomLocation() {
        System.out.print("Enter the complete path where you want to create the file: ");
        String path = scanner.nextLine();
        System.out.println("Enter the file name: ");
        String fileName = scanner.nextLine();
        
        try {
            File directory = new File(path);
            if (!directory.exists()) {
                if (directory.mkdirs()) {
                    System.out.println("Directory created successfully!");
                } else {
                    System.out.println("Failed to create directory!");
                    return;
                }
            }
            
            File file = new File(directory, fileName);
            if (file.createNewFile()) {
                System.out.println("File created successfully: " + file.getAbsolutePath());
            } else {
                System.out.println("File already exists!");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            e.printStackTrace();
        }
    }

    private void readFile() {
        System.out.println("\n=== Read File ===");
        System.out.print("Enter the complete path of the file to read: ");
        String filePath = scanner.nextLine();
        
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("File does not exist!");
                return;
            }
            
            Scanner fileScanner = new Scanner(file);
            System.out.println("\nFile contents:");
            System.out.println("----------------");
            while (fileScanner.hasNextLine()) {
                System.out.println(fileScanner.nextLine());
            }
            System.out.println("----------------");
            fileScanner.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }

    private void updateFile() {
        System.out.println("\n=== Update/Write to File ===");
        System.out.print("Enter the complete path of the file to update: ");
        String filePath = scanner.nextLine();
        
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("File does not exist!");
                return;
            }
            
            System.out.println("Enter the text to write to the file (type 'END' on a new line to finish):");
            FileWriter writer = new FileWriter(file, true); // true for append mode
            
            String line;
            while (!(line = scanner.nextLine()).equals("END")) {
                writer.write(line + "\n");
            }
            
            writer.close();
            System.out.println("File updated successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while updating the file.");
            e.printStackTrace();
        }
    }

    private void deleteFile() {
        System.out.println("\n=== Delete File ===");
        System.out.print("Enter the complete path of the file to delete: ");
        String filePath = scanner.nextLine();
        
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File does not exist!");
            return;
        }
        
        if (file.delete()) {
            System.out.println("File deleted successfully!");
        } else {
            System.out.println("Failed to delete the file!");
        }
    }

    public static void main(String[] args) {
        FileHandlingUtility utility = new FileHandlingUtility();
        System.out.println("Welcome to File Management System!");
        utility.showMainMenu();
    }
} 
