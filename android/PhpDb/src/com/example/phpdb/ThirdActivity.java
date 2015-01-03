package com.example.phpdb;

import java.util.ArrayList;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ThirdActivity extends ActionBarActivity {
	private EditText e1;
	private Button bttn, logo;
	private ListView l1;
	private CustomAdapter a1;
//	private CustomCursor c1;
	private ArrayList<Messg> str = new ArrayList<Messg>();
	private SharedPreferences sp;
	Handler lh1,lh2;
	//private int i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);

		final String name = getIntent().getExtras().getString("name");

		e1 = (EditText) findViewById(R.id.editTextMsg);
		bttn = (Button) findViewById(R.id.buttonAdd);
		l1 = (ListView) findViewById(R.id.listViewFirst);
		logo = (Button) findViewById(R.id.buttonLogout);

		Myshare.initializeContext(getApplicationContext());
		sp = Myshare.getInstance();
//
//		MessagesDBHelper messHelper = new MessagesDBHelper(
//				getApplicationContext());

		SharedPreferences.Editor editor = sp.edit();
		editor.putInt("save_flag", 1);
		editor.putString("name", name);
		editor.commit();
		
		a1 = new CustomAdapter(getApplicationContext(), R.layout.custom_list,
		 str);

		 l1.setAdapter(a1);
//		Cursor cur = messHelper.selectMessages(name);
//
//		c1 = new CustomCursor(getApplicationContext(), cur,
//				R.layout.custom_list);
//		l1.setAdapter(c1);

	//	i = cur.getCount();

//		if (i > 0) {
//
//			Log.e("inside i if", String.valueOf(i));
//			cur.moveToFirst();
//			// do {
//			// String c2 = cur.getString(cur
//			// .getColumnIndex(DatabaseHelper.COLUMN_MESSAGE));
//			// String c3 = cur.getString(cur
//			// .getColumnIndex(DatabaseHelper.COLUMN_TIMESTAMP));
//			// String c1 = cur.getString(cur
//			// .getColumnIndex(DatabaseHelper.COLUMN_USERNAME));
//			//
//			// //str.add(new Messg(c1, c2, c3));
//			//
			// } while (cur.moveToNext());

		//	c1.notifyDataSetChanged();

	//	}
		
		
		lh2 = new Handler(Looper.getMainLooper()) {
			@Override
			public void handleMessage(Message msg) {
				String sname = msg.getData().getString(
						"name1");
				String sdisc = msg.getData().getString(
						"mess1");
				String stm = msg.getData().getString(
						"timestamp1");
				str.add(new Messg(sname,sdisc,stm));
				a1.notifyDataSetChanged();
				Log.e("data",sname+sdisc+stm );
				
			}
		};
		Log.e("data","jejejeje" );

		 new Mygetdata(ThirdActivity.this,lh2).execute(name);
		 l1.setSelection(str.size() - 1);

		bttn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String message = e1.getText().toString().trim();

				if (null != message && !message.toString().equals("")) {
					
					Time t=new Time();
					t.setToNow();
					String tm=""+t.hour+":"+t.minute;
					Log.e("inside add click", message);
				//	str.add(new Messg(name, message, tm));
					
					new Mygetdata(ThirdActivity.this,lh2).execute(name,message,tm,null);
					new Mygetdata(ThirdActivity.this,lh2).execute(name);
					
					l1.setSelection(str.size() - 1);

					e1.setText("");
				
				}
			}
		});
		logo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SharedPreferences.Editor editor = sp.edit();
				editor.putInt("save_flag", 0);
				editor.commit();
				Intent secnd = new Intent(getApplicationContext(),
						LoginActivity.class);

				startActivity(secnd);
				finish();

			}
		});

	}

}
