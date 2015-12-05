package cof.yen.cdc;

import java.awt.List;

public abstract class Utility {
	abstract void addVirtualCharacter(int clientno);
	//abstract void addItem(String name, int index, boolean shared,int x, int y);
	//abstract void updateDirection(int clientno, int MoveCode) ;
	//abstract void getItem(int clientno);
	//abstract List getUpdateInfo();
	abstract void startUpdatingThread();
}
