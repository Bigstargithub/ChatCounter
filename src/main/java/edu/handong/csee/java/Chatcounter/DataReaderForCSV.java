package edu.handong.csee.java.Chatcounter;


import java.io.BufferedReader;
//import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;



public class DataReaderForCSV extends DataReader
{
	 static ArrayList<String> namearraycsv = new ArrayList<String>();
	 static ArrayList<String> messagearraycsv = new ArrayList<String>();
	 static ArrayList<String> messagelistcsv = new ArrayList<String>();
	 static ArrayList<String> date = new ArrayList<String>();
	 static ArrayList<String> user = new ArrayList<String>();
	 static ArrayList<String> strMessage = new ArrayList<String>();
	 
	 int i = 0;
	 int c;
	public void readcsv(String r2) throws IOException 
	{
	  MessageFilter MF = new MessageFilter();
	  ArrayList<String> count2 = new ArrayList();
	  try
	  {
		 
		 File file = new File(r2);
		 BufferedReader br = new BufferedReader(new FileReader(file));
		 Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(br);
		 
		 for(CSVRecord record : records)
		 {
			 date.add(record.get(0).substring(11,19)) ;
			 user.add(record.get(1));
			 strMessage.add(record.get(2));
		 }
			    
			  //for(a=0;a<user.size();a++)
			  //{
				//if(Chatmessage.get(user.get(a)).size() == 0 )
				//{
			//Chatmessage.get(user.get(a)).add(messagecsv(date.get(a),strMessage.get(a)));
				//}
				
			 namearraycsv.add(user.get(i));
			 messagearraycsv.add(messagecsv(date.get(i),strMessage.get(i)));
			
			 i++;
			 
		 //}
		 
		 
		 for(int h = 0 ;h<messagearraycsv.size();h++)
		 {
		  //System.out.println(messagearraycsv.get(h));
		 }
		    
		 
	  }
	  
	  catch(FileNotFoundException e)
	  {
		 System.out.println("There is not file.");
		 System.exit(0);
	  }
	  
  }
	
	public String namecsv(String Line)
	{
		String g = "[";
		if(Line.substring(0, 1).equals(g)) 
		{
		String name = "";
		String pattern = "([0-9]+)-([0-9]+)-([0-9]+)\\s([0-9]+):([0-9]+):([0-9]+)^\\s(.+)^\\s(.+)";
 		Pattern r = Pattern.compile(pattern);
 		Matcher m = r.matcher(Line);
 		if(m.find())
 		 {
		  name = m.group(7);
		  //System.out.println(name);
		  names.add(name);
 		 }
 		System.out.println(name);
 		  return name;
		}
		else return null;
	}
	
	public String messagecsv(String data, String strmessage)
	{
		try 
		{
		 if(strmessage.equals("사진"))
		 {
			strmessage = "Photo";
		 }
		 
		 return "" + data + "" + " " + strmessage + "";
		}
		
		catch(NullPointerException e)
		{
		 strmessage = "";
		}
		return strmessage;
	}
	
	public void addHashmap()
	{
		
		for(int a = 0; a<names2.size();a++)
		{
		 if(!Chatmessage.containsKey(names2.get(a)))
		  {
			Chatmessage.put(names2.get(a), new ArrayList<String>());
		  }
		}
		Iterator iterator = Chatmessage.entrySet().iterator();
		Entry entry;
		while(iterator.hasNext())
		{
		  entry = (Entry)iterator.next();
		  listentry.add((String) entry.getKey());
		}
		
		i = 0;
	   for(i = 0 ; i<user.size();i++)
	   {
		 for(int b = 0; b<listentry.size();b++)
		 {
			if(user.get(i).equals(listentry.get(b)))
            {  
			   for(c =0; c<Chatmessage.get(listentry.get(b)).size();c++)
			   {
                if(Chatmessage.get(listentry.get(b)).get(c).equals(messagecsv(date.get(i),strMessage.get(i))))
                {
            	   break;
                }
			   }
			   if(c>=Chatmessage.get(listentry.get(b)).size())
			   {
				   Chatmessage.get(listentry.get(b)).add(messagecsv(date.get(i),strMessage.get(i)));
			   }
			   //System.out.println(Chatmessage);
            }
		 }	
	   }
			  
		//System.out.println(listentry);	  
     } 
			
	    
		
	
	
}
