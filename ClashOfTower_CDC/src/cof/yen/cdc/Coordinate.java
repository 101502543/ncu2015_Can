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
		// �C��s�٤@��unit�� �n��o��unit�[��vector��
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
