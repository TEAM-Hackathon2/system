package hackathon.system.account;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

  AccountDao accountDao;

  public AccountController(AccountDao accountDao) {
    this.accountDao = accountDao;
  }

  @PostMapping("/registAccount")
  public Object createAccount(@RequestBody Account ac) {
    Map<String, Object> contentMap = new HashMap<>();

    if (!ac.getId().equalsIgnoreCase("admin") &&
        this.accountDao.findById(ac.getId()) == null) {
      this.accountDao.createAccount(ac.getId(), ac.getPassword());
      contentMap.put("status", "success");
      contentMap.put("data", this.accountDao.getAllAccount());
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

    contentMap.put("status", "checkMessage");
    contentMap.put("message", this.accountDao.deleteAccount(ac.getId(), ac.getPassword()));
    return contentMap;
  }



}
