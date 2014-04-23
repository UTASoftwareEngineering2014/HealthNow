package com.application.healthnow.newsfeed;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;
import android.util.Xml;

public class HealthFeedXmlParser {
	private static final String namespace = null;

	public List<Entry> parse(InputStream in) throws XmlPullParserException,
			IOException {
		try {
			XmlPullParser parser = Xml.newPullParser();
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			parser.setInput(in, null);
			parser.nextTag();
			List<Entry> xmlEntries = readFeed(parser);
			return xmlEntries;
		} finally {
			in.close();
		}
	}

	private List<Entry> readFeed(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		List<Entry> entries = new ArrayList<Entry>();
		parser.require(XmlPullParser.START_TAG, namespace, "rss");

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			Log.d("readFeed()", "Before name");
			String name = parser.getName();
			Log.d("readFeed()", "After name" + " " + "name: "+ name.toString());;
			// Starts by looking for the item tag
			if (name.equals("channel")) {
				entries.add(readEntry(parser));
			} else {
				skip(parser);
			}
		}
		return entries;
	}

	public static class Entry {
		public final String title;
		public final String link;
		public final String summary;

		private Entry(String title, String link, String summary) {
			this.title = title;
			this.link = link;
			this.summary = summary;
		}
	}
	
	// Parses the contents of an entry. If it encounters a title, summary, or link tag, hands them
    // off
    // to their respective &quot;read&quot; methods for processing. Otherwise, skips the tag.
    private Entry readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, namespace, "item");
        String title = null;
        String summary = null;
        String link = null;
        
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            Log.d("readEntry()", "Before name.");
            String name = parser.getName();
            Log.d("readEntry()", "Before name." + " " + name.toString());
            if (name.equals("title")) {
                title = readTitle(parser);
            } else if (name.equals("description")) {
                summary = readDescription(parser);
            } else if (name.equals("link")) {
                link = readLink(parser);
            } else {
                skip(parser);
            }
        }
        return new Entry(title, summary, link);
    }

	// Skips tags the parser isn't interested in. Uses depth to handle nested tags. i.e.,
    // if the next tag after a START_TAG isn't a matching END_TAG, it keeps going until it
    // finds the matching END_TAG (as indicated by the value of "depth" being 0).
    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
    	Log.d("skip tag", "Parser skip begin");
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
        	Log.d("skip tag", "Parser" + " " + parser.next());
            switch (parser.next()) {
            case XmlPullParser.END_TAG:
            	Log.d("skip()", "END_TAG" + " " + XmlPullParser.END_TAG);
                    depth--;
                    break;
            case XmlPullParser.START_TAG:
            	Log.d("skip()", "START_TAG" + " " + XmlPullParser.START_TAG);
                    depth++;
                    break;
            }
        }
    }

    // Processes link tags in the feed.
    private String readLink(XmlPullParser parser) throws IOException, XmlPullParserException {
        String link = "";
        parser.require(XmlPullParser.START_TAG, namespace, "link");
        String tag = parser.getName();
        String relType = parser.getAttributeValue(null, "rel");
        if (tag.equals("link")) {
            if (relType.equals("alternate")) {
                link = parser.getAttributeValue(null, "href");
                parser.nextTag();
            }
        }
        parser.require(XmlPullParser.END_TAG, namespace, "link");
        return link;
    }

    // Processes summary tags in the feed.
    private String readDescription(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, namespace, "description");
        String summary = readText(parser);
        parser.require(XmlPullParser.END_TAG, namespace, "description");
        return summary;
    }

	// Processes title tags in the feed.
    private String readTitle(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, namespace, "title");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, namespace, "title");
        return title;
    }

    // For the tags title and summary, extracts their text values.
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        
        return result;
    }
}
