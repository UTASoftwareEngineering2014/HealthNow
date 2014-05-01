package com.application.healthnow;

import java.util.Calendar;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
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

import com.application.healthnow.database.LoginDataBaseAdapter;
import com.application.healthnow.diet.PreferencesActivity;
import com.application.healthnow.diet.WebViewActivity;
import com.application.healthnow.diet.SavedRecipesActivity;
 
public class DietFragment extends Fragment{
	
	public DietFragment() { }
	
	private EditText editTextHeight;
	private EditText editText;
	private EditText editTextWeight;
	private TextView textView;
	int storedDay;
	int breakfast, lunch, dinner;
	private static final String PREFS_NAME = "MyPrefsFile";
	String dayhistory,breakfasthistory,lunchhistory,dinnerhistory;
	LoginDataBaseAdapter DB;
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Fragment#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		/*
		 * Here we are creating the fields to take input from the user for each of the calories per meal
		 * Also instantiating our objects. Registering our on click listeners for each button press.
		 */
		DB = new LoginDataBaseAdapter(getActivity());
		final View rootView = inflater.inflate(R.layout.fragment_diet, container, false);
		Button btnSearchRecipe = (Button) rootView.findViewById(R.id.btn_Search_Recipes);
		Button btnSavedRecipe = (Button) rootView.findViewById(R.id.btn_Saved_Recipes);
		Button btnSaveCalories=(Button)rootView.findViewById(R.id.btn_SaveCalories);
		checkDay();
		editText = (EditText)rootView.findViewById(R.id.et_breakfast);
		editText.setText(Integer.toString(breakfast));
		editText = (EditText)rootView.findViewById(R.id.et_lunch);
		editText.setText(Integer.toString(lunch));
		editText = (EditText)rootView.findViewById(R.id.et_dinner);
		editText.setText(Integer.toString(dinner));
		textView = (TextView) rootView.findViewById(R.id.tv_calorie_counter);
		textView.setText(Integer.toString(breakfast + lunch + dinner));
		int bre[]=returnbreakfasthistory();
		if(bre!=null)Log.d("bre df:",""+bre[0]);

		btnSaveCalories.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				SharedPreferences getPreference = PreferenceManager.getDefaultSharedPreferences(getActivity());
				int dailyCalorieIntake = 0;
				try {
					dailyCalorieIntake = Integer.parseInt(getPreference.getString("cal"+GlobalVariables.userName, "0"));
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
				editor.putInt("breakfast"+GlobalVariables.userName, breakfast);
				editor.putInt("lunch"+GlobalVariables.userName, lunch);
				editor.putInt("dinner"+GlobalVariables.userName, dinner);
				editor.commit();
				
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
		
		editTextHeight = (EditText) rootView.findViewById(R.id.et_weight);
		
		String weight = DB.GetWeight();	
		editTextHeight.setText(weight);
		editTextHeight.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				double weight = Double.valueOf(editTextHeight.getText().toString());
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
		
		editTextWeight = (EditText) rootView.findViewById(R.id.et_height);
		String height = DB.GetHeight();
		editTextWeight.setText(height);
		editTextWeight.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				double height = Double.valueOf(editTextWeight.getText().toString());
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
		settings.getString("url"+GlobalVariables.userName, null);
		checkDay();
		//editText = (EditText) 
	}
	
