package cot.qin.sre;

public class DOMmok {
	public static DOMmok domMok = null;
	private int X = 13,Y = 13;
	public static DOMmok getInstance() {
		if (domMok == null)
			domMok = new DOMmok();
		return domMok;
	}

	public int[] getVirtualCharacterXY() {
		return new int[] { X, Y };
	}
	public void setXY(int x,int y) {
		X = x;
		Y = y;
	}
}
