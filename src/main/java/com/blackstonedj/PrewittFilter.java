package com.blackstonedj;

import java.awt.Color;
import java.awt.image.BufferedImage;

//Prewitt edge detection
public class PrewittFilter implements EdgeDetector
{
	final static int MAX = 765;
	final static int MIN = 0;
	
	public PrewittFilter()
	{		
	}
	
	//sobel edge detection for the x axis
	public BufferedImage edgeDetection(BufferedImage img, EdgeDirection direction)
	{	
		boolean useDirectional = false;
        if(direction != null) useDirectional = true;
		  int[][] edgeVals = new int[img.getWidth()][img.getHeight()];
		  double[][] angleVals = new double[img.getWidth()][img.getHeight()];
	
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
	              if(useDirectional)
	              {
	            	  angleVals[i][j] = Math.toDegrees(Math.atan2(gy, gx));
	              }
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
	              if(useDirectional)
	                {
	                	
	                	edgeColor = direction.getColor(angleVals[i][j]);
	                }
	                
	                else 
	                {
	                	edgeColor = 0xff000000 | (edgeColor << 16) | (edgeColor << 8) | edgeColor;
	                }
	              img.setRGB(i, j, edgeColor);
	         }
	     }
      
	     return img;
	}
	
	//get the color based on pixel angle
	private int getColor(double angle)
	{
		int color = 0;
		Color col;
		
		if(angle == 0 || angle == 180)
		{
			color = (int) new Color(255,0,0).getRGB();
		}
		
		else if(angle == 90 || angle == -180 || angle ==-90)
		{
			color = (int) new Color(0,0,255).getRGB();
		}
	
		else
		{
			double temp = angle;
			temp *= 382.5;
			col = new Color((int)temp);
			color = (int) col.getRGB();
		}

		return color;
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
