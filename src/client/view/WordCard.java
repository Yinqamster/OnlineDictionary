package client.view;

public class WordCard{
	private String userName=null;
	private String word=null;
	private String explanation=null;
	public WordCard(String userName,String word,String explanation)
	{
		this.userName=userName;
		this.word=word;
		this.explanation=explanation;
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
