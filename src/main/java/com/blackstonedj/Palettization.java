package com.blackstonedj;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import javax.net.ssl.SSLEngineResult.HandshakeStatus;

public class Palettization 
{
	private int k = 0;
	public Palettization(int k)
	{
		this.k = k;
	}
	
	public BufferedImage runner(BufferedImage img)
	{
		int passes = 15;
		img = getPalette(img, null, passes);
		
		
		return img;
	}
	
	private BufferedImage getPalette(BufferedImage img, Object[] keys, int passes)
	{
		int[][] pixelVals = convertAllVals(img);
		HashMap<Integer, int[][]> categories = new HashMap<>();
		
		//get starting ran vals
		if(keys == null)
		{
			categories = getRandomVals(img, categories);
		}
		
		//set new category vals to avg vals from prevous pass
		else
		{
			categories.clear();
			for(int t = 0; t < k; t++)
			{				
				int tmpKey = (int) keys[t];
				while(categories.containsKey((int) keys[t]))
				{
					tmpKey++;
					keys[t] = tmpKey;
				}
				categories.put((Integer) keys[t], new int[img.getWidth()][img.getHeight()]);
			}
			passes--;
		}
		
		//assign pixel vals to category
		keys = categories.keySet().toArray();
		int closestKey = 0;
		int currentPixel = 0;

		for (int i = 0; i < img.getWidth(); i++) 
        {			
            for (int j = 0; j < img.getHeight(); j++) 
            { 
            	currentPixel = pixelVals[i][j];           	
            	closestKey = getClosest(keys, currentPixel);
            	int[][] tmpFill = categories.get(closestKey);
            	tmpFill[i][j] = currentPixel;
            	categories.put(closestKey, tmpFill);
            	pixelVals[i][j] = closestKey;
            }
        }
		
		//check if ending pass
		if(passes > 1)
		{
			int index = 0;
			int sum = 0;
			int length = 0;
			int[][] tmpArray;
			
			//get new avg vals then recurse through
			while(index < keys.length)
			{
				sum = 0;
				length = 0;
				tmpArray = categories.get(keys[index]);
				for (int i = 0; i < img.getWidth(); i++) 
		        {			
		            for (int j = 0; j < img.getHeight(); j++) 
		            { 
		            	if(pixelVals[i][j] == (int) keys[index])
		            	{
		            		sum += tmpArray[i][j]; 
		            		length++;
		            	}
		            }
		        }
	
				keys[index] = (sum / length);
				index++;
			}
			
			img = getPalette(img, keys, passes);
		}

		//set pixels to ending value
		else
		{
			for (int i = 0; i < img.getWidth(); i++) 
	        {			
	            for (int j = 0; j < img.getHeight(); j++) 
	            { 
	            	int val = pixelVals[i][j];
	            	img.setRGB(i, j, new Color(val,val,val).getRGB());            	
	            }
	        } 
		}

		
		return img;
	}
	
	private HashMap<Integer, int[][]> getRandomVals(BufferedImage img, HashMap<Integer, int[][]> categories) 
	{
		int q = 0;
		Random rand = new Random();
		while(q  < k)
		{
			int randNumX = rand.nextInt(img.getWidth());
			int randNumY = rand.nextInt(img.getHeight());
			
			int randPixelVal = (int) convertPixelVal(img.getRGB(randNumX, randNumY));
			while(categories.containsKey(randPixelVal))
			{
				randPixelVal++;
				
			}
			categories.put(randPixelVal, new int[img.getWidth()][img.getHeight()]);
			q++;
		}	
		
		return categories;
	}

	private int getClosest(Object[] keys, int pixel)
	{
		int currKeyDistance = 0;
		int smallest = 255;
		int selectedKey = 0;
		int dist = 0;
		for(int i = 0; i < keys.length; i++)
		{
			dist = Math.abs((int)keys[i] - pixel);
			currKeyDistance = dist;
			if(currKeyDistance < smallest)
			{
				smallest = currKeyDistance;
				selectedKey = (int) keys[i];
			}
		}
		
		return selectedKey;
	}
	
	private static int[][] convertAllVals(BufferedImage img) 
	{	
		int[][] pixelVals = new int[img.getWidth()][img.getHeight()];
		for (int i = 0; i < img.getWidth(); i++) 
        {
            for (int j = 0; j < img.getHeight(); j++) 
            { 
            	int rgb = img.getRGB(i, j);
            	int r = (rgb >> 16) & 0xff;
                int g = (rgb >> 8) & 0xff;
                int b = (rgb) & 0xff;
                
                pixelVals[i][j] = (int)(0.2126 * r + 0.7152 * g + 0.0722 * b);
            }
        }
		
		return pixelVals;
    }
	
	private static int convertPixelVal(int rgb) 
	{
        int r = (rgb >> 16) & 0xff;
        int g = (rgb >> 8) & 0xff;
        int b = (rgb) & 0xff;

        return (int)(0.2126 * r + 0.7152 * g + 0.0722 * b);
	}
}
