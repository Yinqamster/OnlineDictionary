package client.view;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUpInterface extends JFrame{
	private static final long serialVersionUID = 1L;
	
//	private DataOutputStream toServer;
	private BufferedWriter toServer;
//	private DataInputStream fromServer;
	private BufferedReader fromServer;
	
	
	public SignUpInterface() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        
		this.setTitle("注册");
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		JPanel jpName = new JPanel();
		jpName.setLayout(new BoxLayout(jpName, BoxLayout.X_AXIS));
		JLabel jlbName = new JLabel("昵称           ");
		jpName.add(jlbName);
		JTextField jtfName = new JTextField(10);
		jpName.add(jtfName);
		
		JPanel jpPw = new JPanel();
		jpPw.setLayout(new BoxLayout(jpPw, BoxLayout.X_AXIS));
		JLabel jlbPassword = new JLabel("设置密码  ");
		jpPw.add(jlbPassword);
		JPasswordField jpfPassword = new JPasswordField(10);
		jpPw.add(jpfPassword);
		
		JPanel jpPw2 = new JPanel();
		jpPw2.setLayout(new BoxLayout(jpPw2, BoxLayout.X_AXIS));
		JLabel jlbPassword2 = new JLabel("确认密码  ");
		jpPw2.add(jlbPassword2);
		JPasswordField jpfPassword2 = new JPasswordField(10);
		jpPw2.add(jpfPassword2);
		
		JPanel jpBt = new JPanel();
		jpBt.setLayout(new BoxLayout(jpBt, BoxLayout.X_AXIS));
		JButton jbSign = new JButton("注册");
		jpBt.add(jbSign);
		JButton jbCancel = new JButton("取消");
		jpBt.add(jbCancel);
		
		panel.add(jpName);
		panel.add(jpPw);
		panel.add(jpPw2);
		panel.add(jpBt);
		
		add(panel);	
		
		jbSign.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try{
					String name = jtfName.getText();
					String password = String.valueOf(jpfPassword.getPassword());
					String password2 = String.valueOf(jpfPassword2.getPassword());
				//	toServer.wrwriteDouble(5.0);
//					toServer.writeChars("1 " + name + " " + password + " " + password2);
					toServer.write("1 " + name + " " + password + " " + password2+"\n");
					toServer.flush();
//					int res = fromServer.readInt();
					int res = Integer.parseInt(fromServer.readLine());
			//		System.out.println(res);
					//在数据库查询用户名是否存在
			//		System.out.println(password + " " + password2);
			//		while (true) {
					if (res == 0) {
		//				System.out.println("name exists");
						SignUpInterface.this.setVisible(false);
						JOptionPane.showMessageDialog(null,"用户名已存在！","Reminder",JOptionPane.INFORMATION_MESSAGE);
						new SignUpInterface();
				//		new nameExistsWrong();
					}
					else {
						if (res == 1) {
						//	toServer.writeBytes(password);
						//	toServer.flush();
							SignUpInterface.this.setVisible(false);
							JOptionPane.showMessageDialog(null,"密码至少为6位！","Reminder",JOptionPane.INFORMATION_MESSAGE);
							new SignUpInterface();
					//		new PasswordShort();
					//		System.out.println("密码至少为6位");
						}
						else if (res == 2) {
					//		toServer.writeBytes(password2);
					//		toServer.flush();
							SignUpInterface.this.setVisible(false);
							JOptionPane.showMessageDialog(null,"两次输入密码不一致！","Reminder",JOptionPane.INFORMATION_MESSAGE);
							new SignUpInterface();
					//		new PasswordNotEqual();
				//			System.out.println("两次密码不一致");
						}
						else if (res == 3){
						//	ud.insert(name, password);
							SignUpInterface.this.setVisible(false);
							JOptionPane.showMessageDialog(null, "                              注册成功！", "Reminder",JOptionPane.PLAIN_MESSAGE);
							new LogInterface();
						//	new SignUpSucceed();
						}
					}
			//		}
				}
				catch(IOException ex) {
					System.out.println(ex);
				}
			}
		});
		
		jbCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//	toServer.writeBytes("abc");
					SignUpInterface.this.setVisible(false);
					new LogInterface();				
			}
		});
		
		try {
			Socket socket = new Socket("localhost", 8000);
			
//			fromServer = new DataInputStream(socket.getInputStream());
			fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
//			toServer = new DataOutputStream(socket.getOutputStream());
			toServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		}
		catch(IOException ex) {
			System.out.println(ex);
		}
	}	
}

/*
class PasswordShort extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public PasswordShort() {
		this.setTitle("提示");
		this.setSize(200, 100);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JLabel jlbWrong = new JLabel("    密码至少为6位     ");
		jlbWrong.setFont(new java.awt.Font("宋体", 0, 17));
		JButton jbtOK = new JButton("确定");
		add(jlbWrong);
		add(jbtOK);
		
		jbtOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PasswordShort.this.setVisible(false);
				new SignUpInterface();
			}
		});
	}	
}


class PasswordNotEqual extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public PasswordNotEqual() {
		this.setTitle("提示");
		this.setSize(200, 100);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JLabel jlbWrong = new JLabel("  两次输入密码不一致   ");
		jlbWrong.setFont(new java.awt.Font("宋体", 0, 17));
		JButton jbtOK = new JButton("确定");
		add(jlbWrong);
		add(jbtOK);
		
		jbtOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PasswordNotEqual.this.setVisible(false);
				new SignUpInterface();
			}
		});
	}	
}

class SignUpSucceed extends JFrame {
	private static final long serialVersionUID = 1L;
		
	public SignUpSucceed() {
		this.setTitle("提示");
		this.setSize(200, 100);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JLabel jlbWrong = new JLabel("        注册成功！         ");
		jlbWrong.setFont(new java.awt.Font("宋体", 0, 17));
		JButton jbtOK = new JButton("确定");
		add(jlbWrong);
		add(jbtOK);
		
		jbtOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUpSucceed.this.setVisible(false);
				new LogInterface();
			}
		});
	}	
}

/*
class nameExistsWrong extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public nameExistsWrong() {
		this.setTitle("提示");
		this.setSize(200, 100);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JLabel jlbWrong = new JLabel("      用户名已存在！       ");
		jlbWrong.setFont(new java.awt.Font("宋体", 0, 17));
		JButton jbtOK = new JButton("确定");
		add(jlbWrong);
		add(jbtOK);
		
		jbtOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nameExistsWrong.this.setVisible(false);
				new SignUpInterface();
			}
		});
	}
}*/

