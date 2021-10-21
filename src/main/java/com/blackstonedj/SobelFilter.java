package com.blackstonedj;

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
	public BufferedImage edgeDetection(BufferedImage img, boolean direction)
	{
        int largestEdge = 0;
        SobelDirectional colorEdge = null;
		boolean useDirectional = false;
        if(direction == true) 
      	{
        	useDirectional = true;
        	colorEdge = new SobelDirectional();
        }
        
		int[][] edgeVals = new int[img.getWidth()][img.getHeight()];
		double[][] angleVals = new double[img.getWidth()][img.getHeight()];

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
                int gx =  ((1 * val00) + (2 * val01) + (1 * val02)) 
                        +  ((0 * val10) + (0 * val11) + (0 * val12))                        
                        +  ((-1 * val20) + (-2 * val21) + (-1 * val22));         
                
                int gy = ((1 * val00) + (0 * val01) + (-1 * val02)) 
                        + ((2 * val10) + (0 * val11) + (-2 * val12))                        
                        + ((1 * val20) + (0 * val21) + (-1 * val22)); 

                int g = (int) Math.sqrt((gx * gx) + (gy * gy));
                

                edgeVals[i][j] = g;
                if(useDirectional)
                {
                	angleVals[i][j] = Math.toDegrees(Math.atan2(gy, gx));
                }          
            }
        }
        
        int max = getMax();
		if(useDirectional)
        {
        	largestEdge = largestEdge(edgeVals, img.getWidth(), img.getHeight(), max);
        }
        //normalize the values - set values
        for (int i = 0; i < img.getWidth(); i++) 
        {
            for (int j = 0; j < img.getHeight(); j++) 
            {
				int edgeColor = edgeVals[i][j];
                edgeColor = (int)(edgeColor * (255.0 / max));
                
                if(useDirectional && edgeColor > 1)
                {
                	edgeColor = colorEdge.getColor(angleVals[i][j], edgeColor, largestEdge);
                }
                
                else if(!useDirectional)
                {
                	edgeColor = 0xff000000 | (edgeColor << 16) | (edgeColor << 8) | edgeColor;
                }
                
                img.setRGB(i, j, edgeColor);
            }
        }
        
        return img;
    }
	
	private int largestEdge(int[][] edgeVals, int width, int height, int max) 
	{
		int largest = -1;
		for (int i = 0; i < width; i++) 
        {
            for (int j = 0; j < height; j++) 
            {
				int edgeColor = edgeVals[i][j];
                edgeColor = (int)(edgeColor * (255.0 / max));
                if(edgeColor > largest) largest = edgeColor;
            }
        }
		
		return largest;
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