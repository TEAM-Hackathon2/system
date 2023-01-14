package hackathon.system.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import hackathon.system.account.Account;
import hackathon.system.account.AccountDao;
import hackathon.system.db.TmpDB;
import hackathon.system.ip.IPChecker;
import hackathon.system.member.Member;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class TmpController {

  AccountDao accountDao;
  @Autowired HttpServletRequest request;
  @Autowired IPChecker ipChecker;

  public TmpController(AccountDao accountDao) {
    this.accountDao = accountDao;
  }

  @PostMapping("/login2")
  public Object getDatas(@RequestBody Account a) {

    Map<String, Object> contentMap = new HashMap<>();

    if (a.getId().equalsIgnoreCase("admin") &&
        this.accountDao.checkAdmin(a.getPassword())) {
      contentMap.put("status", "admin");
      contentMap.put("data", "전체 사용자 데이터 반환");
      return contentMap;
    } else {
      Account requestAccount = this.accountDao.findById(a.getId());
      if (requestAccount == null) {
        contentMap.put("status", "failure");
        contentMap.put("message", "아이디를 찾을 수 없습니다.");
        return contentMap;
      }
      if (!requestAccount.getPassword().equals(a.getPassword())) {
        contentMap.put("status", "failure");
        contentMap.put("message", "비밀번호가 잘못되었습니다.");
        return contentMap;
      }
      this.ipChecker.recordIp(this.request.getRemoteAddr(), requestAccount.getId());
      contentMap.put("status", "success");
    }
    return contentMap;
  }

  @GetMapping("/dashboard2")
  public Object addBoard() {

    Map<String, Object> contentMap = new HashMap<>();

    contentMap.put("status", "success");
    contentMap.put("data", this.ipChecker.takeOutIp(this.request.getRemoteAddr()));
    return contentMap;
  }


  @GetMapping("/tmpboard")
  public Object getBoard() {
    TmpDB t = new TmpDB();
    Member[] m = t.makeTmpMembers();

    Map<String, Object> contentMap = new HashMap<>();

    contentMap.put("status", "success");
    contentMap.put("data", m);
    return contentMap;
  }


}
