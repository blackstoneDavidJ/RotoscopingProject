package project;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class SoberFilter 
{
	
	public SoberFilter()
	{		
	}
	
	public BufferedImage edgeDetection(BufferedImage img)
	{	
        int[][] edgeColors = new int[img.getWidth()][img.getHeight()];
        int maxGradient = -1;
        
        int val00 = getGrayScale(img.getRGB(0, 0));
        int val01 = getGrayScale(img.getRGB(1 - 1, 1));
        int val02 = getGrayScale(img.getRGB(1 - 1, 1 + 1));
        int val00x = img.getRGB(1 - 1, 1 - 1);
        int val01x = img.getRGB(1 - 1, 1);
        int val02x = img.getRGB(1 - 1, 1 + 1);
        System.out.println("grey: " +val00);
        System.out.println("grey: " +val01);
        System.out.println("grey: " +val02);
        System.out.println("here");
        System.out.println("greyFromimg: " +val00x);
        System.out.println("greyFromgimg: " +val01x);
        System.out.println("greyFromgImg: " +val02x);
        /*for (int i = 1; i < img.getWidth() - 1; i++) {
            for (int j = 1; j < img.getHeight() - 1; j++) {

                int val00xx = getGrayScale(img.getRGB(i - 1, j - 1));
                int val01xx = getGrayScale(img.getRGB(i - 1, j));
                int val02xx = getGrayScale(img.getRGB(i - 1, j + 1));
                int val00xxx = img.getRGB(i - 1, j - 1);
                int val01xxx = img.getRGB(i - 1, j);
                int val02xxx = img.getRGB(i - 1, j + 1);
                System.out.println("grey: " +val00);
                System.out.println("grey: " +val01);
                System.out.println("grey: " +val02);
                System.out.println("greyFromimg: " +val00x);
                System.out.println("greyFromgimg: " +val01x);
                System.out.println("greyFromgImg: " +val02x);
                

                int val10 = getGrayScale(img.getRGB(i, j - 1));
                int val11 = getGrayScale(img.getRGB(i, j));
                int val12 = getGrayScale(img.getRGB(i, j + 1));
                int val20 = getGrayScale(img.getRGB(i + 1, j - 1));
                int val21 = getGrayScale(img.getRGB(i + 1, j));
                int val22 = getGrayScale(img.getRGB(i + 1, j + 1));

                int val20x = img.getRGB(i + 1, j - 1);
                int val21x = img.getRGB(i + 1, j);
                int val22x = img.getRGB(i + 1, j + 1);
                int gx =  ((1 * val00) + (2 * val01) + (1 * val02)) 
                        + ((0 * val10) + (0 * val11) + (0 * val12))
                        + ((-1 * val20) + (-2 * val21) + (-1 * val22));

                int g = (int) Math.sqrt((gx * gx));
              
                if(maxGradient < g) 
                {
                    maxGradient = g;
                }

                edgeColors[i][j] = g;
            }
            break;
        }

        for (int i = 1; i < img.getWidth() - 1; i++) 
        {
            for (int j = 1; j < img.getHeight() - 1; j++) 
            {
                int edgeColor = edgeColors[i][j];
                edgeColor = (int)(edgeColor * (255.0 / maxGradient));
                edgeColor = 0xff000000 | (edgeColor << 16) | (edgeColor << 8) | edgeColor;

                img.setRGB(i, j, edgeColor);
            }
        }
        */
        return img;
    }
	
	public int  getGrayScale(int rgb) 
	{
        int r = (rgb >> 16) & 0xff;
        int g = (rgb >> 8) & 0xff;
        int b = (rgb) & 0xff;
        
        
        //from https://en.wikipedia.org/wiki/Grayscale, calculating luminance
        int gray = (int)(0.2126 * r + 0.7152 * g + 0.0722 * b);
        //int gray = (r + g + b) / 3;

        return gray;
    }
}
