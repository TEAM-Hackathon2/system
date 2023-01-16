package hackathon.system.account;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import hackathon.system.ip.IPChecker;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class AccountController {

  AccountDao accountDao;
  @Autowired HttpServletRequest request;
  @Autowired IPChecker ipChecker;

  public AccountController(AccountDao accountDao) {
    this.accountDao = accountDao;
  }

  @PostMapping("/login")
  public Object getDatas(@RequestBody Account a) {

    Map<String, Object> contentMap = new HashMap<>();

    if (a.getId().equalsIgnoreCase("admin") &&
        this.accountDao.checkAdmin(a.getPassword())) {
      contentMap.put("status", "admin");
      contentMap.put("data", "전체 사용자 데이터 반환"); //애는 비번틀렸다팝업안뜸
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
      contentMap.put("data", "개인 사용자 데이터 반환");
    }
    return contentMap;
  }


  @PostMapping("/registAccount")
  public Object createAccount(@RequestBody Account ac) {
    Map<String, Object> contentMap = new HashMap<>();

    if (!ac.getId().equalsIgnoreCase("admin") &&
        this.accountDao.findById(ac.getId()) == null) {
      this.accountDao.createAccount(ac.getId(), ac.getPassword());
      contentMap.put("status", "success");
      contentMap.put("message", "환영합니다!");
      return contentMap;
    }

    contentMap.put("status", "failure");
    contentMap.put("message", "중복된 계정입니다.");
    return contentMap;

  }

  @PutMapping("/pwdchage")
  public Object changePassword(@RequestBody Account ac, String newPassword) {
    Map<String, Object> contentMap = new HashMap<>();

    contentMap.put("status", "checkMessage");
    contentMap.put("message", this.accountDao.changePassword(ac.getId(), ac.getPassword(), newPassword));
    return contentMap;
  }


  @DeleteMapping("/deleteAccount")
  public Object deleteAccount(@RequestBody Account ac) {
    Map<String, Object> contentMap = new HashMap<>();

    if (this.accountDao.deleteAccount(ac.getId(), ac.getPassword())) {
      contentMap.put("status", "success");
    } else {
      contentMap.put("status", "failure");
    }

    //contentMap.put("message", );
    return contentMap;
  }



}
