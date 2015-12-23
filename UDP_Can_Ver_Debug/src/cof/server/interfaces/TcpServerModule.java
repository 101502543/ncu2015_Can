package cof.server.interfaces;

import java.util.ArrayList;

public interface TcpServerModule {
	public void writeMsgToClient(int clientNo, String msg);
	public void writeMsgToAllClient(String msg);
	public static ArrayList<String> getClientIPTable() {
		// TODO Auto-generated method stub
		return null;
	}
}
