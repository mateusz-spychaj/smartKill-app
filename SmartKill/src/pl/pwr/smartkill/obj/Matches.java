
package pl.pwr.smartkill.obj;

import java.util.HashMap;
import java.util.List;

public class Matches{
   	private List<HashMap<String, String>> matches;
   	private String status;

 	public List<HashMap<String, String>> getMatches(){
		return this.matches;
	}
	public void setMatches(List<HashMap<String, String>> matches){
		this.matches = matches;
	}
 	public String getStatus(){
		return this.status;
	}
	public void setStatus(String status){
		this.status = status;
	}
}
