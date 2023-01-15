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

  public boolean checkIn(double time, Date... dates) {
    Attendance at = new Attendance();
    String date;

    at.setInTime(time);
    if (dates.length == 0) {
      date = new Date(System.currentTimeMillis()).toString();
    } else {
      date = dates[0].toString();
    }

    if (Attendances.containsKey(date)
        && Attendances.get(date).getInTime() != 0) {
      return false;
    }

    Attendances.put(date, at);
    return true;
  }

  public boolean checkOut(double time, Date... dates) {
    String date;
    if (dates.length == 0) {
      date = new Date(System.currentTimeMillis()).toString();
    } else {
      date = dates[0].toString();
    }
    if (this.Attendances.get(date).getOutTime() != 0) {
      return false;
    }

    this.Attendances.get(date).setOutTime(time);
    return true;
  }

  public void checkAbsent(String why, Date... dates) {
    Attendance at = new Attendance();
    String date;

    at.setAbsent(true);

    if (why.equals("none")) {
      at.setAbsentType(0);
      at.setAbsentReason(why);
    } else {
      at.setAbsentType(1);
      at.setAbsentReason(why);
    }


    if (dates.length == 0) {
      date = new Date(System.currentTimeMillis()).toString();
    } else {
      date = dates[0].toString();
    }
    this.Attendances.put(date, at);
  }

  public Map<String, Attendance> getAttendances() {
    return Attendances;
  }

  public void setAttendances(Map<String, Attendance> attendances) {
    this.Attendances = attendances;
  }

  public Map<String, Attendance> getOneDayAttendances(String date) {
    Map<String, Attendance> tmp = new HashMap<>();
    tmp.put(date,this.Attendances.get(date));
    return tmp;
  }
}
