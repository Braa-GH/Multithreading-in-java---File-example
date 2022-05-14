import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileOperations extends Thread{


    File file = new File("student.txt");
    private static ArrayList<Student> students = new ArrayList<>();

    static {
        students.add(new Student("Braa",19,"male","IT"));
        students.add(new Student("Omar",20,"male","Eng"));
        students.add(new Student("Sara",18,"female","IT"));
        students.add(new Student("Khaled",21,"male","IT"));
        students.add(new Student("Farah",20,"female","IT"));
    }

    public synchronized void read() throws IOException, InterruptedException {
        System.out.println("Reading From File Executed ….");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = "";
        if (reader.readLine() == null) {
            System.out.println("File is Empty , waiting until File is Full.");
            wait();
        }

        while ((line = reader.readLine()) != null){
            System.out.println(line);
        }
        System.out.println("Reading From File Successfully!");
    }

    public synchronized void write() throws IOException {
        System.out.println("Writing to File is Executed ….");
        FileWriter writer = new FileWriter(file);
        for (Student std: students){
            writer.write(std.toString() + "\n");
        }
        writer.close();
        System.out.println("Writing To File Successfully!");
        notify();
    }



}
