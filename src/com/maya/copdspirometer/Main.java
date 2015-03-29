package com.maya.copdspirometer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.content.Context;
import android.view.View.OnClickListener;

public class Main extends Activity {

	ImageButton button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Context context = this;
		button = (ImageButton) findViewById(R.id.imageButton1);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    Intent intent = new Intent(context, About.class);
                           startActivity(intent);   
			}
		});
		
		ImageButton button2 = (ImageButton) findViewById(R.id.imageButton4);
		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    Intent intent = new Intent(context, Settings.class);
                           startActivity(intent);   
			}
		});
		
		ImageButton button3 = (ImageButton) findViewById(R.id.imageButton2);
		button3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    Intent intent = new Intent(context, BeginTest.class);
                           startActivity(intent);   
			}
		});
		
		ImageButton button4 = (ImageButton) findViewById(R.id.imageButton3);
		button4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    Intent intent = new Intent(context, SelfQuiz.class);
                           startActivity(intent);   
			}
		});

		
	}
	
}
