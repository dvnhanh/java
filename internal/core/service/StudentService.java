package internal.core.service;

import java.util.ArrayList;

import internal.core.domain.Student;
import internal.infracstructure.datastore.binary.StudentRepo;
import utils.NewError;

public class StudentService {
    StudentRepo stdRepo;
    
    public StudentService(StudentRepo stdRepo){
        this.stdRepo = stdRepo;
    }

    public Student getStudentByCode(Integer stuCode) {

        return stdRepo.getStudentByCode(stuCode);
    }

    public NewError getStudentsList(ArrayList<Student> arrStd) {

        return stdRepo.getStudentsList(arrStd);
    }

    public NewError addStudent(Student stu) {
        
        return stdRepo.addStudent(stu);
    }

    public NewError updateStudent(Student stu) {

        return stdRepo.updateStudent(stu);
    }

    public NewError deleteStudentByCode(Integer stuCode) {

        return stdRepo.deleteStudentByCode(stuCode);
    }
}
