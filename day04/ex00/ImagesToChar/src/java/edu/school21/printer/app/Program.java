package edu.school21.printer.app;

import edu.school21.printer.logic.*;

import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Program {
    public static void main(String[] args) {
        try{
            File path = new File(args[0]);
            BufferedImage image = ImageIO.read(path);
            char backGround = args[1].charAt(0);
            char fill = args[2].charAt(0);
            ConvertImage newImage = new ConvertImage(image);
            newImage.printImage(backGround, fill);
        } catch (IOException | ArrayIndexOutOfBoundsException e){
            System.out.println("File error");
        }
    }
}
