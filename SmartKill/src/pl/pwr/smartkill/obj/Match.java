
package pl.pwr.smartkill.obj;


public class Match{
   	private String created_at;
   	private String due_date;
   	private Number id;
   	private String lat;
   	private Number length;
   	private String lng;
   	private Number max_players;
   	private String name;
   	private String password;
   	private Number size;

 	public String getCreated_at(){
		return this.created_at;
	}
	public void setCreated_at(String created_at){
		this.created_at = created_at;
	}
 	public String getDue_date(){
		return this.due_date;
	}
	public void setDue_date(String due_date){
		this.due_date = due_date;
	}
 	public Number getId(){
		return this.id;
	}
	public void setId(Number id){
		this.id = id;
	}
 	public String getLat(){
		return this.lat;
	}
	public void setLat(String lat){
		this.lat = lat;
	}
 	public Number getLength(){
		return this.length;
	}
	public void setLength(Number length){
		this.length = length;
	}
 	public String getLng(){
		return this.lng;
	}
	public void setLng(String lng){
		this.lng = lng;
	}
 	public Number getMax_players(){
		return this.max_players;
	}
	public void setMax_players(Number max_players){
		this.max_players = max_players;
	}
 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
 	public String getPassword(){
		return this.password;
	}
	public void setPassword(String password){
		this.password = password;
	}
 	public Number getSize(){
		return this.size;
	}
	public void setSize(Number size){
		this.size = size;
	}
}
