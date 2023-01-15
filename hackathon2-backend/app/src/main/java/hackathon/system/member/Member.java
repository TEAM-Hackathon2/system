package hackathon.system.member;

import java.sql.Date;

public class Member {        //계정이랑 통합해야할수도
  private Attendances attendance;
  private int no;
  private String name;
  private String id;
  private String createDate;
  private String age;
  private String gender;
  private String address;
  private String tel;

  public Member() {
    this.createDate = new Date(System.currentTimeMillis()).toString();
    this.attendance = new Attendances();
  }

  public Member(String createDate) {
    //attendance = new Attendances(createDate);
    this.createDate = createDate;
  }

  public Member(String createDate, int no, String name, String id) {
    //attendance = new Attendances(createDate);
    this.no = no;
    this.name = name;
    this.id = id;
  }

  public Member(int no, String name, String id, String age, String gender, String address, String tel) {
    this.no = no;
    this.name = name;
    this.id = id;
    this.age = age;
    this.gender = gender;
    this.address = address;
    this.tel = tel;

    this.createDate = new Date(System.currentTimeMillis()).toString();
    this.attendance = new Attendances();
  }

  public Member(int no, String name, String id, String age,
      String gender, String address, String tel, Attendances at) {
    this.no = no;
    this.name = name;
    this.id = id;
    this.age = age;
    this.gender = gender;
    this.address = address;
    this.tel = tel;
    this.createDate = new Date(System.currentTimeMillis()).toString();
    this.attendance = at;
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
  public String getCreateDate() {
    return createDate;
  }
  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }  public String getAge() {
    return age;
  }
  public void setAge(String age) {
    this.age = age;
  }
  public String getGender() {
    return gender;
  }
  public void setGender(String gender) {
    this.gender = gender;
  }
  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
}