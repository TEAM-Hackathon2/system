package hackathon.system.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import hackathon.system.account.Account;
import hackathon.system.account.AccountDao;
import hackathon.system.dao.TmpDao;
import hackathon.system.ip.IPChecker;
import hackathon.system.vo.Attendance;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
@RestController
public class TmpController {

  TmpDao tmpDao;
  AccountDao accountDao;
  @Autowired HttpServletRequest request;
  @Autowired IPChecker ipChecker;

  public TmpController(TmpDao tmpDao, AccountDao accountDao) {
    this.tmpDao = tmpDao;
    this.accountDao = accountDao;
  }


  @PostMapping("/login")
  public Object getDatas(String id, String password) {


    Map<String, Object> contentMap = new HashMap<>();

    if (id.equalsIgnoreCase("admin") &&
        this.accountDao.checkAdmin(password)) {
      Attendance[] tmps = tmpDao.getAll();
      contentMap.put("status", "admin");
      contentMap.put("data", tmps);
    } else {
      Account requestAccount = this.accountDao.findById(id);
      if (requestAccount == null) {
        contentMap.put("status", "failure");
        contentMap.put("message", "아이디를 찾을 수 없습니다.");
        return contentMap;
      }
      if (!requestAccount.getPassword().equals(password)) {
        contentMap.put("status", "failure");
        contentMap.put("message", "비밀번호가 잘못되었습니다.");
        return contentMap;
      }
      this.ipChecker.recordIp(this.request.getRemoteAddr(), requestAccount.getId());

      contentMap.put("status", "success");
    }
    return contentMap;
  }

  @GetMapping("/dashboard")
  public Object addBoard() {

    Map<String, Object> contentMap = new HashMap<>();

    contentMap.put("status", "success");
    contentMap.put("data", this.ipChecker.takeOutIp(this.request.getRemoteAddr()));
    return contentMap;
  }


}
