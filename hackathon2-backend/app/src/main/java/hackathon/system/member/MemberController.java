package hackathon.system.member;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
@RestController
public class MemberController {

  MemberDao memberDao;

  public MemberController(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @PostMapping("/addMember") //추가 삭제 이런거해야함
  public Object addMemer(@RequestBody Member m) {

    Map<String, Object> contentMap = new HashMap<>();

    memberDao.insert(m);

    contentMap.put("status", "success");
    return contentMap;
  }

  @PutMapping("/instage")
  public Object inStage(String id, double time) {

    Map<String, Object> contentMap = new HashMap<>();
    if(memberDao.findById(id).getAttendance().checkIn(time)) {
      contentMap.put("status", "success");
    } else {
      contentMap.put("status", "failure");
    }
    return contentMap;
  }

  @PutMapping("/outstage")
  public Object outStage(String id, double time) {

    Map<String, Object> contentMap = new HashMap<>();
    if(memberDao.findById(id).getAttendance().checkOut(time)) {
      contentMap.put("status", "success");
    } else {
      contentMap.put("status", "failure");
    }
    return contentMap;
  }

  @DeleteMapping("/delmember")
  public Object delMember(String id) {

    Map<String, Object> contentMap = new HashMap<>();

    this.memberDao.delete(memberDao.findById(id));
    contentMap.put("status", "success");
    return contentMap;
  }

}
