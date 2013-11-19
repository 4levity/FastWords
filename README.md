FastWords
=========

Android app allows quickly looking up a word or phrase from multiple dictionary/lookup sources with minimal UI. 

App stays open on notification bar. To use it, first select a word or phrase of text from within the browser, email or other app and "copy" to clipboard. Then click on FastWords app in notification bar. Clipboard contents will be looked up on remote dictionary sources. 

*NotifyService*: Run in background and maintain active icon in notification bar

*SelectWordSearch*: Main foreground activity

*ExpandableListAdapterSearch*: User interface is an expandable list. Add new Word Search Providers here.

*WordSearchProvider*: Abstract base class for "word search providers", objects which take a word or short phrase as input and return a definition or other text.
* *WSPUrbanDict*: Look up definitions on UrbanDictionary.com
* *WSPWiktionary*: Look up definitions on Wiktionary
* *WSPDummy*: Dummy static text


Building
========

eclipse project for Android

requires library: json-simple v1.1.1 or later (edit project settings to specify location of json-simple jar)

