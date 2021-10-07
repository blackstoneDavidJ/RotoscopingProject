package com.blackstonedj;

import java.awt.image.BufferedImage;

public class Main 
{
	public static void main(String[] args) 
	{
		//get image
		String path = "resources/blurtest.png";
		ImageModder modder = new ImageModder(path);
		BufferedImage img = modder.getImage();

		SobelFilter sob = new SobelFilter();
		GreyScale wght = new GreyScale();
		GaussianBlur blur = new GaussianBlur(img, 10);
		//modder.save("engine_grey_scale", wght.greyScale(img));
		modder.save("gausstest", blur.gaussianFilter(10));
		//modder.save("pokemon_radius-10_stdev-10", blur.gaussianFilter(10));
	}
}
