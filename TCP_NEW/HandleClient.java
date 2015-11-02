import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Timer;


public class HandleClient extends Thread{

    private Socket ClientSocket = null;
	private int client_num = 0;
	
	OutputStreamWriter osw = null;
	InputStreamReader isr = null;
	BufferedReader br = null;
	
	public HandleClient(Socket socket, int clientNO) {
		// TODO Auto-generated constructor stub
		this.ClientSocket = socket;
		this.client_num = clientNO;
	}
	
	public void run(){
		String request = null;
		try{
			osw = new OutputStreamWriter(ClientSocket.getOutputStream());
			System.out.println("Connection from : " + ClientSocket.getInetAddress());
			isr = new InputStreamReader(ClientSocket.getInputStream());
			br = new BufferedReader(isr);
			
			DataTask da = new DataTask();			
			do{
				request = br.readLine();
				System.out.println("From client " + client_num + ":" + request);
				
				if(request.split(" ")[0].equals("GET")){
					String temp = da.available(request.split(" ")[1]);
					if(temp.equals("YES")){
						da.set(request.split(" ")[1],client_num);
					}			
					System.out.println(temp +" "+ request.split(" ")[1]);
					osw.write(temp +" "+ request.split(" ")[1]+"\n");
					osw.flush();
				}
				else if(request.split(" ")[0].equals("RELEASE")){
					da.release(request.split(" ")[1]);
					osw.write("release"+"\n");
					osw.flush();
					System.out.println("release~~");
				}
				else{
					System.out.println("error\n");
					osw.write("ERROR");
					osw.flush();
				}
			}while(!request.equals("END"));
			System.out.println("Client" + client_num + "closed connection");
			osw.close();
			isr.close();
			br.close();
			ClientSocket.close();
			
		}catch(IOException ioe){
			System.exit(1);
		}
	}//run
	
}
