package br.com.devdojo.repository;

import br.com.devdojo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByNameIgnoreCaseContaining(String name);

}
