package com.smileup.helpers;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.smileup.R;

public class ImportSlidingMenu {
	private Activity context;
	private ImageView editButton;

	private ArrayList<HashMap<String, Object>> itemContentList = new ArrayList<HashMap<String, Object>>();
	private ListView list;
	private OnItemClickListener mItemListener;
	private View.OnClickListener mOnClickListener;
	private ArrayList<Object> ownInfo;

	private int[] icons = { R.drawable.grey_campaign, R.drawable.grey_discover,
			R.drawable.grey_face, R.drawable.white_person, R.drawable.grey_faq,
			R.drawable.smileupicon };
	private String[] subtitle = { "Be a hero for your cause.",
			"Find new causes.", "You have 2 new notifications.",
			"Find the people who support your cause!",
			"Find help and answers.", "Learn more about SmileUP" };
	private String[] title = { "Create Campaign", "Discover",
			"View Notifications", "Find Friends", "FAQ", "About" };

	public ImportSlidingMenu(Activity paramActivity) {
		this.context = paramActivity;
	}

	public SlidingMenu createLeftMenu() {
		SlidingMenu mSlideingMenu = new SlidingMenu(this.context);
		mSlideingMenu.setMode(SlidingMenu.LEFT);
		mSlideingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		mSlideingMenu.setTouchModeBehind(SlidingMenu.TOUCHMODE_MARGIN);
		mSlideingMenu.setFadeDegree(0.35F);
		mSlideingMenu.setClickable(true);
		mSlideingMenu.setFocusable(true);
		mSlideingMenu.setFocusableInTouchMode(true);
		mSlideingMenu.setBehindOffsetRes(R.dimen.slidemenu_horizontal_offset);
		mSlideingMenu
				.attachToActivity(this.context, SlidingMenu.SLIDING_WINDOW);
		mSlideingMenu.setMenu(R.layout.leftslidingmenu_main);

		this.list = ((ListView) mSlideingMenu.getMenu().findViewById(
				R.id.leftmenu_constant_main));

		setListViewData(this.list);

		this.editButton = ((ImageView) mSlideingMenu.getMenu().findViewById(
				R.id.setting_leftslidingmenu));
		return mSlideingMenu;
	}

	public void setDataSet(ArrayList<Object> paramArrayList) {
		this.ownInfo = paramArrayList;
	}

	public void setListViewItemClickListener(OnItemClickListener itemListener) {
		this.mItemListener = itemListener;
		if (this.mItemListener != null)
			this.list.setOnItemClickListener(this.mItemListener);
	}

	public void setSettingsOnClickListener(View.OnClickListener clickListener) {
		this.mOnClickListener = clickListener;
		if (this.mOnClickListener != null)
			this.editButton.setOnClickListener(this.mOnClickListener);
	}

	private void setListViewData(ListView list) {

		for (int i = 0; i < this.title.length; i++) {
			HashMap<String, Object> hashmap = new HashMap<String, Object>();

			hashmap.put("title", this.title[i]);
			hashmap.put("subtitle", this.subtitle[i]);
			hashmap.put("icon", Integer.valueOf(this.icons[i]));

			this.itemContentList.add(hashmap);
		}
		list.setAdapter(new SimpleAdapter(this.context, this.itemContentList,
				R.layout.item_listview_leftslidingmenu, new String[] { "title",
						"subtitle", "icon" }, new int[] {
						R.id.title_itemlistview_leftslidingmenu,
						R.id.subtitle_itemlistview_leftslidingmenu,
						R.id.icon_itemlistview_leftslidingmenu }));

	}

}
