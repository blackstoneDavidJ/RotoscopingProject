package com.blackstonedj;

public class Main {

	public static void main(String[] args) 
	{
		String path = "resources/apples.png";
		
		AverageMethod avg = new AverageMethod(path);
		WeightedMethod wght = new WeightedMethod(path);
		
		avg.greyScale();
		wght.greyScale();
		
	}

}
