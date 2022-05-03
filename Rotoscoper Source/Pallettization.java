package com.blackstonedj;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;


public class Pallettization 
{
	private static final int PASSES = 15;
	private int k = 0;
	private int imgWidth;
	private int imgHeight;
	
	public Pallettization(int k)
	{
		this.k = k;
	}
	
	public BufferedImage runner(BufferedImage img) throws IOException
	{
		
		BufferedImage palletteImg = null;
		if(img != null)
		{
			imgWidth = img.getWidth();
			imgHeight = img.getHeight();
			int[][] pixelVals = convertAllVals(img);
			img.flush();
			if(!allEqual(pixelVals))
			{
				 palletteImg = arrayToImage(getPalette(pixelVals, null, PASSES));
			}
		}
		return palletteImg;
	}
	
	public BufferedImage[] runner(BufferedImage[] images) throws IOException
	{
		BufferedImage[] palletteImages = null;
		int[][] pixelVals = getPalette(putImagesIntoArray(images), null, PASSES);
		
		return palletteImages;
	}


	private boolean allEqual(int[][] arr) 
	{	
		return Arrays.stream(arr).distinct().count() == arr[0][0];
	}

	private int[][] getPalette(int[][] pixelVals, Object[] keys, int passes)
	{
		HashMap<Integer, int[][]> categories = new HashMap<>();
		
		//get starting ran vals
		if(keys == null)
		{
			categories = getRandomVals(pixelVals, categories);
			if(categories == null)
			{
				return pixelVals;
			}
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
				
				categories.put((Integer) keys[t], new int[imgWidth][imgHeight]);
				
			}
			passes--;
		}
		
		//assign pixel vals to category
		keys = categories.keySet().toArray();
		int closestKey = 0;
		int currentPixel = 0;
		int[][] tmpFill = new int[imgWidth][imgHeight];
		for (int i = 0; i < imgWidth; i++) 
        {			
            for (int j = 0; j < imgHeight; j++) 
            { 
            	
            	currentPixel = pixelVals[i][j];           	
            	closestKey = getClosest(keys, currentPixel);
            	tmpFill = categories.get(closestKey);
            	tmpFill[i][j] = currentPixel;
            	pixelVals[i][j] = closestKey;
            	
            	categories.put(closestKey, tmpFill);            	
            }
        }		

		//check if ending pass
		if(passes > 1)
		{
			int index = 0;
			int sumR = 0;
			int sumG = 0;
			int sumB = 0;
			int length = 0;
			int tmpRGB = 0;
			int[][] tmpArray;
			
			//get new avg vals then recurse through
			while(index < keys.length)
			{
				sumR = 0;
				sumG = 0;
				sumB = 0;
				tmpRGB = 0;
				length = 0;
				tmpArray = categories.get(keys[index]);
				for (int i = 0; i < imgWidth; i++) 
		        {			
		            for (int j = 0; j < imgHeight; j++) 
		            { 
		            	if(pixelVals[i][j] == (int) keys[index])
		            	{
		            		tmpRGB = tmpArray[i][j];
		            		sumB += tmpRGB & 0xff;
		            		sumG += (tmpRGB & 0xff00) >> 8;
		            		sumR += (tmpRGB & 0xff0000) >> 16;							
		            		length++;
		            	}
		            }
		        }
				if(length == 0)
				{
					length++;
				}
				keys[index] = new Color(sumR/length, sumG/length, sumB/length).getRGB();
				index++;
			}
			
			pixelVals = getPalette(pixelVals,keys, passes);
		}
	
		return pixelVals;
	}
	
	private HashMap<Integer, int[][]> getRandomVals(int[][] pixelVals, HashMap<Integer, int[][]> categories) 
	{
		int q = 0;
		int containsCount = 0;
		Random rand = new Random();
		while(q  < k && containsCount < 100)
		{

			int randNumX = rand.nextInt(imgWidth);
			int randNumY = rand.nextInt(imgHeight);
			int randPixelVal = pixelVals[randNumX][randNumY];
			if(!categories.containsKey(randPixelVal))
			{
				categories.put(randPixelVal, new int[imgWidth][imgHeight]);
				q++;
			}	
			
			else
			{
				containsCount++;
			}
		}	
		
		if(containsCount >= 100)
		{
			return null;
		}
		
		return categories;
	}

	private int getClosest(Object[] keys, int pixel)
	{
		double smallest = 1000000;
		int selectedKey = 0;
		double dist = 0;
		for(int i = 0; i < keys.length; i++)
		{
			dist = getDistance(pixel,(int) keys[i]);
			if(dist < smallest)
			{
				smallest = dist;
				selectedKey = (int) keys[i];
			}
		}

		return selectedKey;
	} 
	
	private double getDistance(int p1, int p2) 
	{
		double dist = 0;
		
		int b1 = p1 & 0xff;
		int g1 = (p1 & 0xff00) >> 8;
		int r1 = (p1 & 0xff0000) >> 16;
		
		int b2 = p2 & 0xff;
		int g2 = (p2 & 0xff00) >> 8;
		int r2 = (p2 & 0xff0000) >> 16;
				
		dist = Math.sqrt(Math.pow(r2 - r1, 2) + Math.pow(b2 - b1, 2) + Math.pow(g2 - g1, 2));
		
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
	
	private BufferedImage arrayToImage(int[][] pixelVals)
	{

		BufferedImage img = new BufferedImage(imgWidth,imgHeight, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < imgWidth; i++) 
        {			
            for (int j = 0; j < imgHeight; j++) 
            { 
            	img.setRGB(i, j, pixelVals[i][j]);            	
            }
        } 
		return img;
	}
	
	private int[][] putImagesIntoArray(BufferedImage[] images)
	{
		int[][] imageArray = new int[imgWidth * images.length][imgWidth * images.length];
		List list = new ArrayList(Arrays.asList(imageArray));
		for(BufferedImage img : images)
		{
			list.addAll(Arrays.asList(convertAllVals(img)));
		}
		
		imageArray = (int[][]) list.toArray();
		
		return imageArray;
	}
	
	private BufferedImage splitArrayToImage(int[][] images)
	{
		
	}
}
