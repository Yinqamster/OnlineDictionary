package client.control;
import javax.imageio.ImageIO;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.swing.*;

import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.*;
import java.util.List;

import org.apache.http.HttpEntity;  
import org.apache.http.HttpResponse;  
import org.apache.http.client.ClientProtocolException;  
import org.apache.http.client.HttpClient;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.util.EntityUtils;  
import org.apache.http.impl.client.DefaultHttpClient;  
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.memetix.mst.language.Language;  
import com.memetix.mst.translate.Translate;  

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


//////////////锟铰硷拷锟斤拷锟斤拷/////////////////
public class DealAction {
	
	String word=null;
	boolean b=true;
	HttpClient client=new DefaultHttpClient();
	private static final char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
            'e', 'f' };
	//check whether the input is legal
	public boolean checkLegality(String str)
	{
		Pattern p=Pattern.compile("[a-zA-Z -]+");
		Matcher m=p.matcher(str);
		if(!m.matches())
		{
			JOptionPane.showMessageDialog(null,"Input Wrong!","Reminder",JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		return true;
	}
	
	//byte to hex
	private static String byteArrayToHex(byte[] byteArray) {
        char[] resultCharArray = new char[byteArray.length * 2];
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }
        return new String(resultCharArray);

    }
	//md5
	 public static String md5(String input) throws UnsupportedEncodingException {
	        if (input == null)
	            return null;

	        try {
	            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
	            byte[] inputByteArray = input.getBytes("utf-8");
	            messageDigest.update(inputByteArray);
	            byte[] resultByteArray = messageDigest.digest();
	            return byteArrayToHex(resultByteArray);
	        } catch (NoSuchAlgorithmException e) {
	            return null;
	        }
	    }

	//get  meaning from baidu
	public String getMeaningFromBaiDu(String word)
	{
		String retr=null;
		//String url="http://api.fanyi.baidu.com/api/trans/vip/translate?q="+word+"&from=zh&to=en&appid=20160413000018571&salt=1435660288&sign=39c436658c2f20730931b5139094c0a7";
		//String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";
		String appid="20161205000033481";
		String securityKey="MubEL_Y3Sjwk3F4flv4h";
		String salt = String.valueOf(11111);
        String src = appid + word + salt + securityKey; 
        String sign=null;
		try {
			sign = md5(src);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        String url="http://api.fanyi.baidu.com/api/trans/vip/translate?q="+word+"&from=en&to=zh&appid=20161205000033481&salt="+salt+"&sign="+sign;
        //Element paragraph = null;
        HttpGet get = new HttpGet(url);
        try{
        	HttpResponse response = client.execute(get);
        	HttpEntity entity = response.getEntity();
        	InputStream returnStream = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(returnStream,"UTF-8"));
            StringBuilder result = new StringBuilder();
            String str = null;
            while ((str = reader.readLine()) != null) {
                result.append(str).append("\n");
            }
      //      System.out.println(result);
            try {
				JSONObject resultJson = new JSONObject(result.toString());
				 JSONArray array = (JSONArray) resultJson.get("trans_result");
			     JSONObject dst = (JSONObject) array.get(0);
			     retr = dst.getString("dst");
			     retr = URLDecoder.decode(retr,"UTF-8");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
            //JSONObject resultJson = new JSONObject(result.toString());
        catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
        return retr;
	}

	//get meaning from bing
	public String getMeaningFromBing(String word)
	{
		Translate.setClientId("1ef3d644-5a79-433f-ad59-1a042b76ddda");
	//	Translate.setClientSecret("Z13DcWtKcLNTtKEWL+XIP81WUqxF01wcQOgGYDW8MSI");
		Translate.setClientSecret("xamoWoyARVJXixFIkjFL8hfvZMF0/MPcytUfbnyPRuo=");
		String translatedText=null;
		try {
			translatedText = Translate.execute(word, Language.ENGLISH, Language.CHINESE_SIMPLIFIED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return translatedText;
	}
	
	//get meaning from youdao
	public String getMeaningFromYouDao(String word)
	{
		String text="";
		//Element paragraph = null;
		HttpGet get = new HttpGet(
				"http://fanyi.youdao.com/openapi.do?keyfrom=youdao111&key=60638690&type=data&doctype=xml&version=1.1&q="
						+ word);
		try{
			//HttpResponse response = client.execute(get);
			//HttpEntity entity = response.getEntity();
			//String str = EntityUtils.toString(entity, "UTF-8");
			//JSONObject json = new JSONObject(str);
			//JSONArray array = (JSONArray) json.get("有道翻译");
			HttpResponse response = client.execute(get);
        	HttpEntity entity = response.getEntity();
        	InputStream returnStream = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(returnStream,"UTF-8"));
            StringBuilder result = new StringBuilder();
            String str = null;
            while ((str = reader.readLine()) != null) {
                result.append(str).append("\n");
            }
   //         System.out.println(result);
            int re1=result.indexOf("<us-phonetic><![CDATA");
            int re2=result.indexOf("]></us-phonetic>");
            if (re1 != -1) {
	            text+="音标:";
	            text+=result.substring(re1+21, re2);
            }
            text+="\n基本释义:";
            re1=result.indexOf("<paragraph><![CDATA[");
            re2=result.indexOf("]]></paragraph>");
            text+=result.substring(re1 + 20, re2);
            text+="\n网络释义:";
            re1 =result.indexOf("<ex><![CDATA[");
            re2 =result.indexOf("]]></ex>");
            text+=result.substring(re1 + 13, re2);
			/*org.dom4j.Document doc = (org.dom4j.Document) DocumentHelper
					.parseText(str);
			Element root = (Element) doc.getRootElement();
			Element query = root.element("query");
			List clist = root.elements();
			//System.out.println("原文:" + query.getText());
			paragraph = (Element) root.element("translation")
					.element("paragraph");
			//System.out.println("翻译:" + paragraph.getText());*/
			}
		catch (ClientProtocolException e) {
			b = false;
			e.printStackTrace();
		} catch (IOException e) {
			b = false;
			e.printStackTrace();
		}
//		System.out.println(text);
		return text;
/*		Element paragraph = null;
		HttpGet get = new HttpGet(
				"http://fanyi.youdao.com/openapi.do?keyfrom=youdao111&key=60638690&type=data&doctype=xml&version=1.1&q="
						+ word);
		try{
			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			String str = EntityUtils.toString(entity, "UTF-8");
			System.out.println(str);
			org.dom4j.Document doc = (org.dom4j.Document) DocumentHelper
					.parseText(str);
			Element root = (Element) doc.getRootElement();
			Element query = root.element("query");
			List clist = root.elements();
			//System.out.println("原文:" + query.getText());
			paragraph = (Element) root.element("translation")
					.element("paragraph");
			//System.out.println("翻译:" + paragraph.getText());
			}
		catch (ClientProtocolException e) {
			b = false;
			e.printStackTrace();
		} catch (IOException e) {
			b = false;
			e.printStackTrace();
		} catch (DocumentException e1) {
			b = false;
			e1.printStackTrace();
		}
		return paragraph.getText();*/
	}
	//生成图片
	
}
	
