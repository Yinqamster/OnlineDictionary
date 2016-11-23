package client.control;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.net.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.lang.*;

//////////////�¼�����/////////////////
public class DealAction {
	//������뵥�ʵĺϷ���
	public boolean checkLegality(String str)
	{
		Pattern p=Pattern.compile("[a-zA-Z -]+");
		Matcher m=p.matcher(str);
		if(!m.matches())
		{
			JOptionPane.showMessageDialog(null,"输入不合法，请重新输入！","Reminder",JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		return true;
	}
	//�ӰٶȻ�ȡ��Ϣ
	public String getMeaningFromBaiDu(String text)
	{
		text=text.replace(' ', '+');
		String meaning;
		String path=null;
		try{
			path="http://dict.baidu.com/s?wd=";
			path+=URLEncoder.encode(text,"UTF-8");
		}
		catch(UnsupportedEncodingException ex)
		{
			ex.printStackTrace();
		}
		meaning=getExplanation(path,1);
		//meaning=getPageInfo(path);
		//////////////////����ȡҳ�����ݲ�����//////////////////
		return meaning;
	}
	//��bing��ȡ��Ϣ
	public String getMeaningFromBing(String text)
	{
		text=text.replace(' ','+');
		String meaning;
		String path=null;
		try{
			path="http://cn.bing.com/dict/search?q=";
			path+=URLEncoder.encode(text,"UTF-8");
		}
		catch(UnsupportedEncodingException ex)
		{
			ex.printStackTrace();
		}
		meaning=getExplanation(path,3);
		//////////////////����ȡҳ�����ݲ�����//////////////////
		return meaning;
	}
	//���е���ȡ��Ϣ
	public String getMeaningFromYouDao(String text)
	{
		text=text.replace(" ", "%20");
		String meaning;
		String path=null;
		try{
			path += "http://dict.youdao.com/w/";
			path += URLEncoder.encode(text,"UTF-8");
		}
		catch(UnsupportedEncodingException ex)
		{
			ex.printStackTrace();
		}
		meaning=getExplanation(path,2);
		//////////////////����ȡҳ�����ݲ�����//////////////////
		return meaning;
	}
	
	private String getPageInfo(String path)
	{
		String pageInfo=null;
		URL url=null;
		BufferedReader input = null;
		try{
			url=new URL(path);
			input=new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			StringBuilder tmpPage = new StringBuilder();
			String tmpLine = new String();
			while((tmpLine=input.readLine())!= null){
				tmpPage.append(tmpLine+"\n");
			}
			pageInfo=tmpPage.toString();
			input.close();
		}
		catch(MalformedURLException ex)
		{
			pageInfo="URL not found";
		}
		catch(IOException e)
		{
			pageInfo=e.getMessage();
		}
		//pageInfo="here";
		return pageInfo;
	}
	//�ٶ�1���е�2��bing3
	private String getExplanation(String path,int type)
	{
		String pageInfo=getPageInfo(path);
		String explanation=" ";
		int index=0;
		switch(type)
		{
		case 1:{
			index=pageInfo.indexOf("<div><p><strong>art.</strong><span>");
			while(pageInfo.charAt(index)!='易')
			{
				explanation+=pageInfo.charAt(index);
				index++;
			}
			index=0;
		}break;
		case 3:{
			index=pageInfo.indexOf("必应词典为您提供");
			while(pageInfo.charAt(index)!='\"')
			{
				explanation+=pageInfo.charAt(index);
				index++;
			}
			//////////////////////�ƺ�û�취��Ӧ\n
			index=0;
			explanation.replace(';', '\n');
		}break;
		}
		return explanation;
	}
}
