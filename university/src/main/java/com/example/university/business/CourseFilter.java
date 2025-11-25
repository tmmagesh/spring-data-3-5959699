package com.example.university.business;

import com.example.university.domain.Course;
import com.example.university.domain.Department;
import com.example.university.domain.Staff;

import java.util.Optional;

/**
 * Takeaway fromCo-pilot Explanation:
 * Your CourseFilter is a fluent, flexible filter builder that uses Optional to
 * make filter criteria explicit and safe. It avoids null checks and makes the
 * API expressive for dynamic queries.
 * 
 * &^%%%%%Helper class to filter courses in the Dynamic Query Service&*******&^&*^&%*%
 */
public class CourseFilter {
    private Optional<Department> department = Optional.empty();
    private Optional<Integer> credits = Optional.empty();
    private Optional<Staff> instructor = Optional.empty();

    // Static factory method to create a new CourseFilter instance
    // Provides a clean entry point: CourseFilter filter = CourseFilter.filterBy();

    // This avoids calling new CourseFilter() directly and makes the API more
    // expressive.
    //Static factory method????
    public static CourseFilter filterBy() {
        return new CourseFilter();
    }

    // Each method sets the corresponding filter and returns this. This enables
    // method chaining,
    // e.g.:
    // CourseFilter filter = CourseFilter.filterBy()
    // .department(dept)
    // .credits(4)
    // .instructor(staff);
    //
    // Enables chaining:
    // / CourseFilter filter = CourseFilter.filterBy()
    // .department(dept)
    // .credits(4)
    // .instructor(staff); or .instructor(profSmith);
    public CourseFilter department(Department department) {
        this.department = Optional.of(department);
        return this;
    }

    public CourseFilter credits(Integer credits) {
        this.credits = Optional.of(credits);
        return this;
    }

    public CourseFilter instructor(Staff instructor) {
        this.instructor = Optional.of(instructor);
        return this;
    }

    public Optional<Department> getDepartment() {
        return department;
    }

    public Optional<Integer> getCredits() {
        return credits;
    }

    public Optional<Staff> getInstructor() {
        return instructor;
    }

    public boolean meetsCriteria(Course course) {
        return (instructor.map(i -> course.getInstructor().equals(i)).orElse(true)
                && credits.map(c -> course.getCredits().equals(c)).orElse(true)
                && department.map(d -> course.getDepartment().equals(d)).orElse(true));

    }
}

// What Optional Means
// It’s a container object that can either:

// Hold a non-null value (Optional.of(value)), or

// Be empty (Optional.empty()).
// It’s designed to avoid NullPointerException by making the absence of a value
// explicit.

// Usage in CourseFilter
// In CourseFilter, Optional is used for filter criteria (department, credits,
// instructor).
// Each criterion is an Optional, meaning it may or may not be set.

/*
 * Why Use It?Clarity:Instead of returning null
 * when something isn’t found, you return Optional.empty().
 * 
 * Safety: Forces the caller to handle the possibility of “no value.”
 * caller to handle the possibility of“no value.”
 * 
 * Functional style:
 * Provides methods like map, filter, orElse, ifPresent for clean handling
 * 
 * Examples:
 * Optional<String> name = Optional.of("Magesh");
 * System.out.println(name.get()); // prints "Magesh"
 * 
 * Optional<String> emptyName = Optional.empty();
 * System.out.println(emptyName.orElse("No Name")); // prints "No Name"
 * 
 * Key Methods
 * isPresent() → checks if a value exists.
 * 
 * get() → retrieves the value (throws exception if empty).
 * 
 * orElse(defaultValue) → returns value if present, otherwise default.
 * 
 * ifPresent(action) → executes action if value exists.
 * 
 * map() → transforms the value if present.
 * : Optional means “a value that might be there, or might not.” It’s Java’s way
 * of handling missing values safely without relying on null.
 * 
 * Would you like me to also show you how Optional compares to using null
 * directly, with pros and cons side by side?
 * Comparison: Optional vs null
 * **Aspect*** ***Using Optional*** *** Using null***
 * Meaning*** Explicitly says “value may be absent” Implicit absence, but not
 * obvious
 * Safety*** Avoids NullPointerException
 * with safe methods Risk of NullPointerException if not handled properly
 * Readability*** Clear intent: Optional.empty() vs Optional.of(value) Less
 * clear:
 * null could mean many things
 * API Support*** Provides methods like map, filter, orElse, ifPresent No
 * built-in
 * helpers, must write checks manually
 *** 
 * Best Use Case*** Return types in methods (e.g., findById) Internal fields,
 * simple data models
 * Debugging Easier to trace missing values in stack traces Harder to trace null
 * Performance Slight overhead (wrapper object) Lightweight (just null)
 * Style Encourages functional programming style Traditional imperative style
 * 
 *** Key Takeaway***
 * Use Optional when you want to signal explicitly that a value may be missing,
 * especially in method return types (e.g., repository lookups, parsing
 * results).
 * 
 * Use null for internal fields or performance-sensitive code where wrapping in
 * Optional adds unnecessary overhead.
 * 
 * 
 * * //Static factory method????
 * public static CourseFilter filterBy() {
 * return new CourseFilter();
 * }
 * 
 * FACTORY METHOD PATTERN
 * The Factory Method Pattern is a creational design pattern that provides an
 * interface for creating objects in a superclass, but allows subclasses to
 * alter the
 * type of objects that will be created.
 * What is a Factory Method?
 * In Java (and OOP in general), a factory method is a static method that
 * creates and returns an instance of a class.
 * 
 * Instead of calling new CourseFilter() directly, you call a named method like
 * CourseFilter.filterBy().
 * 
 * This method acts as a factory because it produces (or “manufactures”) objects
 * for you.
 * 
 */
