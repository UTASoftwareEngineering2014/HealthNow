package com.application.healthnow;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.XMLReader;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment{
	
	private List<String> item = new ArrayList<String>();
	public HomeFragment() { }
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.fragment_home, container, false);
		 
//		try {
//			URL rssUrl = new URL("http://www.health.com/health/diet-fitness/feed");
//			SAXParserFactory mySaxParserFactory = SAXParserFactory.newInstance();
//			SAXParser mySaxParser = mySaxParserFactory.newSAXParser();
//			XMLReader myXmlReader = mySaxParser.getXMLReader();
//			//RSSHandler myRssHandler = new RSSHandler();
//			
//		}
		return rootView;
	}
}
