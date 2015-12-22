package cof.yen.cdc;

public class Coordinate {
	public static Coordinate coordinate = null;
	
	public Coordinate() {
		
	}
	
	public static Coordinate getInstance(){
		if(coordinate == null)
			coordinate = new Coordinate();
		return coordinate;
	}
	
	public void addNewUnit(){
		// 每當新稱一個unit後 要把這個unit加到vector裡
	}
	
	public void startUpdateUnitPos(){
			// add a thread to update location 
			// fighting()
	}	
	
	private void fighting(){
		// testCollision();
		// if(unit.isBreak())  then setPreposition(unit) and unit.goBreaked();
	}
	
	public void setNextStepPosition(Unit myUnit){
			// need to add a new update command
	}

	public void setPreStepPosition(Unit myUnit){
			// need to add a new update command
	}
}
