package cof.can.udpbc;

import java.util.Vector;
import java.util.Random;

public class Centralized_Data_Center {

	public Vector updateInfo = new Vector();
	public Vector updateInfo1 = new Vector();
	public Vector updateInfo2 = new Vector();
	Random ran = new Random();

	public Vector getUpdateInfo1() {
		int i = 1;
		int k = 1;
		updateInfo1.add("ADD,ItemName," + i + ",OwnerName," + k);
		updateInfo = updateInfo1;
		return updateInfo;
	}

	public Vector getUpdateInfo2() {

		int j = 1;
		int z = 1;
		int speed = 10;
		int locationX = 1;
		int locationY = 1;
		String dir;
		dir = "N";
		updateInfo2.add("UPDATE,ItemName," + j + ",OwnerName," + z + "," + dir
				+ "," + speed + "," + locationX + "," + locationY);

		updateInfo = updateInfo2;
		return updateInfo;
	}

	// This is the random module.
	// public Vector getUpdateInfo() {
	// int count = ran.nextInt(4) + 1;
	// if (count == 1) {
	// int i = ran.nextInt(10) + 1;
	// int k = ran.nextInt(4)+1;
	// updateInfo1.add("ADD,ItemName," + i + ",OwnerName,"+k);
	// updateInfo= updateInfo1;
	// }
	// else{
	// int j = ran.nextInt(10) + 1;
	// int z = ran.nextInt(4) + 1;
	// int speed = ran.nextInt(50) + 10;
	// int locationX = ran.nextInt(5) + 1;
	// int locationY = ran.nextInt(5) + 1;
	// String dir;
	// if (z == 1) {
	// dir = "N";
	// } else if (z == 2) {
	// dir = "W";
	// } else if (z == 3) {
	// dir = "E";
	// } else {
	// dir = "S";
	// }
	// updateInfo2.add("UPDATE,ItemName," + j + ",OwnerName," + z + "," + dir
	// + "," + speed + "," + locationX + "," + locationY + "");
	//
	// updateInfo= updateInfo2;
	// }
	// return updateInfo;
	// }

}
