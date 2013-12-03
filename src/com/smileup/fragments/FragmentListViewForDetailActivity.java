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
import android.widget.ListView;

import com.smileup.DetailSecondLevelActivity;
import com.smileup.R;
import com.smileup.adapters.ListViewAdapterForDetailActivity;
import com.smileup.dummycode.HardCodes;

public class FragmentListViewForDetailActivity extends Fragment {
	private String[] charitiesNames = null;
	private String[] charitiesPhotoNames = null;
	private Handler handler;
	private ListView listview;
	private ArrayList<HashMap<String, Object>> listForCharities;
	private HandlerThread thread;
	private View view;

	private static final String Charities = "charities";

	@Override
	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);

		Intent localIntent = getActivity().getIntent();

		this.charitiesNames = localIntent.getStringArrayExtra("charity_name");
		this.charitiesPhotoNames = localIntent
				.getStringArrayExtra("charitryphoto_name");

		this.thread = new HandlerThread("ListViewFragmentForCamp");
		this.thread.start();

		this.handler = new Handler(this.thread.getLooper()) {

			public void handleMessage(Message paramMessage) {

				if ((!FragmentListViewForDetailActivity.this.getActivity()
						.isFinishing())
						&& (!FragmentListViewForDetailActivity.this
								.isRemoving())) {

					FragmentListViewForDetailActivity.this
							.getActivity()
							.runOnUiThread(
									new FragmentListViewForDetailActivity.updateDisplay());
				}
			}
		};
	}

	@Override
	public View onCreateView(LayoutInflater paramLayoutInflater,
			ViewGroup parentView, Bundle paramBundle) {

		this.listForCharities = new ArrayList<HashMap<String, Object>>();

		createDiscoverList();

		this.view = paramLayoutInflater.inflate(
				R.layout.fragment_listview_forcampaign, parentView, false);
		this.listview = ((ListView) this.view.findViewById(R.id.listview_listviewforcamp_fragment));
		this.listview.setOnItemClickListener(new MyGridItemClickListener());

		this.handler.sendMessage(new Message());

		return this.view;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		if (thread != null)
			this.thread.quit();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	private void createDiscoverList() {
		if ((this.listForCharities != null)
				&& (this.charitiesPhotoNames != null)
				&& (this.charitiesNames != null)) {
			for (int i = 0; i < this.charitiesPhotoNames.length; i++) {

				HashMap<String, Object> hashmap = new HashMap<String, Object>();
				hashmap.put("headerphotoforlistviewofdetail",
						this.charitiesPhotoNames[i]);
				hashmap.put("nameforlistviewofdetail", this.charitiesNames[i]);

				this.listForCharities.add(hashmap);
			}
		}
	}

	class MyGridItemClickListener implements AdapterView.OnItemClickListener {

		public void onItemClick(AdapterView<?> paramAdapterView,
				View paramView, int paramInt, long paramLong) {
			if (paramInt == 1) {
				Intent intent1 = new Intent(
						FragmentListViewForDetailActivity.this.getActivity(),
						DetailSecondLevelActivity.class);
				intent1.putExtra("photos_names", HardCodes.veggielutionPhotos);
				intent1.putExtra("title", "Veggielution");
				FragmentListViewForDetailActivity.this.startActivity(intent1);

			} else {
				Intent intent2 = new Intent(
						FragmentListViewForDetailActivity.this.getActivity(),
						DetailSecondLevelActivity.class);
				FragmentListViewForDetailActivity.this.startActivity(intent2);
			}
		}
	}

	class updateDisplay implements Runnable {

		public void run() {

			ListViewAdapterForDetailActivity listviewAdapter = new ListViewAdapterForDetailActivity(
					FragmentListViewForDetailActivity.this.getActivity(),
					R.layout.item_listview_detail_activity,
					FragmentListViewForDetailActivity.this.listForCharities,
					null);
			FragmentListViewForDetailActivity.this.listview
					.setAdapter(listviewAdapter);
			FragmentListViewForDetailActivity.this.listview.invalidate();
		}
	}
}

/*
 * Location: C:\Users\Hanyu\Desktop\classes_dex2jar.jar Qualified Name:
 * com.smileup.appv1.fragment.FragmentListViewForDetailActivity JD-Core Version:
 * 0.6.0
 */