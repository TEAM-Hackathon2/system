package hackathon.system.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import hackathon.system.ip.IPChecker;
import hackathon.system.member.Member;
import hackathon.system.member.MemberDao;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class DataController {
  MemberDao memberDao;
  @Autowired HttpServletRequest request;
  @Autowired IPChecker ipChecker;

  public DataController(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @GetMapping("/admin")
  public Object getAll() {

    Map<String, Object> contentMap = new HashMap<>();


    contentMap.put("status", "success");
    contentMap.put("plz", memberDao.findAll());
    contentMap.put("onedaydata", memberDao.getOneDayData());
    return contentMap;
  }

  @GetMapping("/indiv")
  public Object getIndiv() {
    Map<String, Object> contentMap = new HashMap<>();

    Member m = memberDao.findById(this.ipChecker.takeOutIp(this.request.getRemoteAddr()));
    contentMap.put("status", "success");
    contentMap.put("data", m);
    return contentMap;
  }
}
