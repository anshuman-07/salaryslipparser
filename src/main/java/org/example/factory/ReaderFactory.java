package org.example.factory;

import org.example.reader.FileReader;
import org.example.reader.ImageReader;
import org.example.reader.PdfReader;


public class ReaderFactory {
    public static final String PDF = "pdf";
    public static final String PNG = "png";
    public static final String JPEG = "jpeg";
    public static FileReader createReader(String extension){
        if(extension.equals(PDF))
            return new PdfReader();
        else if(extension.equals(PNG) || extension.equals(JPEG))
            return new ImageReader();
        throw new RuntimeException("file extension not recognised");
    }
}
