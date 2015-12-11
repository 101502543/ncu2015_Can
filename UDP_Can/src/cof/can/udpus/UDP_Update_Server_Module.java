package cof.can.udpus;

import java.net.*;

public class UDP_Update_Server_Module {
	int port;// connection port
	Dynamic_Object_Module DOM = new Dynamic_Object_Module();
	public static boolean flag[] = new boolean[4];
	public static boolean flagItem[] = new boolean[10];

	public void initUDPServer() throws Exception {
		UDP_Update_Server_Module server = new UDP_Update_Server_Module(8888);
		// judge whether the player is used or not
		for (int i = 0; i < 4; i++) {
			flag[i] = false;
		}
		for (int i = 0; i < 10; i++) {
			flagItem[i] = false;
		}

		server.run(); // execute the server
	}

	public UDP_Update_Server_Module(int openPortNum) {
		port = openPortNum; // set the port
	}

	public void run() throws Exception {
		final int msgSize = 8000; // message MAX size 8000.
		byte buffer[] = new byte[msgSize]; // set the message buffer

		while (true) {
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			// set the receive UDP server
			DatagramSocket socket = new DatagramSocket(port);
			socket.receive(packet); // receive packet。
			// transfer the receive message to String。
			String receiveMsg = new String(buffer, 0, packet.getLength());

			judgeCommand(receiveMsg);
			socket.close();
		}
	}

	public void judgeCommand(String receiveMsg) {

		// split the receive message
		String msgArray[] = receiveMsg.substring(1, receiveMsg.length() - 1)
				.split(",");

		// call addVirutalCharavter or addItem to DOM
		if (receiveMsg.substring(1, 2).equals("A")) {
			System.out.println("ADD");
			if ((flag[Integer.parseInt(msgArray[4]) - 1]) == false) {
				DOM.addVirutalCharacter(Integer.parseInt(msgArray[4]));
				flag[Integer.parseInt(msgArray[4]) - 1] = true;
			}

			if ((flagItem[Integer.parseInt(msgArray[2]) - 1]) == false) {
				DOM.addItem(msgArray[1], Integer.parseInt(msgArray[2]), true);
				flagItem[Integer.parseInt(msgArray[2]) - 1] = true;
			} else {
				System.out.println("The item with index:" + msgArray[2]
						+ " is added.");
			}

		}

		// call updateVirutalCharavter or updateItem to DOM
		else if (receiveMsg.substring(1, 2).equals("U")) {
			System.out.println("UPDATE");
			DOM.updateVirtualCharacter(Integer.parseInt(msgArray[4]),
					msgArray[5], Integer.parseInt(msgArray[6]),
					Integer.parseInt(msgArray[7]),
					Integer.parseInt(msgArray[8]));
			DOM.updateItem(Integer.parseInt(msgArray[2]), true, msgArray[3]);
		}
	}

}
