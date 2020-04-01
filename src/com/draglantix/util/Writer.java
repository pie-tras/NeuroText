package com.draglantix.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
	
	public static File CreateFile(String pathname) {
		File file = null;
		try {
	      file = new File("res/"+pathname);
	      file.createNewFile();
	    } catch (IOException e) {
	      System.out.println("An error occurred while attempting to create " + pathname);
	      e.printStackTrace();
	    }
		return file;
	}
	
	public static void WriteFile(File file, String content) {
		 try {
	      FileWriter writer = new FileWriter(file.getAbsolutePath());
	      writer.write(content);
	      writer.close();
	    } catch (IOException e) {
	      System.out.println("An error occurred writing to file " + file.getName());
	      e.printStackTrace();
	    }
	}
}
