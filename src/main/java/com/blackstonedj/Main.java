package com.blackstonedj;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main 
{
	public static void main(String[] args) 
	{
		String ImagePath = "resources/input/lizard.png";
		String VideoPath = "C:\\Users\\David\\eclipse-workspace\\imageProcessinhg\\resources\\input\\videos\\trees.mp4";
		ImageModder modder = new ImageModder(ImagePath);
		BufferedImage img = modder.getImage();
		GreyScale grey = new GreyScale();
		EdgeDetector edge = new SobelFilter();
		NewGaussianBlur blur = new NewGaussianBlur(1);
		DoubleThreshold thresh = new DoubleThreshold(.4f, .2f);
		
		Palettization palette = new Palettization(12);
		Combiner combiner = new Combiner();
		CannyEdge canny = new CannyEdge(edge, grey, blur, thresh, true, true);

		VideoFilter filter = new VideoFilter(VideoPath);
		try {
			filter.filter(canny, combiner, palette, ImagePath, 10);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//modder.save("lizard_canny", combiner.combineImages(canny.proccessor(img), palette.runner(new ImageModder(ImagePath).getImage())));
	
	}
}