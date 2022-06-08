package edu.school21.printer.logic;

import java.awt.image.BufferedImage;

public class ConvertImage {
    private final BufferedImage image;

    public ConvertImage(BufferedImage image) {
        this.image = image;
    }

    public void printImage(char background, char fill){
        int w = image.getWidth();
        int h = image.getHeight();
        int [][] imageInArray = new int[w][h];

        int i = 0;
        while (i < w){
            int l = 0;
            while (l < h){
                imageInArray[i][l] = image.getRGB(l, i);
                if (imageInArray[i][l] != -1)
                    imageInArray[i][l] = fill;
                if (imageInArray[i][l] == -1)
                    imageInArray[i][l] = background;
                System.out.print((char)imageInArray[i][l]);
                l++;
            }
            System.out.println();
            i++;
        }
    }
}
