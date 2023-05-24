package internal.handler;

import internal.core.service.StudentService;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import internal.core.domain.Student;
import utils.NewError;

public class Handler {
    StudentService stdSvc;
    
    public Handler(StudentService stdSvc){
        this.stdSvc = stdSvc;
    }

    public void Begin() {
        int feature = 0;
        String stduAddress;
        String stduName;
        Float stduScore;
        String stduNote;
        String stduImagePath;
        String filePath;
        Scanner in = new Scanner(System.in).useDelimiter("\n");

        try{
            System.out.println("------- ** Manage student ** -----------");
            System.out.println("Features");
            System.out.println("1: Add new student");
            System.out.println("2: Get list students");
            System.out.println("3: Update Student");
            System.out.println("4: Delete Student");
            System.out.println("5: Import student list to file txt");
            System.out.println("6: Export student list to file txt");
        
            do {
                System.out.print("Please enter feature (number) or Press 0 to exists: ");
                feature = in.nextInt();

                switch (feature) {
                    case 1:
                        System.out.println("1: Add new student");
                        
                        System.out.print("Name: ");
                        stduName = in.next();

                        System.out.print("Score: ");
                        stduScore = in.nextFloat();

                        System.out.print("Address: ");
                        stduAddress = in.next();

                        System.out.print("Note: ");
                        stduNote = in.next();

                        System.out.print("Image path: ");
                        stduImagePath = in.next();
                    

                        Student stdu = new Student();
                        stdu.setterStuCode(generatorStuCode());
                        stdu.setterStuAddress(stduAddress);
                        stdu.setterStuName(stduName);
                        stdu.setterStuImagPath(stduImagePath);
                        stdu.setterStuScore(stduScore);
                        stdu.setterStuNote(stduNote);

                        addStudent(stdu);
                        System.out.println("Success");
                        break;
                    case 2:
                        System.out.println("2: Get list students");
                        ArrayList<Student> arrayList = new ArrayList<Student>(); 
                        // TODO: Write code to call the function and show array list to console
                        // Reference:
                        // 1. You have avariable arrayList in the line number 74
                        // 2. Determine the function that has parameters and pass them to the funtion getStudentsList(Arraylist<Student>)
                        System.out.println("Success");
                        break;
                    case 3:
                        // TODO: Write code to call the function updateStudent (check below)
                        // 1. Declare new object student
                        // 2. In the object student have funtion setter, you need to use the function it to set attribute for object
                        // 3. Determine the function, parameter of the function and call it.
                        System.out.println("Success");
                        break;
                    case 4:
                        System.out.println("4: Delete Student");

                        // TODO: Write code to call the function deleteStudent (check below)
                        // 1. In the object student have funtion setter, you need to use the function it to set attribute for object
                        // 2. Determine the function, parameter of the function and call it.
                        System.out.println("Success");
                        break;
                    case 5:
                        System.out.println("5: Import student list to file txt");
                        System.out.print("Enter file path: ");
                        filePath = in.next();
                        importStudents(filePath);

                        System.out.println("Success");
                        break;
                    case 6:
                        System.out.println("6: Export student list to file txt");
                        System.out.print("Enter file path: ");
                        filePath = in.next();
                        ExportStudents("./export_data.txt");

                        System.out.println("Success");
                        break;
                    default:
                        System.out.println("6: Export student list to file txt");
                        ExportStudents("./export_data.txt");
                        
                        System.out.println("Existed!");
                        break;
                }
            }
            while (feature > 0);
        } finally {
            in.close();
        }
    };

    public Student getStudentByCode(Integer stuCode) {

        return stdSvc.getStudentByCode(stuCode);
    }

    public NewError getStudentsList(ArrayList<Student> arrStd) {

        return stdSvc.getStudentsList(arrStd);
    }

    public NewError addStudent(Student stu) {

        return stdSvc.addStudent(stu);
    }

    public NewError updateStudent(Student stu) {

        return stdSvc.updateStudent(stu);
    }

    public NewError deleteStudentByCode(Integer stuCode) {
        
        return stdSvc.deleteStudentByCode(stuCode);
    }            

    public NewError importStudents(String filePath) {
        BufferedReader reader;

		try {
			reader = new BufferedReader(new FileReader(filePath));
			boolean temp = true;

			while (temp) {
				String line = reader.readLine();
                if (line == null) {
                    temp = false;
                    break;
                }

                String[] arrOfStr = line.split(",",6);
                Student std = new Student();
                std.setterStuCode(Integer.parseInt(arrOfStr[0]));
                std.setterStuName(arrOfStr[1]);
                std.setterStuScore(Float.parseFloat(arrOfStr[2]));
                std.setterStuAddress(arrOfStr[3]);
                std.setterStuNote(arrOfStr[4]);
                std.setterStuImagPath(arrOfStr[5]);

                this.stdSvc.addStudent(std);
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
    }

    public NewError ExportStudents(String filePath) {
        BufferedWriter writer;

        ArrayList<Student> arrStd = new ArrayList<Student>();
        this.stdSvc.getStudentsList(arrStd);
		try {
			writer = new BufferedWriter(new FileWriter(filePath));
            System.out.println(arrStd.size());
            for (Student std : arrStd) {
                String line = std.getterStuCode() + "," + 
                    std.getterStuName() + "," + 
                    std.getterStuScore() + "," + 
                    std.getterStuAddress() + "," +
                    std.getterStuNote() + "," + 
                    std.getterStuImagByte();

                writer.write(line);
            }
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
    }

    private int generatorStuCode(){
        int min = 1111111;
        int max = 9999999;
    
        return(int)Math.floor(Math.random() * (max - min + 1) + min);
    }

    private void showImageToConsole(String imagePath) {
        try{
            BufferedImage image = ImageIO.read(new File(imagePath));
   
            image.getGraphics().drawLine(1, 1, image.getWidth()-1, image.getHeight()-1);
            image.getGraphics().drawLine(1, image.getHeight()-1, image.getWidth()-1, 1);
   
            ImageIO.write(image, "png", new File(imagePath));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}