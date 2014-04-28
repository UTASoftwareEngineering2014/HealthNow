package com.application.healthnow;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class HomeFragment extends Fragment {

	private WebView mobileWebView;
	private boolean isWebViewAvailable;
	private String URL = "http://www.health.com/health/";

	/**
	 * Creates a new fragment which loads the supplied url as soon as it can
	 * 
	 * @param url
	 *            the url to load once initialized
	 */
	public HomeFragment() {
	}
	

	/**
	 * Called to instantiate the view. Creates and returns the WebView.
	 */
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (mobileWebView != null) {
			mobileWebView.destroy();
		}
		mobileWebView = new WebView(getActivity());
		mobileWebView.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if ((keyCode == KeyEvent.KEYCODE_BACK)
						&& mobileWebView.canGoBack()) {
					mobileWebView.goBack();
					return true;
				}
				return false;
			}

		});
		mobileWebView.setWebViewClient(new InnerWebViewClient()); // forces it
																	// to open
																	// in app
		mobileWebView.loadUrl(URL);
		isWebViewAvailable = true;
		WebSettings settings = mobileWebView.getSettings();
		settings.setJavaScriptEnabled(true);
		return mobileWebView;
	}

	/**
	 * Convenience method for loading a url. Will fail if {@link View} is not
	 * initialized (but won't throw an {@link Exception})
	 * 
	 * @param url
	 */
	public void loadUrl(String url) {
		if (isWebViewAvailable)
			getWebView().loadUrl(URL = url);
		else
			Log.w("ImprovedWebViewFragment",
					"WebView cannot be found. Check the view and fragment have been loaded.");
	}

	/**
	 * Called when the fragment is visible to the user and actively running.
	 * Resumes the WebView.
	 */
	@Override
	public void onPause() {
		super.onPause();
		mobileWebView.onPause();
		Bundle webViewBundle =  new Bundle();
//		webViewBundle
	}

	/**
	 * Called when the fragment is no longer resumed. Pauses the WebView.
	 */
	@Override
	public void onResume() {
		mobileWebView.onResume();
		super.onResume();
	}

	/**
	 * Called when the WebView has been detached from the fragment. The WebView
	 * is no longer available after this time.
	 */
	@Override
	public void onDestroyView() {
		isWebViewAvailable = false;
		super.onDestroyView();
	}

	/**
	 * Called when the fragment is no longer in use. Destroys the internal state
	 * of the WebView.
	 */
	@Override
	public void onDestroy() {
		if (mobileWebView != null) {
			mobileWebView.destroy();
			mobileWebView = null;
		}
		super.onDestroy();
	}

	/**
	 * Gets the WebView.
	 */
	public WebView getWebView() {
		return isWebViewAvailable ? mobileWebView : null;
	}

	/**
	 * 
	 * Saving the state of the Health WebView so it does not have to reload the
	 * page every time the fragment gets clicked.
	 * 
	 */
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	/* To ensure links open within the application */
	private class InnerWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			//Toast toast = Toast.makeText(getActivity(),
				//	"Health magazine has finished loading", Toast.LENGTH_SHORT);
			//toast.show();
		}
	}
}
