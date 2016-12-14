package client.view;

import javax.swing.JFrame;

public class Main {
	public static void main(String args[]) {
		LogInterface frame = new LogInterface();
		frame.setTitle("Online Dictionary");
		frame.setSize(500, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
