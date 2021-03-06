package client.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogInterface extends JFrame{
	private static final long serialVersionUID = 1L;
	
//	private DataOutputStream toServer;
//	private DataInputStream fromServer;
	private BufferedWriter toServer;
	private BufferedReader fromServer;
	
	JTextField jtfUser = new JTextField(10);
	JPasswordField jpfPassword = new JPasswordField(10);
	JButton jpLog = new JButton("Log In");
	JButton jpSignIn = new JButton("Sign In");
	JButton jbtTourOk = new JButton("Go");
//	InteractionWithServer task;
	
	
	public LogInterface() {
		
		this.setTitle("Online Dictionary");
		this.setSize(500, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		setLayout (new BorderLayout(10, 10));
		JPanel jpShowPane = constructShowPane();
		jpShowPane.setPreferredSize(new Dimension(300, 500));
		add(jpShowPane, BorderLayout.WEST);
		
		JPanel jpLogPane = constructLogPane();
		jpLogPane.setPreferredSize(new Dimension(100, 500));
		add(jpLogPane, BorderLayout.CENTER);
		
		
		
		try {
			Socket socket = new Socket("localhost", 8000);
			fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			toServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
		//	task = new InteractionWithServer(socket);
		//	task.start();
			
			//new Thread(task).start();
		//	t = new Thread(task);
		//	t.start();
		//	t.join(100);
	//		sleep(1000);
	//		t.sleep(1000);
			
			bindLogClickEvent(socket);
			bindSighUpClickEvent(socket);
			bindTourOkClickEvent();
		}
		catch(IOException ex) {
			System.out.println(ex);
		}
	/*	catch(InterruptedException i) {
			System.out.println(i);
		}*/
		
		
	}
	
	private JPanel constructLogPane() {
		JPanel jpLogPane = new JPanel();
		jpLogPane.setLayout(new BorderLayout(10, 10));
		
		JPanel jpUserPane = new JPanel();
		jpUserPane.setPreferredSize(new Dimension(110, 60));
		JLabel jlbUser = new JLabel("User Name:");
		jlbUser.setFont(new java.awt.Font("", 1, 15));
		jlbUser.setPreferredSize(new Dimension(110,30));
		jpUserPane.add(jlbUser);
	//	JTextField jtfUser = new JTextField(10);
		jtfUser.setPreferredSize(new Dimension(110,20));
		jpUserPane.add(jtfUser);
		
		JPanel jpPasswordPane = new JPanel();
		jpPasswordPane.setPreferredSize(new Dimension(110, 60));
		JLabel jlbPassword = new JLabel("Password:");
		jlbPassword.setFont(new java.awt.Font("", 1, 15));
		jlbPassword.setPreferredSize(new Dimension(110, 30));
		jpPasswordPane.add(jlbPassword);
//		JPasswordField jpfPassword = new JPasswordField(10);
	/*	JTextField jtfPassword = new JTextField(10); */
		jpPasswordPane.add(jpfPassword);
		
		JPanel jpSignPane = new JPanel();
		jpSignPane.setPreferredSize(new Dimension(110, 100));
//		JButton jpLog = new JButton("登录");
		jpSignPane.add(jpLog);
//		JButton jpSignUp = new JButton("注册");
		jpSignPane.add(jpSignIn);
		
		jpLogPane.add(jpUserPane, BorderLayout.NORTH);
		jpLogPane.add(jpPasswordPane, BorderLayout.CENTER);
		jpLogPane.add(jpSignPane, BorderLayout.SOUTH);
		
		return jpLogPane;
		
	}
	
	private JPanel constructShowPane() {
		JPanel jpShowPane = new JPanel();
		jpShowPane.setLayout(new BorderLayout(0, 10));
		
		JPanel jpTitlePane = new JPanel();
		
		JLabel jlbTitle = new JLabel("Online Dictionary");
		jlbTitle.setFont(new java.awt.Font("", 1, 20));
		jpTitlePane.add(jlbTitle);
				
		ImageIcon img = new ImageIcon("字典.png");
		img.setImage((img.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT)));
		JLabel lblImage = new JLabel(img);
	//	lblImage.setBounds(0, 0, 20, 20);
		jpTitlePane.add(lblImage);
		
		JPanel jpTextPane = new JPanel();
	//	jpTextPane.setLayout(new BorderLayout(5, 5));
		JLabel jlbText0 = new JLabel("Instruction：");
		jlbText0.setFont(new java.awt.Font("宋体", 0, 18));
		JLabel jlbText1 = new JLabel("Search from Baidu, YouDao, Bing Dictionary");
		jlbText1.setFont(new java.awt.Font("宋体", 0, 13));
		JLabel jlbText2 = new JLabel("New users please register");
		jlbText2.setFont(new java.awt.Font("宋体", 0, 13));
		jpTextPane.add(jlbText0, BorderLayout.NORTH);
		jpTextPane.add(jlbText1, BorderLayout.CENTER);
		jpTextPane.add(jlbText2, BorderLayout.SOUTH);
		
		JPanel jpTourPane = new JPanel();
		JLabel jlbText3 = new JLabel("Enter visitor mode");
		jlbText3.setFont(new java.awt.Font("宋体", 0, 18));
//		JButton jbtTourOk = new JButton("Go");
		jbtTourOk.setFont(new java.awt.Font("宋体", 0, 10));
		jpTourPane.add(jlbText3);
		jpTourPane.add(jbtTourOk);
		
		jpShowPane.add(jpTitlePane, BorderLayout.NORTH);
		jpShowPane.add(jpTextPane, BorderLayout.CENTER);
		jpShowPane.add(jpTourPane, BorderLayout.SOUTH);
		return jpShowPane;
	}
	
	private void bindLogClickEvent(Socket socket) {
		jpLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					
					//用户名为空   密码为空   用户名不存在  密码不正确
					String userName = jtfUser.getText();
					String userPassword = String.valueOf(jpfPassword.getPassword());
					
	//				System.out.println(userName + userPassword);
					if (userName.isEmpty()) {
						LogInterface.this.setVisible(false);
						JOptionPane.showMessageDialog(null,"User name cannot be empty！","Reminder",JOptionPane.INFORMATION_MESSAGE);
						new LogInterface();
				//		System.out.println("用户名为空");
					}
					else if(userPassword.isEmpty()) {
						LogInterface.this.setVisible(false);
						JOptionPane.showMessageDialog(null,"Password can not be empty！","Reminder",JOptionPane.INFORMATION_MESSAGE);
						new LogInterface();
			//			new userPasswordIsEmpty();
			//			System.out.println("密码为空");
					}
					else {
						toServer.write("0 " + userName + " " + userPassword + "\n");
						toServer.flush();
						int res = Integer.parseInt(fromServer.readLine());
				//		t.sleep(50);
				//		task.join(100);
				//		int res = task.getResNum();
				//		System.out.println(res);
						if(res == 0) {
							LogInterface.this.setVisible(false);
							JOptionPane.showMessageDialog(null,"User name does not exist！","Reminder",JOptionPane.INFORMATION_MESSAGE);
						//	task = null;
							new LogInterface();
							
					//		new userNameNotExists();
				//			System.out.println("用户名不存在");
						}
						else {
							if (res == 1) {
						//		System.out.println("Login Successful");
								LogInterface.this.setVisible(false);
						//		task = null;
								new FrontPage(userName, socket);
							}
							else if (res == 2) {
								LogInterface.this.setVisible(false);
								JOptionPane.showMessageDialog(null,"Password Error！","Reminder",JOptionPane.INFORMATION_MESSAGE);
						//		task = null;
								new LogInterface();
				//				new passwordNotCorrect();
					//			System.out.println("密码错误");
							}
						}
					}
				}
				catch(IOException ex) {
					System.out.println(ex);
				}
			/*	catch(InterruptedException i) {
					System.out.println(i);
				}*/
				
			}
		});
	}
	
	private void bindSighUpClickEvent(Socket socket) {
		jpSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogInterface.this.setVisible(false);
				new SignInInterface(socket);
			}
		});
	}
	
	private void bindTourOkClickEvent() {
		//游客界面
		jbtTourOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogInterface.this.setVisible(false);
				new TouristPage();
			}
		});
	}
	
	

}

