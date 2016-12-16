package client.view;

import java.io.Serializable;
//单词卡类
public class WordCard implements Serializable{
	private String userName=null;   //发送单词卡的用户名
	private String word=null;       //发送的单词
	private String explanation=null;//意思
	private String toUser = null;   //发送给的用户
	
	public WordCard(String toUser, String userName,String word,String explanation)
	{
		this.toUser = toUser;
		this.userName=userName;
		this.word=word;
		this.explanation=explanation;
	}
	public WordCard(String userName,String word,String explanation)
	{
		this.userName=userName;
		this.word=word;
		this.explanation=explanation;
	}
	public String getToUser() {
		return toUser;
	}
	public String getUserName()
	{
		return userName;
	}
	public String getWord()
	{
		return word;
	}
	public String getExplanation()
	{
		return explanation;
	}
}
