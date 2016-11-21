package view;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import control.UserDatabase;

public class SignUpInterface extends JFrame{
	private static final long serialVersionUID = 1L;
	
	
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
			UserDatabase ud = new UserDatabase();
			public void actionPerformed(ActionEvent e) {
				String name = jtfName.getText();
				String password = String.valueOf(jpfPassword.getPassword());
				String password2 = String.valueOf(jpfPassword2.getPassword());
				//在数据库查询用户名是否存在
		//		System.out.println(password + " " + password2);
				ud.createConnection();
				if (ud.nameExists(name)) {
					System.out.println("name exists");
					SignUpInterface.this.setVisible(false);
					new nameExistsWrong();
				}
				else {
					System.out.println("ok");
					if (password.length() < 6) {
						SignUpInterface.this.setVisible(false);
						new PasswordShort();
				//		System.out.println("密码至少为6位");
					}
					else if (!password.equals(password2)) {
						SignUpInterface.this.setVisible(false);
						new PasswordNotEqual();
			//			System.out.println("两次密码不一致");
					}
					else {
						
			//			ud.createConnection();
						ud.insert(name, password);
						SignUpInterface.this.setVisible(false);
						new SignUpSucceed();
					}
				}
			}
		});
		
		jbCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUpInterface.this.setVisible(false);
				new LogInterface();
			}
		});
	}	
}

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
}

