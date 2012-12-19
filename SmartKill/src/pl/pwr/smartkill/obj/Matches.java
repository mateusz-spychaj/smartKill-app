
package pl.pwr.smartkill.obj;

import java.util.List;

public class Matches{
   	private List<Match> matches;
   	private String status;

 	public List<Match> getMatches(){
		return this.matches;
	}
	public void setMatches(List<Match> matches){
		this.matches = matches;
	}
 	public String getStatus(){
		return this.status;
	}
	public void setStatus(String status){
		this.status = status;
	}
}
