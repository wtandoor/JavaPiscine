package edu.school21.printer.app;

import edu.school21.printer.logic.ConvertImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class Main {

    private InputStream getFileResources(String fileName){
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null)
            throw new IllegalArgumentException("file not found " + fileName);
        else
            return inputStream;
    }

    public static void main(String[] args) {
        InputStream is = new Main().getFileResources( "it.bmp");
        try{
            BufferedImage image = ImageIO.read(is);
            char backGround = args[0].charAt(0);
            char fill = args[1].charAt(0);
            ConvertImage newImage = new ConvertImage(image);
            newImage.printImage(backGround, fill);
        } catch (IOException | ArrayIndexOutOfBoundsException e){
            System.out.println("File error");
        }
    }
}
