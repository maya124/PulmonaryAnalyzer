package com.maya.copdspirometer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.*;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.*;

public class About extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		
		ImageButton button = (ImageButton) findViewById(R.id.page1Button);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				ImageView img=(ImageView)findViewById(R.id.imageView1);
				img.setVisibility(View.INVISIBLE);
				ImageView img2=(ImageView)findViewById(R.id.imageView2);
				img2.setVisibility(View.VISIBLE);
				ImageButton b=(ImageButton)findViewById(R.id.page2Button);
				b.setVisibility(View.VISIBLE);
				ImageButton b2=(ImageButton)findViewById(R.id.page1Button);
				b2.setVisibility(View.INVISIBLE);
			}
		});
		
		ImageButton button2 = (ImageButton) findViewById(R.id.page2Button);
		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				ImageView img=(ImageView)findViewById(R.id.imageView1);
				img.setVisibility(View.VISIBLE);
				ImageView img2=(ImageView)findViewById(R.id.imageView2);
				img2.setVisibility(View.INVISIBLE);
				ImageButton b=(ImageButton)findViewById(R.id.page2Button);
				b.setVisibility(View.INVISIBLE);
				ImageButton b2=(ImageButton)findViewById(R.id.page1Button);
				b2.setVisibility(View.VISIBLE);
			}
		});
	}
}
