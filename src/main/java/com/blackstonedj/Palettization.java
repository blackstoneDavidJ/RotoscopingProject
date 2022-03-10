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
		int[][] tmpFill = new int[img.getWidth()][img.getHeight()];
		for (int i = 0; i < keys.length; i++) 
        {	
			//System.out.println("key: " +keys[i]);
        }
		//System.out.println("key: " +keys[0]);
		for (int i = 0; i < img.getWidth(); i++) 
        {			
            for (int j = 0; j < img.getHeight(); j++) 
            { 
            	
            	currentPixel = pixelVals[i][j];           	
            	closestKey = getClosest(keys, currentPixel);
				//System.out.println("sel key: " +closestKey);
            	tmpFill = categories.get(closestKey);
            	if(tmpFill == null)
            	{
            		System.out.println("null");
            	}
            	tmpFill[i][j] = currentPixel;
            	pixelVals[i][j] = closestKey;
            	
            	categories.put(closestKey, tmpFill);            	
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
				if(length == 0) 
				{
					//System.out.println("0: " +keys[index]);
					length++;
				}
				keys[index] = (sum / length);
				index++;
			}
			for(int x = 0; x < keys.length; x++)
			{
				 if((int) keys[x] == 0) System.out.println((int)keys[x] +" i: " +x);
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
	            	int rgb = pixelVals[i][j];
	            	img.setRGB(i, j, new Color(rgb).getRGB());            	
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
			
			int randPixelVal = img.getRGB(randNumX, randNumY);
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
		int smallest = 10000;
		int selectedKey = 0;
		int dist = 0;
		for(int i = 0; i < keys.length; i++)
		{
			dist = getDistance(pixel,(int) keys[i]);
			System.out.println("distance: "+dist +" smallest: " +smallest +" keysel: " +selectedKey + " i: " +i +"kval: " +(int) keys[i]);
			if(dist < smallest)
			{
				smallest = dist;
				int ke = (int) keys[i];
				selectedKey = (int) keys[i];
				System.out.println("key: " +selectedKey);
			}
		}
		
		if(selectedKey == 0)
		{
			System.out.println("0");
		}
		
		else
		{
			System.out.println("key: " +selectedKey);
		}
		
		System.out.println("--------");
		return selectedKey;
	} 
	
	private int getDistance(int p1, int p2) 
	{
		int dist = 0;
		
		int b1 = p1 & 0xff;
		int g1 = (p1 & 0xff00) >> 8;
		int r1 = (p1 & 0xff0000) >> 16;
		
		int b2 = p2 & 0xff;
		int g2 = (p2 & 0xff00) >> 8;
		int r2 = (p2 & 0xff0000) >> 16;
				
		dist = (int) Math.sqrt(Math.pow(r2 - r1, 2) + Math.pow(b2 - b1, 2) + Math.pow(g2 - g1, 2));
		
		return dist;
	}

	private static int[][] convertAllVals(BufferedImage img) 
	{	
		int[][] pixelVals = new int[img.getWidth()][img.getHeight()];
		for (int i = 0; i < img.getWidth(); i++) 
        {
            for (int j = 0; j < img.getHeight(); j++) 
            { 
            	int rgb = img.getRGB(i, j);

                pixelVals[i][j] = rgb;
            }
        }
		
		return pixelVals;
    }
}
