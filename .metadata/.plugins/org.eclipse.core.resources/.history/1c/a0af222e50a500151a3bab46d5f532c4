package cot.qin.sre;

import static org.junit.Assert.assertEquals;

import javax.swing.JFrame;

import org.junit.Test;

public class SRETest extends JFrame {

	@Test
	public void testRenderScene() {
		//setup
		SceneRenderEngine sre = SceneRenderEngine.getInstance();
		DOMmok dom = DOMmok.getInstance();
		add(sre);
		sre.setFocusable(true);

		setTitle("Clash of Tower"); 
		setSize(1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setVisible(true);
		//assert
		assertEquals(false, sre.outOfBoundXY);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//exercise
		sre.renderScene();
		//assert
		assertEquals(false, sre.outOfBoundXY);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//exercise
		dom.setXY(10, 29);
		sre.renderScene();
		//assert
		assertEquals(false, sre.outOfBoundXY);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//exercise
		dom.setXY(29, 10);
		sre.renderScene();
		//assert
		assertEquals(false, sre.outOfBoundXY);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//exercise
		dom.setXY(40, 30);
		sre.renderScene();
		//assert
		assertEquals(true, sre.outOfBoundXY);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
