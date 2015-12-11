package cof.can.udpus;

public class Dynamic_Object_Module {

	public void addVirutalCharacter(int clientno) {
		System.out.println("Client " + clientno + " joins the game.");

	}

	public void addItem(String name, int index, boolean shared) {
		System.out.println("The item" + index + "(" + name
				+ ") is added to the map.");
		System.out.println();
	}

	public void updateVirtualCharacter(int clientno, String dir, int speed,
			int x, int y) {
		System.out.println("character" + clientno + " with direction:" + dir
				+ " and speed:" + speed + "m/s in location(" + x + "," + y
				+ ") is updated.");

	}

	public void updateItem(int index, boolean shared, String owner) {
		System.out.println("the item of " + owner + " with index " + index
				+ " is updated.");
		System.out.println();
	}
}
