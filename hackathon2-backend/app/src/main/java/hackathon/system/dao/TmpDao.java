package hackathon.system.dao;

import org.springframework.stereotype.Repository;
import hackathon.system.vo.Attendance;

@Repository
public class TmpDao {
  private Attendance[] tmps = new Attendance[20];

  public Attendance[] getAll() {
    for (int i = 0; i < 20; i++) {
      this.tmps[i] = new Attendance();
      tmps[i].setInTime(i * 100);
    }


    return this.tmps;
  }
}
