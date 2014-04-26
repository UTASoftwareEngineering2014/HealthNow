package com.application.healthnow;

import java.util.ArrayList;
import java.util.List;

//import name.bagi.levente.pedometer.Pedometer;

//import name.bagi.levente.pedometer.Pedometer;

import com.application.healthnow.diet.SavedRecipesActivity;
import com.application.healthnow.diet.WebViewActivity;
import com.application.healthnow.exercise.Saved_Exercise;
import com.application.healthnow.exercise.Search_Exercise;
import com.application.healthnow.heartratemonitor.HeartRateMonitor;

import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.CompoundButton;

public class ExerciseFragment extends Fragment {
	String tag = "Exercise fragment";

	public ExerciseFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_exercise, container,
				false);
		Button calculateCalories = (Button) rootView
				.findViewById(R.id.btn_calculateCalories);
		Button searchExercise = (Button) rootView
				.findViewById(R.id.btn_searchExercise);
		Button savedExercise = (Button) rootView
				.findViewById(R.id.btn_savedexercise);
		Button resetTimer = (Button) rootView.findViewById(R.id.btn_resetTimer);
		ToggleButton timer_toggle = (ToggleButton) rootView
				.findViewById(R.id.togbtn_timerToggle);
		Button startPedometer = (Button) rootView
				.findViewById(R.id.btn_startPedometer);
		Button heartRateMonitor = (Button) rootView
				.findViewById(R.id.btn_heartRateMonitor);
		Context exerciseContext = getActivity().getApplicationContext();
		View exerciseView = rootView;
		buttonlistener fortimer = new buttonlistener(exerciseView,
				exerciseContext);
		calculateCalories.setOnClickListener(new buttonlistener(exerciseView,
				exerciseContext));
		searchExercise.setOnClickListener(new buttonlistener(exerciseView,
				exerciseContext));
		savedExercise.setOnClickListener(new buttonlistener(exerciseView,
				exerciseContext));
		timer_toggle.setOnCheckedChangeListener(fortimer);
		resetTimer.setOnClickListener(fortimer);
		startPedometer.setOnClickListener(new buttonlistener(exerciseView,
				exerciseContext));
		heartRateMonitor.setOnClickListener(new buttonlistener(exerciseView,
				exerciseContext));
		return rootView;

	}

	class buttonlistener implements View.OnClickListener,
			CompoundButton.OnCheckedChangeListener {

		long timeWhenStopped;
		View exerciseView;
		Context exerciseContext;

		public buttonlistener(View v, Context c) {
			exerciseView = v;
			exerciseContext = c;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_calculateCalories: {
				PopupMenu pum = new PopupMenu(exerciseContext,
						exerciseView.findViewById(R.id.btn_calculateCalories));
				pum.inflate(R.menu.choose_exercise_menu);
				pum.show();
				pum.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem item) {
						// TODO Auto-generated method stub
						switch (item.getItemId()) {
						case R.id.Running: {
							Log.d("item0", "test");
							FragmentManager f = getFragmentManager();
							new runningDialog("r").show(f, "test");
						}
							break;
						case R.id.Cycling: {
							Log.d("item1", "item1");
							FragmentManager f = getFragmentManager();
							new runningDialog("c").show(f, "test");
						}
							break;

						}
						return false;
					}
				});
			}
				break;

			case R.id.btn_searchExercise: {
				Intent searchexerciseIntent = new Intent(getActivity(),
						Search_Exercise.class);
				startActivity(searchexerciseIntent);
			}
				break;

			case R.id.btn_savedexercise: {
				Log.d("savedexercise", "test");
				Intent savedExerciseIntent = new Intent(getActivity(),
						Saved_Exercise.class);
				startActivity(savedExerciseIntent);
			}
				break;

			case R.id.btn_resetTimer: {

				Chronometer timer = (Chronometer) exerciseView
						.findViewById(R.id.cm_exerciseTimer);
				timer.setBase(SystemClock.elapsedRealtime());
				ToggleButton timer_toggle = (ToggleButton) exerciseView
						.findViewById(R.id.togbtn_timerToggle);
				if (!(timer_toggle.isChecked()))
					timer.stop();
				timeWhenStopped = 0;
				Log.d(tag, "resettimer:" + timeWhenStopped);
			}
				break;

			case R.id.btn_startPedometer:
			{
				Log.d(tag, "inpedom");
				//Intent startPedometer = getActivity().getPackageManager().getLaunchIntentForPackage("name.bagi.levente.pedometer");
				//Intent startPedometer = new Intent(Intent.ACTION_MAIN);
				//startPedometer.setComponent(new ComponentName("name.bagi.levente.pedometer","name.bagi.levente.pedometer.Pedometer"));
				Context context = getActivity().getApplicationContext();
   			  	CharSequence text = "Please Calibrate Settings Before Use";
   			  	int duration = Toast.LENGTH_LONG;
   			  	Toast toast = Toast.makeText(context, text, duration);
   			  	toast.show();
				//Intent startPedometer=new Intent(getActivity(),Pedometer.class);
				//startActivity(startPedometer);
			}break;

			case R.id.btn_heartRateMonitor: {

				// Context context = getActivity().getApplicationContext();
				Intent startHeartRateMnitor = new Intent(getActivity(),
						HeartRateMonitor.class);
				startActivity(startHeartRateMnitor);

			}
				break;

			}

		}

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			// TODO Auto-generated method stub
			switch (buttonView.getId()) {
			case R.id.togbtn_timerToggle: {
				Chronometer timer = (Chronometer) exerciseView
						.findViewById(R.id.cm_exerciseTimer);

				if (isChecked) {
					Log.d(tag, "inchronometerstart:" + timeWhenStopped);
					timer.setBase(SystemClock.elapsedRealtime()
							+ timeWhenStopped);
					timer.start();
				} else {

					timeWhenStopped = timer.getBase()
							- SystemClock.elapsedRealtime();
					timer.stop();
					Log.d(tag, "inchronometerstop:" + timeWhenStopped);
				}
			}
			}

		}

	}
}

