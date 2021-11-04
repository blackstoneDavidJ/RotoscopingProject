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
		
		GaussianBlur blur = new GaussianBlur(1,1);
		modder.save("ccir", new SobelFilter().edgeDetection(img,false,true));
		
		
		//GreyScale grey = new GreyScale();
		//img = grey.greyScale(img);
		//modder.save("ccir", new SobelFilter().edgeDetection(img, true,false));
		
		//Circle cl = new Circle(5000);
		//modder.save("cl", cl.makeCircle(400, 400));
		//Square sq = new Square(250);
		//modder.save("square", sq.makeSquare(500,500));
		
		//System.out.println(Math.toDegrees(Math.atan2(120,308)));
	}
}