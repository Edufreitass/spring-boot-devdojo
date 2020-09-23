package br.com.devdojo.javaclient;

import br.com.devdojo.model.Student;

public class JavaSpringClientTest {

    public static void main(String[] args) {
        Student studentPost = new Student();
        studentPost.setName("John Wick");
        studentPost.setEmail("john@pencil.com");
        JavaClientDAO dao = new JavaClientDAO();
//        List<Student> students = dao.listAll();
//        System.out.println(students.size());
        System.out.println(dao.findById(1));
        System.out.println(dao.listAll());
        System.out.println(dao.save(studentPost));
    }

}
