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
/////////////////单词卡,添加到cardLayout中/////////////////////
public class WordCard extends JFrame{
	Font font=new Font("Microsoft YaHei UI",0,20);
	
	public WordCard(String userName,String word,String explanation)
	{
		setSize(500,350);
		setTitle("单词卡");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(null);
		
		JLabel jtaSender=new JLabel("From: "+userName);
		jtaSender.setFont(font);
		jtaSender.setBounds(10, 10, 450, 30);
		add(jtaSender);
		
		JLabel jtaWord=new JLabel("Word: "+word);
		jtaWord.setFont(font);
		jtaWord.setBounds(10,60,450,30);
		add(jtaWord);
		
		JLabel jtaMeaning=new JLabel("Explanation: "+explanation);
		jtaMeaning.setFont(font);
		///setbounds的位置好像不大对，虽然看起来没问题
		jtaMeaning.setBounds(10,20,450,210);;
		add(jtaMeaning);
				
	}
	//主函数用来测试
	/////////////////似乎\n和\t等转义符都没有用//////////////////
	/*public static void main(String[] args)
	{
		WordCard card=new WordCard("user","null","一个");
	}*/
}
