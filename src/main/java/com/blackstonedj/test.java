
import java.awt.image.BufferedImage;

//sobel edge detection
public class SobelFilter 
{
	
	public SobelFilter()
	{		
	}
	
	//sobel edge detection for the x axis
	public BufferedImage edgeDetection(BufferedImage img)
	{	
        int[][] edgeVals = new int[img.getWidth()][img.getHeight()];
        int maxGradient = -1;
      
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
                int gx =  ((1 * val00) + (2 * val01) + (1 * val02)) 
                        + ((0 * val10) + (0 * val11) + (0 * val12))                        
                        + ((-1 * val20) + (-2 * val21) + (-1 * val22));         
                
                int g = (int) Math.sqrt((gx * gx));
              
                //find maxGradient
                if(maxGradient < g) 
                {
                    maxGradient = g;
                }
                edgeVals[i][j] = g;
            }
        }
        
        //normalize the values - set values
        for (int i = 1; i < img.getWidth() - 1; i++) 
        {
            for (int j = 1; j < img.getHeight() - 1; j++) 
            {
                int edgeColor = edgeVals[i][j];
                edgeColor = (int)(edgeColor * (255.0 / maxGradient));
                edgeColor = 0xff000000 | (edgeColor << 16) | (edgeColor << 8) | edgeColor;
                img.setRGB(i, j, edgeColor);
            }
        }
        
        return img;
    }
	
	//converting getRBG val to a value we can use
	public int convertPixelVal(int rgb) 
	{        
        return (int) (((rgb >> 16) & 0xff) + ((rgb >> 8) & 0xff) + ((rgb) & 0xff));
    }
}