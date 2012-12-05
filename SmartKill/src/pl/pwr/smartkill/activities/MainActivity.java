package pl.pwr.smartkill.activities;

import java.util.ArrayList;
import java.util.HashMap;

import pl.pwr.smartkill.R;
import pl.pwr.smartkill.SKApplication;
import pl.pwr.smartkill.adapters.GameListAdapter;
import pl.pwr.smartkill.obj.Match;
import pl.pwr.smartkill.obj.Matches;
import pl.pwr.smartkill.tools.WebserviceHandler;
import pl.pwr.smartkill.tools.httpRequests.PostRequest;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockActivity;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.App;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;


@EActivity(R.layout.activity_main)
public class MainActivity extends SherlockActivity {
	
	@App
	SKApplication app;
	
	@ViewById(R.id.main_list)
	ListView list;
	
	@AfterViews
	public void prepare(){
		getMatches();
	}
	
	@Background
	public void getMatches(){
		HashMap<String , String> params = new HashMap<String, String>();
		params.put("id", app.getSessionId());
		Matches m = new WebserviceHandler<Matches>().getAndParse(this, new PostRequest(SKApplication.API_URL+"matches", params), new Matches());
		updateList(m);
	}
	
	@UiThread
	public void updateList(Matches m){
		ArrayList<Match> l = new ArrayList<Match>();
		for(HashMap<String, String> item:m.getMatches()){
			Match match = new Match();
			match.setCreated_at(item.get("created_at"));
			match.setDue_date(item.get("due_date"));
			match.setId(Integer.parseInt(item.get("id")));
			match.setLat(item.get("lat"));
			match.setLength(Integer.parseInt(item.get("length")));
			match.setLng(item.get("lng"));
			match.setMax_players(Integer.parseInt(item.get("max_players")));
			match.setName(item.get("name"));
			match.setPassword(item.get("password"));
			match.setSize(Integer.parseInt(item.get("size")));
			l.add(match);
		}
		list.setAdapter(new GameListAdapter(this, l)); 	
	}
}
