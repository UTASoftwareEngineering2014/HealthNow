package com.application.healthnow.exercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.application.healthnow.GlobalVariables;
import com.application.healthnow.R;

public class Saved_Exercise extends Activity{
	public static final String PREFS_NAME = "MyPrefsFile";
	//public static final String PREFS_NAME = GlobalVariables.userName;
	String page = null;
	StableArrayAdapter adapter;ArrayList<String> names; ArrayList<String> url;
	ArrayList<String> namescopy;
	ListView listview;
	String URLtoremove;String nametoremove;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setTitle("Saved Exercises");
		setContentView(R.layout.activity_saved_recipes);
		listview = (ListView) findViewById(R.id.lv_SavedRecipes);
		//final Context context = getApplicationContext();
		final Context context = Saved_Exercise.this;
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		CharSequence text = "Press and Hold on an Item to Delete";
		   int duration = Toast.LENGTH_SHORT;

		   Toast toast = Toast.makeText(context, text, duration);
		   toast.show();
		
		
		
		
		
		page = settings.getString("urlsaveExercise", null);
		if (page != null) {
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
			
			 adapter = new StableArrayAdapter(this,
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
			
			
			listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() 
			{

				@Override
				public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
						final int arg2, long arg3) 
				{
					// TODO Auto-generated method stub
					
				
     			  /* CharSequence text = "Clicked"+arg3;
         		   int duration = Toast.LENGTH_LONG;

         		   Toast toast = Toast.makeText(context, text, duration);
         		   toast.show();*/
         		   
         		   //int id=(int)arg3;
					
					

					
					
					AlertDialog.Builder builder = new AlertDialog.Builder(context);

				    builder.setTitle("Confirm");
				    builder.setMessage("Are you sure you want to delete "+names.get(arg2)+"?");

				    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

				        public void onClick(DialogInterface dialog, int which) 
				        {
				            // Do nothing but close the dialog

				         
				        
				            int id=arg2;
							Log.d("se", "id:"+id);
							nametoremove=(names.get(id)).toString();
							URLtoremove=(url.get(id)).toString();
							names.remove(id);
							url.remove(id);
							runOnUiThread(new Runnable()
							{
								public void run()
								{
	                            //reload content
	         	            	
	         	            	addnamestoclone();
	         	            	adapter.clear();
	         	            	addclonetonames();
	         	            	adapter.addAll(names);

	         	            	//if(adapter.isEmpty())Log.d("emty", "adapterempty"+names.size());
	         	            	//listview.setAdapter(adapter);
	         	            	adapter.notifyDataSetChanged();
	         	            	listview.invalidateViews();
	         	            	listview.refreshDrawableState();
	                        
								}

							});
	         		  
							SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
							Log.d("page=", page);
							page="";
							for(int i=0;i<names.size();i++)
							{
								if(i==0)page=page+(names.get(i)).toString()+"\n";
								else page=page+"\n"+(names.get(i)).toString()+"\n";
								page=page+(url.get(i).toString());
								
							}

	         		  
							if(page.equals(""))page=null;
							
							if(page!=null)Log.d("beforecommit", page);
							SharedPreferences.Editor editor = settings.edit();
							editor.putString("urlsaveExercise"+GlobalVariables.userName, page);
							editor.commit();
				        }

				    });

				    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

				        @Override
				        public void onClick(DialogInterface dialog, int which) {
				            // Do nothing
				            dialog.dismiss();
				        }
				    });

				    AlertDialog alert = builder.create();
				    alert.show();
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
				    
					
					

					return true;
				}
				});
			
				
			
			//listview.setOnTouchListener(new SwipeGestureListener(context));
			

			
			

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.saved_recipes, menu);
		return true;
	}
	
	
	public void addnamestoclone()
	{
		namescopy=new ArrayList<String>();
		for(int i=0;i<names.size();i++)
		{
			namescopy.add(names.get(i));
		}
		
	}
	public void addclonetonames()
	{
		names=new ArrayList<String>();
		for(int i=0;i<namescopy.size();i++)
		{
			names.add(namescopy.get(i));
		}
		
	}
	
	
	
	
	
	
	 class SwipeGestureListener extends SimpleOnGestureListener implements
	   OnTouchListener {
	  Context context;
	  GestureDetector gDetector;
	  static final int SWIPE_MIN_DISTANCE = 120;
	  static final int SWIPE_MAX_OFF_PATH = 250;
	  static final int SWIPE_THRESHOLD_VELOCITY = 200;
	 
	  public SwipeGestureListener() {
	   super();
	  }
	 
	  public SwipeGestureListener(Context context) {
	   this(context, null);
	  }
	 
	  public SwipeGestureListener(Context context, GestureDetector gDetector) {
	 
	   if (gDetector == null)
	    gDetector = new GestureDetector(context, this);
	 
	   this.context = context;
	   this.gDetector = gDetector;
	  }
	 
	  @Override
	  public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
	    float velocityY) {
	 
	   final int position = listview.pointToPosition(
	     Math.round(e1.getX()), Math.round(e1.getY()));
	 
	   String countryName = (String) listview.getItemAtPosition(position);
	 
	/*   if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH) {
	    if (Math.abs(e1.getX() - e2.getX()) > SWIPE_MAX_OFF_PATH
	      || Math.abs(velocityY) < SWIPE_THRESHOLD_VELOCITY) {
	     return false;
	    }
	    if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE) {
	     Toast.makeText(context, "bottomToTop" + countryName,
	       Toast.LENGTH_SHORT).show();
	    } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE) {
	     Toast.makeText(context,
	       "topToBottom  " + countryName, Toast.LENGTH_SHORT)
	       .show();
	    }
	   } else {
	    if (Math.abs(velocityX) < SWIPE_THRESHOLD_VELOCITY) {
	     return false;
	    }
	    if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE) {
	     Toast.makeText(context,
	       "swipe RightToLeft " + countryName, 5000).show();
	    } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE) {
	     Toast.makeText(context,
	       "swipe LeftToright  " + countryName, 5000).show();
	    }
	   }
	 */
	   
	   
	   
	   if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE)
	   {
 		   int id=position;
 		   Log.d("se", "id:"+id);
 		   nametoremove=(names.get(id)).toString();
 		   URLtoremove=(url.get(id)).toString();
 		   names.remove(id);
 		   url.remove(id);
 		  runOnUiThread(new Runnable()
 		  {
 	            public void run()
 	            {
                    //reload content
 	            	
 	            	addnamestoclone();
                adapter.clear();
                addclonetonames();
                adapter.addAll(names);

                //if(adapter.isEmpty())Log.d("emty", "adapterempty"+names.size());
                //listview.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                listview.invalidateViews();
                listview.refreshDrawableState();
                
 	            }

 		  });
 		  
 		 SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
 		 Log.d("page=",page);
 		  page="";
 		  for(int i=0;i<names.size();i++)
 		  {
 			 if(i==0)page=page+(names.get(i)).toString()+"\n";
				else page="\n"+page+(names.get(i)).toString()+"\n";
				page=page+(url.get(i).toString());
 		  }

 		  
 		  
 		 SharedPreferences.Editor editor = settings.edit();
 		 editor.putString("urlsaveExercise"+GlobalVariables.userName, page);
 		 editor.commit();
	   }
	   return super.onFling(e1, e2, velocityX, velocityY);
	 
	  }
	 
	  @Override
	  public boolean onTouch(View v, MotionEvent event) {
	 
	   return gDetector.onTouchEvent(event);
	  }
	 
	  public GestureDetector getDetector() {
	   return gDetector;
	  }
	 
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







