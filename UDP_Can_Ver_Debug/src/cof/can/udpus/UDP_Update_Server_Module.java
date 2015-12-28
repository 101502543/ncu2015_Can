package cof.can.udpus;

import java.net.*;

public class UDP_Update_Server_Module {
	int port;// connection port
	boolean judgeOrNot;

	public UDP_Update_Server_Module(int openPortNum) {
		port = openPortNum; // set the port
	}

	public void initUDPServer() throws Exception {
		UDP_Update_Server_Module server = new UDP_Update_Server_Module(8888); // build
																				// the
																				// server
		server.run(); // execute the server
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
			String msgArray[] = receiveMsg.split("*");
			for (int i = 0; i < msgArray.length; i++) {
				judgeCommand(msgArray[i]);
			}
			socket.close();
		}
	}

	// resposible for decoder
	public void judgeCommand(String receiveMsg) {

		String msgArray[] = receiveMsg.split("?");
		switch (msgArray[0]) {
		case "initTowerPosition":
			String subMsgArrayITP[] = msgArray[1].split("$");
			String playerITP[][] = new String[4][2];
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 3; j++) {
					playerITP[i][j] = subMsgArrayITP[i].split(";")[j];
				}
			}
		case "removeUnit":
			String subMsgArrayRU[] = msgArray[1].split(";");
		case "addUnit":
			String subMsgArrayAU[] = msgArray[1].split(";");
		case "setUnitProperty":
			String subMsgArraySUP[] = msgArray[1].split(";");
		case "addMessenger":
			String subMsgArrayAM[] = msgArray[1].split(";");
		case "removeMessenger":

		case "setMessengerProperty":
			String subMsgArraySMP[] = msgArray[1].split(";");
		case "sentMessengerArriveMsg":

		case "sentEndScore":
			String subMsgArraySES[] = msgArray[1].split("$");
			String playerSES[][] = new String[4][2];
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 2; j++) {
					playerSES[i][j] = subMsgArraySES[i].split(";")[j];
				}
			}
		case "setPlayerMoney":
			String subMsgArraySPM[] = msgArray[1].split(";");
		case "setTowerHp":
			String subMsgArraySTH[] = msgArray[1].split(";");
		case "makeTowerFiring":
			String subMsgArrayMTF[] = msgArray[1].split(";");
		case "setGameTime":

		case "setEffect":
			String subMsgArraySE[] = msgArray[1].split(";");
		}

	}

}
