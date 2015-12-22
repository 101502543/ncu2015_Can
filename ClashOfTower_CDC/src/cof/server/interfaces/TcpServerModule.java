package cof.server.interfaces;

public interface TcpServerModule {
	public void writeMsgToClient(int clientNo, String msg);
	public void writeMsgToAllClient(String msg);
	public void getIpTable();
}
