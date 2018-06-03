package edu.handong.csee.java.Chatcounter;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map.Entry;

/**
 * 
 * This is a DataReader.
 * This class have a main method and the main method will read a file.
 * @author Bigstar
 *
 */
public class DataReader {

	static ArrayList<String> names = new ArrayList(); // txt file name arrayList, names.
	static ArrayList<String> names2 = new ArrayList(); //ArrayList that is filtered overlapping names. names2 
	static HashMap<String, ArrayList<String>> Chatmessage = new HashMap<String,ArrayList<String>>(); // save the message, Chatmessage.
	static HashMap<String, Integer> Chatcounter = new HashMap<String,Integer>(); // count the message, ChatCounter.
	static ArrayList<String> listentry = new ArrayList<String>(); // save the key of Hashmap, listentry
	int b;
	int a;
	
	/**
	 * This is a main method.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException
	{
		CLIPrinter clip = new CLIPrinter();
		DataReader dataread = new DataReader();
		
		try
		{
			clip.run(args);
			if(clip.getfilepath() == "")
			{
				
			}
			else
			{
			dataread.run(clip.getfilepath());
			Chatcounter = dataread.sortvalue(Chatcounter);
			DataWriter datawriter = new DataWriter();
			}
		}
		
		catch(IOException e)
		{
			System.out.println(e);
		}
		
	}
	/**
	 * This is a run method.
	 * The run method read a Chat message in csv file and txt file.
	 * And The result is put the Hashmap.
	 * @param r1
	 * @throws IOException
	 */
	public void run(String r1) throws IOException
	{	
		DataReader mydata = new DataReader();
		MessageFilter messagefilt = new MessageFilter();
		ArrayList<String> r2 = mydata.getdata(r1);
		
		DataReaderForTXT datafortxt = new DataReaderForTXT();
		DataReaderForCSV dataforcsv = new DataReaderForCSV();
		
		
		
		for(int i = 0; i < r2.size(); i++)
		{
			String r3 = r2.get(i).substring(r2.get(i).length()-3, r2.get(i).length());
			//System.out.println(r3);
			
			
			if(r3.equals("csv"))
			{
				System.out.println(r2.get(i));
				File file = new File(r2.get(i));
			    dataforcsv.readcsv(file.getPath());
			    messagefilt.WhatFiles(dataforcsv);
			    dataforcsv.addHashmap();
			  }
			  
		}
		for(int j = 0; j<r2.size();j++)
		{
			String r3 = r2.get(j).substring(r2.get(j).length()-3, r2.get(j).length());
			if(r3.equals("txt"))
			{
				System.out.println(r2.get(j));
				File file = new File(r2.get(j));
				datafortxt.readtxt(file.getPath());
				messagefilt.WhatFiles(datafortxt);
				datafortxt.addHashMaptxt();
			}
		}
		
		for(int d =0; d<listentry.size();d++)
		{
			  Chatcounter.put(listentry.get(d),Chatmessage.get(listentry.get(d)).size());
		}
			
	
	}
	
	/**
	 * This is a getdata method.
	 * The method will get the file list from directory.
	 * @param strDir
	 * @return
	 */
	public ArrayList<String> getdata(String strDir)
	{
		// 1. get directory. 
		File myDir = getDirectory(strDir);
		// 2. get list of files from directory
		File[] files = getListOffilesFromDirectory(myDir);
		
		ArrayList<String> messages = readFiles(files);
		return messages;
	}
	
	/**
	 * This method will get a file directory.
	 * @param strDir
	 * @return
	 */
	public File getDirectory(String strDir)
	{
		File myDirectory = new File(strDir);
		return myDirectory;
	}
	
	/**
	 * This method will get a file list from directory.
	 * @param dataDir
	 * @return
	 */
	public File[] getListOffilesFromDirectory(File dataDir)
	{
		return dataDir.listFiles();
	}
	
	/**
	 * This method will add filelist from directory in ArrayList.
	 * @param files
	 * @return
	 */
	public ArrayList<String> readFiles(File[] files)
	{
		ArrayList<String> message = new ArrayList<String>();
		int i = 0;
		while(i < files.length)
		{
			message.add(files[i].getName());
			i++;
		}
		
		return message;

	}
	
	/**
	 * This method will sort the ChatCounter hashmap in ascending order.
	 * @param ChatCounter
	 * @return
	 */
	public HashMap<String, Integer> sortvalue(HashMap<String,Integer> ChatCounter)
	{
		int x =0;
		int[] tempnum = new int[39];
		int tempnum2;
		String tpname;
		 LinkedHashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
		 Iterator iterator = Chatmessage.entrySet().iterator();
		 Entry entry;
		 String[] tempname = new String[39];
		 while(iterator.hasNext())
			{
			  entry = (Entry)iterator.next();
			  tempname[x] = (String) entry.getKey();
			  tempnum[x] = ChatCounter.get(tempname[x]);
			  x++;
			}

		 for(int y =0 ; y<tempnum.length;y++)
		 {
			 for(int z=y+1;z<tempnum.length;z++)
			 {
				 if(tempnum[y] < tempnum[z])
				 {
					 tempnum2 = tempnum[y];
					 tempnum[y] = tempnum[z];
					 tempnum[z] = tempnum2;
					 
					 tpname = tempname[y];
					 tempname[y] = tempname[z];
					 tempname[z] = tpname;
				 }
			 }
		 }
		 for(int n = 0;n<tempnum.length;n++)
		 {
			 temp.put(tempname[n], tempnum[n]);
		 }
		return temp;
	}
	

	
}
