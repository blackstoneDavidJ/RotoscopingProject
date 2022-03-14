package com.blackstonedj;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.Java2DFrameConverter;

public class VideoFilter 
{
	String videoPath;
	double frameRate;
	public VideoFilter(String path)
	{
		videoPath = path;
	}
	
	public void filter() throws IOException
	{
		Frame frame1;
		FFmpegFrameGrabber g = new FFmpegFrameGrabber(videoPath);
		Java2DFrameConverter f = new Java2DFrameConverter();
		GreyScale grey = new GreyScale();				
		ArrayList<BufferedImage> images = new ArrayList<>();
		try {
			g.start();			
			/*FFmpegFrameRecorder recorder = new FFmpegFrameRecorder("resources/twsnewMovie.mp4",g.getImageWidth(), g.getImageHeight());  			 
	        recorder.setFrameRate(g.getFrameRate()); 
	        recorder.setVideoCodec(g.getVideoCodec());
	        recorder.setVideoBitrate(g.getVideoBitrate());  
	        recorder.setFormat(g.getFormat());  
	        recorder.setVideoQuality(0); // maximum quality  
	        recorder.start();  */
	          	          
			System.out.println("array length: " +g.getLengthInFrames());
			
			while(g.grab() != null && g.grabImage() != null)
			{
				try 
				{
					frame1 = g.grabImage();
					images.add(grey.greyScale(f.getBufferedImage(frame1)));
					//recorder.record(frame1);
				} 
				
				catch (Exception e) 		
				{
					e.printStackTrace();
				}

			}
			
			g.close();
			
			//recorder.stop();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		for(int i = 0; i < images.size(); i++)
		{
			try 
			{
				ImageIO.write(images.get(i), "png", baos);				
			} 
			
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
		try 
		{
			byte[] video = baos.toByteArray();
			FileOutputStream out = new FileOutputStream(new File("resources/Video.mp4"));
			out.write(video);			
			out.close();
		} 
		
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
	}
}
