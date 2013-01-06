
package pl.pwr.smartkill.obj;

import java.io.Serializable;
import java.util.ArrayList;


public class Match implements Serializable{
	private static final long serialVersionUID = 1L;
	private Number id;
	private String status;
	private String name;
	private String password;
	private String lat;
	private String lng;
	private Number size;
	private Number length;
	private String due_date;
	private Number max_players;
	private String created_at;
	private Number created_by;
	private Number density;
	private boolean pkg_time;
	private boolean pkg_shield;
	private boolean pkg_snipe;
	private boolean pkg_switch;

 	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCreated_at(){
		return this.created_at;
	}
	public void setCreated_at(String created_at){
		this.created_at = created_at;
	}
 	public Number getCreated_by(){
		return this.created_by;
	}
	public void setCreated_by(Number created_by){
		this.created_by = created_by;
	}
 	public Number getDensity(){
		return this.density;
	}
	public void setDensity(Number density){
		this.density = density;
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
 	public boolean getPkg_shield(){
		return this.pkg_shield;
	}
	public void setPkg_shield(boolean pkg_shield){
		this.pkg_shield = pkg_shield;
	}
 	public boolean getPkg_snipe(){
		return this.pkg_snipe;
	}
	public void setPkg_snipe(boolean pkg_snipe){
		this.pkg_snipe = pkg_snipe;
	}
 	public boolean getPkg_switch(){
		return this.pkg_switch;
	}
	public void setPkg_switch(boolean pkg_switch){
		this.pkg_switch = pkg_switch;
	}
 	public boolean getPkg_time(){
		return this.pkg_time;
	}
	public void setPkg_time(boolean pkg_time){
		this.pkg_time = pkg_time;
	}
 	public Number getSize(){
		return this.size;
	}
	public void setSize(Number size){
		this.size = size;
	}
 	public String getStatus(){
		return this.status;
	}
	public void setStatus(String status){
		this.status = status;
	}
}
