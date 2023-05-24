package dev.collins.content.calender.service;

import dev.collins.content.calender.model.student.Student;
import dev.collins.content.calender.repository.StudentRespository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRespository studentRespository;

    @Autowired
    public StudentService(StudentRespository studentRespository) {
        this.studentRespository = studentRespository;
    }

    public List<Student> getStudents() {
        return studentRespository.findAll();
    }

    @PostConstruct
    public void firstStudent() {
        Student std = new Student(
                "John",
                353,
                2005,
                "Lagos"
        );
        studentRespository.save(new Student());
        studentRespository.save(std);
    }

    public void addStudent (Student student) {
      for (Student stu : studentRespository.findAll()) {
          if (stu.getId().equals(student.getId())) {
              throw new IllegalStateException("ID already exist");
          }
      }
        studentRespository.save(student);
        System.out.println(studentRespository.findAll());
    }

    public Optional<Student> findStudent(Long id) {
        Student sts = null;
        for (Student stu: studentRespository.findAll())
            if (stu.getId().equals(id)) sts = stu;
        return Optional.ofNullable(sts);
    }

    public void deleteById(Long id) {
        boolean isExist = studentRespository.existsById(id);
        if (!isExist) throw new IllegalStateException("Student with ID "
        + id+ " does not exist");
        studentRespository.deleteById(id);
    }

    @Transactional
    public void update(Long id,
                       String name,
                       Integer yOb) {
        Student stud = studentRespository.findStudentById(id)
                .orElseThrow(()-> new IllegalStateException("Student with ID "+ id+" does not exist"
                ));

        if (name != null &&
        !Objects.equals(stud.getName(), name)) {
            stud.setName(name);
        }
        if (yOb != null &&
                !Objects.equals(stud.getYearOfBirth(), yOb)) {
            stud.setYearOfBirth(yOb);
        }
    }
}
