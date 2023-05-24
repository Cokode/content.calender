package dev.collins.content.calender.repository;

import dev.collins.content.calender.model.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRespository extends JpaRepository<Student, Long> {
    Optional<Student> findStudentById(Long id);
    Optional<Student> findStudentByName(String name);
}
