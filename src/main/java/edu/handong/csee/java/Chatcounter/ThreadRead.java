package edu.handong.csee.java.Chatcounter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This is a public class, ThreadRead.</br>
 * This class will read a file used thread.
 * @author Bigstar
 *
 */
public class ThreadRead extends DataReader implements Runnable {

	CLIPrinter clip = new CLIPrinter();
	private String filename;
	private String filepath;
	

	/**
	 * This is a constructor for read filename and filepath.
	 * @param filename
	 * @param filepath
	 */
	public ThreadRead(String filepath)
	{
		this.filepath = filepath;
	}

	/**
	 * This is a run method.</br>
	 * The run method read a Chat message in csv file and txt file.</br>
	 * And The result is put the Hashmap.
	 * 
	 * @param r1
	 * @throws IOException
	 */
	public void run() {
		// TODO Auto-generated method stub
		DataReader mydata = new DataReader();
		MessageFilter messagefilt = new MessageFilter();


		DataReaderForTXT datafortxt = new DataReaderForTXT();
		DataReaderForCSV dataforcsv = new DataReaderForCSV();

		try
		{
			String r3 = filepath.substring(filepath.length()-3, filepath.length());
			// System.out.println(r3);

			//System.out.println(r3);
			if (r3.equals("csv")) {
				File file = new File(filepath);
				dataforcsv.readcsv(file.getPath());
				messagefilt.WhatFiles(dataforcsv);
				dataforcsv.addHashmap();
			}
		
			if (r3.equals("txt")) {
				File file = new File(filepath);
				datafortxt.readtxt(file.getPath());
				messagefilt.WhatFiles(datafortxt);
				datafortxt.addHashMaptxt();
			}
		
		for (int d = 0; d < listentry.size(); d++) {
			Chatcounter.put(listentry.get(d), Chatmessage.get(listentry.get(d)).size());
		}
	    
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
		
		
		

	}

	
}
