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

import com.smileup.DetailSecondLevelActivity;
import com.smileup.R;
import com.smileup.adapters.GridViewAdapterForDetailActivity;
import com.smileup.dummycode.HardCodes;

public class FragmentGridViewForDetailActivity extends Fragment {
	private String[] charitiesNames = null;
	private String[] charitiesPhotoNames = null;
	private GridView gridview;
	private ArrayList<HashMap<String, Object>> gridviewlistforcampaign;
	private Handler handler;
	private HandlerThread thread;
	private View view;

	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);

		Intent intent = getActivity().getIntent();
		this.charitiesNames = intent.getStringArrayExtra("charity_name");
		this.charitiesPhotoNames = intent
				.getStringArrayExtra("charitryphoto_name");

		this.thread = new HandlerThread("GridViewForCamp Fragment");
		this.thread.start();

		this.handler = new Handler(this.thread.getLooper()) {

			public void handleMessage(Message paramMessage) {

				if ((!FragmentGridViewForDetailActivity.this.getActivity()
						.isFinishing())
						&& (!FragmentGridViewForDetailActivity.this
								.isRemoving())) {

					FragmentGridViewForDetailActivity.this
							.getActivity()
							.runOnUiThread(
									new FragmentGridViewForDetailActivity.updateDisplay());
				}
			}
		};
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup parentView,
			Bundle paramBundle) {

		this.gridviewlistforcampaign = new ArrayList<HashMap<String, Object>>();

		createDiscoverList();

		this.view = inflater.inflate(R.layout.fragment_gridview_forcampaign,
				parentView, false);

		this.gridview = ((GridView) this.view
				.findViewById(R.id.gridview_gridviewforcamp_fragment));
		this.gridview.setOnItemClickListener(new MyGridItemClickListener());

		this.handler.sendMessage(new Message());

		return this.view;
	}

	public void onDestroy() {
		super.onDestroy();
		if (thread != null) {
			this.thread.quit();
		}
	}

	public void onResume() {
		super.onResume();
	}

	private void createDiscoverList() {
		if ((this.gridviewlistforcampaign != null)
				&& (this.charitiesPhotoNames != null)
				&& (this.charitiesNames != null)) {

			for (int i = 0; i < this.charitiesPhotoNames.length; i++) {
				HashMap<String, Object> hashmap = new HashMap<String, Object>();
				hashmap.put("headerphotoforgridviewofdetail",
						this.charitiesPhotoNames[i]);
				hashmap.put("nameforgridviewofdetail", this.charitiesNames[i]);

				this.gridviewlistforcampaign.add(hashmap);
			}
		}
	}

	class MyGridItemClickListener implements AdapterView.OnItemClickListener {

		public void onItemClick(AdapterView<?> paramAdapterView,
				View paramView, int position, long paramLong) {

			if (position == 1) {
				Intent intent1 = new Intent(
						FragmentGridViewForDetailActivity.this.getActivity(),
						DetailSecondLevelActivity.class);

				intent1.putExtra("photos_names", HardCodes.veggielutionPhotos);
				intent1.putExtra("title", "Veggielution");

				FragmentGridViewForDetailActivity.this.startActivity(intent1);

			} else {
				Intent intent2 = new Intent(
						FragmentGridViewForDetailActivity.this.getActivity(),
						DetailSecondLevelActivity.class);

				FragmentGridViewForDetailActivity.this.startActivity(intent2);
			}
		}
	}

	class updateDisplay implements Runnable {
		updateDisplay() {
		}

		public void run() {
			GridViewAdapterForDetailActivity gridviewAdapter = new GridViewAdapterForDetailActivity(
					FragmentGridViewForDetailActivity.this.getActivity(),
					R.layout.item_gridview_details_activity,
					FragmentGridViewForDetailActivity.this.gridviewlistforcampaign,
					null);
			FragmentGridViewForDetailActivity.this.gridview
					.setAdapter(gridviewAdapter);
			FragmentGridViewForDetailActivity.this.gridview
					.setOnItemClickListener(new FragmentGridViewForDetailActivity.MyGridItemClickListener());
			FragmentGridViewForDetailActivity.this.gridview.invalidate();
		}
	}
}

/*
 * Location: C:\Users\Hanyu\Desktop\classes_dex2jar.jar Qualified Name:
 * com.smileup.appv1.fragment.FragmentGridViewForDetailActivity JD-Core Version:
 * 0.6.0
 */