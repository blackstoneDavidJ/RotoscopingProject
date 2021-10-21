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
		modder.save("cannyImage_engine", canny.edgeDetector(img, 1));
		*/
		/*
		GreyScale grey = new GreyScale();
		GaussianBlur blur = new GaussianBlur(10);
		blur.setStddev(1);
		modder.save("sobel_cartoon", new SobelFilter().edgeDetection(blur.gaussianFilter(grey.greyScale(img)),null));
		*/
		
		GaussianBlur blur = new GaussianBlur(5, 5);
		modder.save("color_sobel", new SobelFilter().edgeDetection(blur.gaussianFilter(img),true));

	}
}