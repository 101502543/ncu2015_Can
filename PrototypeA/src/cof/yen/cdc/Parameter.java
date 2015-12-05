package cof.yen.cdc;

public class Parameter {
	public static Parameter parameter; 
	final int DIMENSION = 2;
	final int MAX_PLAYER = 4;
	//final int MAP_SIZE = XXX;
	//final int MAP_MAX_EAST = XXX;
	//final int MAP_MAX_NORTH = XXX;
	//final int MAP_MAX_SOUTH = XXX;
	//final int MAP_MAX_WEST = XXX;	
	final int MAX_CHARACTER_VELOCITY = 10;
	final int[] DIRECTION = {1, 2, 3, 4};
	

    public static Parameter getInstance(){
        if(parameter==null){
        	parameter = new Parameter();
        }
        return parameter;
    }
}
