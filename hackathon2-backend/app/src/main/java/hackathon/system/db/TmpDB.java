package hackathon.system.db;

import java.sql.Date;
import hackathon.system.member.Attendances;
import hackathon.system.member.Member;

public class TmpDB {
  private Member[] tmpMembers = new Member[25];
  private String[] names = {
      "윤종광","윤솔","조준호","조승현","신지윤",
      "김현우","전태산","조희성","전서린","이건형",
      "박경한","이예찬","김종현","오병현","차민주",
      "이명준","김다예레","심우준","이준영","한대호",
      "서영훈","서길원","양창덕","안진수","이성민"};

  public Member[] makeTmpMembers() {
    Attendances attendaces = new Attendances();
    Long defaultTime = System.currentTimeMillis() - 1000l * 3600 * 24 * 13;

    for (int i = 0; i < 13; i++) {
      Date tmp = new Date(defaultTime + (1000l * 3600 * 24 * i));
      switch ((int)(Math.random()*8)) {
        case 0:
          attendaces.checkAbsent("none",tmp);
          break;
        case 1:
          attendaces.checkAbsent("sick",tmp);
          break;
        default:
          attendaces.checkIn(8.0 + Math.round((Math.random()*4 * 10))/10.0, tmp);
          attendaces.checkOut(18.0 + Math.round((Math.random()*2 * 10))/10.0, tmp);
      }
    }

    for (int i = 0; i < 25; i++) {
      this.tmpMembers[i] = new Member();
      this.tmpMembers[i].setAttendance(attendaces);
      this.tmpMembers[i].setNo(i + 1);
      this.tmpMembers[i].setId("id" + i);
      this.tmpMembers[i].setName(names[i]);
    }

    return tmpMembers;
  }
}
