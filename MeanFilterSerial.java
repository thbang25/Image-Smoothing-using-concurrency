//2D Median Filter for Image Smoothing
//Thabang Sambo
//CSC2002S
//04 August 2022
//getting our packages
import java.util.*;
import java.io.*;
import java.awt.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

public class MeanFilterSerial {
    public static void main(String[] args) {//main
    
    File fileInput = null; BufferedImage picture = null; // declaring viriables
        
    long speedCount = System.currentTimeMillis(); //method to do the timing measurements
    
        try{ fileInput = new File("../data/download.jpg"); picture = ImageIO.read(fileInput);} // taking in the file we downloaded and coverting it to an image
	     catch(IOException e){System.out.println(e);} //error catch
        
        int w  = picture.getWidth();//we will be scanning the picture and getting the width
        int h = picture.getHeight();//we will be scanning the picture and getting the height

        for (int k = 1; k < h - 1; k++) { //created a nested for loop to get each pixel
            for (int t = 1; t < w - 1; t++) { //created a nested for loop to get each pixel
            Color[] color; //declaring our color array
                 color = new Color[9];
                 
                //insted of looping the array through iteration its better to declare each space in memory to a designated pixel so we can get each specified point
                color[0]  = new Color(picture.getRGB(t-1, k-1)); color[1]  = new Color(picture.getRGB(t-1, k));
                color[2]  = new Color(picture.getRGB(t-1, k+1)); color[3]  = new Color(picture.getRGB(t, k-1));
                color[4]  = new Color(picture.getRGB(t, k)); color[5]  = new Color(picture.getRGB(t, k+1));
                color[6]  = new Color(picture.getRGB(t+1, k-1)); color[7]  = new Color(picture.getRGB(t+1, k));
                color[8]  = new Color(picture.getRGB(t+1, k+1));
                
                //we start by trying to gather and smooth out the color of the picture
                int red = color[0].getRed() + color[1].getRed() + color[2].getRed() +
                         color[3].getRed() +  color[4].getRed() +  color[5].getRed() +
                         color[6].getRed() +  color[7].getRed() +  color[8].getRed();
                int green = color[0].getGreen() +  color[1].getGreen() +  color[2].getGreen() +
                         color[3].getGreen() +  color[4].getGreen() +  color[5].getGreen() +
                         color[6].getGreen() + color[7].getGreen() +  color[8].getGreen();
                int blue =  color[0].getBlue() +  color[1].getBlue() +  color[2].getBlue() +
                         color[3].getBlue() +  color[4].getBlue() +  color[5].getBlue() +
                         color[6].getBlue() +  color[7].getBlue() +  color[8].getBlue();
                 
                //trying to convert the colors into an integer and combining the colors         
                int pixel = ((red/9) << 16) + ((green/9) << 8) + blue/9;
                picture.setRGB(t, k, (int)(pixel));}
                
        if (k % 20 == 0)
        
        // Writing our new smoothed out image in memory to see the result
        try{ImageIO.write(picture, "jpg", 
        new File("../data/meanFilterSerial.jpg"));}
	     catch(IOException e){System.out.println(e);}//error catch
}
         // Writing our new smoothed out image in memory to see the result
        try{ImageIO.write(picture, "jpg", 
        new File("../data/meanFilterSerial.jpg"));}
	     catch(IOException e){System.out.println(e);}
        long stopCount = System.currentTimeMillis();
        System.out.println(Math.abs(speedCount-stopCount)+" seconds(m)");//total amount of time that elasped
        }
} 