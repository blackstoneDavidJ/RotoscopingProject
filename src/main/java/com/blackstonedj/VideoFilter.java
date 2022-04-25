package com.blackstonedj;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.Java2DFrameConverter;

public class VideoFilter 
{
	private String path;
	private CannyEdge canny;
	private Pallettization pallette;
	private ImageModder m = new ImageModder();
	public VideoFilter(CannyEdge canny, Pallettization pallette, String path)
	{
		this.path = path;
		this.canny = canny;
		this.pallette = pallette;
	}

	public void filter( int frameBatch) throws IOException
	{
		FFmpegFrameGrabber g = new FFmpegFrameGrabber(path);
		FFmpegFrameRecorder recorder;
		int counter = 0;
		try {
			g.start();
			recorder = new FFmpegFrameRecorder("resources/city.mp4", g.getImageWidth(), g.getImageHeight());
			recorder.setVideoCodec(g.getVideoCodec());
			recorder.setVideoBitrate(g.getVideoBitrate());
			recorder.setFrameRate(g.getFrameRate());
			recorder.setFormat("mp4");
			recorder.start();
			int frameCount = (g.getLengthInFrames()-200);
			System.out.println("#Frames: " +frameCount);
			Java2DFrameConverter f = new Java2DFrameConverter();
			while(counter < frameCount)
			{
				try 
				{
					Frame[] imageBatch = new Frame[frameBatch];
					for(int i = 0; i < frameBatch; i++)
					{
						imageBatch[i] = g.grabImage();
					}
					
					BufferedImage[] p = getPalletteImages(imageBatch);
					BufferedImage[] c = getCannyImages(imageBatch);
					convertFromFrames(c,p, recorder);
					/*for(int e = 0; e < fr.length; e++)
					{
						recorder.record(fr[e]);
					}	*/
					
					counter += 10;
					System.out.println("frame: " +counter);
				} 
				
				catch (Exception e) 		
				{
					e.printStackTrace();
				}
			}
			
			recorder.stop();
			recorder.release();
			g.close();
		} 		
		catch (Exception e1) 
		{
			e1.printStackTrace();
		}  			
	}
	
	private BufferedImage[] getPalletteImages(Frame[] frames) throws IOException
	{
		Java2DFrameConverter f = new Java2DFrameConverter();
		BufferedImage[] palletteArray = new BufferedImage[frames.length];
		for(int i = 0; i < frames.length; i++)
		{
			palletteArray[i] = pallette.runner(f.convert(frames[i]));
			System.out.println("p"+i);
		}
		
		return palletteArray;
	}
	
	private BufferedImage[] getCannyImages(Frame[] frames) throws IOException
	{
		Java2DFrameConverter f = new Java2DFrameConverter();
		BufferedImage[] cannyArray = new BufferedImage[frames.length];
		for(int i = 0; i < frames.length; i++)
		{
			cannyArray[i] = this.canny.proccessor(f.convert(frames[i]));
			System.out.println("c"+i);
		}
		
		return cannyArray;
	}
	
	private void convertFromFrames(BufferedImage[] c, BufferedImage[] p, FFmpegFrameRecorder recorder) throws org.bytedeco.javacv.FrameRecorder.Exception
	{
		Java2DFrameConverter f = new Java2DFrameConverter();
		
		BufferedImage[] a = combineImages(c,p);
		Frame[] frames = new Frame[a.length];
		
		
		for(int i = 0; i < a.length; i++)
		{
			frames[i] = f.convert(a[i]);
			recorder.record(frames[i]);
		}
	}
	
	public BufferedImage[] combineImages(BufferedImage[] c, BufferedImage[] p)
	{
		BufferedImage[] comb = new BufferedImage[c.length];
		for(int y = 0; y< c.length; y++)
		{
			BufferedImage cannyImg = c[y];
			BufferedImage palletteImg = p[y];
			for(int i = 0; i < cannyImg.getWidth(); i++)
			{
				for(int j = 0; j < cannyImg.getHeight(); j++)
				{
					int pixelCol = cannyImg.getRGB(i,j);
					if(pixelCol == -16777216)
					{
						palletteImg.setRGB(i, j, Color.BLACK.getRGB());
					}
				}
			}
			
			comb[y] = palletteImg;
			//cannyImg.flush();
			//palletteImg.flush();
		}
		
		return comb;
	}
}