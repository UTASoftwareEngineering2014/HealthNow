package com.application.healthnow;

import com.application.healthnow.ExerciseFragment.buttonlistener;
import com.application.healthnow.heartratemonitor.HeartRateMonitor;

import android.os.Bundle;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class VitalSignsFragment extends Fragment{
	
	int heartRate,bloodPressure,glucoseLevel,cholesterol;
	
	public VitalSignsFragment() { }
	
	private EditText editText;
	private TextView textView;
	private ImageView imageView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		final View rootView = inflater.inflate(R.layout.fragment_vitalsigns, container, false);
		 
		Button heartRateMonitor = (Button) rootView
				.findViewById(R.id.btn_vital_heartrate_monitor);
		
		ImageButton imgBtn_bp = (ImageButton) rootView.findViewById(R.id.imgBtn_bp);
		ImageButton imgBtn_hr = (ImageButton) rootView.findViewById(R.id.imgBtn_hr);
		ImageButton imgBtn_gl = (ImageButton) rootView.findViewById(R.id.imgBtn_gl);
		ImageButton imgBtn_ch = (ImageButton) rootView.findViewById(R.id.imgBtn_ch);
		
		Context vitalsignsContext = getActivity().getApplicationContext();
		View vitalsignsView = rootView;
	
		heartRateMonitor.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				Intent startHeartRateMnitor = new Intent(getActivity(),
						HeartRateMonitor.class);
				startActivity(startHeartRateMnitor);
				
			}
		});

		
		editText = (EditText)rootView.findViewById(R.id.et_heartrate);
		editText.setText(Integer.toString(heartRate));
		editText = (EditText)rootView.findViewById(R.id.et_bloodpressure);
		editText.setText(Integer.toString(bloodPressure));
		editText = (EditText)rootView.findViewById(R.id.et_glucoselevel);
		editText.setText(Integer.toString(glucoseLevel));
		editText = (EditText)rootView.findViewById(R.id.et_cholesterol);
		editText.setText(Integer.toString(cholesterol));
		
		editText = (EditText) rootView.findViewById(R.id.et_bloodpressure);
		editText.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				EditText etBP = (EditText) rootView.findViewById(R.id.et_bloodpressure);
				int blood = Integer.valueOf(etBP.getText().toString());
				if(blood>0 && blood < 120)
				{
					ImageView imageView = (ImageView) rootView.findViewById(R.id.img_bp_status);
					imageView.setImageResource(R.drawable.green_light);
				}
				if(blood >= 120 && blood < 140)
				{
					ImageView imageView = (ImageView) rootView.findViewById(R.id.img_bp_status);
					imageView.setImageResource(R.drawable.yellow_light);
				}
				if(blood >= 140)
				{
					ImageView imageView = (ImageView) rootView.findViewById(R.id.img_bp_status);
					imageView.setImageResource(R.drawable.red_light);
				}
				
			}
		});
		
		editText = (EditText) rootView.findViewById(R.id.et_heartrate);
		editText.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				EditText etHR = (EditText) rootView.findViewById(R.id.et_heartrate);
				int heart = Integer.valueOf(etHR.getText().toString());
				if(heart>0 && heart < 80)
				{
					ImageView imageView = (ImageView) rootView.findViewById(R.id.img_hr_status);
					imageView.setImageResource(R.drawable.green_light);
				}
				if(heart >= 80 && heart < 100)
				{
					ImageView imageView = (ImageView) rootView.findViewById(R.id.img_hr_status);
					imageView.setImageResource(R.drawable.yellow_light);
				}
				if(heart >= 100)
				{
					ImageView imageView = (ImageView) rootView.findViewById(R.id.img_hr_status);
					imageView.setImageResource(R.drawable.red_light);
				}
				
			}
		});
		
		editText = (EditText) rootView.findViewById(R.id.et_glucoselevel);
		editText.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				EditText etGL = (EditText) rootView.findViewById(R.id.et_glucoselevel);
				int glucose = Integer.valueOf(etGL.getText().toString());
				if(glucose>0 && glucose < 100)
				{
					ImageView imageView = (ImageView) rootView.findViewById(R.id.img_gl_status);
					imageView.setImageResource(R.drawable.green_light);
				}
				if(glucose >= 100  && glucose < 140)
				{
					ImageView imageView = (ImageView) rootView.findViewById(R.id.img_gl_status);
					imageView.setImageResource(R.drawable.yellow_light);
				}
				if(glucose > 140)
				{
					ImageView imageView = (ImageView) rootView.findViewById(R.id.img_gl_status);
					imageView.setImageResource(R.drawable.red_light);
				}
				
			}
		});
		
		editText = (EditText) rootView.findViewById(R.id.et_cholesterol);
		editText.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				EditText etCH = (EditText) rootView.findViewById(R.id.et_cholesterol);
				int cholesterol = Integer.valueOf(etCH.getText().toString());
				if(cholesterol>0 && cholesterol < 200)
				{
					ImageView imageView = (ImageView) rootView.findViewById(R.id.img_ch_status);
					imageView.setImageResource(R.drawable.green_light);
				}
				if(cholesterol >= 200 && cholesterol < 240)
				{
					ImageView imageView = (ImageView) rootView.findViewById(R.id.img_ch_status);
					imageView.setImageResource(R.drawable.yellow_light);
				}
				if(cholesterol >= 240)
				{
					ImageView imageView = (ImageView) rootView.findViewById(R.id.img_ch_status);
					imageView.setImageResource(R.drawable.red_light);
				}
				
			}
		});
	
		imgBtn_bp.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Context context = getActivity();
				AlertDialog.Builder alert = new AlertDialog.Builder(
			                context);
			    
			        alert.setTitle("Blood Pressure");
			        alert.setMessage("Healthy levels are: <120 mmHg \nFair Levels are:120-140 mmHg \nPoor levels are: >140 mmHg");
			        alert.setPositiveButton("OK", new OnClickListener() {
			            @Override
			            public void onClick(DialogInterface dialog, int which) {
			                // TODO Auto-generated method stub
			                dialog.dismiss();
			            }
			        });
			      
			        alert.show();
				
				
			}
		});
		

		imgBtn_hr.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Context context = getActivity();
				AlertDialog.Builder alert = new AlertDialog.Builder(
			                context);
			    
			        alert.setTitle("Resting Heart Rate");
			        alert.setMessage("Healthy levels are: <80 bpm \nFair Levels are:80-100 bpm \nPoor levels are: >100 bpm");
			        alert.setPositiveButton("OK", new OnClickListener() {
			            @Override
			            public void onClick(DialogInterface dialog, int which) {
			                // TODO Auto-generated method stub
			                dialog.dismiss();
			            }
			        });
			      
			        alert.show();
				
				
			}
		});
		

		imgBtn_gl.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Context context = getActivity();
				AlertDialog.Builder alert = new AlertDialog.Builder(
			                context);
			    
			        alert.setTitle("Glucose Levels");
			        alert.setMessage("Healthy levels are: <100 mg/dL \nFair Levels are:100-140 mg/dL \nPoor levels are: >140 mg/dL");
			        alert.setPositiveButton("OK", new OnClickListener() {
			            @Override
			            public void onClick(DialogInterface dialog, int which) {
			                // TODO Auto-generated method stub
			                dialog.dismiss();
			            }
			        });
			      
			        alert.show();
				
				
			}
		});
		

		imgBtn_ch.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Context context = getActivity();
				AlertDialog.Builder alert = new AlertDialog.Builder(
			                context);
			    
			        alert.setTitle("Cholesteral");
			        alert.setMessage("Healthy levels are: <200 mg/dL \nFair Levels are:200-240 mg/dL \nPoor levels are: >240 mg/dL");
			        alert.setPositiveButton("OK", new OnClickListener() {
			            @Override
			            public void onClick(DialogInterface dialog, int which) {
			                // TODO Auto-generated method stub
			                dialog.dismiss();
			            }
			        });
			      
			        alert.show();
				
				
			}
		});
		
		return rootView;
		
		
	}
	
	
	
	
	
	
}