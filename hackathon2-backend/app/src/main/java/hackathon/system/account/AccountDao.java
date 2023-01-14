package hackathon.system.account;

import org.springframework.stereotype.Repository;

@Repository
public class AccountDao {

  private static final int SIZE = 100;

  private Account admin = new Account("admin", "1111");
  private Account[] accounts = new Account[SIZE];
  //아이디가 key고 password가 벨류여도되지않나싶긴함
  private int count;

  public AccountDao() {
    this.count = 1;
    this.accounts[0] = new Account("fs","1"); //더미데이터
  }

  public boolean checkAdmin(String password) {
    return this.admin.getPassword().equals(password);
  }

  public String createAccount(String id, String password) {

    if (findById(id) == null) {
      this.accounts[this.count++] = new Account(id, password);
      return "가입성공";
    }
    return "가입실패";
  }

  public String changePassword(String id, String beforePassword, String newPassword) {
    Account account = this.findById(id);
    if(account.getPassword().equals(beforePassword)) {
      account.setPassword(newPassword);
      return "비밀번호 변경 성공";
    } else {
      return "비밀번호 변경 실패";
    }
  }

  public String deleteAccount(String id, String password) {
    Account account = this.findById(id);
    if(account.getPassword().equals(password)) {
      return "삭제 성공";
    } else {
      return "삭제 실패";
    }
  }

  public Account findById(String id) {
    for (int i = 0; i < count; i++) {
      if(this.accounts[i].getId().equals(id)) {
        return this.accounts[i];
      }
    }
    return null;
  }
}
