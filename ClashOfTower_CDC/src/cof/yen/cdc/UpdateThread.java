package cof.yen.cdc;

public class UpdateThread implements Runnable{
	private CDC cdc;
	
	public UpdateThread(CDC cdc) {
		this.cdc = cdc;
	}

	@Override
	public void run() {
		// call Tower's autoAddHp
		// call Player's autoAddMoney
	}
}
