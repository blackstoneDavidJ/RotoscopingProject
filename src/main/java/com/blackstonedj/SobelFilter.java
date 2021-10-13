package project;

import java.awt.Color;
import java.awt.image.BufferedImage;

//sobel edge detection
public class SobelFilter implements EdgeDetector
{
	final static int MAX = 1020;
	final static int MIN = 0;
	
	public SobelFilter()
	{		
	}
	
	//sobel edge detection for the x/y axis
	public BufferedImage edgeDetection(BufferedImage img)
	{
        
		int[][] edgeVals = new int[img.getWidth()][img.getHeight()];
		int[][] edgeVals2 = new int[img.getWidth()][img.getHeight()];
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
                
                //apply the Gx/Gy kernel to pixel values
                int gEW =  ((1 * val00) + (2 * val01) + (1 * val02)) 
                        + ((0 * val10) + (0 * val11) + (0 * val12))                        
                        + ((-1 * val20) + (-2 * val21) + (-1 * val22));         
                
                int gNS = ((1 * val00) + (0 * val01) + (-1 * val02)) 
                        + ((2 * val10) + (0 * val11) + (-2 * val12))                        
                        + ((1 * val20) + (0 * val21) + (-1 * val22)); 
                
                int g45 = ((1 * val00) + (0 * val01) + (-1 * val02)) 
                        + ((2 * val10) + (0 * val11) + (-2 * val12))                        
                        + ((1 * val20) + (0 * val21) + (-1 * val22)); 
                
                int g = (int) Math.sqrt((gEW * gEW));
                int g2 = (int) Math.sqrt((gNS * gNS));

                edgeVals[i][j] = g;
                edgeVals2[i][j] = g2;
            }
        }
             
        //normalize the values - set values
        max = getMax();
        for (int i = 0; i < img.getWidth(); i++) 
        {
            for (int j = 0; j < img.getHeight(); j++) 
            {
                int edgeColorX = edgeVals[i][j];
                int edgeColorY = edgeVals2[i][j];
                
                edgeColorX = (int)(edgeColorX * (255.0 / max));
                edgeColorY = (int)(edgeColorY * (255.0 / max));

                if(edgeColorX > 10)
                {
                	edgeColorX = 0xff0000 | (edgeColorX << 16) | (edgeColorX << 8) | edgeColorX;
                }
                
                else
                {
                    edgeColorX = 0xff000000 | (edgeColorX << 16) | (edgeColorX << 8) | edgeColorX;

                }
                
                if(edgeColorY > 10)
                {
                	edgeColorY = 0xff0000 | (edgeColorY << 16) | (edgeColorY << 8) | edgeColorY;
                }
                
                else
                {
                    edgeColorY = 0xff000000 | (edgeColorY << 16) | (edgeColorY << 8) | edgeColorY;

                }
                
                img.setRGB(i, j, edgeColorX | edgeColorY);
            }
        }
        
        return img;
    }
	//1020
	//gets max possible gradient
	private int getMax()
	{
		int gX =  ((1 *  MAX) + (2 *  MAX) + (1 *  MAX)) 
				+ ((0 *  MIN) + (0 *  MIN) + (0 *  MIN))                        
				+ ((-1 * MIN) + (-2 * MIN) + (-1 * MIN));         
         
        int gY = ((1 *  MAX) + (0 * MIN) + (-1 * MIN)) 
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