/*
class userNameIsEmpty extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public userNameIsEmpty() {
		
		this.setTitle("提示");
		this.setSize(200, 100);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JLabel jlbWrong = new JLabel("    用户名不能为空     ");
		jlbWrong.setFont(new java.awt.Font("宋体", 0, 17));
		JButton jbtOK = new JButton("确定");
		add(jlbWrong);
		add(jbtOK);
		
		jbtOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userNameIsEmpty.this.setVisible(false);
				new LogInterface();
			}
		});
	}	
}


class userPasswordIsEmpty extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public userPasswordIsEmpty() {
		this.setTitle("提示");
		this.setSize(200, 100);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JLabel jlbWrong = new JLabel("    密码不能为空     ");
		jlbWrong.setFont(new java.awt.Font("宋体", 0, 17));
		JButton jbtOK = new JButton("确定");
		add(jlbWrong);
		add(jbtOK);
		
		jbtOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userPasswordIsEmpty.this.setVisible(false);
				new LogInterface();
			}
		});
	}	
}



class userNameNotExists extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public userNameNotExists() {
		this.setTitle("提示");
		this.setSize(200, 100);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JLabel jlbWrong = new JLabel("    用户名不存在     ");
		jlbWrong.setFont(new java.awt.Font("宋体", 0, 17));
		JButton jbtOK = new JButton("确定");
		add(jlbWrong);
		add(jbtOK);
		
		jbtOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userNameNotExists.this.setVisible(false);
				new LogInterface();
			}
		});
	}	
}


class passwordNotCorrect extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public passwordNotCorrect() {
		this.setTitle("提示");
		this.setSize(200, 100);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JLabel jlbWrong = new JLabel("      密码错误       ");
		jlbWrong.setFont(new java.awt.Font("宋体", 0, 17));
		JButton jbtOK = new JButton("确定");
		add(jlbWrong);
		add(jbtOK);
		
		jbtOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwordNotCorrect.this.setVisible(false);
				new LogInterface();
			}
		});
	}	
}*/
