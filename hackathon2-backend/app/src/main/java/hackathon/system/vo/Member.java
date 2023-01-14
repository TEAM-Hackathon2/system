package hackathon.system.vo;

import java.sql.Date;

public class Member {
  private int no;
  private String name;
  private String id;
  private String password;
  private Attendance[] monthAttendance;
  private Date createDate;
}