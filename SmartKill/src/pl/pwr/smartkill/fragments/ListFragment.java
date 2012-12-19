package pl.pwr.smartkill.fragments;

import java.util.ArrayList;
import java.util.HashMap;

import pl.pwr.smartkill.R;
import pl.pwr.smartkill.SKApplication;
import pl.pwr.smartkill.activities.MapsActivity;
import pl.pwr.smartkill.activities.MapsActivity_;
import pl.pwr.smartkill.adapters.GameListAdapter;
import pl.pwr.smartkill.obj.Match;
import pl.pwr.smartkill.obj.Matches;
import pl.pwr.smartkill.tools.WebserviceHandler;
import pl.pwr.smartkill.tools.httpRequests.PostRequest;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.UiThread;
@EFragment
public class ListFragment extends Fragment implements OnItemClickListener{
	ArrayList<Match> items;
	boolean loading=false;
	

	
	ListView list;
    static ListFragment newInstance(int num) {
        ListFragment f = new ListFragment();
        return f;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        items = new ArrayList<Match>();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
        View v = inflater.inflate(R.layout.pulllist, container, false);
        list = (ListView) v.findViewById(R.id.pulllist);
    	list.setOnItemClickListener(this);
    	getMatches();
        return v;
    }
    
	@Background
	public void getMatches(){
		HashMap<String , String> params = new HashMap<String, String>();
		params.put("id", ((SKApplication)getActivity().getApplication()).getSessionId());
		Matches m = new WebserviceHandler<Matches>().getAndParse(getActivity(), new PostRequest(SKApplication.API_URL+"matches", params), new Matches());
		updateList(m);
	}
	
	@UiThread
	public void updateList(Matches m){
		list.setVisibility(View.VISIBLE);
		list.setAdapter(new GameListAdapter(getActivity(), m.getMatches())); 	
	}

    @Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
    	MapsActivity_.intent(getActivity()).start();
	}
    
}
