package com.application.healthnow.exercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.application.healthnow.R;

public class Saved_Exercise extends Activity {
	public static final String PREFS_NAME = "MyPrefsFile";
	String page = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setTitle("Saved Exercises");
		setContentView(R.layout.activity_saved_recipes);
		final ListView listview = (ListView) findViewById(R.id.lv_SavedRecipes);
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		page = settings.getString("urlsaveExercise", null);
		if (page != null) {
			String[] pageUrlMapping = page.split("\n");
			final ArrayList<String> names = new ArrayList<String>();
			final ArrayList<String> url = new ArrayList<String>();
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
			
			final StableArrayAdapter adapter = new StableArrayAdapter(this,
					android.R.layout.simple_list_item_1, names);
			listview.setAdapter(adapter);
			listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, final View view,
						int position, long id) {

					/*
					 * final String item = (String)
					 * parent.getItemAtPosition(position);
					 * view.animate().setDuration
					 * (2000).alpha(0).withEndAction(new Runnable() {
					 * 
					 * @Override public void run() { names.remove(item);
					 * adapter.notifyDataSetChanged(); view.setAlpha(1); }
					 * 
					 * });
					 */
					Intent viewExerciseIntent = new Intent(Saved_Exercise.this, View_Exercise.class);
					viewExerciseIntent.putExtra("open", url.get(position));
					viewExerciseIntent.putExtra("name", names.get(position));
					startActivity(viewExerciseIntent);

				}

			});

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.saved_recipes, menu);
		return true;
	}

}

class StableArrayAdapter extends ArrayAdapter<String> {

	HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

	public StableArrayAdapter(Context context, int textViewResourceId,
			List<String> objects) {
		super(context, textViewResourceId, objects);
		for (int i = 0; i < objects.size(); ++i) {
			mIdMap.put(objects.get(i), i);
		}
	}

	@Override
	public long getItemId(int position) {
		String item = getItem(position);
		return mIdMap.get(item);
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}
}

