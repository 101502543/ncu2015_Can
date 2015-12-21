package cot.qin.sdm;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import cot.qin.sre.SceneRenderEngine;

public class ThePanel extends JPanel {
	SceneRenderEngine sre = SceneRenderEngine.getInstance();
	SceneDataModule sdm = SceneDataModule.getInstance();

	public static ThePanel panel = null;

	public static ThePanel getInstance() {
		if (panel == null)
			panel = new ThePanel();
		return panel;
	}

	public ThePanel() {
		// TODO Auto-generated constructor stub
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					sre.setZeroY(-25);
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					sre.setZeroY(+25);
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					sre.setZeroX(-25);
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					sre.setZeroX(+25);
				}
				repaint();
			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		int[] information = sre.getWindowInf();
		// int[] information =
		// {zeroX,zeroY,X,Y,windowSize,nextColumn[0],nextColumn[1],nextRow[0],nextRow[1],imageSizeX,imageSizeY};
		int theX = information[0], theY = information[1];
		for (int y = information[3]; y < information[3] + information[4]; y++) {
			for (int x = information[2]; x < information[2] + information[4]; x++) {
				ImageIcon test = new ImageIcon(sdm.getMap().getBlockImage(x, y));
				Image img = test.getImage();
				g.drawImage(img, theX + (x - information[2]) * information[5],
						theY + (x - information[2]) * information[6],
						information[9], information[10], this);
			}
			theX = information[0] + (y - information[3]) * information[7];
			theY = information[1] + (y - information[3]) * information[8];
		}

		// move test
		ImageIcon test = new ImageIcon(sdm.getMap().getBlockImage(0, 0));
		Image img = test.getImage();
		g.drawImage(img, testX, testY, information[9], information[10], this);

	}

	int testX, testY;

	public void moveTest(int[] xy) {
		xy = sre.changeXY(xy);
		testX = xy[0];
		testY = xy[1];
		repaint();
	}
}
