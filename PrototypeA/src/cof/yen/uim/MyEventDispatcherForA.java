package cof.yen.uim;

import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MyEventDispatcherForA extends EventDispatcher {
	private long eventBeginTime;
	private long eventEndTime;
	private String consoleSeparator= "---------------------------------------"; 
	
	private final int CODE_GET = KeyEvent.VK_SPACE;
	private final int CODE_TURNEAST = KeyEvent.VK_RIGHT;
	private final int CODE_TURNSOUTH = KeyEvent.VK_DOWN;
	private final int CODE_TURNWEST = KeyEvent.VK_LEFT;
	private final int CODE_TURNNORTH = KeyEvent.VK_UP;
	
	private void printEvent(String description, ComponentEvent e){
		Screen eventScreen = (Screen)e.getSource();
		System.out.println("\""+eventScreen.getName()+": "+description+"\"");
	}
	@Override
	public void keyPressed(KeyEvent e) {
		//printEvent("key pressed", e);
		switch(e.getKeyCode()){
		case CODE_TURNEAST:
			TestDriver.getInstance().moveEast();
			break;
		case CODE_TURNSOUTH:
			TestDriver.getInstance().moveSouth();
			break;
		case CODE_TURNWEST:
			TestDriver.getInstance().moveWest();
			break;
		case CODE_TURNNORTH:
			TestDriver.getInstance().moveNorth();
			break;
		case CODE_GET:
			TestDriver.getInstance().grab();
			break;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		//printEvent("key released", e);
		System.out.println();
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		//printEvent("mouse drag", e);		
	}	
	@Override
	public void mousePressed(MouseEvent e) {
		//printEvent("mouse down", e);
		eventBeginTime = System.currentTimeMillis(); 
		
		switch(((Screen)e.getSource()).getName()){
		case "screen 7":
			TestDriver.getInstance().moveEast();
			break;
		case "screen 6":
			TestDriver.getInstance().moveSouth();
			break;
		case "screen 5":
			TestDriver.getInstance().moveWest();
			break;
		case "screen 3":
			TestDriver.getInstance().moveNorth();
			break;
		case "screen 8":
			TestDriver.getInstance().grab();
			break;
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		//printEvent("mouse release", e);
		eventEndTime = System.currentTimeMillis();
	}	
	@Override
	public void mouseClicked(MouseEvent e) {
		long eventExeTime =  eventEndTime - eventBeginTime;
		if(eventExeTime < 3000){
			//printEvent("mouse click", e);
			System.out.println();
			Screen eventScreen = (Screen)e.getComponent();
			eventScreen.grabFocus();
		}
		else{
			System.out.println("No click¡Gpress for "+eventExeTime/1000+" seconds.\n"+consoleSeparator);			
		}
	}
}
