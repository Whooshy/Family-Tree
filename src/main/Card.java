package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import utils.Date;
import utils.Fonts;

public class Card
{
	public String name;
	public int x, y;
	public Date birthDate, deathDate;
	public boolean isAlive, enlarged;
	
	private BufferedImage icon;
	private float prevX, prevY;
	private int offsetX, offsetY;
	
	public Card(String name, int x, int y, Date birthDate, Date deathDate, boolean isAlive)
	{
		this.name = name;
		this.x = x;
		this.y = y;
		this.birthDate = birthDate;
		this.deathDate = deathDate;
		this.isAlive = isAlive;
		
		icon = null;
	}
	
	public boolean mouseHovering()
	{
		return (Main.translatedMouseX >= x - 100 && Main.translatedMouseX <= x + 100 && Main.translatedMouseY >= y - 125 && Main.translatedMouseY <= y + 125);
	}
	
	public boolean mousePressing()
	{
		return Main.lmb && mouseHovering();
	}
	
	public boolean mouseClicking()
	{
		return Main.lmbClicked && mouseHovering();
	}
	
	public void draw(Graphics2D g)
	{		
		if(!mousePressing() && !mouseClicking())
		{
			prevX = x;
			prevY = y;
			offsetX = 0;
			offsetY = 0;
		}
		
		if(mouseClicking() && prevX == x && prevY == y) enlarged = true;
		else if(mousePressing())
		{
			if(offsetX == 0 && offsetY == 0)
			{
				offsetX = x - (int) Main.translatedMouseX;
				offsetY = y - (int) Main.translatedMouseY;
			}
			
			x = (int) Main.translatedMouseX + offsetX;
			y = (int) Main.translatedMouseY + offsetY;
		}
		else if(Main.lmbClicked && !mouseHovering()) enlarged = false;
//		testing



		if(enlarged)
		{
			g.setColor(Color.DARK_GRAY);
			g.fillRect(x - 125, y - 200, 250, 400);
			
			g.setColor(Color.WHITE);
			g.setFont(Fonts.defaultCardMedium);
			
			int nameLength = (int) g.getFontMetrics(Fonts.defaultCardMedium).getStringBounds(name, g).getWidth();
			g.drawString(name, x - nameLength / 2, y + 25);
			
			String birthDateStr = "Born on " + birthDate.getFormal();
			
			g.setFont(Fonts.defaultCardDetails);
			g.drawString(birthDateStr, x - 120, y + 50);
			
			if(!isAlive)
			{
				String deathDateStr = "Died on " + deathDate.getFormal();
				
				g.setFont(Fonts.defaultCardDetails);
				g.drawString(deathDateStr, x - 120, y + 68);
			}
			
			if(icon != null)
			{
				g.drawImage(icon, x - 95, y - 195, 190, 190, null);
			}
		}
		else
		{
			g.setColor(Color.DARK_GRAY);
			g.fillRect(x - 100, y - 125, 200, 250);
			
			g.setColor(Color.WHITE);
			g.setFont(Fonts.defaultCardMedium);
			
			int nameLength = (int) g.getFontMetrics(Fonts.defaultCardMedium).getStringBounds(name, g).getWidth();
			g.drawString(name, x - nameLength / 2, y + 100);
			
			g.setFont(Fonts.defaultCardSmall);
			
			String lifespan = birthDate.year + "-";
			if(!isAlive) lifespan += deathDate.year;
			
			int lifespanLength = (int) g.getFontMetrics(Fonts.defaultCardSmall).getStringBounds(name, g).getWidth();
			g.drawString(lifespan, x - lifespanLength / 2, y + 118);
			
			if(icon != null)
			{
				g.drawImage(icon, x - 95, y - 120, 190, 190, null);
			}
		}
	}
	
	public BufferedImage getIcon()
	{
		return icon;
	}
	
	public void setIcon(BufferedImage img)
	{
		icon = img;
	}
}
