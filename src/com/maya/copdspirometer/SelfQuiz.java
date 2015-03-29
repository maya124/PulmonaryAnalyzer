package com.maya.copdspirometer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.content.Context;
import android.view.View.OnClickListener;

public class SelfQuiz extends Activity {
	static int gender=4, age=200, smokeYears=200, pollution=4;
	static double height=200;
	static double FEV1, FVC, FEV1FVC;
	EditText ageText, smokeYearsText, heightText;
	RadioGroup radioGender, radioPollution;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz);

		final Button submitButton = (Button) findViewById(R.id.submitButton);
		submitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
		        ageText =(EditText)findViewById(R.id.age);
		        if(ageText.getText().toString().length() > 0) age=Integer.parseInt(ageText.getText().toString());
		        
		        smokeYearsText =(EditText)findViewById(R.id.smokeYears);
		        if(smokeYearsText.getText().toString().length() > 0) smokeYears=Integer.parseInt(smokeYearsText.getText().toString());
		        
		        heightText =(EditText)findViewById(R.id.height);
		        if(heightText.getText().toString().length() > 0) height=Integer.parseInt(heightText.getText().toString());
		        
		    	radioGender = (RadioGroup) findViewById(R.id.GenderChoice);
				int selectedId = radioGender.getCheckedRadioButtonId();
				if(selectedId == 2131034200) gender = 1;//female
				else if(selectedId == 2131034199) gender = 0;//male
				else gender = 4; //no gender stated --> error
				
				radioPollution = (RadioGroup) findViewById(R.id.Pollution);
				int selectedId2 = radioPollution.getCheckedRadioButtonId();
				if(selectedId2 == 2131034203) pollution = 1;//presence of pollution
				else if(selectedId2 == 2131034204) pollution = 0;//no pollution
				else pollution = 4; //no pollution stated --> error

				if(gender==4 || smokeYears==200 || age == 200 || height==200 ||pollution==4) 
				{
					System.out.println("Error");
				}
				else
				{
			        heightText.setVisibility(View.GONE);
			        smokeYearsText.setVisibility(View.GONE);
			        ageText.setVisibility(View.GONE);
				    submitButton.setVisibility(View.GONE);
				    
				    

			        height=height*2.54;
				    
					RadioButton maleButton = (RadioButton) findViewById(R.id.gender_male);
					maleButton.setVisibility(View.GONE);
					RadioButton femaleButton = (RadioButton) findViewById(R.id.gender_female);
					femaleButton.setVisibility(View.GONE);
					RadioButton yesButton = (RadioButton) findViewById(R.id.pollution_yes);
					yesButton.setVisibility(View.GONE);
					RadioButton noButton = (RadioButton) findViewById(R.id.pollution_no);
					noButton.setVisibility(View.GONE);
					
					TextView results_heading, results_FVC_heading, results_FEV1_heading, results_ratio_heading, fvc_display, fev1_display,
							ratio_display;
					Button begin_test;
					
			        results_heading =(TextView)findViewById(R.id.results_heading);
			        results_heading.setVisibility(View.VISIBLE);
			        results_FVC_heading =(TextView)findViewById(R.id.results_FVC_heading);
			        results_FVC_heading.setVisibility(View.VISIBLE);
			        results_FEV1_heading =(TextView)findViewById(R.id.results_FEV1_heading);
			        results_FEV1_heading.setVisibility(View.VISIBLE);
			        results_ratio_heading =(TextView)findViewById(R.id.results_ratio_heading);
			        results_ratio_heading.setVisibility(View.VISIBLE);
			        fvc_display =(TextView)findViewById(R.id.fvc_display);
			        fvc_display.setVisibility(View.VISIBLE);
			        fev1_display =(TextView)findViewById(R.id.fev1_display);
			        fev1_display.setVisibility(View.VISIBLE);
			        ratio_display =(TextView)findViewById(R.id.ratio_display);
			        ratio_display.setVisibility(View.VISIBLE);
			        begin_test = (Button)findViewById(R.id.begin_test);
			        begin_test.setVisibility(View.VISIBLE);

			        TextView gender_label, age_label, height_label, smoke_label, pollution_label;
			        gender_label =(TextView)findViewById(R.id.gender_label);
			        gender_label.setVisibility(View.GONE);
			        age_label =(TextView)findViewById(R.id.age_label);
			        age_label.setVisibility(View.GONE);
			        height_label =(TextView)findViewById(R.id.height_label);
			        height_label.setVisibility(View.GONE);
			        smoke_label =(TextView)findViewById(R.id.smoke_label);
			        smoke_label.setVisibility(View.GONE);
			        pollution_label =(TextView)findViewById(R.id.pollution_label);
			        pollution_label.setVisibility(View.GONE);

					
					//************EQUATIONS FOR FINDING NORMAL FEV1/FVC RATIO*****************//
			        //CONSTANTS FROM Hankinson et.al, 1999
					if(gender==0)
					{
						if(age<20)
						{
							FEV1 = (0.004477*age*age) + (-0.04106*age) + (0.00014098*height*height) - 0.7453;
							System.out.println(FEV1);
							FVC = (0.010133*age*age) + (-0.20415*age) + (0.00018642*height*height) -0.2584;
							System.out.println(FVC);
							FEV1FVC = 88.066 + (-0.2066*age);
							System.out.println(FEV1FVC);
						}
						else
						{
							FEV1 = (-0.000172*age*age) + (-0.01303*age) + (0.00014098*height*height) + 0.5536;
							System.out.println(FEV1);
							FVC = (-0.000269*age*age) + (0.00064*age) + (0.00018642*height*height) -0.1933;
							System.out.println(FVC);
							FEV1FVC = 88.066 + (-0.2066*age);
							System.out.println(FEV1FVC);
						}

						
					}
					else
					{
						if(age<18)
						{
							FEV1 = (0.06537*age) + (0.00011496*height*height) - 0.8710;
							System.out.println(FEV1);
							FVC = (0.05916*age) + (0.00014815*height*height) - 1.2082;
							System.out.println(FVC);
							FEV1FVC = 88.066 + (-0.2066*age);
							System.out.println(FEV1FVC);
						}
						else
						{
							FEV1 = (-0.000194*age*age) + (-0.00361*age) + (0.00011496*height*height) + 0.4333;
							System.out.println(FEV1);
							FVC = (-0.000382*age*age) + (0.01870*age) + (0.00014815*height*height) - 0.3560;
							System.out.println(FVC);
							FEV1FVC = 88.066 + (-0.2066*age);
							System.out.println(FEV1FVC);
						}
					}
					
					fvc_display.setText(String.format("%.2f", FVC));
					fev1_display.setText(String.format("%.2f", FEV1));
					ratio_display.setText((String.format("%.2f", FEV1FVC)) + "%");
				}
				
				

			}
		});
		final Context context = this;

		Button button = (Button) findViewById(R.id.begin_test);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    Intent intent = new Intent(context, BeginTest.class);
		//	    intent.putExtra("FVC", FVC);
		//	    intent.putExtra("FEV1", FEV1);
	//		    intent.putExtra("FEV1FVC", FEV1FVC);
                startActivity(intent);   
			}
		});
	}
	
	
}