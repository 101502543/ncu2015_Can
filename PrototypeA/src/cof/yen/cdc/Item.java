package cof.yen.cdc;

public class Item {
	private String name ;
	private int index;
	private boolean shared;
	private int x, y;
	private int ownClientNo = -1;
	
	public Item(String name, int index, boolean shared,int x, int y){
		this.name = name;
		this.index  = index;		
		this.shared = shared;
		this.x = x;
		this.y = y;
	}
		
	public void setOwnClient(int clientNo){
		this.ownClientNo = clientNo;
	}
	
	public int getOwnClient(){
		return ownClientNo;
	}
	
	public void setPosX(int x){
		this.x = x;
	}
	
	public int getPosX(){
		return x;
	}
	
	public void setPosY(int y){
		this.y = y;
	}
	
	public int getPosY(){
		return y;
	}
	
	public void setShared(boolean shared){
		this.shared = shared;
	}
	
	public boolean getShared(){
		return shared;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getIndex(){
		return index;
	}
	
	public String getAllProperty(){
		String result = "Name¡G"+name+"\nIndex¡G"+index+"\nShared¡G"+shared+"\n(x, y)¡G"+x+", "+y;
		return result;		
	}	
}
