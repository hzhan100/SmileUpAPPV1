package com.smileup.fragments;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.smileup.DetailActivity;
import com.smileup.R;
import com.smileup.adapters.DiscoverGridViewAdapter;
import com.smileup.adapters.DiscoverListAdapter;
import com.smileup.dummycode.HardCodes;

public class FragmentDiscoverForMain extends Fragment {
	private ArrayList<HashMap<String, Object>> discoverList;
	private GridView gridview;
	private Handler handler;
	private HandlerThread thread;
	private View mainView;

	private DiscoverListAdapter discoverListAdapter;
	private DiscoverGridViewAdapter gridviewAdapter;

	@Override
	public void onCreate(Bundle savedInstance) {
		super.onCreate(savedInstance);

		this.thread = new HandlerThread("Discover Fragment");
		this.thread.start();

		this.handler = new Handler(this.thread.getLooper()) {

			public void handleMessage(Message msg) {

				if ((!FragmentDiscoverForMain.this.getActivity().isFinishing())
						&& (!FragmentDiscoverForMain.this.isRemoving())) {

					FragmentDiscoverForMain.this.getActivity().runOnUiThread(
							new updateDisplay());
				}
			}
		};
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parentView,
			Bundle paramBundle) {

		this.discoverList = new ArrayList<HashMap<String, Object>>();

		createDiscoverList();

		this.mainView = inflater.inflate(R.layout.fragment_discover_main,
				parentView, false);

		this.gridview = ((GridView) this.mainView
				.findViewById(R.id.gridview_discover_fragment));

		discoverListAdapter = new DiscoverListAdapter(this.getActivity(),
				R.layout.item_gridview_discover_fragment, this.discoverList,
				null);

		// gridviewAdapter = new DiscoverGridViewAdapter(getActivity(), null,
		// "Discover Fragment");

		this.gridview.setAdapter(discoverListAdapter);

		this.gridview.setOnItemClickListener(new MyGridItemClickListener());

		// this.handler.sendMessage(new Message());

		return this.mainView;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (thread != null && thread.isAlive())
			this.thread.quit();

	}

	@Override
	public void onResume() {
		super.onResume();
	}

	private void createDiscoverList() {
		if (this.discoverList != null) {

			for (int i = 0; i < HardCodes.categoriesName.length; i++) {

				HashMap<String, Object> hashmap = new HashMap<String, Object>();
				hashmap.put(DiscoverListAdapter.HeaderPhoto,
						HardCodes.categoriesPhotosName[i]);
				hashmap.put(DiscoverListAdapter.Name,
						HardCodes.categoriesName[i]);

				this.discoverList.add(hashmap);

			}

		}
	}

	class MyGridItemClickListener implements AdapterView.OnItemClickListener {

		public void onItemClick(AdapterView<?> paramAdapterView,
				View paramView, int paramInt, long paramLong) {

			if (paramInt == 1) {
				Intent intent1 = new Intent();
				intent1.putExtra("charity_name", HardCodes.environmentNames);
				intent1.putExtra("charitryphoto_name",
						HardCodes.environmentPhotosNames);
				intent1.putExtra("tile", "Evironment");
				intent1.setClass(FragmentDiscoverForMain.this.getActivity(),
						DetailActivity.class);
				FragmentDiscoverForMain.this.startActivity(intent1);
				return;
			}
			Intent intent2 = new Intent();
			intent2.setClass(FragmentDiscoverForMain.this.getActivity(),
					DetailActivity.class);
			FragmentDiscoverForMain.this.startActivity(intent2);
		}
	}

	class updateDisplay implements Runnable {

		public void run() {
			//
			// discoverListAdapter = new DiscoverListAdapter(
			// FragmentDiscoverForMain.this.getActivity(),
			// R.layout.item_gridview_discover_fragment,
			// FragmentDiscoverForMain.this.discoverList, null);
			//
			// FragmentDiscoverForMain.this.gridview
			// .setAdapter(discoverListAdapter);

			// FragmentDiscoverForMain.this.gridview
			// .setOnItemClickListener(new
			// FragmentDiscoverForMain.MyGridItemClickListener(
			// FragmentDiscoverForMain.this));

			// FragmentDiscoverForMain.this.gridview.invalidate();
		}
	}
}
