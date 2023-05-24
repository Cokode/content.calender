package dev.collins.content.calender.model.student;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

@Entity
@Table
public class Student {
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )

    @GeneratedValue (
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )

    @NotBlank
    private String name;
    @Id
    private Long id;
    private Integer yearOfBirth;
    private String city;
    private Integer age;
    private LocalDate dateCreated;

    public Student(String name,
                   Integer id,
                   int yearOfBirth,
                   String city) {
        this.name = name;
        this.id = 1L;
        this.yearOfBirth = yearOfBirth;
        this.city = city;
        this.dateCreated = LocalDate.now();
    }

    public Student() {
        name = "Collins";
        id = 1L;
        yearOfBirth = 2002;
        city = "Agbor";
        dateCreated = LocalDate.now();
    }

    // getter methods
    public String getName() {
        return name;
    }

    public Long getId() {return id;}

    public int getYearOfBirth() {return yearOfBirth;}

    public String getCity() {
        return city;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public Integer getAge() {
        return 2023 - this.yearOfBirth;
    }
    // Setter Methods


    public void setName(String name) {
        if (name.length() >= 3) {
            this.name = name;
        }
    }

    public void setYearOfBirth(int yearOfBirth) {
        if (yearOfBirth <= 2023 &&
        2023 - yearOfBirth <= 110) {
            this.yearOfBirth = yearOfBirth;
        }
    }

    public void setCity(String nCity) {
        if (nCity.length() >= 3) {
            this.city = nCity;
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", dOb=" + yearOfBirth +
                ", city='" + city + '\'' +
                ", age=" + age +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
