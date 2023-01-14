package hackathon.system.member;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class Attendances {
  private Map<String, Attendance> Attendances = new HashMap<>();
  private String startDate;


  public Attendances() {
    this.startDate = new Date(System.currentTimeMillis()).toString();
  }

  public Attendances(String startDate) {
    this.startDate = startDate;
  }

  public void checkIn(double time, Date... dates) {
    Attendance at = new Attendance();
    String date;

    at.setInTime(time);
    if (dates.length == 0) {
      date = new Date(System.currentTimeMillis()).toString();
    } else {
      date = dates[0].toString();
    }
    Attendances.put(date, at);
  }

  public void checkOut(double time, Date... dates) {
    String date;
    if (dates.length == 0) {
      date = new Date(System.currentTimeMillis()).toString();
    } else {
      date = dates[0].toString();
    }

    if (!this.Attendances.containsKey(date)) {
      //애매해
    }

    this.Attendances.get(date).setOutTime(time);
  }

  public Map<String, Attendance> getAttendances() {
    return Attendances;
  }

  public void setAttendances(Map<String, Attendance> attendances) {
    Attendances = attendances;
  }




}
