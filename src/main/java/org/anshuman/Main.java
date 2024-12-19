package org.anshuman;

import org.anshuman.model.SalaryInformation;
import org.anshuman.processor.SalarySlipProcessor;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter the file path
        System.out.println("Please enter the file path of the salary slip (PDF, PNG, or JPEG): ");
        String filePath = scanner.nextLine();  // Read the input from the user
        SalaryInformation info = new SalarySlipProcessor(filePath).processSalary();
        System.out.println("Extracted Salary Details:");
        System.out.println("Name: " + info.name);
        System.out.println("Gross Salary: " + info.grossSalary);
        System.out.println("Tax Deduction: " + info.taxDeducted);
        System.out.println("Net Pay: " + info.netSalary);
        scanner.close();  // Close the scanner
    }
}
