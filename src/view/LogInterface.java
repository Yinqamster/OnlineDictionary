package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogInterface extends JFrame{
	private static final long serialVersionUID = 1L;
	
	JTextField jtfUser = new JTextField(10);
	JPasswordField jpfPassword = new JPasswordField(10);
	JButton jpLog = new JButton("登录");
	JButton jpSignUp = new JButton("注册");
	JButton jbtTourOk = new JButton("Go");
	
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
		
		bindLogClickEvent();
		bindSighUpClickEvent();
		bindTourOkClickEvent();
		
		
	}
	
	private JPanel constructLogPane() {
		JPanel jpLogPane = new JPanel();
		jpLogPane.setLayout(new BorderLayout(10, 10));
		
		JPanel jpUserPane = new JPanel();
		jpUserPane.setPreferredSize(new Dimension(110, 60));
		JLabel jlbUser = new JLabel("用户名:");
		jlbUser.setFont(new java.awt.Font("宋体", 1, 15));
		jlbUser.setPreferredSize(new Dimension(110,30));
		jpUserPane.add(jlbUser);
	//	JTextField jtfUser = new JTextField(10);
		jtfUser.setPreferredSize(new Dimension(110,20));
		jpUserPane.add(jtfUser);
		
		JPanel jpPasswordPane = new JPanel();
		jpPasswordPane.setPreferredSize(new Dimension(110, 60));
		JLabel jlbPassword = new JLabel("密码:");
		jlbPassword.setFont(new java.awt.Font("宋体", 1, 15));
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
		jpSignPane.add(jpSignUp);
		
		jpLogPane.add(jpUserPane, BorderLayout.NORTH);
		jpLogPane.add(jpPasswordPane, BorderLayout.CENTER);
		jpLogPane.add(jpSignPane, BorderLayout.SOUTH);
		
		return jpLogPane;
		
	}
	
	private JPanel constructShowPane() {
		JPanel jpShowPane = new JPanel();
		jpShowPane.setLayout(new BorderLayout(0, 10));
		
		JPanel jpTitlePane = new JPanel();
		
		JLabel jlbTitle = new JLabel("在线词典");
		jlbTitle.setFont(new java.awt.Font("楷体", 1, 40));
		jpTitlePane.add(jlbTitle);
				
		ImageIcon img = new ImageIcon("字典.png");
		img.setImage((img.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT)));
		JLabel lblImage = new JLabel(img);
	//	lblImage.setBounds(0, 0, 20, 20);
		jpTitlePane.add(lblImage);
		
		JPanel jpTextPane = new JPanel();
	//	jpTextPane.setLayout(new BorderLayout(5, 5));
		JLabel jlbText0 = new JLabel("说明：");
		jlbText0.setFont(new java.awt.Font("宋体", 0, 18));
		JLabel jlbText1 = new JLabel("本词典支持百度、有道、必应查词");
		jlbText1.setFont(new java.awt.Font("宋体", 0, 18));
		JLabel jlbText2 = new JLabel("在右侧登录，新用户请先注册");
		jlbText2.setFont(new java.awt.Font("宋体", 0, 18));
		jpTextPane.add(jlbText0, BorderLayout.NORTH);
		jpTextPane.add(jlbText1, BorderLayout.CENTER);
		jpTextPane.add(jlbText2, BorderLayout.SOUTH);
		
		JPanel jpTourPane = new JPanel();
		JLabel jlbText3 = new JLabel("进入访客模式");
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
	
	private void bindLogClickEvent() {
		jpLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
	
	private void bindSighUpClickEvent() {
		jpSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogInterface.this.setVisible(false);
				new SignUpInterface();
			}
		});
	}
	
	private void bindTourOkClickEvent() {
		
	}
	
	public static void main(String args[]) {
		LogInterface frame = new LogInterface();
		frame.setTitle("Online Dictionary");
		frame.setSize(500, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
