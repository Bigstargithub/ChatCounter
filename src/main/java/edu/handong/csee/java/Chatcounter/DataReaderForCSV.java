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
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class DataReaderForCSV extends DataReader
{

	public ArrayList<String> readcsv(String r2) throws IOException 
	{
		
	  ArrayList<String> count2 = new ArrayList();
	  try
	  {
		 File file = new File(r2);
		 BufferedReader br = new BufferedReader(new FileReader(file));
		 
		 String Line;
		 br.readLine();
		 while((Line = br.readLine())!= null)
		 {
			//System.out.println(Line[2]);
			count2.add(Line);
			//System.out.println(Line);
		 }
		 
		 br.close();
	  }
	  
	  catch(FileNotFoundException e)
	  {
		 System.out.println("There is not file.");
		 System.exit(0);
	  }
	  
	  
      return count2;
    }
	
	public String namecsv(String Line)
	{
		String g = "[";
		if(Line.substring(0, 1).equals(g)) 
		{
		String name = "";
		String pattern = "[0-9]+-[0-9]+-[0-9]+-[0-9]+\\s[0-9]+:[0-9]+:[0-9]+\\\"(.+)\\\"\\,\\\"(.+)\\\"";
 		Pattern r = Pattern.compile(pattern);
 		Matcher m = r.matcher(Line);
 		if(m.find())
 		 {
		  name = m.group(1);
		  //System.out.println(name);
		  names.add(name);
 		 }
 		  return name;
		}
		else return null;
	}
}
