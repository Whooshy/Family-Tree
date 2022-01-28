package main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import utils.Date;
import utils.Fonts;
import utils.Images;

public class Main implements Runnable, KeyListener, MouseListener, MouseMotionListener
{
	private JFrame frame;
	private Canvas canvas;
	
	private Thread thread;
	
	private boolean isRunning = false;
	
	public static int width, height;
	public static boolean lmb, rmb, lmbClicked, rmbClicked;
	public static float cameraX, cameraY, mouseX, mouseY, translatedMouseX, translatedMouseY;
	
	private static boolean camUp, camDown, camLeft, camRight;
	
	private boolean lmbR, rmbR;
	
	int frameCount, fps;
	
	Card exampleCard;
	
	Fonts fonts;
	Images imgs;
	
	public Main()
	{
		//Window creation.
		frame = new JFrame("Family Tree Prototype");
		
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		
		canvas = new Canvas();
		
		width = 1280;
		height = 720;
		
		canvas.setPreferredSize(new Dimension(1280, 720));
		
		canvas.addKeyListener(this);
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);
		
		frame.add(canvas);
		frame.pack();
		frame.setLocationRelativeTo(null);
		
		fonts = new Fonts();
		imgs = new Images();
		
		exampleCard = new Card("John Doe", 0, 0, new Date(1, 1, 1990), new Date(17, 6, 2019), false);
		exampleCard.setIcon(Images.johndoe_icon);
		
		frameCount = 0;
		fps = 0;
		
		start();
	}
	
	public static void main(String[] args)
	{
		//Entry point.
		new Main();
	}
	
	//Initiates the main thread of this program.
	public synchronized void start()
	{
		thread = new Thread(this);
		thread.start();
		
		isRunning = true;
	}
	
	//Handles the destruction of the main thread once the program is stopped.
	public synchronized void stop()
	{
		try
		{
			thread.join();
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		long timer = System.currentTimeMillis();
		long frameTimer = System.nanoTime();
		while(isRunning)
		{
			try
			{
				thread.sleep(5);
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			frameCount++;
			update();
			
			width = frame.getWidth();
			height = frame.getHeight();
						
			if(System.currentTimeMillis() - timer >= 1000)
			{
				fps = frameCount;
				frameCount = 0;
				System.out.println("FPS: " + fps);
				timer += 1000;
			}
		}
		stop();
	}
	
	public void update()
	{
		BufferStrategy bs = canvas.getBufferStrategy();
		if(bs == null)
		{
			canvas.createBufferStrategy(3);
			return;
		}
		Graphics graphics = bs.getDrawGraphics();
		Graphics2D g = (Graphics2D) graphics;
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		
		if(camUp) cameraY -= 3;
		if(camDown) cameraY += 3;
		if(camLeft) cameraX -= 3;
		if(camRight) cameraX += 3;
		
		g.translate(-cameraX + width / 2, -cameraY + height / 2);
		
		g.setColor(Color.LIGHT_GRAY);
		
		int gridStartX = (int) (cameraX - width / 2) / 100 - 1;
		int gridStartY = (int) (cameraY - height / 2) / 100 - 1;
		
		int gridEndX = gridStartX + width / 100 + 2;
		int gridEndY = gridStartY + height / 100 + 2;
		
		translatedMouseX = mouseX + cameraX - width / 2;
		translatedMouseY = mouseY + cameraY - height / 2;
				
		for(int x = gridStartX; x < gridEndX; x++)
		for(int y = gridStartY; y < gridEndY; y++)
		{
			g.drawRect(x * 100, y * 100, 100, 100);
		}
		
		if(lmbR && !lmb)
		{
			lmbClicked = true;
			lmbR = false;
		}
		else
		{
			lmbClicked = false;
		}
		
		if(rmbR && !rmb)
		{
			rmbClicked = true;
			rmbR = false;
		}
		else
		{
			rmbClicked = false;
		}
		
		exampleCard.draw(g);
		
		g.dispose();
		bs.show();
	}

	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e)
	{
		int k = e.getKeyCode();
		
		if(k == e.VK_W)
		{
			camUp = true;
		}
		if(k == e.VK_S)
		{
			camDown = true;
		}
		if(k == e.VK_A)
		{
			camLeft = true;
		}
		if(k == e.VK_D)
		{
			camRight = true;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		int k = e.getKeyCode();
		
		if(k == e.VK_W)
		{
			camUp = false;
		}
		if(k == e.VK_S)
		{
			camDown = false;
		}
		if(k == e.VK_A)
		{
			camLeft = false;
		}
		if(k == e.VK_D)
		{
			camRight = false;
		}
	}

	public void mouseDragged(MouseEvent e)
	{
		mouseX = e.getX();
		mouseY = e.getY();
	}

	public void mouseMoved(MouseEvent e)
	{
		mouseX = e.getX();
		mouseY = e.getY();
	}

	public void mouseClicked(MouseEvent e) {}

	public void mousePressed(MouseEvent e) 
	{
		if(SwingUtilities.isLeftMouseButton(e))
		{
			lmb = true;
			lmbR = true;
		}
		if(SwingUtilities.isRightMouseButton(e))
		{
			rmb = true;
			rmbR = true;
		}
	}
	
	public void mouseReleased(MouseEvent e)
	{
		if(SwingUtilities.isLeftMouseButton(e))
		{
			lmb = false;
		}
		if(SwingUtilities.isRightMouseButton(e))
		{
			rmb = false;
		}
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
