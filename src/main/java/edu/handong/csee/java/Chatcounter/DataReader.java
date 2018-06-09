package edu.handong.csee.java.Chatcounter;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map.Entry;

/**
 * 
 * This is a DataReader.</br>
 * This class have a main method and the main method will read a file.
 * 
 * @author Bigstar
 *
 */
public class DataReader {

	static ArrayList<String> names = new ArrayList(); // txt file name arrayList, names.
	static ArrayList<String> names2 = new ArrayList(); // ArrayList that is filtered overlapping names. names2
	static HashMap<String, ArrayList<String>> Chatmessage = new HashMap<String, ArrayList<String>>(); // save the
																										// message,Chatmessage.
	static HashMap<String, Integer> Chatcounter = new HashMap<String, Integer>(); // count the message, ChatCounter.
	static ArrayList<String> listentry = new ArrayList<String>(); // save the key of Hashmap, listentry
	int b;
	int a;

	/**
	 * This is a main method. This main method uses thread to read a files.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		DataReader dataread = new DataReader();

		CLIPrinter clip = new CLIPrinter();

		clip.run(args);
		if (clip.getfilepath() == null) {
			// throw new NullPointerException();
		}
		ArrayList<ThreadRead> r2 = new ArrayList<ThreadRead>();
		ArrayList<String> r3 = dataread.getdata(clip.getfilepath());
		r3 = dataread.filesort(r3);
		int numOfCoresinMyCPU = Integer.parseInt(clip.getaNumberofThread());
		ExecutorService executor = Executors.newFixedThreadPool(numOfCoresinMyCPU);

		for (int g = 0; g < r3.size(); g++) {
			Runnable worker = new ThreadRead(r3.get(g));
			executor.execute(worker);
			r2.add((ThreadRead) worker);
		}
		executor.shutdown();

		while (!executor.isTerminated()) {

		}
		// dataread.run(clip.getfilepath());
		// Chatcounter = dataread.sortvalue(Chatcounter);
		// DataWriter datawriter = new DataWriter();
		// datawriter.Printoutput(Chatcounter, clip.getfilename());
		Chatcounter = dataread.sortvalue(Chatcounter);
		System.out.println(Chatcounter);
		DataWriter datawrite = new DataWriter();
		datawrite.Printoutput(Chatcounter, clip.getfilename());
	}

	/**
	 * This is a getdata method.</br>
	 * The method will get the file list from directory.
	 * 
	 * @param strDir
	 * @return
	 */
	public ArrayList<String> getdata(String strDir) {
		CLIPrinter clip = new CLIPrinter();
		// 1. get directory.
		File myDir = getDirectory(strDir);
		// 2. get list of files from directory
		File[] files = getListOffilesFromDirectory(myDir);

		ArrayList<String> messages = readFiles(files);
		for (int t = 0; t < messages.size(); t++) {
			messages.set(t, strDir + "\\" + messages.get(t));

		}
		return messages;
	}

	/**
	 * This method will get a file directory.</br>
	 * 
	 * @param strDir
	 * @return
	 */
	public File getDirectory(String strDir) {
		File myDirectory = new File(strDir);
		return myDirectory;
	}

	/**
	 * This method will get a file list from directory.</br>
	 * 
	 * @param dataDir
	 * @return
	 */
	public File[] getListOffilesFromDirectory(File dataDir) {
		return dataDir.listFiles();
	}

	/**
	 * This method will add filelist from directory in ArrayList.</br>
	 * 
	 * @param files
	 * @return
	 */
	public ArrayList<String> readFiles(File[] files) {
		ArrayList<String> message = new ArrayList<String>();
		int i = 0;
		while (i < files.length) {
			message.add(files[i].getName());
			i++;
		}

		return message;

	}

	/**
	 * This method will sort the ChatCounter hashmap in ascending order.
	 * 
	 * @param ChatCounter
	 * @return
	 */
	public HashMap<String, Integer> sortvalue(HashMap<String, Integer> ChatCounter) {
		int x = 0;
		int[] tempnum = new int[39];
		int tempnum2;
		String tpname;
		LinkedHashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
		Iterator iterator = Chatmessage.entrySet().iterator();
		Entry entry;
		String[] tempname = new String[39];
		while (iterator.hasNext()) {
			entry = (Entry) iterator.next();
			tempname[x] = (String) entry.getKey();
			tempnum[x] = ChatCounter.get(tempname[x]);
			x++;
		}

		for (int y = 0; y < tempnum.length; y++) {
			for (int z = y + 1; z < tempnum.length; z++) {
				if (tempnum[y] < tempnum[z]) {
					tempnum2 = tempnum[y];
					tempnum[y] = tempnum[z];
					tempnum[z] = tempnum2;

					tpname = tempname[y];
					tempname[y] = tempname[z];
					tempname[z] = tpname;
				}
			}
		}
		for (int n = 0; n < tempnum.length; n++) {
			temp.put(tempname[n], tempnum[n]);
		}
		return temp;
	}

	public ArrayList<String> filesort(ArrayList<String> r3) {
		ArrayList<String> a3 = new ArrayList<String>();
		String[] a2 = new String[20];
		String a1;
		for (int e = 0; e < r3.size(); e++) {
			a2[e] = r3.get(e);
		}

		for (int f = 0; f < a2.length; f++) {
			for (int t = f + 1; t < a2.length; t++) {
				if (a2[f].substring(a2[f].length() - 3, a2[f].length()).equals("csv")) {
					break;
				}
					else if (a2[t].substring(a2[t].length() - 3, a2[t].length()).equals("csv")) {
						a1 = a2[f];
						a2[f] = a2[t];
						a2[t] = a1;
					}

				}
			
			}
			

		for (int w = 0; w < a2.length; w++) {
			a3.add(a2[w]);
		}
		return a3;
	}

}
