package cof.yen.cdc;

public class UpdateThread implements Runnable{
	private DataCenter dc;	
	
	public UpdateThread(DataCenter dc){
		this.dc = dc;
	}

	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			dc.autoMovePosition();
		}			
	}
}
