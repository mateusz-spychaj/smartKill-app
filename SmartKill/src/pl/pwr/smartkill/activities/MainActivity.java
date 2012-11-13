package pl.pwr.smartkill.activities;

import android.content.Intent;

import com.actionbarsherlock.app.SherlockActivity;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;

import pl.pwr.smartkill.R;


@EActivity(R.layout.activity_main)
public class MainActivity extends SherlockActivity {
	
	@Click(R.id.game_button) 
	public void otworzMape(){
		startActivity(new Intent(this,MapsActivity.class));
	}
}
