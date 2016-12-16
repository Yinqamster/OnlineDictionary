package server.model;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class UserController {
	private static UserController instance;
	private Map<String, Socket> ipSocketMap;
	private Map<String, String> nameIpMap;
	
	public static synchronized UserController getInstance() {
		if(instance == null) {
			instance = new UserController();
		}
		
		return instance;
	}
	
	private UserController() {
		ipSocketMap = new HashMap<String, Socket>();
		nameIpMap = new HashMap<String, String>(); 
	}
	
	public void ipRecord(String ip, Socket socket) {
//		System.out.println(ip);
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
	
	public void nameRecord(String name, String ip) {
		nameIpMap.put(name, ip);
	}
	
	public String getIpByName(String name) {
		if (nameIpMap.containsKey(name)) {
			return nameIpMap.get(name);
		} else {
			System.out.println("No such name");
			return null;
		}
	}
}
