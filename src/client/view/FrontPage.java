package client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class FrontPage extends JFrame{
	private BufferedWriter toServer;
	private BufferedReader fromServer;
//	InteractionWithServer task;
	int judgezanbaidu=0; //判断是点赞还是取消
	int judgezanyoudao=0;
	int judgezanbing=0;
	int numOfWordCard=0;
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
	//DefaultListSelectionModel slList=new DefaultListSelectionModel();
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
		try {
			fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			toServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	//		task = new InteractionWithServer(socket);
	//		task.start();
		//	while (true) {
			Timer timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask() {  
	            public void run() {  
	  //              System.out.println("-------设定要指定任务--------"); 
	                try {
		                Receive task = new Receive(socket);
		    //			new Thread(task).start();;
		                Thread t = new Thread(task);
		    			t.start();
		    			t.join();
		    			t = null;
	            	}
	            	catch(InterruptedException i) {
	            		System.out.println(i);
	            	}
	            }  
	        }, 1000, 10000);
			
		//	}
		}
		catch(IOException ex) {
			System.out.println(ex);
		}
		
		//////////////////////////////////////不知道能不能这么写
		/////////////////////////////////////////////////jlist.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
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
	    		likeOFBaiDu=-1;
	    		likeOFYouDao=-1;
	    		likeOFBing=-1;
	    		jpBaidu.setVisible(false);
	    		jpBing.setVisible(false);
	    		jpYouDao.setVisible(false);
	    	/*	jpMeaning.remove(jpBaidu);
	    		jpMeaning.remove(jpBing);
	    		jpMeaning.remove(jpYouDao);
	    		jpMeaning.repaint();*/
	    		
	    		zanBaiDu.setIcon(img);
	    		zanBing.setIcon(img);
	    		zanYouDao.setIcon(img);
	    		String word=jtfInput.getText();
	    		String meaningOfBaidu="please input";
	    		String meaningOfBing="please input";
	    		String meaningOfYouDao="please input";
	    		boolean baiduSelected=jcBaidu.isSelected();
	    		boolean youDaoSelected=jcYouDao.isSelected();
	    		boolean bingSelected=jcBing.isSelected();
	    	/*	int placeOfBaidu = 0;
	    		int placeOfYouDao = 0;
	    		int placeOfBing = 0;
	    		int position = 0;*/
	    		int seleNo = 0;
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
	    				
	    			/*	placeOfBaidu = 1;
			    		placeOfYouDao = 2;
			    		placeOfBing = 3;*/
	    			}
	    			try {
		    			if(baiduSelected)
		    			{
		    				//String meaning;
		    			//	placeOfBaidu= position++;
		    				seleNo++;
		    				toServer.write("4 baidu get " + word + "\n");
							toServer.flush();
					//		task.join(100);
							likeOFBaiDu = Integer.parseInt(fromServer.readLine()); ///从数据库获取
					//		likeOFBaiDu = Integer.parseInt(task.getResStr());
							meaningOfBaidu = deal.getMeaningFromBaiDu(word);
		    				jtfBaiDu.setText(meaningOfBaidu);
		    		//		System.out.println(meaning);
		    			}
		    			if(youDaoSelected)
		    			{
		    			//	placeOfYouDao= position++;
		    				seleNo++;
		    				toServer.write("4 youdao get " + word + "\n");
							toServer.flush();
					//		task.join(100);
							
		    				likeOFYouDao = Integer.parseInt(fromServer.readLine());  ///从数据库获取
						//	likeOFYouDao = Integer.parseInt(task.getResStr());
							meaningOfYouDao=deal.getMeaningFromYouDao(word);
		    				jtfYouDao.setText(meaningOfYouDao);
		    			}
		    			if(bingSelected)
		    			{
		    			//	placeOfBing= position++;
		    				seleNo++;
		    				toServer.write("4 bing get " + word + "\n");
							toServer.flush();
					//		task.join(100);
							
		    				likeOFBing = Integer.parseInt(fromServer.readLine());    //从数据库获取
		    			//	likeOFBing = Integer.parseInt(task.getResStr()); 
		    				meaningOfBing=deal.getMeaningFromBing(word);
		    				jtfBing.setText(meaningOfBing);
		    			}
		    	//		toServer.flush();
	    			}
	    			catch(IOException ex) {
						System.out.println(ex);
					}
	    	/*		catch(InterruptedException i) {
						System.out.println(i);
					}*/
	    			
	    		}
	    		else {
	    			jtfBaiDu.setText(meaningOfBaidu);
	    			jtfYouDao.setText(meaningOfYouDao);
	    			jtfBing.setText(meaningOfBing);
	    			baiduSelected=true;
    				youDaoSelected=true;
    				bingSelected=true;
	    		}
	    		
	    		
	    		int placeOfBaidu = 0;
	    		int placeOfYouDao = 0;
	    		int placeOfBing = 0;
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
	   // 		System.out.println(sendMeaning);
	    	//	jpMeaning.repaint();
	    	}
	    });
	    //点赞
		zanBaiDu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				judgezanbaidu++;
				if(judgezanbaidu%2==1)
				{
				zanBaiDu.setIcon(imgnew);
				likeOFBaiDu++;
				}
				else
				{
					zanBaiDu.setIcon(img);
					likeOFBaiDu--;
				}
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
				judgezanbing++;
				if(judgezanbing%2==1)
				{
				 zanBing.setIcon(imgnew);
		         likeOFBing++;}
				else{
				zanBing.setIcon(img);
				likeOFBing--;
				}
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
				judgezanyoudao++;
				if(judgezanyoudao%2==1)
				{
				zanYouDao.setIcon(imgnew);
				likeOFYouDao++;}
				else
				{
				zanYouDao.setIcon(img);
				likeOFYouDao--;
				}
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
					
				//	task.join(1000);
					String res = fromServer.readLine();
				//	String res = task.getResStr();
				//	System.out.println(res);
					String[] onlineusers=res.split(" ");	
					if (onlineusers[0].equals("on")) {
						dfList.addElement("Online User :");
						for(int i=1;i<onlineusers.length;i++)
							dfList.addElement(onlineusers[i]);
						jlist.setModel(dfList);
					}
				}
				catch(IOException ex) {
					System.out.println(ex);
				}
		/*		catch(InterruptedException i) {
					System.out.println(i);
				}*/
				
			}
		});
		offlineUser.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				database.createConnection();
				dfList.clear();
				try {
					toServer.write("2 " + "off" + "\n");
					toServer.flush();
			//		task.join(1000);
					String res = fromServer.readLine();
				//	String res = task.getResStr();
				//	String[] offlineusers=database.getOfflineUser().split(" ");
					String[] offlineusers=res.split(" ");
					if (offlineusers[0].equals("off")) {
						dfList.addElement("Offline User :");
						for(int i=1;i<offlineusers.length;i++)
							dfList.addElement(offlineusers[i]);
						jlist.setModel(dfList);
					}
				}
				catch(IOException ex) {
					System.out.println(ex);
				}
		/*		catch(InterruptedException i) {
					System.out.println(i);
				}*/
			}
		});
		//退出登录
		logoutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				try {
					toServer.write("3 " + UserName + "\n");
					toServer.flush();
				//	task.join(100);
					int res = Integer.parseInt(fromServer.readLine());
				//	int res = task.getResNum();
					if (res == 3) {
						FrontPage.this.setVisible(false);
					//	task = null;
						new LogInterface();
					}
				}
				catch(IOException ex) {
					System.out.println(ex);
				}
		/*		catch(InterruptedException i) {
					System.out.println(i);
				}*/
			}
		});
		//查看我的单词卡
		viewMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				WordCardSet set=new WordCardSet(numOfWordCard);
			}
		});
		//生成单词卡
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
				String word=jtfInput.getText();
				if(word!=null)
				{
					jlist.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
					jlist.repaint();
					numOfWordCard++;
					//String word=jtfInput.getText();
					WordCard wordcard=new WordCard(userName,word,sendMeaning);
					try {
						//createImage(wordcard,new Font("Microsoft YaHei UI",0,20),new File("D:\\desktop\\"+numOfWordCard+".png"),500,300);
						File s=new File("D:\\Desktop\\"+numOfWordCard+".png");
						s.canWrite();
						createImage(wordcard,new Font("Microsoft YaHei UI",0,20),s,500,300);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//WordCardSet set=new WordCardSet(numOfWordCard);
					JOptionPane.showMessageDialog(null,"生成成功!可点击查看菜单查看或点击左侧用户发送","Notice",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null,"请先进行单词查询再生成单词卡","Notice",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		//发送给指定的用户，点击jlist选择

		jlist.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				//@SuppressWarnings("deprecation")
								
				String names=jlist.getSelectedValuesList().toString();
				names = names.substring(1, names.length()-1); 
				//System.out.println(names.get(0));
				jlist.repaint();
				String word=jtfInput.getText();
				WordCard wordcard=new WordCard(names, userName,word,sendMeaning);
				int n=JOptionPane.showConfirmDialog(null,"是否发送给用户"+names,"Notice",JOptionPane.OK_CANCEL_OPTION);
				System.out.println(n);
		//		System.out.println(names + " " + word + " " + sendMeaning);
				if(n==0)
		//		System.out.println("times");
				{
				try {
				//	ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				//	out.writeObject(wordcard);
				//	out.flush();
					toServer.write("5 " + names + " " + userName + " " + word + " " + sendMeaning + "\n");
					toServer.flush();
					
					
				}
				catch(IOException ex) {
					System.out.println(ex);
				}}
				
			}
		});
