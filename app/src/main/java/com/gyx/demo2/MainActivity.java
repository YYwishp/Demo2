package com.gyx.demo2;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

	private ListView lv;
	private ArrayList<String> mList;
	private int time = 1;

	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			handler.sendEmptyMessageDelayed(time, 1000);
			mList.add(System.currentTimeMillis()+"");
			myAdapter.notifyDataSetChanged();

		}
	};
	private MyAdapter myAdapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();


	}


	/**
	 * 初始化控件
	 */
	private void initView() {


		lv = (ListView) findViewById(R.id.lv);
		mList = new ArrayList<>();

		myAdapter = new MyAdapter();
		lv.setAdapter(myAdapter);

		handler.sendEmptyMessageDelayed(time, 0);

	}

	private class MyAdapter extends BaseAdapter{

		private View view;

		@Override
		public int getCount() {

			return mList.size()==0? 0:mList.size();
		}

		@Override
		public Object getItem(int position) {
			return mList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				view = View.inflate(MainActivity.this, R.layout.item, null);

				TextView textView = (TextView) view.findViewById(R.id.textview);
				textView.setText(mList.get(position));

			} else {
				view = convertView;

			}

			return view;
		}
	}



}
