package com.maya.copdspirometer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.EditText;
import android.content.Context;
import android.view.View.OnClickListener;

public class Settings extends Activity {

	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		//Change color of text fields, change length of text fields, edit spacing, change switch to
		//yes or no instead of on/off and then add text to the front of the text fields
		//change visiblity of text fields
		Switch button = (Switch) findViewById(R.id.switch1);
		button.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				 if (isChecked) {
					 EditText b=(EditText)findViewById(R.id.editText1);
						b.setVisibility(View.VISIBLE);
					EditText b2=(EditText)findViewById(R.id.editText2);
						b2.setVisibility(View.VISIBLE);
				} else {
					EditText b=(EditText)findViewById(R.id.editText1);
					b.setVisibility(View.INVISIBLE);
				EditText b2=(EditText)findViewById(R.id.editText2);
					b2.setVisibility(View.INVISIBLE);			        }				
			}
		});	
		
		
	}
}