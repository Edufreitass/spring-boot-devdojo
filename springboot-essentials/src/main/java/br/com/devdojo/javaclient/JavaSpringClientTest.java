package br.com.devdojo.javaclient;

import br.com.devdojo.model.Student;

public class JavaSpringClientTest {

    public static void main(String[] args) {
        Student studentPost = new Student();
        studentPost.setName("John Wick 2");
        studentPost.setEmail("john@pencil.com");
        studentPost.setId(25L);
        JavaClientDAO dao = new JavaClientDAO();
        System.out.println(dao.findById(111));
//        System.out.println(dao.listAll());
//        System.out.println(dao.save(studentPost));
//        dao.update(studentPost);
//        dao.delete(25L);

//        List<Student> students = dao.listAll();
//        System.out.println(students.size());
    }

}
