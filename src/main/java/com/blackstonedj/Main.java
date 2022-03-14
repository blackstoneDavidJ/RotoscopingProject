package com.blackstonedj;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.bytedeco.javacv.FFmpegFrameGrabber;

public class Main 
{
	public static void main(String[] args) 
	{
		String ImagePath = "resources/input/dials.png";
		String VideoPath = "resources/input/videos/2min.mp4";
		//FFmpegFrameGrabber g = new FFmpegFrameGrabber("resources/input/videos/2min.mp4");
		VideoFilter filter = new VideoFilter(VideoPath);
		try {
			filter.filter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageModder modder = new ImageModder(ImagePath);
		BufferedImage img = modder.getImage();
		GreyScale grey = new GreyScale();
		EdgeDetector edge = new SobelFilter();
		ImageDrawer draw = new ImageDrawer();
		NewGaussianBlur blur = new NewGaussianBlur(1);
		DoubleThreshold thresh = new DoubleThreshold(.4f, .2f);
		
		Palettization palette = new Palettization(8);
		Combiner combiner = new Combiner();
		CannyEdge canny = new CannyEdge(edge, grey, blur, thresh, true, true);

		//modder.save("combine", combiner.combineImages(canny.proccessor(img), palette.runner(new ImageModder(ImagePath).getImage())));
	}
}