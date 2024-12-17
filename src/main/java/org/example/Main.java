package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
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

        if (filePath.endsWith(".pdf")) {
            // Process PDF file
            extractFromPDF(filePath);
        } else if (filePath.endsWith(".png") || filePath.endsWith(".jpeg") || filePath.endsWith(".jpg")) {
            // Process image file (PNG/JPEG)
            extractFromImage(filePath);
        } else {
            System.out.println("Unsupported file format. Please provide a PDF, PNG, or JPEG file.");
        }

        scanner.close();  // Close the scanner
    }

    // Method to extract text from image using Tesseract OCR
    private static void extractFromImage(String filePath) {
        try {
            // Load the image file
            File imageFile = new File(filePath);
            BufferedImage image = ImageIO.read(imageFile);  // Correct method to read image files

            // Create Tesseract instance and extract text
            ITesseract instance = new Tesseract();
            String text = instance.doOCR(image);

            // Print extracted text
            System.out.println("Extracted text from image:");
            System.out.println(text);

            // Extract required information using regular expressions
            extractSalaryDetails(text);

        } catch (Exception e) {
            System.err.println("Error processing image file: " + e.getMessage());
        }
    }

    private static void extractFromPDF(String filePath) {
        try {
            PDDocument document = PDDocument.load(new File(filePath));
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            document.close();

            System.out.println("Extracted text from PDF:");
            System.out.println(text);

            // Extract required information using regular expressions
            extractSalaryDetails(text);
        } catch (IOException e) {
            System.err.println("Error processing PDF file: " + e.getMessage());
        }
    }

    private static void extractSalaryDetails(String text) {
        String name = extractName(text);
        String grossSalary = extractDetail(text, "Gross", "(?<=Salary[:\\s])([\\d,]+(?:\\.\\d{1,2})?)");
        String taxDeduction = extractDetail(text, "Deduction", "(?<=Tax Deduction[:\\s])([\\d,]+(?:\\.\\d{1,2})?)");
        String netPay = extractDetail(text, "Net", "(?<=Net Pay[:\\s])([\\d,]+(?:\\.\\d{1,2})?)");

        System.out.println("Extracted Salary Details:");
        System.out.println("Name: " + name);
        System.out.println("Gross Salary: " + grossSalary);
        System.out.println("Tax Deduction: " + taxDeduction);
        System.out.println("Net Pay: " + netPay);
    }

    // Utility method to extract Name dynamically (stops at first non-name keyword)
    private static String extractName(String text) {
        // Regex explanation:
        // (?<=Name[:\\s]) - Matches 'Name' followed by ':' or space
        // (.*?)(?=\s+[A-Z]) - Capture everything lazily until we find a space followed by an uppercase letter (indicating a new field)

        String nameRegex = "(?<=Name[:\\s])(.*?)(?=\\s+[A-Z])";

        // Apply regex
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(nameRegex);
        java.util.regex.Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group(1).trim();
        } else {
            return "Not Found";
        }
    }

    // Utility method to extract a specific field using regex pattern
    private static String extractDetail(String text, String label, String pattern) {
        java.util.regex.Pattern r = java.util.regex.Pattern.compile(pattern);
        java.util.regex.Matcher m = r.matcher(text);
        if (m.find()) {
            return m.group(1).trim();
        }
        return "Not Found";
    }
}
