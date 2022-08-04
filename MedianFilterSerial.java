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

public class MedianFilterSerial {
    public static void main(String args[]){//main
    
    File fileInput = null; BufferedImage picture = null;// declaring viriables
    long speedCount = System.currentTimeMillis(); //method to do the timing measurements
        
        try{ fileInput = new File("../data/download.jpg"); picture = ImageIO.read(fileInput);}// taking in the file we downloaded and coverting it to an image
	     catch(IOException e){System.out.println(e);}//error catch
        
        int h=picture.getHeight(); //we will be scanning the picture and getting the width
        int w=picture.getWidth(); //we will be scanning the picture and getting the height
        
        for(int k=1 ; k<h-1 ;k++ )//created a nested for loop to get each pixel
            for(int t=1;t<w-1;t++){//created a nested for loop to get each pixel
            int listPixel[]=new int[9];//declaring our pixel array
            //insted of looping the array through iteration its better to declare each space in memory to a designated pixel so we can get each specified point
                listPixel[0]=picture.getRGB(t, k); listPixel[1]=picture.getRGB(t-1, k-1);
                listPixel[2]=picture.getRGB(t-1, k); listPixel[3]=picture.getRGB(t-1, k+1);
                listPixel[4]=picture.getRGB(t, k-1); listPixel[5]=picture.getRGB(t, k+1);
                listPixel[6]=picture.getRGB(t+1, k-1); listPixel[7]=picture.getRGB(t+1, k);
                listPixel[8]=picture.getRGB(t+1, k+1);
                int n, p; //declaring the iteration variables
                
	   for (n = 0; n < 8; n++)  //looping through     
		    for (p = 0; p < 8-n; p++)  //the pixels in the window 
        	     if (listPixel[p] > listPixel[p+1]){ //and then the middle/median pixel
              int hold=listPixel[p]; listPixel[p]=listPixel[p+1]; listPixel[p+1]=hold;}
                //are sorted replaces the target pixel.
                int pixel = picture.getRGB(t, k);
                int pixels = listPixel[4];
                    if(pixel < 0 || pixels >255)
                              picture.setRGB(t, k, pixels);}
                              
        // Writing our new smoothed out image in memory to see the result
        try{ImageIO.write(picture, "jpg", 
        new File("../data/meadianFilterSerial.jpg"));}//error catch
	     catch(IOException e){System.out.println(e);}
        
        long stopCount = System.currentTimeMillis();
        System.out.println(Math.abs(speedCount-stopCount)+" seconds(m)");}//total amount of time that elasped
}