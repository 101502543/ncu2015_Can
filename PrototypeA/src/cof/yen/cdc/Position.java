package cof.yen.cdc;

public class Position {
	private int  dimension = Parameter.getInstance().DIMENSION;
	private int[] location;
	
	public Position(int x, int y){
		location = new int[dimension];
		this.location[0] = x;
		this.location[1] = y;
	}
	
	public void setPosition(int x, int y){
		this.location[0] = x;
		this.location[1] = y;
	}
	
	public int[] getPosition(){
		return location;
	}
	
	public void addMovement(Position p1){
		this.location[0] += p1.location[0];
		this.location[1] += p1.location[1];
	}	
}
