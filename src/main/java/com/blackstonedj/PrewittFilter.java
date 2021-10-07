package com.blackstonedj;

import java.awt.image.BufferedImage;

//Prewitt edge detection
public class PrewittFilter 
{
	final static int MAX = 765;
	final static int MIN = 0;
	
	public PrewittFilter()
	{		
	}
	
	//sobel edge detection for the x axis
	public BufferedImage edgeDetection(BufferedImage img)
	{	
      int[][] edgeVals = new int[img.getWidth()][img.getHeight()];
      int max;
    
      for (int i = 1; i < img.getWidth() - 1; i++) 
      {
          for (int j = 1; j < img.getHeight() - 1; j++) 
          {
          	//get all 9 values
              int val00 = convertPixelVal(img.getRGB(i - 1, j - 1));
              int val01 = convertPixelVal(img.getRGB(i - 1, j));
              int val02 = convertPixelVal(img.getRGB(i - 1, j + 1));

              int val10 = convertPixelVal(img.getRGB(i, j - 1));
              int val11 = convertPixelVal(img.getRGB(i, j));
              int val12 = convertPixelVal(img.getRGB(i, j + 1));
              
              int val20 = convertPixelVal(img.getRGB(i + 1, j - 1));
              int val21 = convertPixelVal(img.getRGB(i + 1, j));
              int val22 = convertPixelVal(img.getRGB(i + 1, j + 1));
              
              //apply the Gx kernel to pixel values
              int gx =  ((1 * val00) + (1 * val01) + (1 * val02)) 
                      + ((0 * val10) + (0 * val11) + (0 * val12))                        
                      + ((-1 * val20) + (-1 * val21) + (-1 * val22));         
              
              int gy = ((1 * val00) + (0 * val01) + (-1 * val02)) 
                      + ((1 * val10) + (0 * val11) + (-1 * val12))                        
                      + ((1 * val20) + (0 * val21) + (-1 * val22)); 
              
              int g = (int) Math.sqrt((gx * gx) + (gy * gy));
             
              edgeVals[i][j] = g;
          }
      }
           
      //normalize the values - set values
      max = getMax();
      for (int i = 0; i < img.getWidth(); i++) 
      {
          for (int j = 0; j < img.getHeight(); j++) 
          {
              int edgeColor = edgeVals[i][j];
              edgeColor = (int)(edgeColor * (255.0 / max));
              edgeColor = 0xff000000 | (edgeColor << 16) | (edgeColor << 8) | edgeColor;
              img.setRGB(i, j, edgeColor);
          }
      }
      
      return img;
  }
	
	//gets max possible gradient
	private int getMax()
	{
		int gX =  ((1 * MAX) + (2 * MAX) + (1 * MAX)) 
				+ ((0 * MIN) + (0 * MIN) + (0 * MIN))                        
				+ ((-1 * MIN) + (-2 * MIN) + (-1 * MIN));         
       
      int gY = ((1 * MAX) + (0 * MIN) + (-1 * MIN)) 
              + ((2 * MAX) + (0 * MIN) + (-2 * MIN))                        
              + ((1 * MAX) + (0 * MIN) + (-1 * MIN)); 
       
       return (int) Math.sqrt((gX * gY) + (gY * gY));
	}
	
	//converting getRBG val to a value we can use
	private int convertPixelVal(int rgb) 
	{        
      return (int) (((rgb >> 16) & 0xff) + ((rgb >> 8) & 0xff) + ((rgb) & 0xff));
	}
}
