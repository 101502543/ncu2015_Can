package event;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Screen extends JPanel {
	private Dimension pSize;
	private JLabel myLabel;
	
    public Screen(int width, int height, Color color) {
    	initUI(width, height, color);
    }
    
    void initUI(int width, int height, Color color){
    	pSize = new Dimension(width, height);
    	myLabel = new JLabel();
    	myLabel.setForeground(Color.white);
    	add(myLabel);
    	
    	setLayout(new GridBagLayout());
        setPreferredSize(pSize);
        setBackground(color);
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void setLabelName(String name){
    	myLabel.setText(name);
    }
    
    public void setScreenSize(int width, int height){
    	setPreferredSize(new Dimension(width, height));
    }
    
    public Dimension getScreenSize() {
        return pSize;
    }
}
