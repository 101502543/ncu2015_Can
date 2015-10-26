import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class Tcp_Server extends TimerTask implements Runnable {
	private ServerSocket server;
	private int ServerPort;
	private Thread runningthread = null;
	private boolean isStopped = false;
	static Tcp_Server tcp_Server;
	static ServerTreasure serverTreasure;
	Timer timer = new Timer();
	//紀錄client人數
	int clientNum = 0;
	Client_Info[] client_Info = new Client_Info[2];
	//紀錄client編號
	int ClientNo;

	// initialize
	public  Tcp_Server(int ServerPort) {
		this.ServerPort = ServerPort;
	}

	public void run() {
		synchronized (this) {
			this.runningthread = Thread.currentThread();
		}
		openServerSocket();
		timer.schedule(new serverTimer(0,1), 3000,3000);
		Socket socket;
		while (!isStopped) {
			socket = null;
			System.out.println("正在等待連線....");
			try {
				socket = this.server.accept();
				//連線成功後，將client連線資訊紀錄下來
				client_Info[clientNum] = new Client_Info(clientNum++ , socket.getInetAddress().toString());
				System.out.println("client" + (client_Info[(clientNum-1)].number+1) + "已連線 , " + "ip : " + client_Info[clientNum-1].ip);
			} catch (Exception e) {
				if(isStopped){
					System.out.println("Server stopped");
				}
			}
			new Thread(new ServIORunnable(socket,tcp_Server)).start();
		}
		System.out.println("Server已關閉");
	}

	public void openServerSocket() {
		try {
			this.server = new ServerSocket(this.ServerPort);
			System.out.println("Server已開啟");
		} catch (IOException e) {
			System.out.println("Server has already been opened.");
		}
	}

	private synchronized boolean isStopped() {
		return this.isStopped;
	}

	public static void main(String[] args) throws IOException {
		tcp_Server = new Tcp_Server(1234);
		Thread server_thread = new Thread(tcp_Server);
		serverTreasure = new ServerTreasure();
		server_thread.start();
	}
	
	//接收來自client的get message
	public String getTreasureMessage(String message, int clientNum){
		ClientNo = clientNum;
		switch(message){
			case "GA":
				return GetTreasure(0,"A");
			case "GB":
				return GetTreasure(1,"B");
			case "GC":
				return GetTreasure(2,"C");
		}
		return message;
	}
	
	//接收來自client的release message
	public void releaseTreasureMessage(String message){
		switch(message){
		case "RA":
			ReleaseTreasure(0);
			break;
		case "RB":
			ReleaseTreasure(1);
			break;
		case "RC":
			ReleaseTreasure(2);
			break;
		}
	}
	
	//回傳寶物是否可為client擁有之訊息
	public String GetTreasure(int index, String Treasure){
		if(!serverTreasure.treasure[index]){
			serverTreasure.treasure[index] = true;
			return "Yes " + Treasure;
		}else{
			return "NO " + Treasure;
		}
	}
	
	//接受到release message時之事件
	public void ReleaseTreasure(int index){
		serverTreasure.treasure[index] = false;
	}
}

//treasure陣列表示A,B,C三寶物之狀態
class ServerTreasure{
	boolean []treasure = new boolean[]{false,false,false}; 
}

//每三秒列出client擁有寶物之情形
class serverTimer extends TimerTask{
	int index,client_number;
	public serverTimer(int index, int client_number){
		this.index = index;
		this.client_number = client_number;
	}
	String treasure_al[] = new String[]{"A","B","C"};
	public void run() {
		for(index = 0;index < 3; index++){
			if(!Tcp_Server.serverTreasure.treasure[index]){
				System.out.println(treasure_al[index] + " " + "NO " + "0");
			}else{
				System.out.println(treasure_al[index] + " " + "YES" + " " + (Tcp_Server.tcp_Server.ClientNo+1));
			}
		}
	}
}

//紀錄client資訊(client number and ip address)
class Client_Info {
	int number;
	String ip;
	
	public Client_Info(int number, String ip){
		this.number = number;
		this.ip = ip;
	}
}