package com.ijklibrarian.fastwords;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WSPUrbanDict extends WordSearchProvider {
	static final int MAX_URBANDICTIONARY_DEFINITIONS=3;

	@Override
	String getProviderName() {
		return "Urban Dictionary";
	}

	@Override
	String generateResults(String word) {
		String json=get("http://api.urbandictionary.com/v0/define?term="+word);
		String txt=android.text.Html.fromHtml(json).toString();
		String definition="";
		int defNum=1;
		int ix;

		boolean parseError=false;
		JSONParser parser=new JSONParser();
		JSONObject obj=null;
		try {
			obj = (JSONObject) (parser.parse(json));
		} catch (ParseException e) {
			parseError=true;
		}
		
		if(parseError) {
			definition="JSON parse error";		
		} else {
			JSONArray list=(JSONArray) obj.get("list");
			int def=1;
			for(Object o : list) {
				if(def<=MAX_URBANDICTIONARY_DEFINITIONS) {
					if(def>1) {
						definition+="\n\n";
					}
					JSONObject jo = (JSONObject) o;
					definition += "("+def+") " +jo.get("definition");
				}
				def++;
			}
		}
		/*
		definition+="starts with "+txt.substring(0,1500)+"\n\n";
		do {
			definition+="search for "+defNum+"\n";
			ix=txt.indexOf(String.format("%d.", defNum));
			if(ix>0) {
				definition+="search for definition div\n";
				txt=txt.substring(ix+7);
				ix=txt.indexOf("<div class=\"definition\">");
				if(ix>0) {
					definition+="search for end of definition div\n";
					txt=txt.substring(ix+24);
					ix=txt.indexOf("</div>");
					if(ix>0) {
						definition+="add definition "+defNum+"\n";
						if(defNum>1) {
							definition += "------\n";
						}
						definition += txt.substring(0, ix);
					}
				}
			}
			defNum++;
		} while(ix>0);
*/
		return (definition.length()==0)?"not found":definition;
	}

}
