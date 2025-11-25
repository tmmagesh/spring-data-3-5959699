package com.example.university.business;

import com.example.university.dao.CourseDao;
import com.example.university.dao.DepartmentDao;
import com.example.university.dao.StaffDao;
import com.example.university.dao.StudentDao;
import com.example.university.domain.*;
//Redundant @Repository Import
//You imported org.springframework.stereotype.Repository but don’t use it.Safe to remove.
//import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties.Validation;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Business Tier class for the Univeirsity Library
 * 
 * Magesh Comments from Co-pilot
 * UniversityService class is a business-tier service layer that orchestrates
 * persistence operations across multiple DAOs (CourseDao, DepartmentDao,
 * StaffDao, StudentDao). Let’s break down what’s good here, and how you can
 * refine or extend it for clarity, maintainability, and expressive design:
 */
@Service
public class UniversityService {

    private DepartmentDao departmentDao;

    private StaffDao staffDao;

    private StudentDao studentDao;

    private CourseDao courseDao;
    // *** This is not addressed in the current code but worth considering***
    // Validation Add input validation (e.g., non-null names, positive credits/age)
    // before persisting entities. This prevents invalid data from reaching the
    // database.

    // Constructor Injection
    // You’re injecting DAOs via the constructor, which is the recommended Spring
    // practice (immutable dependencies, easier testing).
    public UniversityService(CourseDao courseDao, DepartmentDao departmentDao, StaffDao staffDao,
            StudentDao studentDao) {
        this.courseDao = courseDao;
        this.departmentDao = departmentDao;
        this.staffDao = staffDao;
        this.studentDao = studentDao;
    }
    // Factory Methods for Creating Domain Entities
    // These methods encapsulate the creation and persistence of domain entities,
    // promoting a clear API for clients
    // of the service layer.

    // Magesh Comments from Co-pilot
    // Each method constructs a new domain object and immediately persists it using
    // the appropriate DAO.
    // This keeps object creation logic centralized and consistent.
    // For example, createStudent constructs a Student entity and saves it via
    // studentDao.
    // This pattern can be extended to include validation, logging, or other
    // cross-cutting concerns as needed.
    // Encapsulation of Business Logic

    // CPMAG Methods like createStudent,createFaculty,createDepartment,and
    // createCourse encapsulate entity creation and persistence,keeping controller
    // code clean.
    public Student createStudent(String firstName, String lastName, boolean fullTime, int age) {
        return studentDao.save(new Student(new Person(firstName, lastName), fullTime, age));
    }

    public Staff createFaculty(String firstName, String lastName) {
        return staffDao.save(new Staff(new Person(firstName, lastName)));
    }

    public Department createDepartment(String deptname, Staff deptChair) {
        return departmentDao.save(new Department(deptname, deptChair));
    }

    public Course createCourse(String name, int credits, Staff professor, Department department) {
        return courseDao.save(new Course(name, credits, professor, department));
    }

    // Overloaded createCourse method to handle prerequisites
    // This method allows adding prerequisite courses when creating a new course.
    // It iterates over the provided prerequisite courses and adds them to the new
    // course before saving.
    // This enhances the flexibility of course creation in scenarios where
    // prerequisites are relevant.
    // Magesh Comments from Co-pilot Overloaded createCourse
    // One version creates a course without prerequisites, another allows
    // prerequisites to be added before saving.
    // This is expressive and flexible
    public Course createCourse(String name, int credits, Staff professor, Department department, Course... prereqs) {
        Course c = new Course(name, credits, professor, department);
        for (Course p : prereqs) {
            c.addPrerequisite(p);
        }
        return courseDao.save(c);
    }
    // Retrieval Methods for Domain Entities
    // These methods provide a straightforward way to retrieve all entities of a
    // given type.
    // They delegate the retrieval logic to the respective DAOs, maintaining a clean
    // separation of concerns.
    // Methods like findAllCourses,findAllStaff,findAllDepartments,and
    // findAllStudents encapsulate retrieval logic,keeping controller code clean.
    // Magesh Comments from Co-pilot
    // Each method calls the corresponding DAO’s findAll method to fetch all
    // records of that entity type. Finder Methods
    // CP MAG Finder Methods
    // findAllCourses,findAllStaff,findAllDepartments, and findAllStudents etc.
    // provide simple
    // access to collections of entities.

    public List<Course> findAllCourses() {
        return courseDao.findAll();
    }

    public List<Staff> findAllStaff() {
        return staffDao.findAll();
    }

    public List<Department> findAllDepartments() {
        return departmentDao.findAll();
    }

    public List<Student> findAllStudents() {
        return studentDao.findAll();
    }
// Domain-Specific Queries
//  Add methods that reflect real university operations:
    //***NOT IN THE ORIFINAL GITHUB Version***
    public List<Course> findCoursesByDepartment(Department department) {
        return courseDao.findByDepartment(department);
    }

    // ***NOT IN THE ORIFINAL GITHUB Version***
    public List<Student> findStudentsByFullTimeStatus(boolean fullTime) {
        return studentDao.findByFullTime(fullTime);
    }

    // This is for resetting the database between tests (test data) / This is the
    // cleanup method deleteAll() centralizes deletion logic, useful for resetting
    // test data.
    // Magesh Comments from Co-pilot
    // The deleteAll method orchestrates the deletion of all entities across
    // multiple DAOs.
    // This is particularly useful in testing scenarios where a clean database
    // state is required between tests.

    // Magesh Comments from Co-pilot Areas to Improve
    // Exception Handling in deleteAll()

    // Currently, you just printStackTrace(). Better to use logging (log.error(...))
    // or rethrow a custom exception so failures don’t silently pass.

    // CP MAG Exception Handling in deleteAll()
    // Transactional Boundaries

    // For methods that involve multiple DAOs (like deleteAll or complex course
    // creation), consider annotating with @Transactional to ensure atomicity.
    @Transactional
    public void deleteAll() {
        try {
            studentDao.deleteAll();
            courseDao.deleteAll();
            departmentDao.deleteAll();
            staffDao.deleteAll();
        } catch (Exception e) {
            // e.printStackTrace();
            throw new RuntimeException("Failed to delete all entities", e);
        }
    }
}
