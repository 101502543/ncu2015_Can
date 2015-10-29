package event;

import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MyEventDispatcherForA extends EventDispatcher {
	private long eventBeginTime;
	private long eventEndTime;
	private String consoleSeparator= "---------------------------------------"; 
	
	private void printEvent(String description, ComponentEvent e){
		Screen eventScreen = (Screen)e.getSource();
		System.out.println("\""+eventScreen.getName()+": "+description+"\"");
	}
	@Override
	public void keyPressed(KeyEvent e) {
		printEvent("key pressed", e);
	}
	@Override
	public void keyReleased(KeyEvent e) {
		printEvent("key released", e);
		System.out.println(consoleSeparator);
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		printEvent("mouse drag", e);		
	}	
	@Override
	public void mousePressed(MouseEvent e) {
		printEvent("mouse down", e);
		eventBeginTime = System.currentTimeMillis(); 
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		printEvent("mouse release", e);
		eventEndTime = System.currentTimeMillis();
	}	
	@Override
	public void mouseClicked(MouseEvent e) {
		long eventExeTime =  eventEndTime - eventBeginTime;
		if(eventExeTime < 3000){
			printEvent("mouse click", e);
			System.out.println(consoleSeparator);
			Screen eventScreen = (Screen)e.getComponent();
			eventScreen.grabFocus();
		}
		else{
			System.out.println("No click¡Gpress for "+eventExeTime/1000+" seconds.\n"+consoleSeparator);			
		}
	}
}
