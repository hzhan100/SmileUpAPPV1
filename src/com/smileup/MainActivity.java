package com.smileup;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends SherlockFragmentActivity
{
  private ImageView createCampaignButton;
  private ActionBar mActionBar;
  private View mActionBarView;
  private SlidingMenu mSlidingMenu;
  private TabHost mTabHost;
  private ImageView menuButton;
  private ImportSlidingMenu slidingMenuImporter;

 

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903075);
    this.mActionBar = getSupportActionBar();
    this.mActionBar.setDisplayUseLogoEnabled(false);
    this.mActionBar.setDisplayShowHomeEnabled(false);
    this.mActionBar.setDisplayShowCustomEnabled(true);
    this.mActionBar.setCustomView(2130903063);
    this.mActionBar.setBackgroundDrawable(getResources().getDrawable(17301657));
    this.mActionBarView = this.mActionBar.getCustomView();
    initTabHost(this);
    this.slidingMenuImporter = new ImportSlidingMenu(this);
    this.mSlidingMenu = this.slidingMenuImporter.createLeftMenu();
    this.slidingMenuImporter.setItemClickListenerForSlidingMenu(new ImportSlidingMenu.OnItemClickListenerForSlidingMenu()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        switch (paramInt)
        {
        case 2:
        default:
        case 0:
        case 1:
          do
          {
            do
            {
              return;
              Intent localIntent2 = new Intent();
              localIntent2.setClass(MainActivity.this, CreateCampaignActivity.class);
              MainActivity.this.startActivity(localIntent2);
              return;
            }
            while (MainActivity.this.mSlidingMenu == null);
            MainActivity.this.mSlidingMenu.toggle();
          }
          while (MainActivity.this.mTabHost == null);
          MainActivity.this.mTabHost.setCurrentTab(1);
          return;
        case 3:
        }
        Intent localIntent1 = new Intent();
        localIntent1.setClass(MainActivity.this, FindFriendsActivity.class);
        MainActivity.this.startActivity(localIntent1);
      }
    });
    this.slidingMenuImporter.setOnClickListenerForSlidingMenu(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        Intent localIntent = new Intent(MainActivity.this, SettingsActivity.class);
        MainActivity.this.startActivity(localIntent);
      }
    });
    initActionBarButtons(this.mActionBarView);
  }

  protected void onResume()
  {
    super.onResume();
  }
  
  private void initActionBarButtons(View paramView)
  {
    this.menuButton = ((ImageView)paramView.findViewById(2131034173));
    this.createCampaignButton = ((ImageView)paramView.findViewById(2131034175));
    this.menuButton.setClickable(true);
    this.createCampaignButton.setClickable(true);
    this.menuButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        MainActivity.this.mSlidingMenu.toggle();
      }
    });
    this.createCampaignButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        Intent localIntent = new Intent(MainActivity.this, CreateCampaignActivity.class);
        MainActivity.this.startActivity(localIntent);
      }
    });
  }

  private void initTabHost(Activity paramActivity)
  {
    this.mTabHost = ((TabHost)paramActivity.findViewById(2131034216));
    this.mTabHost.setup();
    this.mTabHost.addTab(this.mTabHost.newTabSpec("Tab1_News").setIndicator("News").setContent(2131034217));
    this.mTabHost.addTab(this.mTabHost.newTabSpec("Tab2_News").setIndicator("Discover").setContent(2131034218));
    this.mTabHost.addTab(this.mTabHost.newTabSpec("Tab3_News").setIndicator("Profile").setContent(2131034219));
    this.mTabHost.setOnTabChangedListener(new MyTabhostListener());
    TabWidget localTabWidget = this.mTabHost.getTabWidget();
    for (int i = 0; ; i++)
    {
      if (i >= localTabWidget.getChildCount())
      {
        this.mTabHost.setCurrentTab(0);
        updateTab(this.mTabHost);
        return;
      }
      TextView localTextView = (TextView)localTabWidget.getChildAt(i).findViewById(16908310);
      localTextView.setTextSize(15.0F);
      localTextView.setTextColor(getResources().getColor(2131165190));
    }
  }

  private void updateTab(TabHost paramTabHost)
  {
    TabWidget localTabWidget = this.mTabHost.getTabWidget();
    int i = 0;
    if (i >= localTabWidget.getChildCount())
      return;
    View localView = localTabWidget.getChildAt(i);
    TextView localTextView = (TextView)localView.findViewById(16908310);
    if (this.mTabHost.getCurrentTab() == i)
    {
      localView.setBackgroundResource(2131165190);
      localTextView.setTextColor(-1);
    }
    while (true)
    {
      i++;
      break;
      localView.setBackgroundResource(17170443);
      localTextView.setTextColor(getResources().getColor(2131165190));
    }
  }

  class MyTabhostListener
    implements TabHost.OnTabChangeListener
  {
    MyTabhostListener()
    {
    }

    public void onTabChanged(String paramString)
    {
      MainActivity.this.mTabHost.setCurrentTabByTag(paramString);
      MainActivity.this.updateTab(MainActivity.this.mTabHost);
    }
  }
}
