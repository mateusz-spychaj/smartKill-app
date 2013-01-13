
package pl.pwr.smartkill.obj;


public class Profile{
   	private boolean admin;
   	private String email;
   	private Number id;
   	private boolean is_active;
   	private Number matches_hunter;
   	private Number matches_prey;
   	private Number points_hunter;
   	private Number points_prey;
   	private String registered_at;
   	private String username;

 	public boolean getAdmin(){
		return this.admin;
	}
	public void setAdmin(boolean admin){
		this.admin = admin;
	}
 	public String getEmail(){
		return this.email;
	}
	public void setEmail(String email){
		this.email = email;
	}
 	public Number getId(){
		return this.id;
	}
	public void setId(Number id){
		this.id = id;
	}
 	public boolean getIs_active(){
		return this.is_active;
	}
	public void setIs_active(boolean is_active){
		this.is_active = is_active;
	}
 	public Number getMatches_hunter(){
		return this.matches_hunter;
	}
	public void setMatches_hunter(Number matches_hunter){
		this.matches_hunter = matches_hunter;
	}
 	public Number getMatches_prey(){
		return this.matches_prey;
	}
	public void setMatches_prey(Number matches_prey){
		this.matches_prey = matches_prey;
	}
 	public Number getPoints_hunter(){
		return this.points_hunter;
	}
	public void setPoints_hunter(Number points_hunter){
		this.points_hunter = points_hunter;
	}
 	public Number getPoints_prey(){
		return this.points_prey;
	}
	public void setPoints_prey(Number points_prey){
		this.points_prey = points_prey;
	}
 	public String getRegistered_at(){
		return this.registered_at;
	}
	public void setRegistered_at(String registered_at){
		this.registered_at = registered_at;
	}
 	public String getUsername(){
		return this.username;
	}
	public void setUsername(String username){
		this.username = username;
	}
}
