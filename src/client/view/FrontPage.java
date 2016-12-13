package client.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Arrays;

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
	private BufferedWriter toServer;
	private BufferedReader fromServer;
	
	
	private client.control.UserDatabase database=new client.control.UserDatabase();
	private client.control.DealAction deal=new client.control.DealAction();
	private Font font=new Font("Microsoft YaHei UI",0,20);
	private Font font1=new Font("Microsoft YaHei UI",0,25);
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
	JPanel checkbox=new JPanel();
	JPanel right=new JPanel();
	private JTextField jtfBaiDu = new JTextField(50); 
	private JTextField jtfBing = new JTextField(50); 
	private JTextField jtfYouDao = new JTextField(50); 
	JList jlist=new JList();
	DefaultListModel dfList=new DefaultListModel();
	DefaultListSelectionModel slList=new DefaultListSelectionModel();
	String userName=null;                                      ///用户名在登录的时候传入          
	String sendMeaning=null;                                   ///要发送的意思，默认为点赞数最多的那个
	JMenuBar jmb=new JMenuBar();
	//JMenu userMenu=new JMenu("UserName");
	JMenu sendCardMenu=new JMenu("Word card");
	//JMenu helpMenu=new JMenu("����");
	//JMenuItem userMenu=new JMenuItem("�û���");
	//JMenuItem sendCardMenu=new JMenuItem("���ʿ�");
	JMenuItem viewMenu=new JMenuItem("Look at my word card");
	JMenuItem helpMenu=new JMenuItem("Help");
	JMenuItem logoutItem=new JMenuItem("Log out");
	JMenuItem onlineUser=new JMenuItem("Online Users");
	JMenuItem offlineUser=new JMenuItem("Offline Users");
	//JMenuItem addFriends=new JMenuItem("Add friends");
	//JMenuItem onlineFriends=new JMenuItem("Online Friends");
	//JMenuItem offlineFriends=new JMenuItem("Offline Friends");
	JMenuItem makeCard=new JMenuItem("Generate word card");
	//JMenuItem sendCard=new JMenuItem("Send word card");
	///////////////////�����ݿ��ȡ���޸���/////////////////////
	int likeOFBaiDu=0;
	int likeOFYouDao=0;
	int likeOFBing=0;
	ImageIcon img = new ImageIcon("like.png");
	ImageIcon imgnew=new ImageIcon("like_1.png");
	ImageIcon imgyoudao = new ImageIcon("youdao.png");
	ImageIcon imgbaidu = new ImageIcon("baidu.png");
	ImageIcon imgbing = new ImageIcon("bing.png");
	JButton zanBaiDu=new JButton(img);
	JButton zanBing=new JButton(img);
	JButton zanYouDao=new JButton(img);
	public FrontPage(String UserName, Socket socket)   
	{
		likeOFBaiDu=0;
		likeOFYouDao=0;
		likeOFBing=0;
		try {
			fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			toServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		}
		catch(IOException ex) {
			System.out.println(ex);
		}
		
		userName=UserName;
		JMenu userMenu=new JMenu(UserName);
		img.setImage((img.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT)));
		imgnew.setImage((imgnew.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT)));
		imgyoudao.setImage((imgyoudao.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT)));
		imgbaidu.setImage((imgbaidu.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT)));
		imgbing.setImage((imgbing.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT)));
		JLabel jlbyoudao = new JLabel(imgyoudao);
		JLabel jlbbaidu = new JLabel(imgbaidu);
		JLabel jlbbing = new JLabel(imgbing);
		
		setSize(1000,800);
		setTitle("Online Dictionary");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(new BorderLayout(10, 10));
		
		//jlist.setVisibleRowCount(30);
		//jlbTitle.setFont(new Font("Microsoft YaHei UI",1,30));
		jlbInput.setFont(font);
		jtfInput.setFont(font);
		jbtSearch.setFont(font);
		//jtfBaiDu.setFont(font);
		//jtfYouDao.setFont(font);
		//jtfBing.setFont(font);
		jcBaidu.setFont(font1);
		jcYouDao.setFont(font1);
		jcBing.setFont(font1);
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
		offlineUser.setFont(font);
		//sendCard.setFont(font);
		//addFriends.setFont(font);
		//onlineFriends.setFont(font);
		//offlineFriends.setFont(font);
        ///////////////////���/////////////////////
		//userMenu.add(addFriends);
		userMenu.add(onlineUser);
		userMenu.add(offlineUser);
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
		right.setLayout(new BorderLayout(10, 10));
		add(right,BorderLayout.EAST);
		checkbox.setLayout(new GridLayout(1,3));
		checkbox.add(jcBaidu);
		checkbox.add(jcYouDao);
		checkbox.add(jcBing);
		right.add(checkbox, BorderLayout.NORTH);
		right.add(jpMeaning, BorderLayout.CENTER);
		jpSearch.setLayout(new BorderLayout(10, 10));
		jpSearch.add(jlbInput,BorderLayout.WEST);
		jpSearch.add(jtfInput,BorderLayout.CENTER);
		jpSearch.add(jbtSearch, BorderLayout.EAST);
		add(jpSearch,BorderLayout.NORTH);
		
		jpMeaning.setLayout(new BorderLayout(10,10));
		jpBaidu.setLayout(new BorderLayout(10, 10));
		jpBaidu.add(jtfBaiDu,BorderLayout.CENTER);
		jpBaidu.add(zanBaiDu,BorderLayout.EAST);
		jpBaidu.add(jlbbaidu,BorderLayout.WEST);
		jpMeaning.add(jpBaidu,BorderLayout.NORTH);    ////位置需要更改
		jpYouDao.setLayout(new BorderLayout(10, 10));
		jpYouDao.add(zanYouDao, BorderLayout.EAST);
		jpYouDao.add(jtfYouDao,BorderLayout.CENTER);
		jpYouDao.add(jlbyoudao, BorderLayout.WEST);
		jpMeaning.add(jpYouDao,BorderLayout.CENTER);  ///位置需要更改
		jpBing.setLayout(new BorderLayout(10, 10));
		jpBing.add(zanBing, BorderLayout.EAST);
		jpBing.add(jtfBing, BorderLayout.CENTER);
		jpBing.add(jlbbing, BorderLayout.WEST);
		jpMeaning.add(jpBing,BorderLayout.SOUTH);   ///位置需要更改
		
		add(new JScrollPane(jlist),BorderLayout.CENTER);
		//jpBaidu.setVisible(false);
		//jpBing.setVisible(false);
		//jpYouDao.setVisible(false);
		//this.validate();
		pack();
		helpMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(null,"This is an online dictionary","Help",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		/////search
		jbtSearch.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		jpBaidu.setVisible(false);
	    		jpBing.setVisible(false);
	    		jpYouDao.setVisible(false);
	    		zanBaiDu.setIcon(img);
	    		zanBing.setIcon(img);
	    		zanYouDao.setIcon(img);
	    		String word=jtfInput.getText();
	    		String meaningOfBaidu="";
	    		String meaningOfBing="";
	    		String meaningOfYouDao="";
	    		boolean baiduSelected=jcBaidu.isSelected();
	    		boolean youDaoSelected=jcYouDao.isSelected();
	    		boolean bingSelected=jcBing.isSelected();
	    		if(deal.checkLegality(word))
	    		{
	    			jtfBaiDu.setText(meaningOfBaidu);
	    			jtfYouDao.setText(meaningOfYouDao);
	    			jtfBing.setText(meaningOfBing);
	    			//如果一个都没有选的话，就全部显示
	    			if((!baiduSelected)&&(!youDaoSelected)&&(!bingSelected))
	    			{
	    				baiduSelected=true;
	    				youDaoSelected=true;
	    				bingSelected=true;
	    			}
	    			try {
		    			if(baiduSelected)
		    			{
		    				//String meaning;
		    				toServer.write("4 baidu get " + word + "\n");
							toServer.flush();
							likeOFBaiDu = Integer.parseInt(fromServer.readLine()); ///从数据库获取
		    				meaningOfBaidu = deal.getMeaningFromBaiDu(word);
		    				jtfBaiDu.setText(meaningOfBaidu);
		    		//		System.out.println(meaning);
		    			}
		    			if(youDaoSelected)
		    			{
		    				toServer.write("4 youdao get " + word + "\n");
							toServer.flush();
		    				likeOFYouDao = Integer.parseInt(fromServer.readLine());;  ///从数据库获取
		    				meaningOfYouDao=deal.getMeaningFromYouDao(word);
		    				jtfYouDao.setText(meaningOfYouDao);
		    			}
		    			if(bingSelected)
		    			{
		    				toServer.write("4 bing get " + word + "\n");
							toServer.flush();
		    				likeOFBing = Integer.parseInt(fromServer.readLine());;    //从数据库获取
		    				meaningOfBing=deal.getMeaningFromBing(word);
		    				jtfBing.setText(meaningOfBing);
		    			}
		    	//		toServer.flush();
	    			}
	    			catch(IOException ex) {
						System.out.println(ex);
					}
	    		}
	    		
	    		int placeOfBaidu = 1;
	    		int placeOfYouDao = 2;
	    		int placeOfBing = 3;
	    		
	    		int[][] pos = {{likeOFBaiDu, 1},{likeOFYouDao, 2},{likeOFBing, 3}};
	    		pos = getPlace(pos);
	    		for (int i = 0; i < 3; i++) {
	    			if(pos[i][1] == 1) {
	    				placeOfBaidu=pos[i][0];
	    			}
	    			else if(pos[i][1] == 2) {
	    				placeOfYouDao=pos[i][0];
	    			}
	    			else if(pos[i][1] == 3) {
	    				placeOfBing=pos[i][0];
	    			}
	    		}
	    /*		int placeOfBaidu=getPlace(likeOFBaiDu,likeOFYouDao,likeOFBing);
	    		int placeOfBing=getPlace(likeOFBing,likeOFBaiDu,likeOFYouDao);
	    		int placeOfYouDao=getPlace(likeOFYouDao,likeOFBing,likeOFBaiDu);*/
	    		if(baiduSelected)
	    		{
	    			switch(placeOfBaidu)
	    			{case 1:{jpMeaning.add(jpBaidu,BorderLayout.NORTH);sendMeaning=meaningOfBaidu;}break;
	    			 case 2:jpMeaning.add(jpBaidu,BorderLayout.CENTER);break;
	    			 case 3:jpMeaning.add(jpBaidu,BorderLayout.SOUTH);break;}
	    			jpBaidu.setVisible(true);
	    		}
	    		if(youDaoSelected)
	    		{
	    			switch(placeOfYouDao)
	    			{case 1:{jpMeaning.add(jpYouDao,BorderLayout.NORTH);sendMeaning=meaningOfYouDao;}break;
	    			 case 2:jpMeaning.add(jpYouDao,BorderLayout.CENTER);break;
	    			 case 3:jpMeaning.add(jpYouDao,BorderLayout.SOUTH);break;}
	    			jpYouDao.setVisible(true);
	    		}
	    		if(bingSelected)
	    		{
	    			switch(placeOfBing)
	    			{case 1:{jpMeaning.add(jpBing,BorderLayout.NORTH);sendMeaning=meaningOfBing;}break;
	    			 case 2:jpMeaning.add(jpBing,BorderLayout.CENTER);break;
	    			 case 3:jpMeaning.add(jpBing,BorderLayout.SOUTH);break;}
	    			jpBing.setVisible(true);
	    		}
	    	}
	    });
	    //点赞
		zanBaiDu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				zanBaiDu.setIcon(imgnew);
				likeOFBaiDu++;
				try {
					toServer.write("4 baidu write " + jtfInput.getText() + " " + UserName + "\n");
					toServer.flush();
				//写回数据库
				}
				catch(IOException ex) {
					System.out.println(ex);
				}
			}
		});
		zanBing.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				 zanBing.setIcon(imgnew);
		         likeOFBing++;
		         try {
		        	 toServer.write("4 bing write " + jtfInput.getText() + " " + UserName + "\n");
		        	 toServer.flush();
				//写回数据库
				 }
				 catch(IOException ex) {
				 	 System.out.println(ex);
				 }
			}
		});
		zanYouDao.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				zanYouDao.setIcon(imgnew);
				likeOFYouDao++;
				try {
					toServer.write("4 youdao write " + jtfInput.getText() + " " + UserName + "\n");
		        	 toServer.flush();
				//写回数据库
				 }
				 catch(IOException ex) {
				 	 System.out.println(ex);
				 }
			}
		});
		
		//在线离线好友
		onlineUser.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
		//		database.createConnection();

				dfList.clear();
				try {
					toServer.write("2 " + "on" + "\n");
					toServer.flush();
					String res = fromServer.readLine();
				//	System.out.println(res);
					String[] onlineusers=res.split(" ");
					dfList.addElement("Online User :");
					for(int i=0;i<onlineusers.length;i++)
						dfList.addElement(onlineusers[i]);
					jlist.setModel(dfList);
				}
				catch(IOException ex) {
					System.out.println(ex);
				}
				
			}
		});
		offlineUser.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				database.createConnection();
				dfList.clear();
				try {
					toServer.write("2 " + "off" + "\n");
					toServer.flush();
					String res = fromServer.readLine();
					String[] offlineusers=database.getOfflineUser().split(" ");
					dfList.addElement("Offline User :");
					for(int i=0;i<offlineusers.length;i++)
						dfList.addElement(offlineusers[i]);
					jlist.setModel(dfList);
				}
				catch(IOException ex) {
					System.out.println(ex);
				}
			}
		});
		//look at user word card
		//有机会再实现吧~
		logoutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				try {
					toServer.write("3 " + UserName + "\n");
					toServer.flush();
					int res = Integer.parseInt(fromServer.readLine());
					if (res == 1) {
						FrontPage.this.setVisible(false);
						new LogInterface();
					}
				}
				catch(IOException ex) {
					System.out.println(ex);
				}
			}
		});
		
		viewMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			}
		});
		makeCard.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				/*String word=jtfInput.getText();
				WordCard wordcard=new WordCard(userName,word,sendMeaning);
				try{
					Socket socket=new Socket(host,8000);
					ObjectOutputStream toServer=new ObjectOutputStream(socket.getOutputStream());
					toServer.writeObject(wordcard);
				}
				catch(IOException ex)
				{
					System.err.println(ex);
				}*/
			}
		});
		//���͵��ʿ�	
	}
