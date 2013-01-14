package pl.pwr.smartkill.fragments;

import java.util.ArrayList;

import pl.pwr.smartkill.R;
import pl.pwr.smartkill.SKApplication;
import pl.pwr.smartkill.obj.Match;
import pl.pwr.smartkill.obj.Matches;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.UiThread;
@EFragment
public class ProfileListFragment extends Fragment implements OnItemClickListener{
	ArrayList<Match> items;
	boolean loading=false;
	

	
	ListView list;
    static ProfileListFragment newInstance(int num) {
        ProfileListFragment f = new ProfileListFragment();
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
    	
        View v = inflater.inflate(R.layout.profile, container, false);
        TextView deaths = (TextView)v.findViewById(R.id.profile_death_count);
        TextView kills = (TextView)v.findViewById(R.id.profile_kill_count);
        TextView asKiller = (TextView)v.findViewById(R.id.profile_games_killer);
        TextView asPrey = (TextView)v.findViewById(R.id.profile_games_prey);
        TextView login = (TextView)v.findViewById(R.id.profile_login);
        deaths.setText(((SKApplication)getActivity().getApplication()).getMyProfile().getPoints_prey()+"");
        kills.setText(((SKApplication)getActivity().getApplication()).getMyProfile().getPoints_hunter()+"");
        login.setText(((SKApplication)getActivity().getApplication()).getMyProfile().getUsername()+"");
        asKiller.setText(((SKApplication)getActivity().getApplication()).getMyProfile().getMatches_hunter()+"");
        asPrey.setText(((SKApplication)getActivity().getApplication()).getMyProfile().getMatches_prey()+"");
//        list.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//            	getMatches();
//            }
//        });
//        
        
//        list.setOnLoadMoreListener(new OnLoadMoreListener() {
//			
//			public void onLoadMore() {
//				page++;
//				refresh();
//			}
//		});
//    	list.setOnItemClickListener(this);
    	getMatches();
        return v;
    }
    
	@Background
	public void getMatches(){
//		HashMap<String , String> params = new HashMap<String, String>();
//		params.put("id", ((SKApplication)getActivity().getApplication()).getSessionId());
//		Matches m = new WebserviceHandler<Matches>().getAndParse(getActivity(), new PostRequest(SKApplication.API_URL+"matches", params), new Matches());
//		updateList(m);
	}
	
	@UiThread
	public void updateList(Matches m){
//		list.onRefreshComplete();
//    	list.onLoadMoreComplete();
//		list.setVisibility(View.VISIBLE);
//		list.setAdapter(new GameListAdapter(getActivity(), m.getMatches())); 	
	}

    @Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
    	//TODO
	}
    
}
