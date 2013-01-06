
package pl.pwr.smartkill.obj;

import java.util.List;

public class Positions{
   	private List<Position> positions;
   	private String status;

 	public List<Position> getPositions(){
		return this.positions;
	}
	public void setPositions(List<Position> positions){
		this.positions = positions;
	}
 	public String getStatus(){
		return this.status;
	}
	public void setStatus(String status){
		this.status = status;
	}
}
