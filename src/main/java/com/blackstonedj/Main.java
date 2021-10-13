package project;

import java.awt.image.BufferedImage;

public class Main 
{
	public static void main(String[] args) 
	{
		//get image
		String path = "resources/cartoon.png";
		ImageModder modder = new ImageModder(path);
		BufferedImage img = modder.getImage();
		/*
		int radius = 5;
		CannyEdge canny = new CannyEdge(new SobelFilter(), new GreyScale(), new GaussianBlur(radius));
		modder.save("cannyImage_stddev_1", canny.edgeDetector(img, 1));
		*/
		
		modder.save("sobel_color_test", new SobelFilter().edgeDetection(img));
	}
}
