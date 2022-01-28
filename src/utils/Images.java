package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images 
{
	public static BufferedImage johndoe_icon;
	
	public Images()
	{
		try
		{
			johndoe_icon = ImageIO.read(new File("./res/johndoe.jpg"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
