package hackathon.system.ip;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class IPChecker {
  private Map<String, String> ipRecorder = new HashMap<>();

  public boolean recordIp(String ip, String accountId) {
    if(this.ipRecorder.containsKey(ip)) {
      return false;
    }
    this.ipRecorder.put(ip,accountId);
    return true;
  }

  public String takeOutIp(String ip) {
    String result = this.ipRecorder.get(ip);
    this.ipRecorder.remove(ip);
    return result;
  }
}
