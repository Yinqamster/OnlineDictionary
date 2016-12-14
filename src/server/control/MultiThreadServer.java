package server.control;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.*;

import client.control.UserDatabase;
import client.view.WordCard;

public class MultiThreadServer extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JTextArea jta = new JTextArea();
	private List<String> message = new ArrayList<String>();
	
	public MultiThreadServer() {
		setLayout(new BorderLayout());
		add(new JScrollPane(jta), BorderLayout.CENTER);
		setTitle("Server");
		setSize(500, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		try {
			ServerSocket serverSocket = new ServerSocket(8000);
			jta.append("MultiThreadServer started at " + new Date() + '\n');
			
			int clientNo = 1;
			
			while(true) {
				Socket socket = serverSocket.accept();
				
				jta.append("Starting thread for client " + clientNo + " at " + new Date() + '\n');
				
				InetAddress inetAddress = socket.getInetAddress();
				jta.append("Client " + clientNo + "'s host name is " + inetAddress.getHostName() + '\n');
				jta.append("Client " + clientNo + "'s IP Address is " + inetAddress.getHostAddress() + '\n');
				
				HandleAClient task = new HandleAClient(socket);
				
				UserController.getInstance().ipRecord(inetAddress.getHostAddress(), socket);
				System.out.println(socket);
				new Thread(task).start();
				
				clientNo++;
			}
			
		}
		catch(IOException ex) {
			System.out.println(ex);
		}
	}
	
	class HandleAClient implements Runnable {
		private Socket socket;
		UserDatabase ud = new UserDatabase();
		public HandleAClient(Socket socket) {
			this.socket = socket;
		}
		public void run() {
//			System.out.println("0");
			ud.createConnection();
			try {
//				DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
//				DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
				BufferedWriter outputToClient = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//	ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			//	ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				while(true) {
			//		double res = inputFromClient.readDouble();
			//		System.out.println(res);
					
//					System.out.println(inputFromClient.readChar());
//					String words[] = inputFromClient.read().split("\\s");
					String words[] = inputFromClient.readLine().split("\\s");
					if (words[0].equals("0")) {  //登录
						System.out.println(UserController.getInstance().getSocketByIp(socket.getInetAddress().getHostAddress()));
						if(!ud.nameExists(words[1])) {
							outputToClient.write("0\n");
						}
						else if(ud.nameExists(words[1])) {
							if (ud.passwordCorrectly(words[1], words[2])) {
								ud.setStateOn(words[1]);
					//			ud.getOnlineUser();
								outputToClient.write("1\n");
								UserController.getInstance().nameRecord(words[1], socket.getInetAddress().getHostAddress());
							}
							else {
								outputToClient.write("2\n");
							}
						}
					}
					else if (words[0].equals("1")) {  //注册
//						System.out.println("1");
						String name = words[1];
						if (ud.nameExists(name)) {
//							outputToClient.writeInt(0);
							outputToClient.write("0\n");
						}
						else {
							String password = words[2];
							String password2 = words[3];
							if (password.length() < 6) {
								outputToClient.write("1\n");
							}
							else if (!password.equals(password2)) {
								outputToClient.write("2\n");
							}
							else {
								ud.insert(name, password);
								outputToClient.write("3\n");
								
							}
						}
					}
					else if (words[0].equals("2")) {  //获取在线离线用户
						if (words[1].equals("on")) {
							String str = ud.getOnlineUser();
						//	System.out.println(str);
							outputToClient.write("on " + str + "\n");
							outputToClient.flush();
						}
						else if (words[1].equals("off")) {
							String str = ud.getOfflineUser();
						//	System.out.println(str);
							outputToClient.write("off " + str + "\n");
							outputToClient.flush();
						}
						
					}
					else if (words[0].equals("3")) {  //logout
						ud.setStateOff(words[1]);
						outputToClient.write("3\n");
					}
					else if (words[0].equals("4")) {  //获取点赞数
						if (words[2].equals("get")) {
					//		System.out.println("ok");
							String str = ud.getZan(words[1], words[3]);
			//				System.out.println(str);
							outputToClient.write(str + "\n");
						}
						else if(words[2].equals("write")) {
							ud.writeZan(words[1], words[3], words[4]);
						}
					}
					else if(words[0].equals("5")) {   //发送单词卡  5 接受者 发送者 单词 意思
						String m = words[1] + " " + words[2] + " " + words[3] + " " + words[4] + "\n";
						if (!message.contains(m)) {
							message.add(m);
				//			
						}
						
			/*			String ip = UserController.getInstance().getIpByName(words[1]);
						Socket socketSend = UserController.getInstance().getSocketByIp(ip);
						System.out.println(ip + "ok" + socketSend + "hh");
						BufferedWriter outputWordCard = new BufferedWriter(new OutputStreamWriter(socketSend.getOutputStream()));
						outputWordCard.write("send " + words[2] + " " + words[3] + " " + words[4] + "\n");
						outputWordCard.flush();*/
					}
					else if(words[0].equals("receive")) {
						if (!message.isEmpty()) {
							
							for (int i = 0; i < message.size(); i++) {
								String[] send = message.get(i).split(" ");
						//		System.out.println(message.size() + " " + words[1] + " " + send[0]);
								if (send[0].equals(words[1])) {
									outputToClient.write("yes " + send[1] + " " + send[2] + " " + send[3] + "\n");
						//			System.out.println("ok");
									message.remove(i);
									outputToClient.flush();
									break;
								}
								else {
									outputToClient.write("no\n");
									outputToClient.flush();
								}
							}
						}
						else {
							outputToClient.write("no\n");
							outputToClient.flush();
						}
					}
					
			/*		WordCard w = (WordCard)in.readObject();
					String ip = UserController.getInstance().getIpByName(w.getToUser());
					Socket socketSend = UserController.getInstance().getSocketByIp(ip);
					System.out.println(ip + "ok" + socketSend + "hh");
					out.writeObject(w);
					out.flush();*/
					
					
					
					
					outputToClient.flush();
			//		outputToClient.writeChars("ok !!");
					jta.append("requests received from client:");
					for (int i = 0; i < words.length; i++) {
						jta.append(" " + words[i]);
					}
					jta.append("\n");
				}
			}
			catch(IOException e){
				System.out.println(e);
			}
			
		}
	}
}
