package client.view;

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
	private JButton jbtOK=new JButton("Ok");
	//private JButton jbtsend=new JButton("send");
	//private JButton jbtdelete=new JButton("delete");
	private int num_of_wordcard;
	//WordCard[] word=new WordCard[50];
	
	public  WordCardSet(int num_of_wordcard)
	{
		this.num_of_wordcard=num_of_wordcard;
		jbtFirst.setFont(font);
		jbtNext.setFont(font);
		jbtPrevious.setFont(font);
		jbtLast.setFont(font);
		jbtOK.setFont(font);
		//jbtsend.setFont(font);
		//jbtdelete.setFont(font);
		cardpanel.setBorder(new LineBorder(Color.BLACK));
		setSize(800,800);
		setTitle("Word Card Set");
		setLocationRelativeTo(null);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		JPanel p=new JPanel();
		for(int i=1;i<=num_of_wordcard;i++)
		{

			ImageIcon icon=new ImageIcon("C:\\Users\\aviva\\Desktop\\WordCard\\"+num_of_wordcard+".png");
//			ImageIcon icon=new ImageIcon("C:\\Users\\aviva\\Desktop\\WordCard\\"+i+".png");
			icon.setImage((icon.getImage().getScaledInstance(500,300,Image.SCALE_DEFAULT)));
			JLabel label=new JLabel(icon);
			//cardpanel.add(word[i],String.valueOf(i));
			cardpanel.add(label, String.valueOf(i));
		}
		p.add(jbtFirst);
		p.add(jbtNext);
		p.add(jbtPrevious);
		p.add(jbtLast);
		p.add(jbtOK);
		//p.add(jbtsend);
		//p.add(jbtdelete);
		add(p,BorderLayout.SOUTH);
		add(cardpanel,BorderLayout.CENTER);
		pack();
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
		jbtOK.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				//cardlayout.last(cardpanel);
				setVisible(false);
			}
		});
	}
	/*public static void main(String[] args)
	{
		WordCardSet n=new WordCardSet(4);
	}*/
}
