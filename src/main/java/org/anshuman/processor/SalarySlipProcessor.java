package org.anshuman.processor;

import org.anshuman.extractor.SalaryInformationExtractor;
import org.anshuman.factory.ReaderFactory;
import org.anshuman.model.SalaryInformation;
import org.anshuman.reader.FileReader;

public class SalarySlipProcessor {
    private final String filePath;
    public SalaryInformation processSalary(){
        FileReader reader = ReaderFactory.createReader(getFileExtension(filePath));
        String text = reader.readFile(filePath);
        return new SalaryInformationExtractor().extractSalaryDetails(text);
    }

    /**
     * Gets the file extension from a given file path.
     *
     * @param filePath the file path as a String
     * @return the file extension without the dot, or an empty string if none is found
     */
    public String getFileExtension(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            return "";
        }
        int lastDotIndex = filePath.lastIndexOf('.');
        int lastSeparatorIndex = Math.max(filePath.lastIndexOf('/'), filePath.lastIndexOf('\\'));

        // Ensure the dot comes after the last file separator and there is something after the dot
        if (lastDotIndex > lastSeparatorIndex && lastDotIndex < filePath.length() - 1) {
            return filePath.substring(lastDotIndex + 1);
        }

        return "";
    }

    public SalarySlipProcessor(String path){
        this.filePath = path;
    }
}
