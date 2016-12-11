package client.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

public class FrontPage extends JFrame{
	private client.control.UserDatabase database=new client.control.UserDatabase();
	private client.control.DealAction deal=new client.control.DealAction();
	private Font font=new Font("Microsoft YaHei UI",0,20);
	//private Font font1=new Font("Microsoft YaHei UI",0,25);
	//private JLabel jlbTitle=new JLabel("Online Dictionary");
	private JLabel jlbInput=new JLabel("Input");
	private JTextField jtfInput=new JTextField();
	private JCheckBox jcBaidu=new JCheckBox("BaiDu");
	private JCheckBox jcYouDao=new JCheckBox("YouDao");
	private JCheckBox jcBing=new JCheckBox("Bing");
	private JButton jbtSearch=new JButton("Search");
	//private JButton zanBaiDu=new JButton("Like");
	//private JButton zanBing=new JButton("Like");
	//private JButton zanYouDao=new JButton("Like");
	//private TitledBorder jlbBaiDu=new TitledBorder("�ٶ�");
	//private JTextArea jtBaiDu=new JTextArea();
	//private TitledBorder jlbYouDao=new TitledBorder("�е�");
	//private JTextArea jtYouDao=new JTextArea();
	//private TitledBorder jlbBing=new TitledBorder("��Ӧ");
	//private JTextArea jtBing=new JTextArea();
	
	
	JPanel jpSearch=new JPanel();
	JPanel jpMeaning=new JPanel();
	JPanel jpBaidu=new JPanel();
	JPanel jpYouDao=new JPanel();
	JPanel jpBing=new JPanel();
	private JTextField jtfBaiDu = new JTextField(50); 
	private JTextField jtfBing = new JTextField(50); 
	private JTextField jtfYouDao = new JTextField(50); 
	JList jlist=new JList();
	DefaultListModel dfList=new DefaultListModel();
	DefaultListSelectionModel slList=new DefaultListSelectionModel();
	//�˵�
	String userName="userName";                    
	JMenuBar jmb=new JMenuBar();
	JMenu userMenu=new JMenu("UserName");
	JMenu sendCardMenu=new JMenu("Word card");
	//JMenu helpMenu=new JMenu("����");
	//JMenuItem userMenu=new JMenuItem("�û���");
	//JMenuItem sendCardMenu=new JMenuItem("���ʿ�");
	JMenuItem viewMenu=new JMenuItem("Look at my word card");
	JMenuItem helpMenu=new JMenuItem("Help");
	JMenuItem logoutItem=new JMenuItem("Log out");
	JMenuItem onlineUser=new JMenuItem("Online Users");
	//JMenuItem addFriends=new JMenuItem("Add friends");
	//JMenuItem onlineFriends=new JMenuItem("Online Friends");
	//JMenuItem offlineFriends=new JMenuItem("Offline Friends");
	JMenuItem makeCard=new JMenuItem("Generate word card");
	//JMenuItem sendCard=new JMenuItem("Send word card");
	///////////////////�����ݿ��ȡ���޸���/////////////////////
	int likeOFBaiDu=0;
	int likeOFYouDao=0;
	int likeOFBing=0;
	
