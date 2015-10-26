import java.io.*;
import java.net.*;
import java.util.Timer;
import java.util.TimerTask;

public class Tcp_Client implements Runnable{
	private String serverIP;
	private int port = 1234;
	DataOutputStream outputStream;
	DataInputStream inputStream;
	Socket clientSocket;
	String[] treasure = new String[]{"A","B","C"};
	private int treasure_index = 0;
	static Tcp_Client tcp_Client;
	Timer timer_client = new Timer();
	//A,B,C寶物之Timer
	Timer[] timer_treasure = new Timer[3];
	//用來紀錄client端所擁有之寶物的狀態
	boolean isHaveTreasure[] = new boolean[]{false,false,false};
	int treasureCount[] = new int[3];
	//紀錄當下時槽
	int nowTimeSlot = 1;
	public Tcp_Client(String serverIP){
		this.serverIP = serverIP;
	}
	public void run(){
		clientSocket = new Socket();
		try {
			clientSocket.connect(new InetSocketAddress(this.serverIP,this.port));
			outputStream = new DataOutputStream(clientSocket.getOutputStream());
			//outputStream.writeUTF("我是client1 , 我的ip address : " + clientSocket.getLocalAddress());
			inputStream = new DataInputStream(clientSocket.getInputStream());
			timer_client.schedule(new clientTimer(),3000,3000);
			while(true){
				try {
					//每隔1秒發出get寶物之訊息至server
					Thread.sleep(1000);
					if(treasure_index == 3)
						treasure_index = 0;
					if(!isHaveTreasure[(treasure_index)%3]){
						get_treasure((treasure_index)%3);
					}
					System.out.println("【Time slot】： "+nowTimeSlot+"\n----------------------");
					treasure_index++;
					nowTimeSlot++;
				} catch (Exception e) {
					break;
				}
			}
			
		} catch (Exception e) {
			
		}
	}
	
	//發get寶物訊息至server
	public void get_treasure(int index){
		try {
			outputStream.writeUTF("GET " + treasure[index]);
			String get_response = getMessage();
			System.out.println(get_response);
			// 判斷該client是否擁有此寶物
			if(get_response.split(" ")[0].equals("Yes")){
				int treasure_index = getIndex(get_response.split(" ")[1]);
				isHaveTreasure[treasure_index] = true;
				//寶物時槽設定為當下時槽+5(寶物可擁有之時間)
				treasureCount[treasure_index] = nowTimeSlot + 5;
				//重初始化Timer物件
				timer_treasure[treasure_index] = new Timer();
				timer_treasure[treasure_index].schedule(new treasureTimer(treasure_index), 5000 , 5000);
			}
		} catch (IOException e) {
			//e.printStackTrace();
			return;
		}
	}
	
	//接收來自server回應之訊息(YES or NO)
	public String getMessage() throws IOException{
		String message = inputStream.readUTF();
		//System.out.println(message);
		return message;
	}
	
	// 取得寶物index值(A->0 , B->1 , C->2)
	public int getIndex(String treasure){
		switch(treasure){
			case "A" :
				return 0;
			case "B" :
				return 1;
			case "C" :
				return 2;
		}
		return 3;
	}
	
	public static void main(String[] args) throws IOException{
		tcp_Client = new Tcp_Client("127.0.0.1");
		Thread thread = new Thread(tcp_Client);
		thread.start();
	}
}

//client每三秒印出寶物擁有狀況及可擁有之時間
class clientTimer extends TimerTask{
	String treasure_al[] = new String[]{"A","B","C"};
	public void run(){
		for(int index = 0; index < 3; index++){
			if(Tcp_Client.tcp_Client.isHaveTreasure[index]){
				System.out.println(treasure_al[index] + " " + "YES "+ (Tcp_Client.tcp_Client.treasureCount[index] - Tcp_Client.tcp_Client.nowTimeSlot));
			}
			else{
				System.out.println(treasure_al[index] + " " + "NO");
			}
		}
	}
}

//持有寶物五秒後之事件(Release寶物)
class treasureTimer extends TimerTask{
	private int index;
	public treasureTimer(int index){
		this.index = index;
	}
	
	public void run() {
		System.out.println("index = " + index);
		Tcp_Client.tcp_Client.isHaveTreasure[index] = false;
		try {
			Tcp_Client.tcp_Client.outputStream.writeUTF("RELEASE " + Tcp_Client.tcp_Client.treasure[index]);
			Tcp_Client.tcp_Client.timer_treasure[index].cancel();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
