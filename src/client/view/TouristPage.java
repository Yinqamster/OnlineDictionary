package client.view;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
/////////////////////锟轿客斤拷锟斤拷//////////////////////
public class TouristPage extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private Font font=new Font("Microsoft YaHei UI",0,20);
	private Font font1=new Font("Microsoft YaHei UI",0,25);
	private JLabel jlbTitle=new JLabel("Online Dictionary");
	private JLabel jlbInput=new JLabel("Input");
	private JTextField jtfInput=new JTextField();
	private JCheckBox jcBaidu=new JCheckBox("百度");
	private JCheckBox jcYouDao=new JCheckBox("有道");
	private JCheckBox jcBing=new JCheckBox("必应");
	private JButton jbtSearch=new JButton("Search");
	private JButton jbtTouristLogin=new JButton("Login");
	private TitledBorder jlbBaiDu=new TitledBorder("Baidu");
	private JTextArea jtBaiDu=new JTextArea();
	private TitledBorder jlbYouDao=new TitledBorder("YouDao");
	private JTextArea jtYouDao=new JTextArea();
	private TitledBorder jlbBing=new TitledBorder("Bing");
	private JTextArea jtBing=new JTextArea();
	private client.control.DealAction deal=new client.control.DealAction();
	
	public TouristPage()
	{
		/////////锟斤拷锟�/////////////////////
		jtBaiDu.setLineWrap(true);
		jtYouDao.setLineWrap(true);
		jtBing.setLineWrap(true);
		
		setSize(800,760);
		setTitle("Online Dictionary");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(null);
		///Title
		jbtTouristLogin.setFont(font);
		jbtTouristLogin.setBounds(650,650,100,30);
		add(jbtTouristLogin);
		jlbTitle.setFont(new Font("Microsoft YaHei UI",1,30));
		jlbTitle.setBounds(260, 10, 300, 50);
		add(jlbTitle);
		//Input
		jlbInput.setFont(font);
		jlbInput.setBounds(20, 70, 50, 30);
		add(jlbInput);
		jtfInput.setFont(font);
		jtfInput.setBounds(80, 70, 550, 40);
		add(jtfInput);
		jbtSearch.setFont(font);
		jbtSearch.setBounds(650, 70, 100, 40);
		add(jbtSearch);
		//CheckBox
		jcBaidu.setFont(font1);
		jcBaidu.setBounds(60, 130, 100, 40);
		add(jcBaidu);
		jcYouDao.setFont(font1);
		jcYouDao.setBounds(310, 130, 100, 40);
		add(jcYouDao);
		jcBing.setFont(font1);
		jcBing.setBounds(560, 130, 100, 40);
		add(jcBing);
		//textArea
		jlbBaiDu.setTitleFont(font);
		jtBaiDu.setFont(font);
		jtBaiDu.setBorder(jlbBaiDu);
		jtBaiDu.setBounds(40,200,700,120);
		//jtBaiDu.setVisible(false);
		add(jtBaiDu);
		jlbYouDao.setTitleFont(font);
		jtYouDao.setFont(font);
		jtYouDao.setBorder(jlbYouDao);
		jtYouDao.setBounds(40,350,700,120);
		//jtYouDao.setVisible(false);
		add(jtYouDao);
		jlbBing.setTitleFont(font);
		jtBing.setFont(font);
		jtBing.setBounds(40,500,700,120);
		jtBing.setBorder(jlbBing);
		//jtBing.setVisible(false);
		add(jtBing);
		/////////////////////锟铰硷拷锟斤拷锟斤拷//////////////////
		//锟斤拷录锟斤拷锟铰硷拷锟斤拷锟斤拷
		jbtTouristLogin.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    	///////锟斤拷锟截碉拷录锟斤拷锟斤拷
	    		TouristPage.this.setVisible(false);
				new LogInterface();
	    	}
	    });
		//search锟斤拷钮锟斤拷锟铰硷拷锟斤拷锟斤拷
		jbtSearch.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		String userInput=jtfInput.getText();
	    		boolean baiduSelected=jcBaidu.isSelected();
	    		boolean youDaoSelected=jcYouDao.isSelected();
	    		boolean bingSelected=jcBing.isSelected();
	    		String meaning=null;
	    		jtBaiDu.setText(meaning);
	    		jtYouDao.setText(meaning);
	    		jtBing.setText(meaning);
	    		if(deal.checkLegality(userInput))
	    		{
	    			
	    			if(baiduSelected)
	    			{
	    				//String meaning;
						meaning = deal.getMeaningFromBaiDu(userInput);
	    				jtBaiDu.setText(meaning);
	    				//jtBaiDu.setVisible(true);
	    		//		System.out.println(meaning);
	    			}
	    			if(youDaoSelected)
	    			{
	    				meaning=deal.getMeaningFromYouDao(userInput);
	    				jtYouDao.setText(meaning);
	    				//jtYouDao.setVisible(true);
	    			}
	    			if(bingSelected)
	    			{
	    				meaning=deal.getMeaningFromBing(userInput);
	    				jtBing.setText(meaning);
	    				//jtBing.setVisible(true);
	    			}
	    		}
	    		
	    	}
		});
		jbtTouristLogin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				//cardlayout.last(cardpanel);
				setVisible(false);
			}
		});
		
	}
	
	/*
	public static void main(String[] args)
	{
		TouristPage page=new TouristPage();
	}*/
}