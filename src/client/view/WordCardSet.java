/*package client.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class WordCardSet extends JFrame{
	private CardLayout cardlayout=new CardLayout(20,10);
	private JPanel cardpanel=new JPanel(cardlayout);
	private Font font=new Font("Microsoft YaHei UI",0,20);
	private JButton jbtFirst=new JButton("First");
	private JButton jbtNext=new JButton("Next");
	private JButton jbtPrevious=new JButton("Previous");
	private JButton jbtLast=new JButton("Last");
	private JButton jbtsend=new JButton("send");
	private JButton jbtdelete=new JButton("delete");
	private int num_of_wordcard=3;
	//WordCard[] word=new WordCard[50];
	
	public  WordCardSet()
	{
		jbtFirst.setFont(font);
		jbtNext.setFont(font);
		jbtPrevious.setFont(font);
		jbtLast.setFont(font);
		jbtsend.setFont(font);
		jbtdelete.setFont(font);
		cardpanel.setBorder(new LineBorder(Color.BLACK));
		setSize(600,500);
		setTitle("Word Card Set");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		JPanel p=new JPanel();
		for(int i=0;i<num_of_wordcard;i++)
		{
			//cardpanel.add(word[i],String.valueOf(i));
			cardpanel.add(new WordCard("1","2","3"), String.valueOf(i));
		}
		p.add(jbtFirst);
		p.add(jbtNext);
		p.add(jbtPrevious);
		p.add(jbtLast);
		p.add(jbtsend);
		p.add(jbtdelete);
		add(p,BorderLayout.SOUTH);
		add(cardpanel,BorderLayout.CENTER);
		JPanel[] word=new JPanel[5]
		//first word card is the word card added recently
		jbtFirst.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				cardlayout.first(cardpanel);
			}
		});
		jbtNext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				cardlayout.next(cardpanel);
			}
		});
		jbtPrevious.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				cardlayout.previous(cardpanel);
			}
		});
		jbtLast.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				cardlayout.last(cardpanel);
			}
		});
		jbtsend.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});
		jbtdelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
			
			}
		});
	}
	boolean addWordCard(String userName,String word,String explanation)
	{
		
		if(num_of_wordcard>=49)
		{
			JOptionPane.showMessageDialog(null,"The set is full","Notice",JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		JPanel jpword=new JPanel(new GridLayout(3,1));
		
	}
	public static void main(String[] args)
	{
		WordCardSet n=new WordCardSet();
	}
}*/
