import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;


public class prototypeD_main  extends Canvas implements Runnable 
{
	private Image           	offImage;
	private Graphics        	offGrfx;
	private Thread         		animate;
	private MediaTracker    	tracker;
	private CharacterWalking	cw;
	
	private int             	delay = 83; // 12 fps
	
	private JFrame 				frame;
	private int 				frameSizeX = 300, 
								frameSizeY = 300;
	
	private int					direction;
	
	private Point 				pos = new Point(100, 90); // image position
	
	public static final int 	WK_UP = 4,
          						WK_DOWN = 1,
          						WK_LEFT = 2,
								WK_RIGHT = 3,
								IDLE = 0;
								
	public static void main(String[] args)
	{
		new prototypeD_main().start();
	}

	public prototypeD_main()
	{
		setMinimumSize( new Dimension(frameSizeX, frameSizeY) );
		setMaximumSize( new Dimension(frameSizeX, frameSizeY) );
		setPreferredSize( new Dimension(frameSizeX, frameSizeY) );
		
		frame = new JFrame("PrototypeD: Character Animation");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		frame.add(this, BorderLayout.CENTER);
		frame.pack();//Size the frame.
		
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		   
		frame.addKeyListener(new KeyListener() 
		{
			public void keyTyped(KeyEvent e) 
			{
				// TODO Auto-generated method stub
			}

			public void keyReleased(KeyEvent e) 
			{
				// TODO Auto-generated method stub
				switch (e.getKeyCode()) 
				{
		        	case KeyEvent.VK_DOWN:
		        		direction = WK_DOWN + 4;
		        		break;
		        	case KeyEvent.VK_UP:
						direction = WK_UP + 4;
						break;
		        	case KeyEvent.VK_RIGHT: 
		        		direction = WK_RIGHT + 4;
		        		break;
		        	case KeyEvent.VK_LEFT: 
		        		direction = WK_LEFT + 4;
		        		break;
				}
			}

			public void keyPressed(KeyEvent e) 
			{
				switch (e.getKeyCode()) 
				{
					case KeyEvent.VK_DOWN:
		        		direction = WK_DOWN;
		        		break;
		        	case KeyEvent.VK_UP:
						direction = WK_UP;
						break;
		        	case KeyEvent.VK_RIGHT: 
		        		direction = WK_RIGHT;
		        		break;
		        	case KeyEvent.VK_LEFT: 
		        		direction = WK_LEFT;
		        		break;
		        	case KeyEvent.VK_SPACE: 
		        		direction = IDLE;
		        		break;
				}
			}
		});
		
		init();
	}
	
	public void init() 
	{
		// Load and track the images
		tracker = new MediaTracker(this);
		
		CharacterWalking.initResources(tracker, 0);
	}
	
	public void start() 
	{
		if (animate == null) 
		{
			animate = new Thread(this);
			animate.start();
		}
	}

	public void stop() 
	{
		if (animate != null) 
		{
			animate = null;
		}
	}

	public void run() 
	{
		try 
		{
			tracker.waitForID(0);
		}
		catch (InterruptedException e) 
		{
			return;
		}
	
		// Create and add CharacterWalking
		cw = new CharacterWalking(this, pos);
	
		// Update everything
		long t = System.currentTimeMillis();
		while (Thread.currentThread() == animate) 
		{
			//Set Character Direction
			cw.setDirection(direction-1);
			
			cw.update();
			repaint();
	
			try 
			{
				t += delay;
				Thread.sleep(Math.max(0, t - System.currentTimeMillis()));
			}
			catch (InterruptedException e) 
			{
				break;
			}
		}
	}
	
	public void update(Graphics g) 
	{
		// Create the offscreen graphics context
		if (offGrfx == null) 
		{
			offImage = createImage(getSize().width, getSize().height);
			offGrfx = offImage.getGraphics();
		}
		
		// Draw background to clear
		offGrfx.setColor(getBackground());
		offGrfx.fillRect(0, 0, getWidth(), getHeight());
		
		// Draw the sprites
		cw.draw(offGrfx);
		// Draw the image onto the screen
		g.drawImage(offImage, 0, 0, null);
	}
}


