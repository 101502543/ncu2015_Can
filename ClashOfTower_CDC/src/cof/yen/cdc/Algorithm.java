package cof.yen.cdc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Algorithm {
	private CDC cdc = null;
	private Parameter parameter = null;
	private int curPosIndex = 0;
	
	public Algorithm(CDC cdc) {
		this.cdc = cdc;
		this.parameter = Parameter.getInstance();
	}
	
	
	/*public int[] getNextTowerPos(){
		assert curPosIndex < parameter.MAX_PLAYER_NUM : "curPosIndex is over player number.";
		return parameter.INIT_TOWER_POS[curPosIndex++];		
	}*/
	
	
	public ArrayList<EndScore> getEndScore(){
		int score = 0;
		ArrayList<EndScore> result = new ArrayList<EndScore>();
		
		for(int key : cdc.pm.keySet() ){
			Player curPlayer = cdc.pm.get(key);
			score = (curPlayer.tm.size() + curPlayer.getTowerDestroyNum()) * parameter.END_SCORE_TOWER - curPlayer.getDeadUnitNum();
			result.add(new EndScore(key, score));
		}
		Collections.sort(result, new scoreSort());		
		return result;
	}	
}

class scoreSort implements Comparator<EndScore> {
	@Override
	public int compare(EndScore o1, EndScore o2) {
		return o2.getScore() - o1.getScore();
	}
}