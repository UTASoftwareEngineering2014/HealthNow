package com.application.healthnow;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.application.healthnow.search.ExpandableListAdapter;
import com.application.healthnow.search.WebViewResult;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

public class SearchResultsActivity extends Activity 
{
	public static final String PREFS_NAME = "MyPrefsFile";
	ArrayList<String> names; ArrayList<String> url;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    List<String> exercise ;
    List<String> diet;
    List<String> exerciseurl;
    List<String> dieturl;
    int sizeofdietresult,sizeofexerciseresult;
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.search_results);
    	Log.d("inqsearch", "search");
    	expListView = (ExpandableListView) findViewById(R.id.expandableListView1);
    	exercise=new ArrayList<String>();
    	diet=new ArrayList<String>();
    	exerciseurl=new ArrayList<String>();
    	dieturl=new ArrayList<String>();
    	prepareListData(getIntent());
    	listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
    	expListView.setAdapter(listAdapter);
    	// Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener()
        {
 
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                    int groupPosition, int childPosition, long id) 
            {
                /*Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                        listDataHeader.get(groupPosition)).get(
                                        childPosition), Toast.LENGTH_SHORT)
                        .show();*/
            	switch (groupPosition)
            	{
            		case 0:
            		{
            			
            			if(sizeofdietresult==1)
            			{
            				Intent openLink=new Intent(SearchResultsActivity.this,WebViewResult.class);
            				openLink.putExtra("open", dieturl.get(childPosition));
            				startActivity(openLink);
            			}
            		}break;
            		case 1:
            		{
            			if(sizeofexerciseresult==1)
            			{
            				Intent openLink=new Intent(SearchResultsActivity.this,WebViewResult.class);
            				openLink.putExtra("open", exerciseurl.get(childPosition));
            				startActivity(openLink);
            			}
            		}break;
            	}
                return false;
            }

			
        });

        //handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {

        handleIntent(intent);
    }

    private void handleIntent(Intent intent) 
    {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) 
        {
        	//Log.d("inqsearch", "search");
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
            Toast.makeText(getApplicationContext(), query,
            		   Toast.LENGTH_LONG).show();
        }
    }
    
    
    
    
    
    
    private void prepareListData(Intent search) 
    {
    	String query = search.getStringExtra(SearchManager.QUERY);
    	SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		String page = settings.getString("url"+GlobalVariables.userName, null);
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
 
        // Adding child data
        listDataHeader.add("Diet");
        listDataHeader.add("Exercise");
 
        // Adding child data
 
        
		if (page != null) 
		{
			String[] pageUrlMapping = page.split("\n");
			names = new ArrayList<String>();
			url = new ArrayList<String>();

			for(int i=0;i<pageUrlMapping.length;i++)
			{
				if(i%2 == 0)
				{
					names.add(pageUrlMapping[i].toString());
				}
				else
				{
					url.add(pageUrlMapping[i].toString());
				}
			}
			for(int i=0;i<names.size();i++)
			{
				if((names.get(i).toString()).toLowerCase().contains(query))
				{
					diet.add(names.get(i).toString());
					dieturl.add(url.get(i).toString());
				}
			}
			sizeofdietresult=1;
		}
		if(diet.size()==0)
		{
			diet.add("No Match In Diet");
			sizeofdietresult=0;
			Log.d("no match", "diet");
		}
		page = settings.getString("urlsaveExercise"+GlobalVariables.userName, null);
		if (page != null) 
		{
			String[] pageUrlMapping = page.split("\n");
			names = new ArrayList<String>();
			url = new ArrayList<String>();

			for(int i=0;i<pageUrlMapping.length;i++)
			{
				if(i%2 == 0)
				{
					names.add(pageUrlMapping[i].toString());
				}
				else
				{
					url.add(pageUrlMapping[i].toString());
				}
			}
		
			for(int i=0;i<names.size();i++)
			{
				if(names.get(i).toString().toLowerCase().contains(query))
				{
					exercise.add(names.get(i).toString());
					exerciseurl.add(url.get(i).toString());
				}
			}
			sizeofexerciseresult=1;
		}
		if(exercise.size()==0)
		{
			exercise.add("No Match In Exercise");
			sizeofexerciseresult=0;
		}
    	
    	
    	
    	
    	


 
        listDataChild.put(listDataHeader.get(0), diet); // Header, Child data
        listDataChild.put(listDataHeader.get(1), exercise);
    }
    
}
    
    
    
    

