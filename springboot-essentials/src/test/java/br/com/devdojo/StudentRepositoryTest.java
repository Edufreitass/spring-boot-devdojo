package br.com.devdojo;

import br.com.devdojo.model.Student;
import br.com.devdojo.repository.StudentRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Rule
    private ExpectedException thrown = ExpectedException.none();

    @Test
    public void whenCreateThenPersistData() {
        Student student = new Student("Eduardo", "eduardo@email.com");
        this.studentRepository.save(student);

        assertThat(student.getId()).isNotNull();
        assertThat(student.getName()).isEqualTo("Eduardo");
        assertThat(student.getEmail()).isEqualTo("eduardo@email.com");
    }

    @Test
    public void whenDeleteThenRemoveData() {
        Student student = new Student("Eduardo", "eduardo@email.com");
        studentRepository.save(student);
        studentRepository.delete(student);
        assertThat(studentRepository.findById(student.getId())).isEmpty();
    }

    @Test
    public void whenUpdateThenChangeAndPersistData() {
        Student student = new Student("Eduardo", "eduardo@email.com");
        this.studentRepository.save(student);
        student.setName("Eduardo222");
        student.setEmail("eduardo222@email.com");
        this.studentRepository.save(student);
        student = this.studentRepository.findById(student.getId()).get();
        assertThat(student.getName()).isEqualTo("Eduardo222");
        assertThat(student.getEmail()).isEqualTo("eduardo222@email.com");
    }

    @Test
    public void whenFindByNameIgnoreCaseContainingThenIgnoreCase() {
        Student student = new Student("Eduardo", "eduardo@email.com");
        Student student2 = new Student("eduardo", "eduardo222@email.com");
        this.studentRepository.save(student);
        this.studentRepository.save(student2);
        List<Student> studentList = studentRepository.findByNameIgnoreCaseContaining("eduardo");
        assertThat(studentList.size()).isEqualTo(2);
    }

    @Test
    public void whenNotEmptyNameThenNoConstraintViolations() {
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("O campo nome do estudante é obrigatório");
        this.studentRepository.save(new Student());
    }

    @Test
    public void whenNotEmptyEmailThenNoConstraintViolations() {
        thrown.expect(ConstraintViolationException.class);
        Student student = new Student();
        student.setName("Eduardo");
        this.studentRepository.save(student);
    }

    @Test
    public void whenNotEmptyEmailIsNotValidThenNoConstraintViolations() {
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("Digite um email válido");
        Student student = new Student();
        student.setName("Eduardo");
        student.setEmail("eduardoemail.com");
        this.studentRepository.save(student);
    }

}
