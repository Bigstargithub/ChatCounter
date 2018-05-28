package edu.handong.csee.java.Chatcounter;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MessageFilter extends DataReader{
	
	DataReader data = new DataReader();
	public static void WhatFiles(DataReader data) throws FileNotFoundException
	{
		int n;
		int N =0;
		if(data instanceof DataReaderForCSV)
		{
			DataReaderForCSV dataforcsv = new DataReaderForCSV();
			while(true)
			{
			  
			}
		}
		
		else if (data instanceof DataReaderForTXT)
		{
			
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
		    	
		    	if(n>=names2.size())
		    	{
		    	  names2.add(data.names.get(N));
		    	  
		    	}
		    	N++;
		    	if(N>=data.names.size()) break;
		      }
		     System.out.println(names2);
		     //System.out.println(names2.size());
		 }
		    
			
		}
	 
	public int[] counttxt(String a)
	{
		try
		{
			for(int i = 0; i < names2.size();i++)
			{
				//System.out.println(data.names2.get(i));
				//System.out.println(a);
				if(a.equals(null))
				{
					break;
				}
				
				if(a.equals(names2.get(i)))
				{
					
					count[i] = count[i] + 1;
					break;
				}
			}
		}
		
		catch(NullPointerException e)
		{
			
		}
		
		return count;
	}
}

