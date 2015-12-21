import javax.swing.JFrame;

import cot.qin.sdm.ThePanel;
import cot.qin.sre.DOMmok;
import cot.qin.sre.SceneRenderEngine;

public class ClientTest {
	SceneRenderEngine sre = SceneRenderEngine.getInstance();
	DOMmok dom = DOMmok.getInstance();
	ThePanel panel = ThePanel.getInstance();
	JFrame frame = new JFrame();
	int[][] testmove = { { 110, 190 }, { 110, 180 }, { 110, 170 },
			{ 110, 160 }, { 110, 150 }, { 110, 140 }, { 110, 130 },
			{ 110, 120 }, { 110, 110 }, { 110, 100 }, { 110, 90 }, { 110, 80 },
			{ 110, 70 }, { 110, 60 }, { 110, 50 }, { 120, 50 }, { 130, 50 },
			{ 140, 50 }, { 150, 50 }, { 160, 50 }, { 170, 50 } };

	public ClientTest() {
		// TODO Auto-generated constructor stub
		frame.add(panel);
		panel.setFocusable(true);
		frame.setTitle("Clash of Tower");
		frame.setSize(1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		for (int[] is : testmove) {
			panel.moveTest(is);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		ClientTest test = new ClientTest();
	}
}
