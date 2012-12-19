package pl.pwr.smartkill.adapters;

import java.util.ArrayList;
import java.util.List;

import pl.pwr.smartkill.R;
import pl.pwr.smartkill.obj.Match;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GameListAdapter extends BaseAdapter {

	private List<Match> items = new ArrayList<Match>();
	private static LayoutInflater inflater=null;

	class ViewHolder{
		TextView name;
		TextView max;
		TextView free;
		TextView password;
	}

	public GameListAdapter(Activity a, List<Match> itms) {
		items=itms;
		inflater = (LayoutInflater)a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		return items.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView==null){
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list_games, null);
			holder.name = (TextView) convertView.findViewById(R.id.game_title);
			holder.max = (TextView) convertView.findViewById(R.id.game_max_players);
			holder.free = (TextView) convertView.findViewById(R.id.game_free_slots);
			holder.password = (TextView) convertView.findViewById(R.id.game_password);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		final Match item = items.get(position);
		holder.name.setText(item.getName());
		holder.max.setText(item.getMax_players()+"");
		//TODO

		return convertView;
	}
}