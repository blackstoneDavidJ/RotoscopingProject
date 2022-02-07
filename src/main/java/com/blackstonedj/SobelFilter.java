package com.blackstonedj;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.blackstonedj.SobelDirectional.Direction;

//sobel edge detection
public class SobelFilter implements EdgeDetector
{
	final static int MAX = 1020;
	final static int MIN = 0;
	
	public SobelFilter()
	{		
	}
	
	//sobel edge detection for the x/y axis
	public BufferedImage edgeDetection(BufferedImage img, boolean direction, boolean magnitude)
	{
        int largestEdge = -1;
        SobelDirectional colorEdge = null;
        Direction[][] dirVals = null;
        if(direction || magnitude) 
      	{
        	colorEdge = new SobelDirectional();
        	dirVals = new Direction[img.getWidth()][img.getHeight()];
        }  
        
		int[][] edgeVals = new int[img.getWidth()][img.getHeight()];
		double[][] angleVals = new double[img.getWidth()][img.getHeight()];
		int gx = 0;
		int gy = 0;
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
	            gx =  ((1 * val00) + (2 * val01) + (1 * val02)) 
	                     +  ((0 * val10) + (0 * val11) + (0 * val12))                        
	                     +  ((-1 * val20) + (-2 * val21) + (-1 * val22));    
	             
	            gy = ((1 * val00) + (0 * val01) + (-1 * val02)) 
	                     + ((2 * val10) + (0 * val11) + (-2 * val12))                        
	                     + ((1 * val20) + (0 * val21) + (-1 * val22)); 
	
	            int g = (int) Math.sqrt((gx * gx) + (gy * gy));
	         	
	         	
	             
	            // if(gx > 0 && gy > 0) System.out.println("[" +gx +"," +gy +"]: ");
	            edgeVals[i][j] = g;
	            angleVals[i][j] = Math.toDegrees(Math.atan2(gy, gx));             	
            }
        }
		        		
        double max = getMax();
        Color[][] colors = new Color[img.getWidth()][img.getHeight()];
		if(direction || magnitude)
        {
        	largestEdge = largestEdge(edgeVals, img.getWidth(), img.getHeight(), max);
        }
		
        //normalize the values - set values
        for (int i = 1; i < img.getWidth() - 1; i++) 
        {
            for (int j = 1; j < img.getHeight() - 1; j++) 
            {
				int edgeColor = edgeVals[i][j];
                edgeColor = (int)(edgeColor * (255.0 / max));
                 
                if(edgeColor > 0)
                {
	            	if(direction || magnitude) 
	            	{
	                	Color color = colorEdge.getColor(angleVals[i][j], edgeColor, largestEdge);
	                	colors[i][j] = color;
	                	edgeColor = color.getRGB();
	            		dirVals[i][j] = colorEdge.getDirection(angleVals[i][j]);
	            		//System.out.print(dirVals[i][j]);
	            	}
	            	
	                else if(!direction && !magnitude)
	                {
	                	edgeColor = 0xff000000 | (edgeColor << 16) | (edgeColor << 8) | edgeColor;
	                }
                }
                
                img.setRGB(i, j, edgeColor);
            }
        }
        
        if(magnitude)
        {
        	img = edgeThinner(dirVals, edgeVals, colors, img, direction);
        }
        
        return img;
    }

	private BufferedImage edgeThinner(Direction[][] dir, int[][] edgeCmp, Color[][] colors, BufferedImage img, boolean direction) 
	{
		int t = 0;
		int passes = 0;
		Direction[] dirArray = new Direction[] { Direction.NS, Direction.EW, Direction.NESW, Direction.NWSE };
		int[][]edge = fillTmpArray(edgeCmp, img);
		while(t < dirArray.length)
		{
			while(passes < 2)
			{
				edge = edgeThinnerHelper(dirArray[t], dir, edge, edgeCmp, img);
				passes++;
			}
			passes = 0;
			t++;
		}
		
		edgeCmp = edge;
		double max = getMax();
		for (int i = 1; i < img.getWidth() - 1; i++) 
        {
            for (int j = 1; j < img.getHeight() - 1; j++) 
            {
            	int edgeColor = edgeCmp[i][j];
                edgeColor = (int)(edgeColor * (255.0 / max));
            	if(edgeColor == 0) edgeColor = new Color(0,0,0).getRGB();
            	else if(direction && edgeColor > 0) edgeColor = colors[i][j].getRGB();
            	else
            	{
            		edgeColor = 0xff000000 | (edgeColor << 16) | (edgeColor << 8) | edgeColor;
            	}
            	
            	img.setRGB(i, j, edgeColor);            	
            }
        }
		
		return img;
	}
	
	private int[][] edgeThinnerHelper(Direction currentDir, Direction[][] dir, int[][] edge, int[][] edgeCmp, BufferedImage img)
	{
		for (int i = 1; i < img.getWidth()-1; i++) 
        {
            for (int j = 1; j < img.getHeight()-1; j++) 
            {     
            	
            	if(dir[i][j] == Direction.NS && dir[i][j] == currentDir)
            	{
            		
            		if(i == 287 && j == 113) System.out.println("i: " +i +" j: " +j +" dir: " +dir[i][j] + " edge: " +edgeCmp[i][j] +" edge- i: " +(i) +" j: " +(j-1) +" " +edgeCmp[i][j-1]  +" edge+ j: " +(i) +" j: " +(j+1) +" "
            				+edgeCmp[i][j+1]);
            		if(dir[i][j-1] == Direction.NS && edgeCmp[i][j-1] > edgeCmp[i][j] ||
            				dir[i][j+1] == Direction.NS && edgeCmp[i][j+1] > edgeCmp[i][j])
            		{
            			System.out.println("1");
            			edge[i][j] = 0;
            		}
            		
            		else if(dir[i][j-1] != Direction.NS && dir[i][j+1] != Direction.NS ||
            				edgeCmp[i][j-1] == 0 && edgeCmp[i][j+1] == 0)
            		{
            			System.out.println("2");
            			edge[i][j] = 0;
            		}
            		
            		else if(edgeCmp[i][j-1] == edgeCmp[i][j])
            		{
            			System.out.println("3");

            			edge[i][j+1] = 0;
            		}
            		
            		else if(edgeCmp[i][j+1] == edgeCmp[i][j])
            		{
            			System.out.println("4");
            			edge[i][j+1] = 0;
            		}
            		
            		
            		System.out.println(" edge: " +edge[i][j]);
            	}
            	
            	
            	else if(dir[i][j] == Direction.EW && dir[i][j] == currentDir)
            	{
            		if(i == 450 && j == 251) System.out.println("i: " +i +" j: " +j +" dir: " +dir[i][j] + " edge: " +edgeCmp[i][j] +" edge- i: " +(i-1) +" j: " +(j) +" " +edgeCmp[i-1][j]  +" edge+ j: " +(i+1) +" j: " +(j) +" "
            				+edgeCmp[i+1][j]);
            		if(dir[i-1][j] == Direction.EW && edgeCmp[i-1][j] > edgeCmp[i][j] ||
            				dir[i+1][j] == Direction.EW && edgeCmp[i+1][j] > edgeCmp[i][j])
            		{
            			System.out.println("1");
            			edge[i][j] = 0;
            		}
            		
            		else if(dir[i-1][j] != Direction.EW && dir[i+1][j] != Direction.EW ||
            				edgeCmp[i-1][j] == 0 && edgeCmp[i+1][j] == 0)
            		{
            			System.out.println("2");
            			edge[i][j] = 0;
            		}          		
            		
            		else if(edgeCmp[i-1][j] == edgeCmp[i][j])
            		{
            			System.out.println("3");

            			edge[i+1][j] = 0;
            		}
            		
            		else if(edgeCmp[i+1][j] == edgeCmp[i][j])
            		{
            			System.out.println("4");
            			edge[i+1][j] = 0;
            		}
            		
            		
            		System.out.println(" edge: " +edge[i][j]);
            	}
            	
            	else if(dir[i][j] == Direction.NESW && dir[i][j] == currentDir)
            	{
     
            		
            		if(i == 384 && j == 199) System.out.println("i: " +i +" j: " +j +" dir: " +dir[i][j] + " edge: " +edgeCmp[i][j] +" edge- i: " +(i-1) +" j: " +(j+1) +" " +edgeCmp[i-1][j+1]  +" edge+ i: " +(i+1) +" j: " +(j-1) +" "
            				+edgeCmp[i+1][j-1]);
            		if(/*dir[i-1][j+1] == Direction.NESW && */edgeCmp[i-1][j+1] > edgeCmp[i][j] ||
            				/*dir[i+1][j-1] == Direction.NESW && */edgeCmp[i+1][j-1] > edgeCmp[i][j])
            		{
            			//System.out.println("1");
            			edge[i][j] = 0;
            		}
            		
            		else if(dir[i-1][j+1] != Direction.NESW && dir[i+1][j-1] != Direction.NESW &&
            				edgeCmp[i-1][j+1] == 0 && edgeCmp[i+1][j-1] == 0)

            		{
            			System.out.println("2");
            			edge[i][j] = 0;
            		}
            		
            		
            		else if(edgeCmp[i-1][j+1] == edgeCmp[i][j])
            		{
            			System.out.println("3");

            			edge[i-1][j+1] = 0;
            			
            		}
            		
            		else if(edgeCmp[i+1][j-1] == edgeCmp[i][j])
            		{
            			System.out.println("4");
            			edge[i+1][j-1] = 0;
            		}
            		
            	
            		//System.out.println(" edge: " +edge[i][j]);
            	}

            	if(dir[i][j] == Direction.NWSE && dir[i][j] == currentDir)
            	{
            		System.out.println("i: " +i +" j: " +j +" dir: " +dir[i][j] + " edge: " +edgeCmp[i][j] +" edge- i: " +(i-1) +" j: " +(j-1) +" " +edgeCmp[i-1][j-1]  +" edge+ j: " +(i+1) +" j: " +(j+1) +" "
            				+edgeCmp[i+1][j+1]);
            		if(/*dir[i-1][j-1] == Direction.NWSE && */edgeCmp[i-1][j-1] > edgeCmp[i][j] ||
            				/*dir[i+1][j+1] == Direction.NWSE && */edgeCmp[i+1][j+1] > edgeCmp[i][j])
            		{
            			System.out.println("1");
            			edge[i][j] = 0;
            		}
            		
            		else if(dir[i-1][j-1] != Direction.NWSE && dir[i+1][j+1] != Direction.NWSE &&
            				edgeCmp[i-1][j-1] == 0 && edgeCmp[i+1][j+1] == 0)
            		{
            			System.out.println("2");
            			edge[i][j] = 0;
            		}
            		
            		
            		else if(edgeCmp[i-1][j-1] == edgeCmp[i][j])
            		{
            			System.out.println("3");

            			edge[i-1][j-1] = 0;
            		}
            		
            		else if(edgeCmp[i+1][j+1] == edgeCmp[i][j])
            		{
            			System.out.println("4");
            			edge[i+1][j+1] = 0;
            		}
            		
            		
            		System.out.println(" edge: " +edge[i][j]);
            	}
            	
            	/*//remove singels
            	if(edge[i-1][j] == 0 && edge[i+1][j] == 0 && edge[i][j-1] == 0 && edge[i][j+1] == 0 && 
            	   edge[i-1][j-1] == 0 && edge[i+1][j+1] == 0 && edge[i+1][j-1] == 0 && edge[i-1][j+1] == 0)
            	{
            		edge[i][j] = 0;
            	}*/
            }
        }
	
		return edge;
	}
	
	private int[][]  fillTmpArray(int[][]array, BufferedImage img)
	{
		int[][] arrayNew = new int[array.length][array.length];
		for (int i = 0; i < img.getWidth(); i++) 
        {
            for (int j = 0; j < img.getHeight(); j++) 
            {
            	arrayNew[i][j] = array[i][j]; 
            }
        }
		
		return arrayNew;
	}

	private int largestEdge(int[][] edgeVals, int width, int height, double max) 
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
	private double getMax()
	{
		int gX =  ((1 *  MAX) + (2 *  MAX) + (1 *  MAX)) 
				+ ((0 *  MIN) + (0 *  MIN) + (0 *  MIN))                        
				+ ((-1 * MIN) + (-2 * MIN) + (-1 * MIN));         
         
        int gY = ((1 *  MAX) + (0 * MIN) + (-1 * MIN)) 
                + ((2 * MAX) + (0 * MIN) + (-2 * MIN))                        
                + ((1 * MAX) + (0 * MIN) + (-1 * MIN)); 
         
        return Math.sqrt((gX * gY) + (gY * gY));
	}
	
	//converting getRBG val to a value we can use
	/*private int convertPixelVal(int rgb) 
	{        
        return (int) (((rgb >> 16) & 0xff) + ((rgb >> 8) & 0xff) + ((rgb) & 0xff));
    }*/
	
	public static int convertPixelVal(int rgb) {
        int r = (rgb >> 16) & 0xff;
        int g = (rgb >> 8) & 0xff;
        int b = (rgb) & 0xff;

        //from https://en.wikipedia.org/wiki/Grayscale, calculating luminance
        int gray = (int)(0.2126 * r + 0.7152 * g + 0.0722 * b);
        //int gray = (r + g + b) / 3;

        return gray;
    }
}