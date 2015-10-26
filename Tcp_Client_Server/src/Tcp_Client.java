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
	//A,B,C�_����Timer
	Timer[] timer_treasure = new Timer[3];
	//�ΨӬ���client�ݩҾ֦����_�������A
	boolean isHaveTreasure[] = new boolean[]{false,false,false};
	int treasureCount[] = new int[3];
	//������U�ɼ�
	int nowTimeSlot = 1;
	public Tcp_Client(String serverIP){
		this.serverIP = serverIP;
	}
	public void run(){
		clientSocket = new Socket();
		try {
			clientSocket.connect(new InetSocketAddress(this.serverIP,this.port));
			outputStream = new DataOutputStream(clientSocket.getOutputStream());
			//outputStream.writeUTF("�ڬOclient1 , �ڪ�ip address : " + clientSocket.getLocalAddress());
			inputStream = new DataInputStream(clientSocket.getInputStream());
			timer_client.schedule(new clientTimer(),3000,3000);
			while(true){
				try {
					//�C�j1��o�Xget�_�����T����server
					Thread.sleep(1000);
					if(treasure_index == 3)
						treasure_index = 0;
					if(!isHaveTreasure[(treasure_index)%3]){
						get_treasure((treasure_index)%3);
					}
					System.out.println("�iTime slot�j�G "+nowTimeSlot+"\n----------------------");
					treasure_index++;
					nowTimeSlot++;
				} catch (Exception e) {
					break;
				}
			}
			
		} catch (Exception e) {
			
		}
	}
	
	//�oget�_���T����server
	public void get_treasure(int index){
		try {
			outputStream.writeUTF("GET " + treasure[index]);
			String get_response = getMessage();
			System.out.println(get_response);
			// �P�_��client�O�_�֦����_��
			if(get_response.split(" ")[0].equals("Yes")){
				int treasure_index = getIndex(get_response.split(" ")[1]);
				isHaveTreasure[treasure_index] = true;
				//�_���ɼѳ]�w����U�ɼ�+5(�_���i�֦����ɶ�)
				treasureCount[treasure_index] = nowTimeSlot + 5;
				//����l��Timer����
				timer_treasure[treasure_index] = new Timer();
				timer_treasure[treasure_index].schedule(new treasureTimer(treasure_index), 5000 , 5000);
			}
		} catch (IOException e) {
			//e.printStackTrace();
			return;
		}
	}
	
	//�����Ӧ�server�^�����T��(YES or NO)
	public String getMessage() throws IOException{
		String message = inputStream.readUTF();
		//System.out.println(message);
		return message;
	}
	
	// ���o�_��index��(A->0 , B->1 , C->2)
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

//client�C�T��L�X�_���֦����p�Υi�֦����ɶ�
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

//�����_������ᤧ�ƥ�(Release�_��)
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