	public FrontPage()
	{
		ImageIcon img = new ImageIcon("like.jpg");
		img.setImage((img.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT)));
		ImageIcon imgyoudao = new ImageIcon("youdao.png");
		imgyoudao.setImage((imgyoudao.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT)));
		ImageIcon imgbaidu = new ImageIcon("baidu.png");
		imgbaidu.setImage((imgbaidu.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT)));
		ImageIcon imgbing = new ImageIcon("bing.png");
		imgbing.setImage((imgbing.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT)));
		JButton zanBaiDu=new JButton(img);
		JButton zanBing=new JButton(img);
		JButton zanYouDao=new JButton(img);
		JLabel jlbyoudao = new JLabel(imgyoudao);
		JLabel jlbaidu = new JLabel(imgbaidu);
		JLabel jlbing = new JLabel(imgbing);
		
		setSize(1000,800);
		setTitle("Online Dictionary");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(new BorderLayout(10, 10));
	
		//jlbTitle.setFont(new Font("Microsoft YaHei UI",1,30));
		jlbInput.setFont(font);
		jtfInput.setFont(font);
		jbtSearch.setFont(font);
		//jcBaidu.setFont(font1);
		//jcYouDao.setFont(font1);
		//jcBing.setFont(font1);
		//zanBaiDu.setFont(font);
		//zanBing.setFont(font);
		//zanYouDao.setFont(font);
		/*jlbBaiDu.setTitleFont(font);
		jlbYouDao.setTitleFont(font);
		jlbBing.setTitleFont(font);
		jtBaiDu.setFont(font);
		jtYouDao.setFont(font);
		jtBing.setFont(font);*/
		jlist.setFont(font);
		jmb.setFont(font);
		userMenu.setFont(font);
		sendCardMenu.setFont(font);
		helpMenu.setFont(font);
		logoutItem.setFont(font);
		onlineUser.setFont(font);
		makeCard.setFont(font);
		viewMenu.setFont(font);
		//sendCard.setFont(font);
		//addFriends.setFont(font);
		//onlineFriends.setFont(font);
		//offlineFriends.setFont(font);
        ///////////////////���/////////////////////
		//userMenu.add(addFriends);
		userMenu.add(onlineUser);
		//userMenu.add(onlineFriends);
		//userMenu.add(offlineFriends);
		userMenu.add(logoutItem);
		sendCardMenu.add(makeCard);
		sendCardMenu.add(viewMenu);
		jmb.add(userMenu);
		jmb.add(sendCardMenu);
		jmb.add(helpMenu);
		this.setJMenuBar(jmb);
		//this.validate();////!!without it，the menu is invisible
		jmb.setVisible(true);
		
		jpSearch.setLayout(new BorderLayout(10, 10));
		//jpSearch.setPreferredSize(new Dimension(30,800));
		//jlbInput.setPreferredSize(new Dimension(30,100));
		//jtfInput.setPreferredSize(new Dimension(30,600));
		//jbtSearch.setPreferredSize(new Dimension(30,100));
		jpSearch.add(jlbInput,BorderLayout.WEST);
		jpSearch.add(jtfInput,BorderLayout.CENTER);
		jpSearch.add(jbtSearch, BorderLayout.EAST);
		add(jpSearch,BorderLayout.NORTH);
		
		jpMeaning.setLayout(new BorderLayout(10, 10));
		jpBaidu.setLayout(new BorderLayout(10, 10));
		jpBaidu.add(jtfBaiDu,BorderLayout.CENTER);
		jpBaidu.add(zanBaiDu,BorderLayout.EAST);
		jpMeaning.add(jpBaidu,BorderLayout.NORTH);
		jpYouDao.setLayout(new BorderLayout(10, 10));
		jpBing.setLayout(new BorderLayout(10, 10));
		
		add(new JScrollPane(jlist),BorderLayout.CENTER);
		add(jpMeaning,BorderLayout.EAST);
		this.validate();
		//pack();
		/*jlist.setBounds(0, 0,150,800);
		jlist.setVisibleRowCount(30);
		//add(new JScrollPane(jlist),BorderLayout.CENTER);
		////滚动条不知道怎么加啊啊啊啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊
		//new JScrollPane(jlist);
		//JScrollPane scroll=new JScrollPane(jlist);
		//scroll.setBounds(150,0,10, 800);
		//add(scroll);
		setSize(1000,800);
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
		//
		//jlist.setBounds(0, 0,150,800);
		add(jlist);
		
		//
		jlbTitle.setBounds(410,30,400,80);
		add(jlbTitle);
		jlbInput.setBounds(180,120,50,30);
		add(jlbInput);
		jtfInput.setBounds(260,120,500, 40);
		add(jtfInput);
		jbtSearch.setBounds(780, 120, 100, 40);
		add(jbtSearch);
		jcBaidu.setBounds(260, 180, 100, 40);
		jcBing.setBounds(470, 180, 100, 40);
		jcYouDao.setBounds(680, 180, 100, 40);
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
		//sendCardMenu.add(sendCard);
		sendCardMenu.add(viewMenu);
		jmb.add(userMenu);
		//userMenu.setBounds(0, 0, 100,40);
	
		jmb.add(sendCardMenu);
		jmb.add(helpMenu);
		this.setJMenuBar(jmb);
		this.validate();////!!without it，the menu is invisible
		jmb.setVisible(true);*/
		
		helpMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(null,"This is an online dictionary","Help",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		/////�鿴�����û�
		onlineUser.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dfList.clear();
				String[] onlineusers=database.getOnlineUser().split(" ");
				for(int i=0;i<onlineusers.length;i++)
					dfList.addElement(onlineusers[i]);
				jlist.setModel(dfList);
			}
		});
		////�鿴���ߺ���
		/*onlineFriends.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dfList.clear();
				String[] onlineusers=database.getOnlinefriends();
				for(int i=0;i<onlineusers.length;i++)
					dfList.addElement(onlineusers[i]);
				jlist.setModel(dfList);
			}
		});
		//�鿴���ߺ���
		offlineFriends.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dfList.clear();
				String[] onlineusers=database.getofflinefriends();
				for(int i=0;i<onlineusers.length;i++)
					dfList.addElement(onlineusers[i]);
				jlist.setModel(dfList);
			}
		});
		//��Ӻ���
		addFriends.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//slList.clear();
				String[] onlineusers=database.getOfflineuser();
				for(int i=0;i<onlineusers.length;i++)
				{
				}
				jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			}
		});
		//look at user word card
		viewMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			}
		});
		makeCard.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});*/
		//���͵��ʿ�
		/*sendCard.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});*/
		
	}
	public static void main(String[] args)
	{
		FrontPage page=new FrontPage();
	}
}
