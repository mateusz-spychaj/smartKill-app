package pl.pwr.smartkill.activities;

import java.util.ArrayList;
import java.util.HashMap;

import pl.pwr.smartkill.R;
import pl.pwr.smartkill.SKApplication;
import pl.pwr.smartkill.fragments.ListFragment_;
import pl.pwr.smartkill.tools.WebserviceHandler;
import pl.pwr.smartkill.tools.httpRequests.PostRequest;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.googlecode.androidannotations.annotations.App;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EActivity;


@EActivity
public class MainActivity extends SherlockFragmentActivity {
	
	@App
	SKApplication app;
	
	private ViewPager  mViewPager;
	private TabsAdapter mTabsAdapter;

	private static MainActivity ourInstance; 

	public static MainActivity getInstance(){
		return ourInstance;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS); 
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		setContentView(R.layout.main);
		getSupportActionBar().setSubtitle(app.getMyProfile().getUsername());
		overridePendingTransition(R.anim.shift_in, R.anim.shift_out);

		ourInstance=this;

//		ActionBar.Tab tab1 = getSupportActionBar().newTab().setText("Wszystkie");
		ActionBar.Tab tab2 = getSupportActionBar().newTab().setText("Zapisany");
//		ActionBar.Tab tab3 = getSupportActionBar().newTab().setText("Profil");
		
		mViewPager = (ViewPager)findViewById(R.id.pager);
		mTabsAdapter = new TabsAdapter(this, getSupportActionBar(), mViewPager);
//		mTabsAdapter.addTab(tab1, EmptyListFragment_.class);
		mTabsAdapter.addTab(tab2, ListFragment_.class);
//		mTabsAdapter.addTab(tab3, EmptyListFragment_.class);
		mViewPager.setCurrentItem(0);

	}

	public static class TabsAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener, ActionBar.TabListener {
		private final Context mContext;
		private final ActionBar mActionBar;
		private final ViewPager mViewPager;
		private final ArrayList<String> mTabs = new ArrayList<String>();

		public TabsAdapter(FragmentActivity activity, ActionBar actionBar, ViewPager pager) {
			super(activity.getSupportFragmentManager());
			mContext = activity;
			mActionBar = actionBar;
			mViewPager = pager;
			mViewPager.setAdapter(this);
			mViewPager.setOnPageChangeListener(this);
		}

		public void addTab(ActionBar.Tab tab, Class<?> clss) {
			mTabs.add(clss.getName());
			mActionBar.addTab(tab.setTabListener(this));
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return mTabs.size();
		}

		@Override
		public Fragment getItem(int position) {
			return Fragment.instantiate(mContext, mTabs.get(position), null);
		}

		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		}

		@Override
		public void onPageSelected(int position) {
			mActionBar.setSelectedNavigationItem(position);
		}

		@Override
		public void onPageScrollStateChanged(int state) {
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			mViewPager.setCurrentItem(tab.getPosition());
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		}

	}
	
//	@AfterViews
//	public void prepare(){
//		getMatches();
//	}
//	

	
	public static final int REFRESH=1;
	public static final int LOGOUT=2;
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuItem item = menu.add(0,REFRESH,0,"Odśwież");
		item.setIcon( R.drawable.refresh)
		.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		MenuItem item2 = menu.add(0,LOGOUT,0,"Wyloguj");
		item2.setIcon( R.drawable.logout)
		.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case REFRESH:
			setProgressBarIndeterminateVisibility(true);
//			getMatches();
			break;
		case LOGOUT:
			setProgressBarIndeterminateVisibility(true);
			logout();
			break;
		}
		return true;
	}
	@Background
	public void logout(){
		HashMap<String , String> params = new HashMap<String, String>();
		params.put("id", app.getSessionId());
		try {
			new WebserviceHandler<String>().getAndParse(this, new PostRequest(SKApplication.API_URL+"logout", params), new String());
		} catch (Exception e) {
		}
		finish();
	}
}
