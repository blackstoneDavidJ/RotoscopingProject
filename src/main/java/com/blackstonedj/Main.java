package com.blackstonedj;

//import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main 
{
	public static void main(String[] args) 
	{
		//String ImagePath = "resources/input/lizard.png";
		String VideoPath = "C:/Users/daveb/Desktop/SProject/blackstonedj/resources/input/videos/cars.mp4";
		//ImageModder modder = new ImageModder(ImagePath);
		//BufferedImage img = modder.getImage();
		GreyScale grey = new GreyScale();
		EdgeDetector edge = new SobelFilter();
		GaussianBlur blur = new GaussianBlur(1);
		DoubleThreshold thresh = new DoubleThreshold(.4f, .2f);
		Pallettization palette = new Pallettization(6);
		//Combiner combiner = new Combiner();
		CannyEdge canny = new CannyEdge(edge, grey, blur, thresh, true, true);
		VideoFilter filter = new VideoFilter(canny, palette, VideoPath);
		
		int batchAmount = 10;
		try 
		{
			filter.filter(batchAmount);
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}