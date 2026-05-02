package com.klef.fsad.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class ExamApplication implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;

    public static void main(String[] args) {
        // Disabling the web server so it runs exclusively as a console application
        SpringApplication app = new SpringApplication(ExamApplication.class);
        app.setWebApplicationType(org.springframework.boot.WebApplicationType.NONE);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=================================================");
        System.out.println("===== Spring Data JPA Operations Demo ===========");
        System.out.println("=================================================");
        System.out.println("1. Insert a new Customer Account");
        System.out.println("2. Update Customer Account Name and Status by ID");
        System.out.println("3. Exit");
        
        while (true) {
            System.out.print("\nEnter your choice (1-3): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    insertRecord(scanner);
                    break;
                case 2:
                    updateRecord(scanner);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void insertRecord(Scanner scanner) {
        try {
            System.out.print("Enter Customer Name: ");
            String name = scanner.nextLine();
            
            System.out.print("Enter Description: ");
            String description = scanner.nextLine();
            
            System.out.print("Enter Status: ");
            String status = scanner.nextLine();
            
            CustomerAccount account = new CustomerAccount(name, description, new Date(), status);
            
            // Perform insert operation using Repository
            CustomerAccount savedAccount = customerRepository.save(account);
            
            System.out.println("Record inserted successfully! Generated ID: " + savedAccount.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateRecord(Scanner scanner) {
        try {
            System.out.print("Enter Customer Account ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            // Retrieve the object using Repository
            Optional<CustomerAccount> optionalAccount = customerRepository.findById(id);
            
            if (optionalAccount.isPresent()) {
                CustomerAccount account = optionalAccount.get();
                System.out.println("Current details: " + account);
                
                System.out.print("Enter new Name (leave empty to keep current): ");
                String newName = scanner.nextLine();
                if (!newName.trim().isEmpty()) {
                    account.setName(newName);
                }
                
                System.out.print("Enter new Status (leave empty to keep current): ");
                String newStatus = scanner.nextLine();
                if (!newStatus.trim().isEmpty()) {
                    account.setStatus(newStatus);
                }
                
                // Perform update operation using Repository
                customerRepository.save(account);
                
                System.out.println("Record updated successfully!");
                System.out.println("Updated details: " + account);
            } else {
                System.out.println("Customer Account with ID " + id + " not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
