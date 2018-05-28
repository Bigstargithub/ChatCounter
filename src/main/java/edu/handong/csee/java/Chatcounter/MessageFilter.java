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
			int n;
			int N =0;
			DataReaderForTXT d2 = new DataReaderForTXT();
		    data.names2.add(data.names.get(0));
		     while(true)
		     {
		    	String a = data.names.get(N);
		    	for(n=0;n<=data.names2.size()-1;n++)
		    	{
		    		if(a.equals(data.names2.get(n)))
		    		{
		    		  break;
		    		}
		    	
		    	}
		    	if(n>=data.names2.size())
		    	{
		    	  data.names2.add(data.names.get(N));
		    	  
		    	}
		    	N++;
		    	if(N>=data.names.size()) break;
		      }
		     System.out.println(data.names2);
		     
		 }
		    
			
		}
		
	}

