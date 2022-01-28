package utils;

import java.awt.Font;

public class Fonts
{
	public static Font defaultCardSmall, defaultCardMedium, defaultCardDetails;
	
	public Fonts()
	{
		defaultCardSmall = new Font("Times New Roman", Font.ITALIC, 18);
		defaultCardMedium = new Font("Times New Roman", Font.PLAIN, 30);
		defaultCardDetails = new Font("Arial", Font.BOLD, 15);
	}
}
