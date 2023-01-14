package hackathon.system.member;

import java.util.Calendar;

//월별 관리가 가능할까 싶어 만든 클래스지만 아직 쓰이지 않음
public class MonthAttendance {
  private Attendance[] mounthData;
  private int month;
  private int startDay;

  public MonthAttendance(int year, int month, int day) {
    Calendar cal = Calendar.getInstance();
    cal.set(year, month, day);
    mounthData = new Attendance[cal.getActualMaximum(Calendar.DAY_OF_MONTH)];

    this.month = month + 1; //... +1을하던 위에서 -1을 하던
    this.startDay = day;
  }

  public boolean checkIntime() {


    return false;
  }

  public boolean checkOutime() {

    return false;
  }
}
