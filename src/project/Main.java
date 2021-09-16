package project;

import java.awt.image.BufferedImage;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args) 
	{
		//get image
		String path = "resources/cartoon.png";
		ImageModder modder = new ImageModder(path);
		BufferedImage img = modder.getImage();
		
		//input for stDev and radius
		/*Scanner scan = new Scanner(System.in);
		System.out.println("Please enter the stdDev for the image: ");
		double stDev = scan.nextDouble();
		int radius = 11;*/
		
		//GaussianBlur blur = new GaussianBlur(img, radius);
		WeightedMethod wgt = new WeightedMethod();
		SoberFilter sob = new SoberFilter();
		//generate weightmatrix, use matrix on image
		//BufferedImage gaussianImg = blur.gaussianFilter(stDev);
		BufferedImage greyImage = wgt.greyScale(img);
		//modder.save("grey", greyImage);
		modder.save(("edgetest_grey"), sob.edgeDetection(greyImage));
		//scan.close();
	}
}