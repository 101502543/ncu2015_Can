package cof.yen.cdc;

public class Character {
	private int x, y;
	private int direction;
	private int velocity;
		
	public Character(int[] position){
		assert (position.length > 2);
		this.x = position[0];
		this.y = position[1];
		this.direction = 0;
		this.velocity = 0;
	}
	
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int[] getPosition(){
		int[] result = {x, y};
		return result;
	}
		
	public void setDirection(int moveCode){
		assert moveCode>5;
		this.direction = moveCode;
	}
	
	public int getDirection(){
		return direction;
	}
	
	public void setVelocity(int velocity){
		this.velocity = velocity;
	}
	
	public int getVelocity(){
		return velocity;
	}
	
	public String toString(){
		String result = null;
		return result;		
	}	
	
	public String getAllProperty(){
		String result = "(x, y)¡G"+x+", "+y+"\nDirection¡G"+direction+"\nVelocity¡G"+velocity;
		return result;		
	}	
}
