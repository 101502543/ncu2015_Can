//FreeGeng since 2015/10/9
//encoding:UTF-8
import java.net.*;

public class UDP_server {

	int port;//connection port
	
	public static void main(String args[]) throws Exception {
        UDP_server server = new UDP_server(8888); // build the server
        server.run();                           // execute the server
    }
 
    public UDP_server(int openPortNum) {
        port = openPortNum;                            // set the port
    }
 
    public void run() throws Exception {
        final int msgSize = 8000;                    // message MAX size 8000.
        byte buffer[] = new byte[msgSize];            // set the message buffer
       
        while(true) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            DatagramSocket socket = new DatagramSocket(port);         // set the receive UDP Socket.
            socket.receive(packet);                                    // receive packet。
            String receiveMsg = new String(buffer, 0, packet.getLength());    // transfer the receive message to String。
            System.out.println("(X,Y)="+receiveMsg);
            socket.close();                                      
        }
    }
}
