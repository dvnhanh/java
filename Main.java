import internal.core.service.StudentService;
import internal.handler.Handler;
import internal.infracstructure.datastore.binary.StudentRepo;

public class Main {
  public static void main(String[] args) {
    StudentRepo stuRepo = new StudentRepo("./data_student.ser");
    StudentService stdSvc = new StudentService(stuRepo);
    Handler handler = new Handler(stdSvc);
    
    handler.Begin();
  }
}