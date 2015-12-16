package event;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class TestWindow extends JFrame {
	private int separatorSize = 5;
	private int screenNum = 9;
	private int[][] screenSize = {{800, 455},
								  {80 , 80 },
								  {80 , 80 },
								  {80 , 80 },
								  {80 , 80 },
								  {80 , 80 },
								  {80 , 80 },
								  {300, 160},
								  {250, 160}}; 
	private Color[] screenColor = {Color.red, Color.black, Color.blue, Color.CYAN, Color.DARK_GRAY, Color.pink, Color.green, Color.magenta, Color.ORANGE};
	private int[][] screenLocation ; 
	private ScreenFactory screenFactory;
	private Screen[] screenSet;
	private EventDispatcher eventDispatcher;
	
	public TestWindow(){
		super("Clash of Tower");
		initSetting();
		initComponent();
	}	
	
	private void initSetting(){
		addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});        
		setLayout(null);
        setSize(820, 660);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        setResizable(false);
	}
	
	private void initComponent(){
		initScreenLocation();
		screenFactory = new ScreenFactory();
		eventDispatcher = new MyEventDispatcherForA();
		screenSet = screenFactory.createScreen(screenNum, screenSize, screenColor);
		for(int i=0; i<screenNum; i++){
			int tmpLocationX = screenLocation[i][0];
			int tmpLocationY = screenLocation[i][1];
			int tmpScreenWidth = screenSize[i][0];
			int tmpScreenHeight = screenSize[i][1];
			
			screenSet[i].setBounds(tmpLocationX, tmpLocationY, tmpScreenWidth, tmpScreenHeight);
			add(screenSet[i]);
			screenSet[i].addMouseListener(eventDispatcher);
			screenSet[i].addMouseMotionListener(eventDispatcher);
			screenSet[i].addKeyListener(eventDispatcher);
		}
	}
	
	private void initScreenLocation(){
		screenLocation = new int[][]{{separatorSize			, separatorSize		}, 
								    {widthOf(3, 8, 9)		, heightOf(2, 1)	},
								    {widthOf(3, 8, 9, 2)	, heightOf(2, 1)	},
								    {widthOf(3, 8, 9, 2, 3)	, heightOf(2, 1)	},
								    {widthOf(3, 8, 9)		, heightOf(2, 1, 2)	},
								    {widthOf(3, 8, 9, 5)	, heightOf(2, 1, 3)	},
								    {widthOf(3, 8, 9, 5, 6)	, heightOf(2, 1, 4)	},
								    {widthOf(2, 9)			, heightOf(2, 1)	},
								    {separatorSize			, heightOf(2, 1)	}}; 
	}
	
	private int widthOf(int numOfSeparator, int... screenNumber){
		int screenWidthSum = 0;
		for(int index:screenNumber){
			screenWidthSum += screenSize[index-1][0];
		}
		return (numOfSeparator*separatorSize) + screenWidthSum;
	}
	private int heightOf(int numOfSeparator, int... screenNumber){
		int screenHeightSum = 0;
		for(int index:screenNumber){
			screenHeightSum += screenSize[index-1][1];
		}
		return (numOfSeparator*separatorSize) + screenHeightSum;
	}
	
	public static void main(String args[]){
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TestWindow().setVisible(true);
            }
        });
	}
}
