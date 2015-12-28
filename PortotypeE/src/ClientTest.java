import javax.swing.JFrame;

import cot.qin.sre.DOMmok;
import cot.qin.sre.SceneRenderEngine;
import cot.qin.sre.ThePanel;

public class ClientTest {
	SceneRenderEngine sre = SceneRenderEngine.getInstance();
	DOMmok dom = DOMmok.getInstance();
	ThePanel panel = ThePanel.getInstance();
	JFrame frame = new JFrame();
	int[][] testmove = { { 1000, 1500 }, { 1000, 1450 }, { 1000, 1400 }, { 1000, 1350 }, { 1000, 1300 }, { 1000, 1250 },
			{ 1000, 1200 }, { 1000, 1150 }, { 1000, 1100 }, { 1000, 1050 }, { 1000, 1000 }, { 1050, 1000 },
			{ 1100, 1000 }, { 1150, 1000 }, { 1200, 1000 }, { 1250, 1000 }, { 1300, 1000 }, { 1350, 1000 },
			{ 1400, 1000 }, { 1450, 1000 }, { 1500, 1000 } };

	public ClientTest() {
		// TODO Auto-generated constructor stub
		frame.add(panel);
		panel.setFocusable(true);
		frame.setTitle("Clash of Tower");
		frame.setSize(1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
//		for (int[] is : testmove) {
//			panel.moveTest(is);
//			try {
//				Thread.sleep(200);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}

	public static void main(String[] args) {
		ClientTest test = new ClientTest();
	}
}
