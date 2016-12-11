package client.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
/////////////////���ʿ�,��ӵ�cardLayout��/////////////////////
public class WordCard extends JPanel{
	Font font=new Font("Microsoft YaHei UI",0,20);
	
	public WordCard(String userName,String word,String explanation)
	{
		//setLayout(new GridLayout(3,1));
		setSize(500,350);
		//setTitle("���ʿ�");
		//setLocationRelativeTo(null);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(new BorderLayout());
		
		JLabel jtaSender=new JLabel("From: "+userName);
		jtaSender.setFont(font);
		//jtaSender.setBounds(10, 10, 450, 30);
		add(jtaSender,BorderLayout.NORTH);
		
		JLabel jtaWord=new JLabel("Word: "+word);
		jtaWord.setFont(font);
		//jtaWord.setBounds(10,60,450,30);
		add(jtaWord,BorderLayout.CENTER);
		
		JLabel jtaMeaning=new JLabel("Explanation: "+explanation);
		jtaMeaning.setFont(font);
		///setbounds��λ�ú��񲻴�ԣ���Ȼ������û����
		//jtaMeaning.setBounds(10,20,450,210);;
		add(jtaMeaning,BorderLayout.SOUTH);
				
	}
}
