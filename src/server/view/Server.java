package server.view;

import javax.swing.JFrame;

import server.control.MultiThreadServer;

public class Server extends JFrame{
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MultiThreadServer();
	}

}
