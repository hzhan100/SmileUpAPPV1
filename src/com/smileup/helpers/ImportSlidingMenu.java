package com.smileup.helpers;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class ImportSlidingMenu
{
  private Activity context;
  private ImageView editButton;
  private int[] icons = { 2130837658, 2130837660, 2130837661, 2130837725, 2130837663, 2130837709 };
  private ArrayList<HashMap<String, Object>> itemContentList = new ArrayList();
  private ListView list;
  private OnItemClickListenerForSlidingMenu mItemListener;
  private View.OnClickListener mOnClickListener;
  private ArrayList<Object> ownInfo;
  private String[] subtitle = { "Be a hero for your cause.", "Find new causes.", "You have 2 new notifications.", "Find the people who support your cause!", "Find help and answers.", "Learn more about SmileUP" };
  private String[] title = { "Create Campaign", "Discover", "View Notifications", "Find Friends", "FAQ", "About" };

  public ImportSlidingMenu(Activity paramActivity)
  {
    this.context = paramActivity;
  }

  private void setListViewData(ListView paramListView)
  {
    for (int i = 0; ; i++)
    {
      if (i >= this.title.length)
      {
        paramListView.setAdapter(new SimpleAdapter(this.context, this.itemContentList, 2130903095, new String[] { "title", "subtitle", "icon" }, new int[] { 2131034296, 2131034297, 2131034295 }));
        return;
      }
      HashMap localHashMap = new HashMap();
      localHashMap.put("title", this.title[i]);
      localHashMap.put("subtitle", this.subtitle[i]);
      localHashMap.put("icon", Integer.valueOf(this.icons[i]));
      this.itemContentList.add(localHashMap);
    }
  }

  public SlidingMenu createLeftMenu()
  {
    SlidingMenu localSlidingMenu = new SlidingMenu(this.context);
    localSlidingMenu.setMode(0);
    localSlidingMenu.setTouchModeAbove(1);
    localSlidingMenu.setTouchModeBehind(0);
    localSlidingMenu.setFadeDegree(0.35F);
    localSlidingMenu.setClickable(true);
    localSlidingMenu.setFocusable(true);
    localSlidingMenu.setFocusableInTouchMode(true);
    localSlidingMenu.setBehindOffsetRes(2131230737);
    localSlidingMenu.attachToActivity(this.context, 0);
    localSlidingMenu.setMenu(2130903097);
    this.list = ((ListView)localSlidingMenu.findViewById(2131034326));
    setListViewData(this.list);
    this.editButton = ((ImageView)localSlidingMenu.findViewById(2131034325));
    return localSlidingMenu;
  }

  public void setDataSet(ArrayList<Object> paramArrayList)
  {
    this.ownInfo = paramArrayList;
  }

  public void setItemClickListenerForSlidingMenu(OnItemClickListenerForSlidingMenu paramOnItemClickListenerForSlidingMenu)
  {
    this.mItemListener = paramOnItemClickListenerForSlidingMenu;
    if (this.mItemListener != null)
      this.list.setOnItemClickListener(this.mItemListener);
  }

  public void setOnClickListenerForSlidingMenu(View.OnClickListener paramOnClickListener)
  {
    this.mOnClickListener = paramOnClickListener;
    if (this.mOnClickListener != null)
      this.editButton.setOnClickListener(this.mOnClickListener);
  }

  public static abstract interface OnItemClickListenerForSlidingMenu extends AdapterView.OnItemClickListener
  {
  }
}

/* Location:           C:\Users\Hanyu\Desktop\classes_dex2jar.jar
 * Qualified Name:     com.smileup.appv1.tools.ImportSlidingMenu
 * JD-Core Version:    0.6.0
 */