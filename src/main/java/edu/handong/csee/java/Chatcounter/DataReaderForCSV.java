package edu.handong.csee.java.Chatcounter;


//import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.opencsv.CSVReader;

public class DataReaderForCSV extends DataReader
{

	public ArrayList<String[]> readcsv(String r2) throws IOException 
	{
	  String[] name2;
	  ArrayList<String[]> count2 = new ArrayList();
	  try
	  {
		 File file = new File(r2);
		 CSVReader cr = new CSVReader(new InputStreamReader(new FileInputStream(file), "UTF-8")); 
		 String[] Line;
		 cr.readNext();
		 while((Line = cr.readNext())!= null)
		 {
			//System.out.println(Line[2]);
			count2.add(Line);
			//System.out.println(Line);
		 }
		 
		 cr.close();
		  
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
		String pattern = "([0-9]+)-([0-9]+)-([0-9]+)\\s([0-9]+):([0-9]+):[0-9]+\\,\\\"(.+)\\\"\\,\\\"(.+)\\\""; 
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
