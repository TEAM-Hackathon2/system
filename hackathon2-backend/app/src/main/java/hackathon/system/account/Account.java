package hackathon.system.account;

import java.sql.Date;

public class Account {
  private String id;
  private String password;
  private String createDate;

  public Account(String id, String password) {
    this.id = id;
    this.password = password;
    this.createDate = new Date(System.currentTimeMillis()).toString();
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
