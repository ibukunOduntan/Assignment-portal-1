package org.systemspecs.interns.service.serviceimpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systemspecs.interns.domain.Course;
import org.systemspecs.interns.domain.Lecturer;
import org.systemspecs.interns.domain.Student;
import org.systemspecs.interns.repository.CourseRepo;
import org.systemspecs.interns.repository.LecturerRepo;
import org.systemspecs.interns.repository.StudentRepo;
import org.systemspecs.interns.service.LecturerService;
import org.systemspecs.interns.service.StudentService;

import java.util.List;

@Service

@Transactional

public class LecturerServiceImpl implements LecturerService {

    private final LecturerRepo repo;
    private final CourseRepo course_repo;


    public LecturerServiceImpl(LecturerRepo repo, CourseRepo course_repo) {
        this.repo = repo;
        this.course_repo = course_repo;
    }



    @Override
    public List<Course> getAllCoursesTaught(Long lecturerId) {
        return repo.findCoursesTaughtById(lecturerId);
    }

    @Override
    public void addCourseToLecturer(String fullName, List<String> course_list) {
        {
            Lecturer lecturer = repo.findByFullName(fullName);

            for (String s : course_list) {
                Course course = course_repo.findByCode(s);

                lecturer.getCourses_Taught().add(course);
            }
        };

    }


}
