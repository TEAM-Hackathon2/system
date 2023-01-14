package hackathon.system.vo;

public class Attendance {
  private int inTime;
  private int outTime;
  private boolean absent;
  private int absentType;
  private String absentReason;


  public int getInTime() {
    return inTime;
  }

  public void setInTime(int inTime) {
    this.inTime = inTime;
  }

  public int getOutTime() {
    return outTime;
  }

  public void setOutTime(int outTime) {
    this.outTime = outTime;
  }

  public boolean isAbsent() {
    return absent;
  }

  public void setAbsent(boolean absent) {
    this.absent = absent;
  }

  public int getAbsentType() {
    return absentType;
  }

  public void setAbsentType(int absentType) {
    this.absentType = absentType;
  }

  public String getAbsentReason() {
    return absentReason;
  }

  public void setAbsentReason(String absentReason) {
    this.absentReason = absentReason;
  }
}