/*	int getPlace(int a,int b,int c)    //第一个参数为需要判定位置的数
	{
		if (a>=b&&a>=c)
			return 1;
		else if((b>=a&&a>=c)||(c>=a&&a>=b))
			return 2;
		else
			return 3;
	}*/
	
	void sort(int[][] pos) {
		int[] temp1 = {pos[0][0], pos[1][0], pos[2][0]};
		int[] temp2 = {pos[0][1], pos[1][1], pos[2][1]};
		int a, b;
		for (int i = 0; i < 2; i++) {
			for (int j = i+1; j < 3; j++) {
				if (temp1[i] >= temp1[j]) {
					a = temp1[i];
					temp1[i] = temp1[j];
					temp1[j] = a;
					b = temp2[i];
					temp2[i] = temp2[j];
					temp2[j] = b;
				}
			}
		}
		
		for (int i = 0; i < 3; i++) {
			pos[i][0] = temp1[i];
			pos[i][1] = temp2[i];
		}
		for (int i = 0; i < 3; i++) {
			System.out.println(pos[i][0] + " " + pos[i][1]);
		}
	}
	
	int[][] getPlace(int[][] pos) {
		
		for (int i = 0; i < 2; i++) {
			for (int j = i+1; j < 3; j++) {
				sort(pos);
				if (pos[i][0] == pos[j][0]) {
					pos[j][0]++;
				}
			}
		}
		
		
		for (int i = 0; i < 3; i++) {
			System.out.println(pos[i][0] + " " + pos[i][1]);
		}
		pos[0][0] = 3;
		pos[1][0] = 2;
		pos[2][0] = 1;
		return pos;
	}
	
	/*
	public static void main(String[] args)
	{
		FrontPage page=new FrontPage("Aviva");
	}*/
}
