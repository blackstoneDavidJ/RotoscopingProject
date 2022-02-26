package com.blackstonedj;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class DoubleThreshold 
{
	private float upper = 0;
	private float lower = 0;
	Label[][] labels;
	int[][] gradiants;
	Color[][] colors;
	public DoubleThreshold(float upper, float lower)
	{
		this.upper = upper;
		this.lower = lower;
	}
	
	public BufferedImage DoubleThresholder(BufferedImage img, int[][] edgeVals)
	{
		gradiants = edgeVals;
		int largest = findLargest(img, gradiants);
		colors = new Color[img.getWidth()][img.getHeight()];
		labels = new Label[img.getWidth()][img.getHeight()];
		for (int i = 0; i < img.getWidth(); i++) 
        {
            for (int j = 0; j < img.getHeight(); j++) 
            { 
            	//upper
            	if(gradiants[i][j] >= (largest * upper))
            	{
            		colors[i][j] = Color.BLUE;
            		labels[i][j] = Label.STRONG;
            	}
            		
            	//lower
            	else if(gradiants[i][j] >= (largest * lower) && gradiants[i][j] < (largest * upper))
            	{
            		colors[i][j] = Color.RED;
            		labels[i][j] = Label.WEAK;
            	}
            	//remove
            	else if(gradiants[i][j] < (largest * lower))
            	{
            		gradiants[i][j] = 0;
            		colors[i][j] = Color.BLACK;
            		labels[i][j] = Label.NONE;
            	}
            	
            	img.setRGB(i, j, colors[i][j].getRGB());
            }
        }
		
		return img;
	}
	
	public BufferedImage hysterisis(BufferedImage img, double max)
	{
		for (int i = 1; i < img.getWidth()-1; i++) 
        {
            for (int j = 1; j < img.getHeight()-1; j++) 
            { 
            	if(labels[i][j] == Label.STRONG)
            	{
            		gradiants = checkNeighbors(gradiants, i, j);
            	}
            }
        }
		
		for (int i = 1; i < img.getWidth()-1; i++) 
        {
            for (int j = 1; j < img.getHeight()-1; j++) 
            { 
            	if(labels[i][j] != Label.STRONG)
            	{
            		gradiants[i][j] = 0;
            	}
            }
        }
		
	    for (int i = 1; i < img.getWidth() - 1; i++) 
        {
            for (int j = 1; j < img.getHeight() - 1; j++) 
            {
				int edgeColor = gradiants[i][j];
                edgeColor = (int)(edgeColor * (255.0 / 720));
                 
                if(edgeColor > 0)
                {
	                edgeColor = 0xff000000 | (edgeColor << 16) | (edgeColor << 8) | edgeColor;
                }
                
                img.setRGB(i, j, edgeColor);
            }
        }
		
		return img;
	}
	
	//recursive method to check current and neighboring pixels
	private int[][] checkNeighbors(int[][] g, int i, int j) 
	{
		if(labels[i-1][j] == Label.WEAK)
		{
			labels[i-1][j] = Label.STRONG;
			g[i-1][j] = gradiants[i][j];
			checkNeighbors(g, i-1, j);
		}
		
		if(labels[i+1][j] == Label.WEAK)
		{
			labels[i+1][j] = Label.STRONG;
			g[i+1][j] = gradiants[i][j];
			checkNeighbors(g, i+1, j);
		}
		
		if(labels[i][j-1] == Label.WEAK)
		{
			labels[i][j-1] = Label.STRONG;
			g[i][j-1] = gradiants[i][j];
			checkNeighbors(g, i, j-1);
		}
		
		if(labels[i][j+1] == Label.WEAK)
		{
			labels[i][j+1] = Label.STRONG;
			g[i][j+1] = gradiants[i][j];
			checkNeighbors(g, i, j+1);
		}
		
		if(labels[i-1][j-1] == Label.WEAK)
		{
			labels[i-1][j-1] = Label.STRONG;
			g[i-1][j-1] = gradiants[i][j];
			checkNeighbors(g, i-1, j-1);
		}
		
		if(labels[i+1][j+1] == Label.WEAK)
		{
			labels[i+1][j+1] = Label.STRONG;
			g[i+1][j+1] = gradiants[i][j];
			checkNeighbors(g, i+1, j+1);
		}
		
		if(labels[i-1][j+1] == Label.WEAK)
		{
			labels[i-1][j+1] = Label.STRONG;
			g[i-1][j+1] = gradiants[i][j];
			checkNeighbors(g, i-1, j+1);
		}
		
		if(labels[i+1][j-1] == Label.WEAK)
		{
			labels[i+1][j-1] = Label.STRONG;
			g[i+1][j-1] = gradiants[i][j];
			checkNeighbors(g, i+1, j-1);
		}		
		
		return g;
	}

	private int findLargest(BufferedImage img, int[][] gradiants) 
	{             
		int largest = 0;
		for (int i = 0; i < img.getWidth(); i++) 
        {
            for (int j = 0; j < img.getHeight(); j++) 
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
	
	public enum Label 
	{
		NONE,
		WEAK,
		STRONG
	}
	
	/*if(labels[i-1][j] == Label.STRONG)
	{
		gradiants[i][j] = gradiants[i-1][j];
		colors[i][j] = Color.WHITE;
	}
	
	else if(labels[i+1][j] == Label.STRONG)
	{
		gradiants[i][j] = gradiants[i+1][j];
		colors[i][j] = Color.WHITE;
	}
	
	else if(labels[i][j-1] == Label.STRONG)
	{
		gradiants[i][j] = gradiants[i][j-1];
		colors[i][j] = Color.WHITE;
	}
	
	else if(labels[i][j+1] == Label.STRONG)
	{
		gradiants[i][j] = gradiants[i][j+1];
		colors[i][j] = Color.WHITE;
	}
	
	else if(labels[i-1][j-1] == Label.STRONG)
	{
		gradiants[i][j] = gradiants[i-1][j-1];
		colors[i][j] = Color.WHITE;
	}
	
	else if(labels[i+1][j+1] == Label.STRONG)
	{
		gradiants[i][j] = gradiants[i+1][j+1];
		colors[i][j] = Color.WHITE;
	}
	
	else if(labels[i-1][j+1] == Label.STRONG)
	{
		gradiants[i][j] = gradiants[i-1][j+1];
		colors[i][j] = Color.WHITE;
	}
	
	else if(labels[i+1][j-1] == Label.STRONG)
	{
		gradiants[i][j] = gradiants[i+1][j-1];
		colors[i][j] = Color.WHITE;
	}
	
	else 
	{
		gradiants[i][j] = 0;
		colors[i][j] = Color.BLACK;
	}
	
	img.setRGB(i, j, colors[i][j].getRGB());*/
}
