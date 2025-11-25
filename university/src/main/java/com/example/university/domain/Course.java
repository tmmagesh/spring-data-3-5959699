package com.example.university.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * JPA Entity for a Course offered at the University.
 *
 * Created by maryellenbowman.
 */
@Entity
@Table(name="course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="credits")
    private Integer credits;

    @ManyToOne
    @JoinColumn(name="instructor_id")
    private Staff instructor;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "course_prerequisites",
        joinColumns = @JoinColumn(name = "id_of_course"),
        inverseJoinColumns = @JoinColumn(name = "id_prerequisite_course"))
    private List<Course> prerequisites = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name="department_id")
    private Department department;

    public Course(String name, Integer credits, Staff instructor, Department department) {
        this.name = name;
        this.credits = credits;
        this.instructor = instructor;
        this.department = department;
    }

    protected Course() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Staff getInstructor() {
        return instructor;
    }

    public Department getDepartment() {
        return department;
    }
    // This is a helper method to add a prerequisite course to the current course.
    // It adds the given prerequisite course to the prerequisites list and returns
    // the current course instance for method chaining.
    public Course addPrerequisite(Course prerequisite){
        prerequisites.add(prerequisite);
        return this;
    }

    public Integer getCredits() {
        return credits;
    }

    public List<Course> getPrerequisites() {
        return prerequisites;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", credits=" + credits +
                ", instructor=" + instructor +
                ", department=" + department.getName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id.equals(course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
