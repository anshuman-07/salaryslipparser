package org.anshuman.factory;

import org.anshuman.reader.FileReader;
import org.anshuman.reader.ImageReader;
import org.anshuman.reader.PdfReader;


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
