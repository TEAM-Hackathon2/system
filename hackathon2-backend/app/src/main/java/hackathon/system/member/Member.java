package hackathon.system.member;

public class Member {        //계정이랑 통합해야할수도
  private int no;
  private String name;
  private String id;
  private String password;
  private String createDate;
  private Attendances attendance;

  public Member() {
    //attendance = new Attendances();
  }

  public Member(String createDate) {
    //attendance = new Attendances(createDate);
    this.createDate = createDate;
  }

  public Member(String createDate, int no, String name, String id, String password) {
    //attendance = new Attendances(createDate);
    this.no = no;
    this.name = name;
    this.id = id;
    this.password = password;
  }

  public void setAttendance(Attendances attendance) {
    this.attendance = attendance;
  }

  public Attendances getAttendance() {
    return this.attendance;
  }


  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getCreateDate() {
    return createDate;
  }
  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }
}