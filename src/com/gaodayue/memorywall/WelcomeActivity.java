package com.gaodayue.memorywall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends Activity {
	Button btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		
		btn = (Button) findViewById(R.id.bt_login);
	}
	
	public void onClickLogin(View v) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
    }
}
