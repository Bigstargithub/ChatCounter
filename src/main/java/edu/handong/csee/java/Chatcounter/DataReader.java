package edu.handong.csee.java.Chatcounter;

import java.io.File;
import java.util.ArrayList;

public class DataReader {

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
		
		return message;

		}
}
