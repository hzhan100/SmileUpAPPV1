package com.smileup;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockPreferenceActivity;
import com.smileup.dummycode.HardCodes;
import com.smileup.preferences.DialogPreference;
import com.smileup.preferences.PhotoPreference;
import com.smileup.preferences.SwitchPreference;
import com.smileup.preferences.ToggleButtonPreference;

public class SettingsActivity extends SherlockPreferenceActivity implements
		SharedPreferences.OnSharedPreferenceChangeListener {

	private static final String campaignsFromFriendsPreference = "campaigns_from_friends_preference";
	private static final String donationsPreference = "donations_preference";
	private static final String emailPreference = "email_preference";
	private static final String facebookAccountPreference = "facebook_account_preference";
	private static final String firstNamePreference = "firstname_preference";
	private static final String friendRequestsPreference = "friend_requests_preference";
	private static final String genderPreference = "gender_preference";
	private static final String gmailAccountPreference = "gmail_account_preference";
	private static final String introPreference = "intro_preference";
	private static final String lastNamePreference = "lastname_preference";
	private static final String linkedinAccountPreference = "linkedin_account_preference";
	private static final String notificationoPreference = "notification_preference";
	private static final String passwordPreference = "password_preference";
	private static final String photoPreference = "photo_preference";
	private static final String protectAccountPreference = "protect_account_preference";
	private static final String sharingPreference = "sharing_preference";
	private static final String tumblrAccountPreference = "tumblr_account_preference";
	private static final String twitterAccountPreference = "twitter_account_preference";
	private ImageView backButton;
	private CheckBoxPreference donateToMyCampaign;
	private DialogPreference email;
	private ToggleButtonPreference facebook;
	private DialogPreference firstName;
	private CheckBoxPreference friendRequests;
	private SwitchPreference gender;
	private ToggleButtonPreference gmail;
	private DialogPreference intro;
	private DialogPreference lastName;
	private ToggleButtonPreference linkedin;
	private ActionBar mActionBar;
	private View mActionBarView;
	private SharedPreferences mPrefers;
	private CheckBoxPreference newCampaignFromFriends;
	private SwitchPreference notification;
	private DialogPreference password;
	private PhotoPreference photo;
	private SwitchPreference protectAccount;
	private SwitchPreference sharing;
	private TextView titleActionBar;
	private ToggleButtonPreference tumblr;
	private ToggleButtonPreference twitter;

	@SuppressWarnings("deprecation")
	private void findPreferences() {
		this.photo = ((PhotoPreference) findPreference("photo_preference"));
		this.intro = ((DialogPreference) findPreference("intro_preference"));
		this.email = ((DialogPreference) findPreference("email_preference"));
		this.password = ((DialogPreference) findPreference("password_preference"));
		this.firstName = ((DialogPreference) findPreference("firstname_preference"));
		this.lastName = ((DialogPreference) findPreference("lastname_preference"));
		this.facebook = ((ToggleButtonPreference) findPreference("facebook_account_preference"));
		this.twitter = ((ToggleButtonPreference) findPreference("twitter_account_preference"));
		this.tumblr = ((ToggleButtonPreference) findPreference("tumblr_account_preference"));
		this.linkedin = ((ToggleButtonPreference) findPreference("linkedin_account_preference"));
		this.gmail = ((ToggleButtonPreference) findPreference("gmail_account_preference"));
		this.friendRequests = ((CheckBoxPreference) findPreference("friend_requests_preference"));
		this.donateToMyCampaign = ((CheckBoxPreference) findPreference("donations_preference"));
		this.newCampaignFromFriends = ((CheckBoxPreference) findPreference("campaigns_from_friends_preference"));
		this.gender = ((SwitchPreference) findPreference("gender_preference"));
		this.notification = ((SwitchPreference) findPreference("notification_preference"));
		this.sharing = ((SwitchPreference) findPreference("sharing_preference"));
		this.protectAccount = ((SwitchPreference) findPreference("protect_account_preference"));
	}

	private void initActionBarViews() {
		this.backButton = ((ImageView) this.mActionBarView
				.findViewById(R.id.backbutton_actionbar_detail_activity));
		this.backButton.setOnClickListener(new MyActionBarBackButtonListener());
		this.titleActionBar = ((TextView) this.mActionBarView
				.findViewById(R.id.title_actionbar_detail_activity));
		this.titleActionBar.setText("Settings");
	}

	private void initDialogPreferences() {
		this.intro.setTitleText("");
		this.intro.setHintForContent("Say something about yourself");
		this.intro.setSharePrefsKey("intro_preference");
		this.email.setTitleText("Email");
		this.email.setHintForContent("123@gamil.com");
		this.email.setSharePrefsKey("email_preference");
		this.password.setTitleText("Password");
		this.password.setHintForContent("******");
		this.password.setSharePrefsKey("password_preference");
		this.firstName.setTitleText("FirstName");
		this.firstName.setHintForContent("");
		this.firstName.setSharePrefsKey("firstname_preference");
		this.lastName.setTitleText("LastName");
		this.lastName.setHintForContent("");
		this.lastName.setSharePrefsKey("lastname_preference");
	}

	private void initPhotoPreference() {
		this.photo.setBackgroundDrawable(getResources().getDrawable(
				HardCodes.myPhotos[1]));
		this.photo.setPhotoDrawable(getResources().getDrawable(
				HardCodes.myPhotos[0]));
	}

	private void initPreferences() {
		findPreferences();
		initPhotoPreference();
		initSwtichPreferences();
		initDialogPreferences();
		initToggleButtonPreference();
	}

	private void initSharePreferences() {

		this.mPrefers = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		this.mPrefers.registerOnSharedPreferenceChangeListener(this);

		SharedPreferences.Editor editor = this.mPrefers.edit();
		editor.putBoolean("gender_preference",
				this.mPrefers.getBoolean("gender_preference", false));
		editor.putBoolean("notification_preference",
				this.mPrefers.getBoolean("notification_preference", false));
		editor.putBoolean("sharing_preference",
				this.mPrefers.getBoolean("sharing_preference", false));
		editor.putBoolean("protect_account_preference",
				this.mPrefers.getBoolean("protect_account_preference", false));
		editor.putBoolean("facebook_account_preference",
				this.mPrefers.getBoolean("facebook_account_preference", false));
		editor.putBoolean("tumblr_account_preference",
				this.mPrefers.getBoolean("tumblr_account_preference", false));
		editor.putBoolean("twitter_account_preference",
				this.mPrefers.getBoolean("twitter_account_preference", false));
		editor.putBoolean("linkedin_account_preference",
				this.mPrefers.getBoolean("linkedin_account_preference", false));
		editor.commit();
	}

	private void initSwtichPreferences() {

		this.gender.setTitle("Gender");
		this.gender.setTextOff("Female");
		this.gender.setTextOn("Male");
		this.gender.setSharePreferenceKey("gender_preference");
		this.notification.setTitle("Notifications");
		this.notification.setTextOff("OFF");
		this.notification.setTextOn("ON");
		this.notification.setSharePreferenceKey("notification_preference");
		this.sharing.setTitle("Sharing");
		this.sharing.setTextOff("OFF");
		this.sharing.setTextOn("ON");
		this.sharing.setSharePreferenceKey("sharing_preference");
		this.protectAccount.setTitle("Protect Account");
		this.protectAccount.setTextOff("OFF");
		this.protectAccount.setTextOn("ON");
		this.protectAccount.setSharePreferenceKey("protect_account_preference");
	}

	private void initToggleButtonPreference() {

		this.facebook.setIconDrawable(getResources().getDrawable(
				R.drawable.facbook));
		this.facebook.setTitleText("Facebook");
		this.facebook.setSharePrefsKey("facebook_account_preference");

		this.twitter.setIconDrawable(getResources().getDrawable(
				R.drawable.twitter));
		this.twitter.setTitleText("Twitter");
		this.twitter.setSharePrefsKey("twitter_account_preference");

		this.tumblr.setIconDrawable(getResources().getDrawable(
				R.drawable.tumblr));
		this.tumblr.setTitleText("Tumblr");
		this.tumblr.setSharePrefsKey("tumblr_account_preference");

		this.linkedin.setIconDrawable(getResources().getDrawable(
				R.drawable.linkedin));
		this.linkedin.setTitleText("Linkedin");
		this.linkedin.setSharePrefsKey("linkedin_account_preference");

		this.gmail
				.setIconDrawable(getResources().getDrawable(R.drawable.gmail));
		this.gmail.setTitleText("Gmail");
		this.gmail.setSharePrefsKey("gmail_account_preference");
	}

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		
		addPreferencesFromResource(R.xml.settings);
		this.mActionBar = getSupportActionBar();
		this.mActionBar.setDisplayUseLogoEnabled(false);
		this.mActionBar.setDisplayShowHomeEnabled(false);
		this.mActionBar.setDisplayShowCustomEnabled(true);
		this.mActionBar.setCustomView(R.layout.actionbar_detail_activity);
		this.mActionBar.setBackgroundDrawable(getResources().getDrawable(
				android.R.color.white));
		this.mActionBarView = this.mActionBar.getCustomView();
		
		initSharePreferences();
		initActionBarViews();
		initPreferences();
	}

	public void onSharedPreferenceChanged(SharedPreferences sharedPrefs,
			String key) {
		if ("gender_preference".equals(key))

			Toast.makeText(getApplicationContext(),
					"Gender Preference is changed", Toast.LENGTH_SHORT).show();
	}

	private class MyActionBarBackButtonListener implements View.OnClickListener {
		private MyActionBarBackButtonListener() {
		}

		public void onClick(View paramView) {
			SettingsActivity.this.finish();
		}
	}
}

/*
 * Location: C:\Users\Hanyu\Desktop\classes_dex2jar.jar Qualified Name:
 * com.smileup.appv1.SettingsActivity JD-Core Version: 0.6.0
 */