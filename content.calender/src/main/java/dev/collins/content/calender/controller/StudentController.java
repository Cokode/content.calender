package dev.collins.content.calender.controller;

import dev.collins.content.calender.model.student.Student;
import dev.collins.content.calender.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/api/student")
public class StudentController {
    private StudentService studentRepo;

    public StudentController(StudentService studentRepo) {
        this.studentRepo = studentRepo;
    }

    @GetMapping
    public List<Student> findAll () {
        return studentRepo.getStudents();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addSudent (@Valid @RequestBody Student student) {
        if (student.getId() == null)
            throw new IllegalStateException("ID can't be empty");
        studentRepo.addStudent(student);
    }

    @GetMapping("/{id}")
    public Student findById(@PathVariable Long id) {
        return studentRepo.findStudent(id).orElseThrow(()-> new
                ResponseStatusException(
                HttpStatus.NOT_FOUND, "Content Not Found!"
        ));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id) {
        studentRepo.deleteById(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public void updateStudent(@PathVariable Long id,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) Integer yearOfBirth) {
        studentRepo.update(id, name, yearOfBirth);
    }

    @GetMapping("/{home}")
    public String home() {
        return "Hello JWT";
    }
}
