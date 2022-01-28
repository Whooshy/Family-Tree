package utils;

import java.awt.Font;

public class Fonts
{
	public static Font defaultCardSmall, defaultCardMedium, defaultCardDetails, dropdown;
	
	public Fonts()
	{
		defaultCardSmall = new Font("Times New Roman", Font.ITALIC, 18);
		defaultCardMedium = new Font("Times New Roman", Font.PLAIN, 30);
		defaultCardDetails = new Font("Arial", Font.BOLD, 15);
		
		dropdown = new Font("Arial", Font.PLAIN, 20);
	}
}
