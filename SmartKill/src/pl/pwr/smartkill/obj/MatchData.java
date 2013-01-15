package pl.pwr.smartkill.obj;

public class MatchData {
       private Number id;
       private Number matchId;
       private String status;

       public Number getId() {
               return this.id;
       }

       public void setId(Number id) {
               this.id = id;
       }

       public Number getMatchId() {
               return this.matchId;
       }

       public void setMatchId(Number matchId) {
               this.matchId = matchId;
       }

       public String getStatus() {
               return status;
       }

       public void setStatus(String status) {
               this.status = status;
       }
}