import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class ServIORunnable implements Runnable{
	private Socket clientSocket = null;
	private Tcp_Server tcp_Serve = null;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	
	public ServIORunnable(Socket clientSocket,Tcp_Server tcp_Server) throws IOException{
		this.clientSocket = clientSocket;
		this.tcp_Serve = tcp_Server;
		inputStream = new DataInputStream(clientSocket.getInputStream());
		outputStream = new DataOutputStream(clientSocket.getOutputStream());
	}

	public void run() {
		try {
			//inputStream = new DataInputStream(clientSocket.getInputStream());
			//outputStream = new DataOutputStream(clientSocket.getOutputStream());
			//System.out.println("Client : " + inputStream.readUTF());
			while(true){
//				for(int i = 0; i < tcp_Serve.clientNum; i++){
//					if(clientSocket.getInetAddress().toString().equals(tcp_Serve.client_Info[i].ip)){
//						getMessage(inputStream.readUTF(),tcp_Serve.client_Info[i].number);
//					}
//				}
				getMessage(inputStream.readUTF());
				
			}
		} catch (Exception e) {
			
		}
		
	}
	
	//�����Ӧ�client�ݤ��T��(get�_�� or release�_��)
	public void getMessage(String message) throws IOException{
		//System.out.println("message : " + message + " clientNum = " + (clientNum+1));
		switch(message){
			case "GET A":
				sendMessage(tcp_Serve.getTreasureMessage("GA",clientSocket.getInetAddress().toString()));
				break;
			case "GET B":
				sendMessage(tcp_Serve.getTreasureMessage("GB",clientSocket.getInetAddress().toString()));
				break;
			case "GET C":
				sendMessage(tcp_Serve.getTreasureMessage("GC",clientSocket.getInetAddress().toString()));
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
	
	//�ǰT���i��client���_���O�_�i�Q�֦�
	public void sendMessage(String message) throws IOException{
		//System.out.println("message : " + message);
		outputStream.writeUTF(message);
		outputStream.flush();
	}
}
