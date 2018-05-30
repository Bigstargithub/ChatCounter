package edu.handong.csee.java.Chatcounter;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class DataWriter {
	
	public HashMap<String,Integer> Printoutput(ArrayList<String> lines,String targetFileName)
	{
       File file = new File(targetFileName);
       FileOutputStream fos = new FileOutputStream(file);
       DataOutputStream dos = new DataOutputStream(fos);
       
       for(String line : lines)
       {
    	   dos.write(line + "\n").getBytes();
       }
       
       dos.close();
       fos.close();
	}

}
