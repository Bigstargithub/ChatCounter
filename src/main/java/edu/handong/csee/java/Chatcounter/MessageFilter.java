package edu.handong.csee.java.Chatcounter;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MessageFilter {
	
	public static void WhatFiles(DataReader data) throws FileNotFoundException
	{
		
		if(data instanceof DataReaderForCSV)
		{
			DataReaderForCSV dataforcsv = new DataReaderForCSV();
			
		}
		
		else if (data instanceof DataReaderForTXT)
		{
			DataReaderForTXT d2 = new DataReaderForTXT();
		}
		
	}

}
