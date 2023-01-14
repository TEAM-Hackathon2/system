package hackathon.system.member;

public class Attendance {
  private double inTime;
  private double outTime;
  private boolean absent;
  private int absentType;
  private String absentReason;


  public double getInTime() {
    return inTime;
  }

  public void setInTime(double inTime) {
    this.inTime = inTime;
  }

  public double getOutTime() {
    return outTime;
  }

  public void setOutTime(double outTime) {
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
