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
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 * This is a DataReaderForCSV class.</br>
 * This class will read the csv files and count chat in csv file.</br>
 * and add the hashmap (Chatmessage)
 * 
 * @author Bigstar
 *
 */
public class DataReaderForCSV extends DataReader {
	static ArrayList<String> namearraycsv = new ArrayList<String>(); // save the name in ArrayList (namearraycsv)
	static ArrayList<String> messagearraycsv = new ArrayList<String>();// save the message in ArrayList
																		// (messagearraycsv)
	static ArrayList<String> date = new ArrayList<String>(); // save the date in ArrayList(date)
	static ArrayList<String> user = new ArrayList<String>(); // save the name in ArrayList(date)
	static ArrayList<String> strMessage = new ArrayList<String>(); // save the message in ArrayList(date)

	int i = 0;
	int c;

	/**
	 * This is a readcsv method.</br>
	 * This method will read csv file.
	 * 
	 * @param r2
	 * @throws IOException
	 */
	public void readcsv(String r2) throws IOException {
		MessageFilter MF = new MessageFilter();
		ArrayList<String> count2 = new ArrayList();
		try {
			File file = new File(r2);
			BufferedReader br = new BufferedReader(new FileReader(file));
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(br);

			for (CSVRecord record : records) {
				date.add(record.get(0).substring(11, 19));
				user.add(record.get(1));
				strMessage.add(record.get(2));
			}
			// System.out.println(user);
			namearraycsv.add(user.get(i));
			messagearraycsv.add(messagecsv(date.get(i), strMessage.get(i)));

			i++;
		}

		catch (FileNotFoundException e) {
			System.out.println("There is not file.");
			System.exit(0);
		}

	}

	/**
	 * This method will return the message.</br>
	 * The message will change if the message is Korean mean "Photo"
	 * @param data
	 * @param strmessage
	 * @return
	 */
	public String messagecsv(String data, String strmessage) {
		try {
			if (strmessage.equals("사진")) {
				strmessage = "Photo";
			}

			return "" + data + "" + " " + strmessage + "";
		}

		catch (NullPointerException e) {
			strmessage = "";
		}
		return strmessage;
	}

	/**
	 * This method save the name and message in hashmap (Chatmessage).</br>
	 * But This method will not save the data that is overlapping.
	 */
	public void addHashmap() {

		for (int a = 0; a < names2.size(); a++) {
			if (!Chatmessage.containsKey(names2.get(a))) {
				Chatmessage.put(names2.get(a), new ArrayList<String>());
			}
		}
		Iterator iterator = Chatmessage.entrySet().iterator();
		Entry entry;
		while (iterator.hasNext()) {
			entry = (Entry) iterator.next();
			listentry.add((String) entry.getKey());
		}

		i = 0;
		for (i = 0; i < user.size(); i++) {
			for (int b = 0; b < listentry.size(); b++) {
				if (user.get(i).equals(listentry.get(b))) {
					for (c = 0; c < Chatmessage.get(listentry.get(b)).size(); c++) {
						if (Chatmessage.get(listentry.get(b)).get(c)
								.equals(messagecsv(date.get(i), strMessage.get(i)))) {
							break;
						}
					}
					if (c >= Chatmessage.get(listentry.get(b)).size()) {
						Chatmessage.get(listentry.get(b)).add(messagecsv(date.get(i), strMessage.get(i)));
					}

				}
			}
		}

	}

}
