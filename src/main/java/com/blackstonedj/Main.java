package com.blackstonedj;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.bytedeco.javacv.FFmpegFrameGrabber;

public class Main 
{
	public static void main(String[] args) 
	{
		String ImagePath = "resources/input/lizard.png";
		String VideoPath = "resources/input/videos/cars.mp4";
		ImageModder modder = new ImageModder(ImagePath);
		BufferedImage img = modder.getImage();
		GreyScale grey = new GreyScale();
		EdgeDetector edge = new SobelFilter();
		NewGaussianBlur blur = new NewGaussianBlur(1);
		DoubleThreshold thresh = new DoubleThreshold(.4f, .2f);
		
		Palettization palette = new Palettization(16);
		Combiner combiner = new Combiner();
		CannyEdge canny = new CannyEdge(edge, grey, blur, thresh, true, true);

		VideoFilter filter = new VideoFilter(VideoPath);
		try {
			filter.filter(canny, combiner, palette, ImagePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//modder.save("lizard_canny", combiner.combineImages(canny.proccessor(img), palette.runner(new ImageModder(ImagePath).getImage())));
	
	}
}