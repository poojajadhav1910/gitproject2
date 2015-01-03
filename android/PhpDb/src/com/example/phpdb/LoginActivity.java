package com.example.phpdb;

import android.content.Intent;
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

public class LoginActivity extends ActionBarActivity {
	private Button bttn;
	private EditText e1, e2;
	Handler lh;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		e1 = (EditText) findViewById(R.id.editText1);
		e2 = (EditText) findViewById(R.id.editText2);
		bttn = (Button) findViewById(R.id.buttonLogin);

		bttn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (e1.getText().toString().equals("")
						&& e1.getText().toString().equals("")) {
					Toast.makeText(getApplicationContext(),
							"all fields are mandatory", 5).show();
				} else {
					
					final String email = e1.getText().toString();
					String pass = e2.getText().toString();
					
				
					lh = new Handler(Looper.getMainLooper()) {
						@Override
						public void handleMessage(Message msg) {
							
							String aResponse = msg.getData().getString(
									"message");

							if (aResponse.equals("valid")) {

								Intent intnd = new Intent(
										getApplicationContext(),
										ThirdActivity.class);
								intnd.putExtra("name", email);
								startActivity(intnd);

							} else {
								Toast.makeText(getApplicationContext(),
										aResponse, Toast.LENGTH_SHORT).show();
							}
						}
					};
					new Mygetdata(LoginActivity.this, lh).execute(email, pass);

				}

			}
		});

	}

}