class runningDialog extends DialogFragment {
	double cal;
	String type;

	public runningDialog(String s) {
		// TODO Auto-generated constructor stub
		type = s;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		// Get the layout inflater
		View promptsView = getActivity().getLayoutInflater().inflate(
				R.layout.running_layout, null);
		// Inflate and set the layout for the dialog
		// Pass null as the parent view because its going in the dialog layout
		builder.setView(promptsView);
		final EditText et_time = (EditText) promptsView
				.findViewById(R.id.running_time);
		final EditText et_distance = (EditText) promptsView
				.findViewById(R.id.running_distance);
		final EditText et_weight = (EditText) promptsView
				.findViewById(R.id.running_weight);

		// Add action buttons
		builder.setPositiveButton("Calculate",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						// calculate calories
						if (type.equalsIgnoreCase("r")) {
							Log.d("time123", "test");
							try {
								double time = (Integer.parseInt(et_time
										.getText().toString())) / 60.0;
								double dist = Double.parseDouble(et_distance
										.getText().toString());
								double speed = dist / time;
								double weight = Double.parseDouble(et_weight
										.getText().toString());
								int count = 0;
								if (speed >= 2 && speed < 2.5) {
									cal = .0175 * 2.5 * (weight / 2.2) * time
											* 60;
									Log.e("cal", String.valueOf(cal));
								} else if (speed >= 2.5 && speed < 3) {
									cal = .0175 * 3 * (weight / 2.2) * time
											* 60;
								} else if (speed >= 3 && speed < 3.5) {
									cal = .0175 * 3.5 * (weight / 2.2) * time
											* 60;
								} else if (speed >= 3.5 && speed < 4) {
									cal = .0175 * 4 * (weight / 2.2) * time
											* 60;
								} else if (speed >= 4 && speed < 4.5) {
									cal = .0175 * 4 * (weight / 2.2) * time
											* 60;
								} else if (speed >= 4.5 && speed < 5.2) {
									cal = .0175 * 8 * (weight / 2.2) * time
											* 60;
								} else if (speed >= 5.2 && speed < 6) {
									cal = .0175 * 3 * (weight / 2.2) * time
											* 60;
								} else if (speed >= 6 && speed < 6.7) {
									cal = .0175 * 10 * (weight / 2.2) * time
											* 60;
								} else if (speed >= 6.7 && speed < 7) {
									cal = .0175 * 11 * (weight / 2.2) * time
											* 60;
								} else if (speed >= 7 && speed < 7.5) {
									cal = .0175 * 11.5 * (weight / 2.2) * time
											* 60;
								} else if (speed >= 7.5 && speed < 8) {
									cal = .0175 * 12.5 * (weight / 2.2) * time
											* 60;
								} else if (speed >= 8 && speed < 8.6) {
									cal = .0175 * 13.5 * (weight / 2.2) * time
											* 60;
								} else if (speed >= 8.6 && speed < 9) {
									cal = .0175 * 14 * (weight / 2.2) * time
											* 60;
								} else if (speed >= 9 && speed < 10) {
									cal = .0175 * 15 * (weight / 2.2) * time
											* 60;
								} else if (speed >= 10 && speed < 10.9) {
									cal = .0175 * 16 * (weight / 2.2) * time
											* 60;
								} else if (speed >= 10.9 && speed < 12) {
									cal = .0175 * 18 * (weight / 2.2) * time
											* 60;
								} else {
									count = 1;
									Context context = getActivity()
											.getApplicationContext();
									CharSequence text = "Average Speed should be between 2 and 12 mph for calculation";
									int duration = Toast.LENGTH_LONG;

									Toast toast = Toast.makeText(context, text,
											duration);
									toast.show();
								}

								if (count == 0) {
									Context context = getActivity()
											.getApplicationContext();
									cal = Math.round(cal * 100);
									cal = cal / 100.0;
									CharSequence text = "Calories Burnt:"
											+ String.valueOf(cal
													+ " calories\nGood Job!");
									int duration = Toast.LENGTH_LONG;
									Toast toast = Toast.makeText(context, text,
											duration);
									toast.show();
								}
							} catch (Exception e) {
								Context context = getActivity()
										.getApplicationContext();
								CharSequence text = "Invalid Input";
								int duration = Toast.LENGTH_LONG;
								Toast toast = Toast.makeText(context, text,
										duration);
								toast.show();
							}
						}

						else if (type.equalsIgnoreCase("c")) {
							try {
								double time = (Integer.parseInt(et_time
										.getText().toString())) / 60.0;
								double dist = Double.parseDouble(et_distance
										.getText().toString());
								double speed = dist / time;
								double weight = Double.parseDouble(et_weight
										.getText().toString());
								int count = 0;

								if (speed >= 1 && speed < 10) {
									cal = .0175 * 4 * (weight / 2.2) * time
											* 60;
								} else if (speed >= 10 && speed < 11.9) {
									cal = .0175 * 6 * (weight / 2.2) * time
											* 60;
								} else if (speed >= 11.9 && speed < 13.9) {
									cal = .0175 * 8 * (weight / 2.2) * time
											* 60;
								} else if (speed >= 13.9 && speed < 15.9) {
									cal = .0175 * 10 * (weight / 2.2) * time
											* 60;
								} else if (speed >= 15.9 && speed < 19) {
									cal = .0175 * 12 * (weight / 2.2) * time
											* 60;
								} else {
									count = 1;
									Context context = getActivity()
											.getApplicationContext();
									CharSequence text = "Average Speed should be between 1 and 19 mph for calculation";
									int duration = Toast.LENGTH_LONG;

									Toast toast = Toast.makeText(context, text,
											duration);
									toast.show();
								}

								if (count == 0) {
									Context context = getActivity()
											.getApplicationContext();
									cal = Math.round(cal * 100);
									cal = cal / 100.0;
									CharSequence text = "Calories Burnt:"
											+ String.valueOf(cal
													+ " calories\nGood Job!");
									int duration = Toast.LENGTH_LONG;
									Toast toast = Toast.makeText(context, text,
											duration);
									toast.show();
								}

							} catch (Exception e) {
								Context context = getActivity()
										.getApplicationContext();
								CharSequence text = "Invalid Input";
								int duration = Toast.LENGTH_LONG;
								Toast toast = Toast.makeText(context, text,
										duration);
								toast.show();
							}
						}
					}
				}).setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

					}
				});
		return builder.create();
	}
}
