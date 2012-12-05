package pl.pwr.smartkill.obj;

import com.google.android.maps.GeoPoint;

public class Player {

	private String name;
	private int id;
	private GeoPoint location;
	private boolean alive;
	
	public Player(int id, String name) {
		this.setId(id);
		this.setName(name);
		setAlive(true);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public GeoPoint getLocation() {
		return location;
	}

	public void setLocation(GeoPoint location) {
		this.location = location;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
}
