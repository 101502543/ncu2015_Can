package event;

import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

public class EventDispatcher implements MouseInputListener, KeyListener {
	
	private void printEvent(String description, ComponentEvent e){
		Screen eventScreen = (Screen)e.getSource();
		System.out.println("\""+eventScreen.getName()+": "+description+"\"");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		printEvent("keyboard input", e);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		//printEvent("key pressed", e);
	}
	@Override
	public void keyReleased(KeyEvent e) {
		//printEvent("key released", e);
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		printEvent("mouse click", e);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		printEvent("mouse down", e);
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		printEvent("mouse drag", e);		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		printEvent("mouse release", e);
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		//printEvent("mouse entered", e);
	}
	@Override
	public void mouseExited(MouseEvent e) {
		//printEvent("mouse exited", e);
	}
	@Override
	public void mouseMoved(MouseEvent e) {
	}
}
