package org.anshuman.extractor;

import org.anshuman.model.SalaryInformation;

public class SalaryInformationExtractor {

    public   SalaryInformation extractSalaryDetails(String text) {
        String name = extractName(text);
        String grossSalary = extractDetail(text, "Gross", "(?<=Salary[:\\s])([\\d,]+(?:\\.\\d{1,2})?)");
        String taxDeduction = extractDetail(text, "Deduction", "(?<=Tax Deduction[:\\s])([\\d,]+(?:\\.\\d{1,2})?)");
        String netPay = extractDetail(text, "Net", "(?<=Net Pay[:\\s])([\\d,]+(?:\\.\\d{1,2})?)");
        System.out.println("Name: " + name);
        System.out.println("Gross Salary: " + grossSalary);
        System.out.println("Tax Deduction: " + taxDeduction);
        System.out.println("Net Pay: " + netPay);
        SalaryInformation info = new SalaryInformation();
        info.name = name;
        info.grossSalary = Double.parseDouble(grossSalary);
        info.taxDeducted = Double.parseDouble(taxDeduction);
        info.netSalary = Double.parseDouble(netPay);
        return info;
    }

    // Utility method to extract Name dynamically (stops at first non-name keyword)
    private  String extractName(String text) {
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
    private  String extractDetail(String text, String label, String pattern) {
        java.util.regex.Pattern r = java.util.regex.Pattern.compile(pattern);
        java.util.regex.Matcher m = r.matcher(text);
        if (m.find()) {
            return m.group(1).trim();
        }
        return "Not Found";
    }
}
