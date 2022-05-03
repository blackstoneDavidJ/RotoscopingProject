package com.blackstonedj;

//import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main 
{
	public static void main(String[] args) 
	{
		String VideoPath = args[0];
		GreyScale grey = new GreyScale();
		EdgeDetector edge = new SobelFilter();
		GaussianBlur blur = new GaussianBlur(1);
		DoubleThreshold thresh = new DoubleThreshold(.5f, .2f);
		Pallettization palette = new Pallettization(20);
		CannyEdge canny = new CannyEdge(edge, grey, blur, thresh, true, true);
		VideoFilter filter = new VideoFilter(canny, palette, VideoPath, args[1]);
		try 
		{
			filter.filter();
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}