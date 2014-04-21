package com.application.healthnow;

import java.util.Calendar;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.application.healthnow.diet.PreferencesActivity;
import com.application.healthnow.diet.WebViewActivity;
import com.application.healthnow.diet.SavedRecipesActivity;

public class DietFragment extends Fragment{
	
	public DietFragment() { }
	
	private EditText editText;
	private TextView textView;
	int storedDay;
	int breakfast, lunch, dinner;
	private static final String PREFS_NAME = "MyPrefsFile";
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		final View rootView = inflater.inflate(R.layout.fragment_diet, container, false);
		Button btnSearchRecipe = (Button) rootView.findViewById(R.id.btn_Search_Recipes);
		Button btnSavedRecipe = (Button) rootView.findViewById(R.id.btn_Saved_Recipes);
		Button btnSaveCalories=(Button)rootView.findViewById(R.id.btn_SaveCalories);
		ImageButton imgBtnSetPreferences = (ImageButton) rootView.findViewById(R.id.imgBtn_menu_preferences);
		checkDay();
		editText = (EditText)rootView.findViewById(R.id.et_breakfast);
		editText.setText(Integer.toString(breakfast));
		editText = (EditText)rootView.findViewById(R.id.et_lunch);
		editText.setText(Integer.toString(lunch));
		editText = (EditText)rootView.findViewById(R.id.et_dinner);
		editText.setText(Integer.toString(dinner));
		textView = (TextView) rootView.findViewById(R.id.tv_calorie_counter);
		textView.setText(Integer.toString(breakfast + lunch + dinner));
		

		btnSaveCalories.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				SharedPreferences getPreference = PreferenceManager.getDefaultSharedPreferences(getActivity());
				int dailyCalorieIntake = 0;
				try {
					dailyCalorieIntake = Integer.parseInt(getPreference.getString("cal", "0"));
				} catch(Exception e) {
					Context context = getActivity();
					CharSequence dailyCaloriePrompt = "Please Enter a Number Only for Daily Calorie Goal.";
					int duration = Toast.LENGTH_SHORT;
					Toast toast = Toast.makeText(context, dailyCaloriePrompt, duration);
					toast.show();
				}
				
				int totalCalorie = 0;
				editText = (EditText)rootView.findViewById(R.id.et_breakfast);
				int calorie = Integer.parseInt(editText.getText().toString());
				totalCalorie = calorie;
				breakfast = calorie;
				editText = (EditText) rootView.findViewById(R.id.et_lunch);
				calorie = Integer.parseInt(editText.getText().toString());
				totalCalorie = totalCalorie+calorie;
				lunch = calorie;
				editText = (EditText) rootView.findViewById(R.id.et_dinner);
				calorie = Integer.parseInt(editText.getText().toString());
				totalCalorie = totalCalorie + calorie;
				dinner = calorie;
				textView = (TextView) rootView.findViewById(R.id.tv_calorie_counter);
				textView.setText(Integer.toString(totalCalorie));
				
				ProgressDialog progressDialog = new ProgressDialog(arg0.getContext());
				progressDialog.setCancelable(true);
				progressDialog.setMessage("Daily Progress");
				progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				progressDialog.setMax(dailyCalorieIntake);
				progressDialog.show();
				progressDialog.setProgress(totalCalorie);
				
				SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
				SharedPreferences.Editor editor = settings.edit();
				editor.putInt("Breakfast", breakfast);
				editor.putInt("Lunch", lunch);
				editor.putInt("Dinner", dinner);
				editor.commit();
				
			}
			
		});
		
		imgBtnSetPreferences.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent preferencesIntent = new Intent(getActivity(), PreferencesActivity.class);
				startActivity(preferencesIntent);
			}
		});
		
		btnSearchRecipe.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent searchRecipeIntent = new Intent(getActivity(), WebViewActivity.class);
				startActivity(searchRecipeIntent);
			}
		});
		
		btnSavedRecipe.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent savedRecipeIntent = new Intent(getActivity(), SavedRecipesActivity.class);
				startActivity(savedRecipeIntent);
			}
		});
		
		editText = (EditText) rootView.findViewById(R.id.et_weight);
		editText.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				double weight = Double.valueOf(editText.getText().toString());
				EditText etHeight = (EditText) rootView.findViewById(R.id.et_height);
				double height = Double.valueOf(etHeight.getText().toString());
				if((height == 0) == false)
				{
					TextView textView = (TextView) rootView.findViewById(R.id.tv_BMI_Counter);
					double BMI = (weight/(height*height))*703;
					textView.setText(String.format("%.3f",BMI));
				}
			}
		});
		
		editText = (EditText) rootView.findViewById(R.id.et_height);
		editText.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				double height = Double.valueOf(editText.getText().toString());
				EditText etWeight = (EditText) rootView.findViewById(R.id.et_weight);
				double weight = Double.valueOf(etWeight.getText().toString());
				if((weight == 0) == false)
				{
					TextView textView = (TextView) rootView.findViewById(R.id.tv_BMI_Counter);
					double BMI = (weight/(height*height))*703;
					textView.setText(String.format("%.3f",BMI));
				}
			}
		});
		
		return rootView;
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		inflater.inflate(R.menu.main, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
		settings.getString("url", null);
		checkDay();
		//editText = (EditText) 
	}
	
	private void checkDay() {
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_YEAR);
		SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
		storedDay = settings.getInt("day", 0);
		
		if(storedDay == 0)
		{
			SharedPreferences.Editor editor = settings.edit();
			editor.putInt("day", day);
			breakfast = 0;
			lunch = 0;
			dinner = 0;
			editor.putInt("breakfast", breakfast);
			editor.putInt("lunch", lunch);
			editor.putInt("dinner", dinner);
			storedDay = day;
			editor.commit();
		}
		else if(storedDay == day)
		{
			breakfast = settings.getInt("breakfast", 0);
			lunch = settings.getInt("lunch", 0);
			dinner = settings.getInt("dinner", 0);
		}
		else
		{
			SharedPreferences.Editor editor = settings.edit();
			breakfast = 0;
			lunch = 0;
			dinner = 0;
			editor.putInt("breakfast", breakfast);
			editor.putInt("lunch", lunch);
			editor.putInt("dinner", dinner);
			editor.putInt("day", day);
			storedDay = day;
			editor.commit();
		}
	}
}
