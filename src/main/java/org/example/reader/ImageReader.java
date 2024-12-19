package org.example.reader;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageReader implements FileReader{
    @Override
    public String readFile(String path) {
        try {
            // Load the image file
            File imageFile = new File(path);
            BufferedImage image = ImageIO.read(imageFile);  // Correct method to read image files

            // Create Tesseract instance and extract text
            ITesseract instance = new Tesseract();
            String text = instance.doOCR(image);

            // Print extracted text
            System.out.println("Extracted text from image:");
            System.out.println(text);
            return text;
        } catch (Exception e) {
            System.err.println("Error processing image file: " + e.getMessage());
        }
        return "";
    }
}
