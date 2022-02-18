package com.blackstonedj;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class DoubleThreshold 
{
	private float upper = 0;
	private float lower = 0;
	public DoubleThreshold(float upper, float lower)
	{
		this.upper = upper;
		this.lower = lower;
	}
	
	public BufferedImage DoubleThresholder(BufferedImage img, int[][] edgeVals)
	{
		int[][] gradiants = edgeVals;
		int largest = findLargest(img, gradiants);
		Color[][] colors = new Color[img.getWidth()][img.getHeight()];
		for (int i = 1; i < img.getWidth() - 1; i++) 
        {
            for (int j = 1; j < img.getHeight() - 1; j++) 
            { 
            	//upper
            	if(gradiants[i][j] >= (largest * .60f))
            	{
            		colors[i][j] = new Color(0,0,255);
            	}
            		
            	//lower
            	else if(gradiants[i][j] >= (largest * .07f) && gradiants[i][j] < (largest * .60f))
            	{
            		colors[i][j] = new Color(255,0,0);
            	}
            	//remove
            	else if(gradiants[i][j] < (largest * .7f))
            	{
            		gradiants[i][j] = 0;
            		colors[i][j] = new Color(0,0,0);
            	}
            	
            	img.setRGB(i, j, colors[i][j].getRGB());
            }
        }
		
		return img;
	}
	
	private int findLargest(BufferedImage img, int[][] gradiants) 
	{
		int largest = 0;
		for (int i = 1; i < img.getWidth() - 1; i++) 
        {
            for (int j = 1; j < img.getHeight() - 1; j++) 
            { 
            	if(largest < gradiants[i][j])
            	{
            		largest = gradiants[i][j];
            	}
            }
        }
		
		return largest;
	}

	public static int convertPixelVal(int rgb) {
        int r = (rgb >> 16) & 0xff;
        int g = (rgb >> 8) & 0xff;
        int b = (rgb) & 0xff;

        return (int)(0.2126 * r + 0.7152 * g + 0.0722 * b);
    }
}
