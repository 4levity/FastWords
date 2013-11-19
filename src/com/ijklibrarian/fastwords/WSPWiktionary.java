package com.ijklibrarian.fastwords;

public class WSPWiktionary extends WordSearchProvider {

	@Override
	String getProviderName() {
		return "Wiktionary";
	}

	@Override
	String generateResults(String word) {
//		String html=get("http://en.wiktionary.org/w/api.php?action=query&format=txt&titles="+word.toLowerCase());
		String html=get("http://en.wiktionary.org/wiki/"+word.toLowerCase());		
		
		String txt=android.text.Html.fromHtml(html).toString();
		return txt;
	}

}
