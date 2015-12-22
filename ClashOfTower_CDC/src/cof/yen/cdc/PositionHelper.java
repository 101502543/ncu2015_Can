package cof.yen.cdc;

import java.util.ArrayList;

public class PositionHelper extends ArrayList<Integer>{
	public static PositionHelper ph = null;
	private Parameter parameter = null;
	
	public PositionHelper() {
		parameter = Parameter.getInstance();
	}

	public static PositionHelper getInstance(){
		if(ph == null)
			ph = new PositionHelper();
		return ph;
	}
	
	public int[] getTowerPos(int clientNo){
		for(int i=0 ; i<this.size() ; i++)
			if(this.get(i) == clientNo)
				return parameter.INIT_TOWER_POS[i];
		assert false: "No such Client"+clientNo;
		return null;
	}
}
