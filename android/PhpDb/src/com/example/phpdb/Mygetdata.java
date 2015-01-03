package com.example.phpdb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

public class Mygetdata extends AsyncTask<String, Void, Void> {

	private static Context context;
	private StringBuilder total = new StringBuilder();
	private Handler temp;

	public Mygetdata(Context context, Handler temp) {

		this.context = context;
		this.temp = temp;

	}

	@Override
	protected Void doInBackground(String... params) {

		int parmlen = params.length;

		if (parmlen == 3) {

			send(params[0], params[1], params[2]);

		} else if (parmlen == 2) {

			send(params[0], params[1]);

		} else if (parmlen == 1) {
			getmessage(params[0]);

		} else {
			send(params[0], params[1], params[2], params[3]);
		}
		return null;
	}

	private void send(String email, String pass) {

		HttpClient httpclient = new DefaultHttpClient();

		// specify the URL you want to post to
		HttpPost httppost = new HttpPost("http://server.domain/login.php");

		try {

			// create a list to store HTTP variables and their values
			List nameValuePairs = new ArrayList();

			// add an HTTP variable and value pair
			nameValuePairs.add(new BasicNameValuePair("email", email));
			nameValuePairs.add(new BasicNameValuePair("pass", pass));

			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			// send the variable and value, in other words post, to the URL

			HttpResponse response = httpclient.execute(httppost);

			BufferedReader r = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));

			// total = response.getEntity().getContent().toString();
			String line = null;

			while ((line = r.readLine()) != null) {
				total.append(line);

			}

			Message msgObj = temp.obtainMessage();
			Bundle b = new Bundle();
			b.putString("message", total.toString());
			msgObj.setData(b);
			temp.sendMessage(msgObj);

			Log.e("insend2parm", total.toString());

		} catch (ClientProtocolException e) {

			e.printStackTrace();
			Log.e("incatch", "fhsdkf");

		} catch (IOException e) {

			e.printStackTrace();
			Log.e("ioexptn", total.toString());

		}

	}

	protected void onPostExecute() {
	}

	public void send(String eval1, String eval2, String eval3) {

		HttpClient httpclient = new DefaultHttpClient();

		// specify the URL you want to post to
		HttpPost httppost = new HttpPost("http://192.168.0.116/register.php");
		try {

			// create a list to store HTTP variables and their values
			List nameValuePairs = new ArrayList();

			// add an HTTP variable and value pair
			nameValuePairs.add(new BasicNameValuePair("name", eval1));
			nameValuePairs.add(new BasicNameValuePair("email", eval2));
			nameValuePairs.add(new BasicNameValuePair("pass", eval3));

			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			// send the variable and value, in other words post, to the URL
			HttpResponse response = httpclient.execute(httppost);

			BufferedReader r = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));

			// total = response.getEntity().getContent().toString();

			String line = null;
			while ((line = r.readLine()) != null) {
				total.append(line);

			}
			Message msgObj = new Message();
			Bundle b = new Bundle();
			b.putString("message", total.toString());
			msgObj.setData(b);
			temp.sendMessage(msgObj);

		} catch (ClientProtocolException e) {

			e.printStackTrace();
			Log.e("incatch", "fhsdkf");

		} catch (IOException e) {

			e.printStackTrace();
			Log.e("ioexptn", "");

		}

	}

	public void send(String eval1, String eval2, String eval3, String eval4) {

		HttpClient httpclient = new DefaultHttpClient();
		Log.e("inside todo addsent", eval2);
		// specify the URL you want to post to
		HttpPost httppost = new HttpPost("http://192.168.0.116/msginsert.php");
		try {

			// create a list to store HTTP variables and their values
			List nameValuePairs = new ArrayList();

			// add an HTTP variable and value pair
			nameValuePairs.add(new BasicNameValuePair("email", eval1));
			nameValuePairs.add(new BasicNameValuePair("message", eval2));
			nameValuePairs.add(new BasicNameValuePair("timestamp", eval3));

			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			// send the variable and value, in other words post, to the URL
			HttpResponse response = httpclient.execute(httppost);

		} catch (ClientProtocolException e) {

			e.printStackTrace();
			Log.e("incatch", "fhsdkf");

		} catch (IOException e) {

			e.printStackTrace();
			Log.e("ioexptn", "");

		}

	}

	public void getmessage(String email) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://192.168.0.116/getmsg.php");

		try {

			List nameValuePairs = new ArrayList();

			nameValuePairs.add(new BasicNameValuePair("email", email));

			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse response = httpclient.execute(httppost);

			BufferedReader r = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
	
			String line = null;
			while ((line = r.readLine()) != null) {
				total.append(line);

			}

			Log.e("abc", total.toString());

		} catch (ClientProtocolException e) {

			e.printStackTrace();
			Log.e("incatch", "fhsdkf");

		} catch (IOException e) {

			e.printStackTrace();
			Log.e("ioexptn", "");

		}
		putData(total.toString());
	}

	public void putData(String result1) {

		if (result1.equals("") && result1 == null) {
			Log.e("IF", "null");

		} else {

			String OutputData = "";
			// JSONArray jsonResponse;
			JSONObject jsonObject;
			Log.e("Else", "good");
			try {

				/******
				 * Creates a new JSONObject with name/value mappings from the
				 * JSON string.
				 ********/
				jsonObject = new JSONObject(result1);
				// jsonResponse = new JSONArray(result1);
				/*****
				 * Returns the value mapped by name if it exists and is a
				 * JSONArray.
				 ***/
				/******* Returns null otherwise. *******/
				JSONArray jsonMainNode = jsonObject.optJSONArray("android");

				/*********** Process each JSON Node ************/

				// int lengthJsonArr = jsonResponse.length();
				int lengthJsonArr = jsonMainNode.length();

				for (int i = 0; i < lengthJsonArr; i++) {
					/****** Get Object for each JSON node. ***********/
					JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);

					/******* Fetch node values **********/
					int msg_id = Integer.parseInt(jsonChildNode
							.optString("_id").toString());
					String userName = jsonChildNode.optString("name")
							.toString();
					String msgDec = jsonChildNode.optString("message")
							.toString();
					String mTimeStamp = jsonChildNode.optString("timestamp")
							.toString();
					Message msgObj = new Message();
					Bundle b = new Bundle();
					b.putString("name1", userName);
					b.putString("mess1", msgDec);
					b.putString("timestamp1", mTimeStamp);
					msgObj.setData(b);
					temp.sendMessage(msgObj);

					OutputData += " \n\n     " + userName + " | " + msgDec
							+ " | " + mTimeStamp + " \n\n ";
				}

				// Toast.makeText(context, OutputData, Toast.LENGTH_LONG)
				// .show();
				Log.d("json data  parsed", OutputData);

			} catch (JSONException e) {

				e.printStackTrace();
			}
		}
	}

}
