package com.application.healthnow.exercise;

import com.application.healthnow.GlobalVariables;
import com.application.healthnow.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Search_Exercise extends Activity 
{

	String page=null;
	private EditText result;
	final Context context = this;
	public static final String PREFS_NAME = "MyPrefsFile";
	//public static final String PREFS_NAME = GlobalVariables.userName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.activity_next);
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		page = settings.getString("urlsaveExercise", null);
		final WebView webView = (WebView) findViewById(R.id.wv_recipe);
		Button btnSaveRecipeWebView = (Button) findViewById(R.id.btn_saveRecipeWebView);
		btnSaveRecipeWebView.setText("Save Exercise");
		webView.getSettings().setJavaScriptEnabled(true);
		final Activity activity = this;
		webView.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView view, int progress)
			{
				activity.setProgress(progress * 1000);
			}
		});
		
		webView.setWebViewClient(new WebViewClient() {
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
				Toast.makeText(activity, "Oh no! " + description, Toast.LENGTH_SHORT).show();
			}
		});
		
		webView.loadUrl("http://www.bodybuilding.com/exercises/list/index/selected/a");
		btnSaveRecipeWebView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				LayoutInflater layoutInflater = LayoutInflater.from(context);
				View promptsView = layoutInflater.inflate(R.layout.exercise_save_prompt, null);
				
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
				alertDialogBuilder.setView(promptsView);
				
				final EditText userInput = (EditText) promptsView.findViewById(R.id.et_enterRecipe);
				
				alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int id) {
						if(page == null)
						{
							page = (userInput.getText().toString().replace("\n", "")) + "\n";
						}
						else 
						{
							page = page +"\n"+ (userInput.getText().toString().replace("\n", "")) + "\n";
						}
						
						page = page + webView.getUrl() ;
					}
				}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
				
				AlertDialog alertDialog = alertDialogBuilder.create();
				
				alertDialog.show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.next, menu);
		return true;
	}
	
	@Override
	protected void onPause()
	{
		super.onPause();
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("urlsaveExercise"+GlobalVariables.userName, page);
		editor.commit();
	}
}

