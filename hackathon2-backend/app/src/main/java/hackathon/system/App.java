package hackathon.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@SpringBootApplication
@RestController
public class App {
  public static void main(String[] args) {
    SpringApplication.run(App.class, args);


    //    TmpDB t = new TmpDB();
    //
    //    Member[] m = t.makeTmpMembers();
    //
    //    for (Member a : m) {
    //      for (int i = 0; i < 13; i++) {
    //        Attendance tmp = a.getAttendance().getAttendances()
    //            .get(a.getAttendance().getAttendances().keySet().toArray()[i]);
    //        System.out.println(a.getAttendance().getAttendances().keySet().toArray()[i].toString()
    //            + a.getName() + "님"
    //            + tmp.getInTime() + "시 입실"
    //            + tmp.getOutTime() + "시 퇴실");
    //      }
    //    }
  }
}
