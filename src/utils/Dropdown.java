package utils;

import java.awt.Color;
import java.awt.Graphics2D;

import main.Main;

public class Dropdown
{
	private String[] options;
	private int[] separators;
	
	public boolean isActive;
	public int x, y;
	
	public Dropdown()
	{
		isActive = false;
		x = 0;
		y = 0;
	}
	
	public void setConfig(String[] options, int[] separators)
	{
		this.options = options;
		this.separators = separators;
	}
	
	public void draw(Graphics2D g)
	{
		if(Main.rmb)
		{
			if(!isActive)
			{
				x = (int) Main.translatedMouseX;
				y = (int) Main.translatedMouseY;
			}
			isActive = true;
		}
		
		if(Main.lmb)
		{
			isActive = false;
		}
		
		if(isActive)
		{
			g.setColor(Color.DARK_GRAY);
			g.fillRect(x, y, 200, options.length * 18 + 10);
			
			g.setColor(Color.WHITE);
			g.setFont(Fonts.dropdown);
			
			for(int i = 0; i < options.length; i++)
				g.drawString(options[i], x + 5, y + i * 20 + 20);
			
			for(int i = 0; i < separators.length; i++)
				g.drawLine(x, y + ((i + 1) * 20) + 2, x + 200, y + ((i + 1) * 20) + 2);
		}
	}
}
