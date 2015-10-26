import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class ServIORunnable implements Runnable{
	private Socket clientSocket = null;
	private Tcp_Server tcp_Serve = null;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	
	public ServIORunnable(Socket clientSocket,Tcp_Server tcp_Server){
		this.clientSocket = clientSocket;
		this.tcp_Serve = tcp_Server;
	}

	public void run() {
		try {
			inputStream = new DataInputStream(clientSocket.getInputStream());
			outputStream = new DataOutputStream(clientSocket.getOutputStream());
			//System.out.println("Client : " + inputStream.readUTF());
			while(true){
				for(int i = 0; i < tcp_Serve.clientNum; i++){
					if(clientSocket.getInetAddress().toString().equals(tcp_Serve.client_Info[i].ip)){
						getMessage(inputStream.readUTF(),tcp_Serve.client_Info[i].number);
					}
				}
			}
		} catch (Exception e) {
			
		}
		
	}
	
	//接收來自client端之訊息(get寶物 or release寶物)
	public void getMessage(String message , int clientNum) throws IOException{
		switch(message){
			case "GET A":
				sendMessage(tcp_Serve.getTreasureMessage("GA",clientNum));
				break;
			case "GET B":
				sendMessage(tcp_Serve.getTreasureMessage("GB",clientNum));
				break;
			case "GET C":
				sendMessage(tcp_Serve.getTreasureMessage("GC",clientNum));
				break;
			case "RELEASE A":
				tcp_Serve.releaseTreasureMessage("RA");
				break;
			case "RELEASE B":
				tcp_Serve.releaseTreasureMessage("RB");
				break;
			case "RELEASE C":
				tcp_Serve.releaseTreasureMessage("RC");
				break;
		}
	}
	
	//傳訊息告知client該寶物是否可被擁有
	public void sendMessage(String message) throws IOException{
		outputStream.writeUTF(message);
	}
}