//		receive();
		//���͵��ʿ�	
//		try {
		//	while (true) {
		//	Receive task = new Receive(socket);
		//	new Thread(task).start();
		//	}
		}
	//
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
	public static void createImage(client.view.WordCard word,Font font, File outFile,
			Integer width, Integer height) throws Exception {
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_BGR);
		Graphics g = image.getGraphics();
		g.setClip(0, 0, width, height);
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);// 先用黑色填充整张图片,也就是背景
		g.setColor(Color.red);// 在换成黑色
		g.setFont(font);// 设置画笔字体
		/** 用于获得垂直居中y */
		/*Rectangle clip = g.getClipBounds();
		FontMetrics fm = g.getFontMetrics(font);
		int ascent = fm.getAscent();
		int descent = fm.getDescent();
		int y = (clip.height - (ascent + descent)) / 2 + ascent;
		for (int i = 0; i < 6; i++) {// 256 340 0 680
			g.drawString(str, i * 680, y);// 画出字符串
		}*/
		g.drawString("User: "+word.getUserName(),20,50);// 画出字符串
		g.drawString("Word: "+word.getWord(),20,150);
		g.drawString("Meaning: "+word.getExplanation(),20,250);
		g.dispose();
		ImageIO.write(image, "png", outFile);// 输出png图片
	}
	

	
	
	class Receive implements Runnable{
		private BufferedWriter toServer;
		private BufferedReader fromServer;
		
		Socket socket;
		Receive(Socket socket) {
			this.socket = socket;
		}
		public void run() {
			try {
				fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				toServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				
			//	Timer timer = new Timer();  
		  //      timer.scheduleAtFixedRate(new TimerTask() {  
		   //         public void run() {  
		             //   System.out.println("-------设定要指定任务--------"); 
		     //       	try {
		            		toServer.write("receive " + userName + "\n");
		            		toServer.flush();
		            		String str = fromServer.readLine();
		            		System.out.println("receive?? " + str);
		            		String[] words = str.split(" ");
		            		if (words[0].equals("yes")) {
		            			String name = words[1];
		            			String word = words[2];
		            			String meaning = words[3];
		            			int n=JOptionPane.showConfirmDialog(null,"您收到来自用户"+name+"的单词卡，是否接受?","Notice",JOptionPane.OK_CANCEL_OPTION);
		            			if(n==0)
		            			{
		            				numOfWordCard++;
		            				WordCard newcard=new WordCard(name,word,meaning);
		            				File s=new File("D:\\Desktop\\"+numOfWordCard+".png");
		        					s.canWrite();
		        					createImage(newcard,new Font("Microsoft YaHei UI",0,20),s,500,300);
		        					JOptionPane.showMessageDialog(null,"接受成功，可在我的单词卡查看","Notice",JOptionPane.INFORMATION_MESSAGE);
		        					
		            			}
		            			//System.out.println(name);
		            			//System.out.println(word);
		            			//System.out.println(meaning);
		            		}
		//            	}
		 //           	catch(IOException e) {
		 //           		System.out.println(e);
		  //          	}
		   //         }  
		  //      }, 1000, 2000);  
		        
		        
			}
			catch(IOException e){
				System.out.println(e);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*
	public static void main(String[] args)
	{
		FrontPage page=new FrontPage("Aviva");
	}*/
}


