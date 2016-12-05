package client.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class FrontPage extends JFrame{
	private Font font=new Font("Microsoft YaHei UI",0,20);
	private Font font1=new Font("Microsoft YaHei UI",0,25);
	private JLabel jlbTitle=new JLabel("Online Dictionary");
	private JLabel jlbInput=new JLabel("Input");
	private JTextField jtfInput=new JTextField();
	private JCheckBox jcBaidu=new JCheckBox("�ٶ�");
	private JCheckBox jcYouDao=new JCheckBox("�е�");
	private JCheckBox jcBing=new JCheckBox("��Ӧ");
	private JButton jbtSearch=new JButton("Search");
	private JButton zanBaiDu=new JButton("��");
	private JButton zanBing=new JButton("��");
	private JButton zanYouDao=new JButton("��");
	private TitledBorder jlbBaiDu=new TitledBorder("�ٶ�");
	private JTextArea jtBaiDu=new JTextArea();
	private TitledBorder jlbYouDao=new TitledBorder("�е�");
	private JTextArea jtYouDao=new JTextArea();
	private TitledBorder jlbBing=new TitledBorder("��Ӧ");
	private JTextArea jtBing=new JTextArea();
	private client.control.DealAction deal=new client.control.DealAction();
	//�˵�
	String userName="userName";                    ///////////�û�����Ҫ�����ݿ��ȡ////////////////
	JMenuBar jmb=new JMenuBar();
	JMenu userMenu=new JMenu("�û���");
	JMenu sendCardMenu=new JMenu("���ʿ�");
	//JMenu helpMenu=new JMenu("����");
	//JMenuItem userMenu=new JMenuItem("�û���");
	//JMenuItem sendCardMenu=new JMenuItem("���ʿ�");
	JMenuItem helpMenu=new JMenuItem("����");
	JMenuItem logoutItem=new JMenuItem("�˳�");
	JMenuItem onlineUser=new JMenuItem("�鿴�����û�");
	JMenuItem addFriends=new JMenuItem("��Ӻ���");
	JMenuItem onlineFriends=new JMenuItem("�鿴���ߺ���");
	JMenuItem offlineFriends=new JMenuItem("�鿴���ߺ���");
	JMenuItem makeCard=new JMenuItem("���ɵ��ʿ�");
	JMenuItem sendCard=new JMenuItem("���͵��ʿ�");
	///////////////////�����ݿ��ȡ���޸���/////////////////////
	int likeOFBaiDu=0;
	int likeOFYouDao=0;
	int likeOFBing=0;
	
	public FrontPage()
	{
		jlbTitle.setFont(new Font("Microsoft YaHei UI",1,30));
		jlbInput.setFont(font);
		jtfInput.setFont(font);
		jbtSearch.setFont(font);
		jcBaidu.setFont(font1);
		jcYouDao.setFont(font1);
		jcBing.setFont(font1);
		zanBaiDu.setFont(font);
		zanBing.setFont(font);
		zanYouDao.setFont(font);
		jlbBaiDu.setTitleFont(font);
		jlbYouDao.setTitleFont(font);
		jlbBing.setTitleFont(font);
		jtBaiDu.setFont(font);
		jtYouDao.setFont(font);
		jtBing.setFont(font);
		
		jmb.setFont(font);
		userMenu.setFont(font);
		sendCardMenu.setFont(font);
		helpMenu.setFont(font);
		logoutItem.setFont(font);
		onlineUser.setFont(font);
		makeCard.setFont(font);
		sendCard.setFont(font);
		addFriends.setFont(font);
		onlineFriends.setFont(font);
		offlineFriends.setFont(font);
        ///////////////////���/////////////////////
		setSize(800,720);
		//userMenu.setBounds(0, 0, 200,48);
		//sendCardMenu.setBounds(210, 0, 200, 48);
		//helpMenu.setBounds(400, 0, 200, 48);
		//userMenu.setPreferredSize(new Dimension(200,50));
		//sendCard.setPreferredSize(new Dimension(200,50));
		//helpMenu.setPreferredSize(new Dimension(200,50));
		setTitle("Online Dictionary");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(null);
		jlbTitle.setBounds(250,30,400,80);
		add(jlbTitle);
		jlbInput.setBounds(20,120,50,30);
		add(jlbInput);
		jtfInput.setBounds(100,120,500, 40);
		add(jtfInput);
		jbtSearch.setBounds(620, 120, 100, 40);
		add(jbtSearch);
		jcBaidu.setBounds(100, 180, 100, 40);
		jcBing.setBounds(310, 180, 100, 40);
		jcYouDao.setBounds(520, 180, 100, 40);
		add(jcBaidu);
		add(jcBing);
		add(jcYouDao);
		//////�˵�/////
		jmb.setBounds(0, 0, 790,50);
		userMenu.add(addFriends);
		userMenu.add(onlineUser);
		userMenu.add(onlineFriends);
		userMenu.add(offlineFriends);
		userMenu.add(logoutItem);
		sendCardMenu.add(makeCard);
		sendCardMenu.add(sendCard);
		//jmb.setSize(800, 50);
		jmb.add(userMenu);
		//userMenu.setBounds(0, 0, 100,40);
		jmb.add(sendCardMenu);
		jmb.add(helpMenu);
		setJMenuBar(jmb);
		pack();
		jmb.setVisible(true);
		/*helpMenu.addMenuListener(new MenuListener(){
			public void actionPerformed(MenuEvent e)
			{
				JOptionPane.showMessageDialog(null,"��������","����",JOptionPane.INFORMATION_MESSAGE);
			}
		});*/
		helpMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(null,"��������","����",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		/////�鿴�����û�
		onlineUser.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
		////�鿴���ߺ���
		onlineFriends.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
		//�鿴���ߺ���
		offlineFriends.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
		//��Ӻ���
		addFriends.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
		//���ɵ��ʿ�
		makeCard.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
		//���͵��ʿ�
		sendCard.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
	}
	public static void main(String[] args)
	{
		FrontPage page=new FrontPage();
	}
}
