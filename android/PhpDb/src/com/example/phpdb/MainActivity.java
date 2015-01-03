package com.example.phpdb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	
	private EditText e1, e2, e3, e4;
	private Button b1, b2;
	private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
	private SharedPreferences sp;
	private Handler mh;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Myshare.initializeContext(getApplicationContext());
		sp = Myshare.getInstance();
		
		if (sp.getInt("save_flag", 0) == 1) {
			Intent intnd = new Intent(getApplicationContext(),
					ThirdActivity.class);
			intnd.putExtra("name", sp.getString("name", ""));
			startActivity(intnd);

		} else {

			setContentView(R.layout.activity_main);
			e1 = (EditText) findViewById(R.id.editTextName);
			e2 = (EditText) findViewById(R.id.editTextEmail);
			e3 = (EditText) findViewById(R.id.editTextPass);
			e4 = (EditText) findViewById(R.id.editTextCpass);
			b1 = (Button) findViewById(R.id.buttonReg);
			b2 = (Button) findViewById(R.id.buttonAlogn);

			b1.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (null != e1.getText().toString()
							&& null != e2.getText().toString()
							&& null != e3.getText().toString()
							&& null != e4.getText().toString()
							&& !(e1.getText().toString().equals(""))
							&& !(e2.getText().toString().equals(""))
							&& !(e3.getText().toString().equals(""))
							&& !(e4.getText().toString().equals(""))) {

						String sname, semail, spass, scpass;

						sname = e1.getText().toString();
						semail = e2.getText().toString();
						spass = e3.getText().toString();
						scpass = e4.getText().toString();

						if (!(semail.matches(emailPattern))) {

							Toast.makeText(getApplicationContext(),
									"Please enter valid email", 5).show();
						} else if (!spass.equals(scpass)) {
							Toast.makeText(getApplicationContext(),
									"Confirm password not matches  ", 5).show();
						} else {
							mh = new Handler(Looper.getMainLooper()) {
								@Override
								public void handleMessage(Message msg) {
									String aResponse = msg.getData().getString(
											"message");
									Toast.makeText(getApplicationContext(),
											"shdfhs"+aResponse, Toast.LENGTH_SHORT)
											.show();
								}
							};
							new Mygetdata(MainActivity.this, mh).execute(sname,
									semail, spass);

						}

					} else {
						Toast.makeText(getApplicationContext(),
								"Please enter all the fields  ", 5).show();
					}

				}
			});

			b2.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intnd = new Intent(getApplicationContext(),
							LoginActivity.class);
					startActivity(intnd);
				}
			});
		}
	}
}
