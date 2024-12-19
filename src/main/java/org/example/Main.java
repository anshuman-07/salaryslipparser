package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.example.model.SalaryInformation;
import org.example.processor.SalarySlipProcessor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
