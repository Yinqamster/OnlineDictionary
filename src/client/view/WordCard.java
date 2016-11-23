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
public class WordCard extends JFrame{
	Font font=new Font("Microsoft YaHei UI",0,20);
	
	public WordCard(String userName,String word,String explanation)
	{
		setSize(500,350);
		setTitle("���ʿ�");
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
		///setbounds��λ�ú��񲻴�ԣ���Ȼ������û����
		jtaMeaning.setBounds(10,20,450,210);;
		add(jtaMeaning);
				
	}
	//��������������
	/////////////////�ƺ�\n��\t��ת�����û����//////////////////
	/*public static void main(String[] args)
	{
		WordCard card=new WordCard("user","null","һ��");
	}*/
}
