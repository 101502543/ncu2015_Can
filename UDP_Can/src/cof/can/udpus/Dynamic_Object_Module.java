package cof.can.udpus;

public interface Dynamic_Object_Module {
	
	public void addVirtualCharacter(int clientno);
	public void addItem(String name, int index, boolean shared);
	public void updateVirtualCharacter(int clientno, String dir, int speed,int x, int y);
	public void updateItem(int index, boolean shared, String owner);
	
}
