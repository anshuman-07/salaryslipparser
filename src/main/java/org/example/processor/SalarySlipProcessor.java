package org.example.processor;

import org.example.extractor.SalaryInformationExtractor;
import org.example.factory.ReaderFactory;
import org.example.model.SalaryInformation;
import org.example.reader.FileReader;

public class SalarySlipProcessor {
    private String filePath;
    public SalaryInformation processSalary(){
        FileReader reader = ReaderFactory.createReader("pdf");
        String text = reader.readFile(filePath);
        return new SalaryInformationExtractor().extractSalaryDetails(text);
    }

    public SalarySlipProcessor(String path){
        this.filePath = path;
    }
}
