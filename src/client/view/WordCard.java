package client.view;

import java.io.Serializable;

public class WordCard implements Serializable{
	private String userName=null;
	private String word=null;
	private String explanation=null;
	private String toUser = null;
	
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
