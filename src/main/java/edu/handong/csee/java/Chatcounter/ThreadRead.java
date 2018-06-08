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
	public ThreadRead(String filename, String filepath)
	{
		this.filepath = filepath;
		this.filename = filename;
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

		ArrayList<String> r2 = mydata.getdata(filepath);

		DataReaderForTXT datafortxt = new DataReaderForTXT();
		DataReaderForCSV dataforcsv = new DataReaderForCSV();

		try
		{
		for (int i = 0; i < r2.size(); i++) {
			String r3 = r2.get(i).substring(r2.get(i).length() - 3, r2.get(i).length());
			// System.out.println(r3);

			if (r3.equals("csv")) {
				File file = new File(r2.get(i));
				dataforcsv.readcsv(file.getPath());
				messagefilt.WhatFiles(dataforcsv);
				dataforcsv.addHashmap();
			}

		}
		for (int j = 0; j < r2.size(); j++) {
			String r3 = r2.get(j).substring(r2.get(j).length() - 3, r2.get(j).length());
			if (r3.equals("txt")) {

				File file = new File(r2.get(j));
				datafortxt.readtxt(file.getPath());
				messagefilt.WhatFiles(datafortxt);
				datafortxt.addHashMaptxt();
			}
		}
		for (int d = 0; d < listentry.size(); d++) {
			Chatcounter.put(listentry.get(d), Chatmessage.get(listentry.get(d)).size());
		}
	    Chatcounter = mydata.sortvalue(Chatcounter);
		System.out.println(Chatcounter);
		DataWriter datawrite = new DataWriter();
		datawrite.Printoutput(Chatcounter, filename);
		}
		catch (IOException e)
		{
			System.out.println(e);
		}
		
		
		

	}

	
}
