package com.ijklibrarian.fastwords;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class WordSearchProvider {
	abstract String getProviderName();
	abstract String generateResults(String word);
	String lastSearchTerm=null;
	String lastSearchResult=null;
	final static int BUFFER_SIZE=4096;
	
	@Override
	public String toString() {
		return this.getProviderName();
	}

	public Object results(String word) {		
		if(word==null) {
			return "No results";
		} else if(!word.equals(lastSearchTerm) || lastSearchResult==null) {
			lastSearchResult=this.generateResults(word);
			lastSearchTerm=word;
		}
		return lastSearchResult;
	}
	
    public String storeInputStream(InputStream istream)
            throws IOException {
        if (istream != null) {
            Writer target= new StringWriter();
            char[] buffer = new char[BUFFER_SIZE];
            int remainingReads=10;
            try {
            	InputStreamReader streamReader=new InputStreamReader(istream, "UTF-8");
                Reader reader = new BufferedReader(streamReader);
                int n;
                while ((n = reader.read(buffer)) != -1 && remainingReads>0) {
                    target.write(buffer, 0, n);
                    remainingReads--;
                }
            } finally {
                istream.close();
            }
            return target.toString();
        } else {       
            return "";
        }
    }	
    
	public String get(String urlString) {
		// TODO: make network access async not on main thread
		String result=null;
		URL url;
		HttpURLConnection urlConnection=null;
		try {
			url = new URL(urlString);
			urlConnection = (HttpURLConnection) url.openConnection();
			InputStream in = new BufferedInputStream(urlConnection.getInputStream(),BUFFER_SIZE);
			result=storeInputStream(in);
		} catch (MalformedURLException e) {
			result="bad URL";
		} catch (IOException e) {
			result="IO error";
		} finally {
			if(urlConnection!=null)
				urlConnection.disconnect();
		}
		return result==null?"":result;
	}
	public int getMaximumSearchChars() {
		return 30;
	}
}
