package internal.infracstructure.datastore.binary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.nio.file.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import internal.core.domain.Student;
import utils.NewError;

public class StudentRepo {
    private String binaryPath;

    public StudentRepo(String binaryPath) {
        this.binaryPath = binaryPath;
        connect();
    }

    private void connect(){
        Path path = Paths.get(binaryPath);
        System.out.println("Connecting...");
        try {
            if (!Files.exists(path)) {
                System.out.printf("Creating new file with file path: %s", this.binaryPath);
                
                File binaryFile = new File(binaryPath);
                binaryFile.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Connected");
    }

    public Student getStudentByCode(int stuCode) {
        ArrayList<Student> arrStd = new ArrayList<Student>();
        getStudentsList(arrStd);

        Student std = new Student();

        for (Student stdL : arrStd) {
            if (std.getterStuCode() == stuCode) {
                std.setterStuCode(stdL.getterStuCode());
                std.setterStuName(stdL.getterStuName());
                std.setterStuScore(stdL.getterStuScore());
                std.setterStuAddress(stdL.getterStuAddress());
                std.setterStuImagPath(stdL.getterStuImagByte());
                
                return std;
            }
        }
        
        
        return null;
    }

    public NewError getStudentsList(ArrayList<Student> arrStd) {
        try {
            FileInputStream fis = new FileInputStream(this.binaryPath);
            while (fis.available() != 0) {
                ObjectInputStream ois = new ObjectInputStream(fis);
                arrStd.add((Student) ois.readObject());
                System.out.println(58);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;

    }

    public NewError addStudent(Student stu) {
        writeStudentToFile(stu);
        return null;
    }

    public NewError updateStudent(Student stu) {
        ArrayList<Student> arrStd = new ArrayList<Student>();
        getStudentsList(arrStd);

        for (Student stdL : arrStd) {
            if (stdL.getterStuCode() == stu.getterStuCode()) {
                stdL.setterStuCode(stdL.getterStuCode());
                stdL.setterStuName(stdL.getterStuName());
                stdL.setterStuScore(stdL.getterStuScore());
                stdL.setterStuAddress(stdL.getterStuAddress());
                stdL.setterStuImagPath(stdL.getterStuImagByte());
                
                return null;
            }
        }

        return null;
    }

    public NewError deleteStudentByCode(Integer stuCode) {
        ArrayList<Student> arrStd = new ArrayList<Student>();
        getStudentsList(arrStd);

        for (Student std : arrStd) {
            if (std.getterStuCode()== stuCode) {
                arrStd.remove(std);
                break;
            }
        }

        for (Student std : arrStd) {
            writeStudentToFile(std);
        }

        return null;
    }

    private void writeStudentToFile(Student stdu) {
        try {
            FileOutputStream fileOut = new FileOutputStream(this.binaryPath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            
            objectOut.writeObject(stdu);
            objectOut.flush();
            fileOut.close();
            objectOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
