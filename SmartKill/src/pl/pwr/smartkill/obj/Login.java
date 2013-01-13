
package pl.pwr.smartkill.obj;

import java.util.List;

public class Login{
   	private String id;
   	private String status;
   	private User user;

 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
 	public String getStatus(){
		return this.status;
	}
	public void setStatus(String status){
		this.status = status;
	}
 	public User getUser(){
		return this.user;
	}
	public void setUser(User user){
		this.user = user;
	}
}
