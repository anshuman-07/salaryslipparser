package org.anshuman.reader;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PdfReader implements FileReader {
    @Override
    public String readFile(String path) {
        try {
            PDDocument document = PDDocument.load(new File(path));
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            document.close();

            System.out.println("Extracted text from PDF:");
            System.out.println(text);
           return text;
        } catch (IOException e) {
            System.err.println("Error processing PDF file: " + e.getMessage());
        }
        return "";
    }
}
