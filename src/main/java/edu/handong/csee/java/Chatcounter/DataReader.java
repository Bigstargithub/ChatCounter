package edu.handong.csee.java.Chatcounter;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataReader {

	static ArrayList<String> names = new ArrayList();
	static ArrayList<String> names2 = new ArrayList();
	int[] count = new int[40];
	String[] names3 = new String[200];
	public static void main(String[] args) throws IOException
	{
		DataReader data = new DataReader();
		data.run();
	}
	
	public void run() throws IOException
	{
		ArrayList<String> csvdata = new ArrayList();
		Scanner myscanner = new Scanner(System.in);
		System.out.print("Input the absolute directory that you want: ");
		String r1 = myscanner.next();
		//String result = "C:\\Chat:\\JavaProgramming-L19.txt";
		
		DataReader mydata = new DataReader();
		MessageFilter messagefilt = new MessageFilter();
		ArrayList<String> r2 = mydata.getdata(r1);
		
		//System.out.println(r2);
		
		DataReaderForTXT datafortxt = new DataReaderForTXT();
		DataReaderForCSV dataforcsv = new DataReaderForCSV();
		
		
		
		for(int i = 0; i < r2.size(); i++)
		{
			String r3 = r2.get(i).substring(r2.get(i).length()-3, r2.get(i).length());
			//System.out.println(r3);
			if(r3.equals("txt"))
			{
				System.out.println(r2.get(i));
				File file = new File(r2.get(i));
				//System.out.println(r2.get(i));
				names3 = datafortxt.readtxt(file.getPath());
				messagefilt.WhatFiles(datafortxt);
				//System.out.println(names3[0]);
				//System.out.println(names2.size());
				for(int f = 0; f<names3.length; f++)
				{
					count = messagefilt.counttxt(names3[f]);
					//System.out.println(count);
				}
				
				for(int g = 0 ; g <count.length;g++)
				{
					System.out.println(count[g]);
				}
			}
			
			if(r3.equals("csv"))
			{
				System.out.println(r2.get(i));
				File file = new File(r2.get(i));
				csvdata = dataforcsv.readcsv(file.getPath());
				Iterator<String> it = csvdata.iterator();
			     
				 while(it.hasNext())
				{
					String array = (String) it.next();
				}
				
				//dataforcsv.readcsv(file.getPath());
				 //System.out.println(csvdata);
			}
			
		}
		
		
		
	}
	
	public ArrayList<String> getdata(String strDir)
	{
		// 1. get directory. 
		File myDir = getDirectory(strDir);
		// 2. get list of files from directory
		File[] files = getListOffilesFromDirectory(myDir);
		
		ArrayList<String> messages = readFiles(files);
		return messages;
	}
	
	public File getDirectory(String strDir)
	{
		File myDirectory = new File(strDir);
		return myDirectory;
	}
	
	public File[] getListOffilesFromDirectory(File dataDir)
	{
		return dataDir.listFiles();
	}
	
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
	
}
