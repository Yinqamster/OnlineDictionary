package server.control;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class UserController {
	private static UserController instance;
	private Map<String, Socket> ipSocketMap;
	
	public static synchronized UserController getInstance() {
		if(instance == null) {
			instance = new UserController();
		}
		
		return instance;
	}
	
	private UserController() {
		ipSocketMap = new HashMap<String, Socket>();
	}
	
	public void ipRecord(String ip, Socket socket) {
		System.out.println(ip);
		ipSocketMap.put(ip, socket);
	}
	
	public Socket getSocketByIp(String ip) {
		if(ipSocketMap.containsKey(ip)) {
			return ipSocketMap.get(ip);
		} else {
			System.out.println("No such ip");
			return null;
		}
	}
}
