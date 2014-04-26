package com.application.healthnow.search;
import com.application.healthnow.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewResult extends Activity
{
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setTitle(getIntent().getExtras().getString("name"));
		setContentView(R.layout.activity_view_recipe);
		final WebView webview=(WebView)findViewById(R.id.wv_viewrecipe);

		 webview.getSettings().setJavaScriptEnabled(true);
		 final Activity activity = this;
		 webview.setWebChromeClient(new WebChromeClient() 
		 {
		   public void onProgressChanged(WebView view, int progress) 
		   {
		     // Activities and WebViews measure progress with different scales.
		     // The progress meter will automatically disappear when we reach 100%
		     activity.setProgress(progress * 1000);
		   }
		 });
		 webview.setWebViewClient(new WebViewClient() 
		 {
		   public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
		     Toast.makeText(activity, "Oh no! " + description, Toast.LENGTH_SHORT).show();
		   }
		 });
		 
		 String openRecipe = getIntent().getExtras().getString("open");
		 webview.loadUrl(openRecipe);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.viewrecipe, menu);
		return true;
	}
}

