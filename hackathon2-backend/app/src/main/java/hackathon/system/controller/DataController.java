package hackathon.system.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import hackathon.system.account.AccountDao;
import hackathon.system.db.TmpDB;
import hackathon.system.ip.IPChecker;
import hackathon.system.member.Member;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class DataController {
  AccountDao accountDao;
  @Autowired HttpServletRequest request;
  @Autowired IPChecker ipChecker;

  public DataController(AccountDao accountDao) {
    this.accountDao = accountDao;
  }

  @GetMapping("/admin")
  public Object getAll() {

    TmpDB t = new TmpDB();
    Member[] m = t.getTmpData();
    Member[] o = t.getOneDayData();

    Map<String, Object> contentMap = new HashMap<>();

    contentMap.put("status", "success");
    contentMap.put("data", m);
    contentMap.put("onedaydata", o);
    return contentMap;
  }

  @GetMapping("/indiv")
  public Object getIndiv() {

    Map<String, Object> contentMap = new HashMap<>();

    contentMap.put("status", "success");
    contentMap.put("data", this.ipChecker.takeOutIp(this.request.getRemoteAddr()));
    return contentMap;
  }
}
