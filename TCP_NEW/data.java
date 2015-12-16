import java.util.TimerTask;


public class data extends TimerTask {

    DataTask dd = new DataTask();
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(dd.answer[0] == 0){
			System.out.println("A" +" NO "+ dd.answer[0]);
		}else{
			System.out.println("A" +" YES "+ dd.answer[0]);
		}
		if(dd.answer[1] == 0){
			System.out.println("B" +" NO "+ dd.answer[1]);
		}else{
			System.out.println("B" +" YES "+ dd.answer[1]);
		}
		if(dd.answer[2] == 0){
			System.out.println("C" +" NO "+ dd.answer[2]);
		}else{
			System.out.println("C" +" YES "+ dd.answer[2]);
		}
	}
	
}
