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
  private String[] ages = {
      "25","20","20","21","20",
      "10","5","3","5","10",
      "25","30","20","22","20",
      "15","30","5","4","20",
      "20","21","21","20","22"};
  private String[] genders = {
      "남","여","남","남","여",
      "여","여","남","여","남",
      "여","여","남","여","여",
      "남","남","여","남","남",
      "남","남","남","남","여"};
  private String[] addresses = {
      "서울시 강남구 비트캠프","서울시 강남구 비트캠프","서울시 강남구 비트캠프","서울시 강남구 비트캠프","서울시 강남구 비트캠프",
      "서울시 강남구 비트캠프","서울시 강남구 비트캠프","서울시 강남구 비트캠프","서울시 강남구 비트캠프","서울시 강남구 비트캠프",
      "서울시 강남구 비트캠프","서울시 강남구 비트캠프","서울시 강남구 비트캠프","서울시 강남구 비트캠프","서울시 강남구 비트캠프",
      "서울시 강남구 비트캠프","서울시 강남구 비트캠프","서울시 강남구 비트캠프","서울시 강남구 비트캠프","서울시 강남구 비트캠프",
      "서울시 강남구 비트캠프","서울시 강남구 비트캠프","서울시 강남구 비트캠프","서울시 강남구 비트캠프","서울시 강남구 비트캠프"};
  private String[] tels = {
      "010-1234-5678","010-1234-5678","010-1234-5678","010-1234-5678","010-1234-5678",
      "010-1234-5678","010-1234-5678","010-1234-5678","010-1234-5678","010-1234-5678",
      "010-1234-5678","010-1234-5678","010-1234-5678","010-1234-5678","010-1234-5678",
      "010-1234-5678","010-1234-5678","010-1234-5678","010-1234-5678","010-1234-5678",
      "010-1234-5678","010-1234-5678","010-1234-5678","010-1234-5678","010-1234-5678"};

  public TmpDB() {
    makeTmpMembers();
  }

  private Member[] makeTmpMembers() {

    Long defaultTime = System.currentTimeMillis() - 1000l * 3600 * 24 * 14;
    for (int i = 0; i < 25; i++) {
      Attendances attendaces = new Attendances();
      for (int j = 0; j < 15; j++) {

        Date tmp = new Date(defaultTime + (1000l * 3600 * 24 * j));
        switch ((int)(Math.random()*10)) {
          case 0:
            attendaces.checkAbsent("none",tmp);
            break;
          case 1:
            attendaces.checkAbsent("sick",tmp);
            break;
          default:
            attendaces.checkIn(8.0 + Math.round((Math.random()*3 * 10))/10.0, tmp);
            attendaces.checkOut(18.0 + Math.round((Math.random()*2 * 10))/10.0, tmp);
        }
      }
      this.tmpMembers[i] = new Member(i+1,names[i], "id"+ names[i],
          ages[i], genders[i],addresses[i],tels[i],attendaces);
    }
    return tmpMembers;
  }

  public Member[] getTmpData() {
    return this.tmpMembers;
  }
}
