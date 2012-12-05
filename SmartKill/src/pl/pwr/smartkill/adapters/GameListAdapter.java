package pl.pwr.smartkill.adapters;

import java.util.ArrayList;

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

	private ArrayList<Match> items = new ArrayList<Match>();
	private static LayoutInflater inflater=null;

	class ViewHolder{
//		ImageView status;
//		ImageView avatar;
		TextView name;
//		CheckBox checkbox;
	}

	public GameListAdapter(Activity a, ArrayList<Match> itms) {
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
			holder.name = (TextView) convertView.findViewById(R.id.list_games_name);
//			holder.status = (ImageView) convertView.findViewById(R.id.list_who_status);
//			holder.avatar = (ImageView) convertView.findViewById(R.id.list_who_avatar);
//			holder.checkbox = (CheckBox) convertView.findViewById(R.id.list_who_selected);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		final Match item = items.get(position);
		holder.name.setText(item.getName());
		//TODO

		return convertView;
	}
}