	private void checkDay() 
	{
		
		Calendar calendar = Calendar.getInstance();
		//int day = calendar.get(Calendar.DAY_OF_YEAR);
		int day = calendar.get(Calendar.MINUTE);
		SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
		storedDay = settings.getInt("day"+GlobalVariables.userName, 0);
		Log.d("dietfragment","storedday="+storedDay+"day="+day);
		breakfasthistory=settings.getString("breakfasthistory"+GlobalVariables.userName, "");
		Log.d("break",breakfasthistory);
		dayhistory=settings.getString("dayhistory"+GlobalVariables.userName, "");
		Log.d("dietfragment", dayhistory);
		if(storedDay == 0)
		{
			
			SharedPreferences.Editor editor = settings.edit();
			editor.putInt("day"+GlobalVariables.userName, day);
			breakfast = 0;
			lunch = 0;
			dinner = 0;
			editor.putInt("breakfast"+GlobalVariables.userName, breakfast);
			editor.putInt("lunch"+GlobalVariables.userName, lunch);
			editor.putInt("dinner"+GlobalVariables.userName, dinner);
			storedDay = day;
			editor.commit();
		}
		else if(storedDay == day)
		{
			breakfast = settings.getInt("breakfast"+GlobalVariables.userName, 0);
			lunch = settings.getInt("lunch"+GlobalVariables.userName, 0);
			dinner = settings.getInt("dinner"+GlobalVariables.userName, 0);
			//Log.d("breakfast", "="+breakfast);	
		}
		else
		{
			breakfast = settings.getInt("breakfast"+GlobalVariables.userName, 0);
			lunch = settings.getInt("lunch"+GlobalVariables.userName, 0);
			dinner = settings.getInt("dinner"+GlobalVariables.userName, 0);
			Log.d("in else", "dietfrag "+breakfast);
			SharedPreferences.Editor editor = settings.edit();
			dayhistory=settings.getString("dayhistory"+GlobalVariables.userName, "");
			//Log.d("dietfragment", dayhistory);
			editor.putString("dayhistory"+GlobalVariables.userName, dayhistory+day+" ");
			breakfasthistory=settings.getString("breakfasthistory"+GlobalVariables.userName, "");
			editor.putString("breakfasthistory"+GlobalVariables.userName, breakfasthistory+breakfast+" ");
			lunchhistory=settings.getString("lunchhistory"+GlobalVariables.userName, "");
			editor.putString("lunchhistory"+GlobalVariables.userName, lunchhistory+lunch+" ");
			dinnerhistory=settings.getString("dinnerhistory"+GlobalVariables.userName, "");
			editor.putString("dinnerhistory"+GlobalVariables.userName, dinnerhistory+dinner+" ");
			breakfast = 0;
			lunch = 0;
			dinner = 0;
			editor.putInt("breakfast"+GlobalVariables.userName, breakfast);
			editor.putInt("lunch"+GlobalVariables.userName, lunch);
			editor.putInt("dinner"+GlobalVariables.userName, dinner);
			editor.putInt("day"+GlobalVariables.userName, day);
			storedDay = day;
			editor.commit();
		}
	}
	
	public int[] returndayhistory()
	{
		SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
		String dayhist=settings.getString("dayhistory"+GlobalVariables.userName, "");
		int days[]=null;
		if(!(dayhist.equals("")))
		{
			
				
			String dayarray[]=dayhist.split(" ");
			int size=dayarray.length;
			days=new int[size];
			for(int i=0;i<size;i++)
			{
				days[i]=Integer.parseInt((dayarray[i].toString()));
			}
			
		}
		return days;
		
	}
	public int[] returnbreakfasthistory()
	{
		SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
		String dayhist=settings.getString("breakfasthistory"+GlobalVariables.userName, "");
		int days[]=null;
		if(!(dayhist.equals("")))
		{
			
				
			String dayarray[]=dayhist.split(" ");
			int size=dayarray.length;
			days=new int[size];
			for(int i=0;i<size;i++)
			{
				days[i]=Integer.parseInt((dayarray[i].toString()));
			}
			
		}
		return days;
		
	}
	public int[] returnlunchhistory()
	{
		SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
		String dayhist=settings.getString("lunchhistory"+GlobalVariables.userName, "");
		int days[]=null;
		if(!(dayhist.equals("")))
		{
			
				
			String dayarray[]=dayhist.split(" ");
			int size=dayarray.length;
			days=new int[size];
			for(int i=0;i<size;i++)
			{
				days[i]=Integer.parseInt((dayarray[i].toString()));
			}
			
		}
		return days;
		
	}
	public int[] returndinnerhistory()
	{
		SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
		String dayhist=settings.getString("dinnerhistory"+GlobalVariables.userName, "");
		int days[]=null;
		if(!(dayhist.equals("")))
		{
			
				
			String dayarray[]=dayhist.split(" ");
			int size=dayarray.length;
			days=new int[size];
			for(int i=0;i<size;i++)
			{
				days[i]=Integer.parseInt((dayarray[i].toString()));
			}
			
		}
		return days;
		
	}
}
