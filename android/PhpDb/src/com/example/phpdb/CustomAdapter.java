package com.example.phpdb;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

class CustomAdapter extends ArrayAdapter<Messg> {

	private List<Messg> lst;
	private LayoutInflater glf;
	private int resourse;

	public CustomAdapter(Context context, int resourse1, List<Messg> objects) {

		super(context, resourse1, objects);

		resourse = resourse1;
		lst = objects;
		glf = LayoutInflater.from(context);
	}

	public class ViewHolder {
		TextView t1, t2, t3;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View vi = convertView;
		ViewHolder holder;

		if (null == vi) {

			holder = new ViewHolder();

			vi = glf.inflate(resourse, parent, false);

			holder.t1 = (TextView) vi.findViewById(R.id.textViewLname);
			holder.t2 = (TextView) vi.findViewById(R.id.textViewLdesc);
			holder.t3 = (TextView) vi.findViewById(R.id.textViewLtime);

			vi.setTag(holder);
			Log.e("TAG STATUS", "IF");
		} else {
			Log.e("TAG STATUS", "ELSE");
			holder = (ViewHolder) vi.getTag();
		}

		Messg anm = lst.get(position);

		holder.t3.setText(anm.getTm());
		holder.t1.setText(anm.getName());
		holder.t2.setText(anm.getDesc());

		if (position % 2 == 1) {
			vi.setBackgroundColor(Color.CYAN);
		} else {
			vi.setBackgroundColor(Color.LTGRAY);
		}

		return vi;
	}

}
