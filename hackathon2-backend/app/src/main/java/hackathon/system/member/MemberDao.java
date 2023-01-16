package hackathon.system.member;

import java.sql.Date;
import java.util.Arrays;
import org.springframework.stereotype.Repository;
import hackathon.system.db.TmpDB;

@Repository
public class MemberDao {

  private static final int SIZE = 100;
  private Member[] members = new Member[SIZE];
  private int count;
  private int no;

  public static TmpDB tmp = new TmpDB();


  public MemberDao() {
    for (Member m : tmp.getTmpData()) {
      this.insert(m);
    }
  }

  public Member[] getOneDayData() {
    Member[] datas = new Member[this.count];
    for (int i = 0; i < this.count; i++) {
      Member m = this.members[i];
      Attendances a = new Attendances();
      a.setAttendances(this.members[i].getAttendance().getOneDayAttendances(
          new Date(System.currentTimeMillis() - 1000l * 3600 * 24).toString()));


      datas[i] = new Member(m.getNo(), m.getName(), m.getId(), m.getAge(),
          m.getGender(), m.getAddress(), m.getTel(), a);
      datas[i].setCreateDate(m.getCreateDate());
    }
    return datas;
  }

  public void insert(Member member) {
    if (member.getAttendance().getAttendances() == null) {
      System.out.println("널인데?");
    }
    member.setNo(++no);
    this.members[this.count++] = member;
  }

  public void update(Member member) {
    this.members[this.indexOf(member)] = member;
  }

  public void delete(Member member) {
    for (int i = this.indexOf(member) + 1; i < this.count; i++) {
      this.members[i - 1] = this.members[i];
    }
    this.members[--this.count] = null; // 레퍼런스 카운트를 줄인다.
  }

  public Member[] findAll() {
    return Arrays.copyOf(this.members, this.count);
  }

  public Member findByNo(int no) {
    for (int i = 0; i < this.count; i++) {
      if (this.members[i].getNo() == no) {
        return this.members[i];
      }
    }
    return null;
  }

  public Member findById(String id) {
    for (int i = 0; i < this.count; i++) {
      if (this.members[i].getId().equals(id)) {
        return this.members[i];
      }
    }
    return null;
  }


  private int indexOf(Member member) {
    for (int i = 0; i < this.count; i++) {
      if (this.members[i].getNo() == member.getNo()) {
        return i;
      }
    }
    return -1;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }
}
