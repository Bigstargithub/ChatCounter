package edu.handong.csee.java.Chatcounter;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataReader {

	public static void main(String[] args)
	{
		Scanner myscanner = new Scanner(System.in);
		System.out.print("Input the absolute directory that you want: ");
		String r1 = myscanner.next();
		String result = "";
		
		DataReader mydata = new DataReader();
		ArrayList<String> r2 = mydata.getdata(r1);
		
		
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
