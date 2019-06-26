package shl;

import org.springframework.data.annotation.Id;

public class Games {

    @Id
    public String id;

    String date;
    String firstParty;
    String secondParty;
    String startTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFirstParty() {
        return firstParty;
    }

    public void setFirstParty(String firstParty) {
        this.firstParty = firstParty;
    }

    public String getSecondParty() {
        return secondParty;
    }

    public void setSecondParty(String secondParty) {
        this.secondParty = secondParty;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "Games{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", firstParty='" + firstParty + '\'' +
                ", secondParty='" + secondParty + '\'' +
                ", startTime='" + startTime + '\'' +
                '}';
    }
}
