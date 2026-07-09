package com.itcube.journal.controller;

import com.itcube.journal.dto.course.CourseRequestDTO;
import com.itcube.journal.dto.course.CourseResponseDTO;
import com.itcube.journal.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    @PreAuthorize("hasRole('journal.course_viewer')")
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses() {
        return ResponseEntity.ok(courseService.findAllCourses());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('journal.course_viewer')")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.findCourseById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('journal.course_creator')")
    public ResponseEntity<CourseResponseDTO> createCourse(
            @RequestBody CourseRequestDTO courseRequestDTO) {
        return ResponseEntity.ok(courseService.createCourse(courseRequestDTO));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('journal.course_editor')")
    public ResponseEntity<CourseResponseDTO> updateCourse(
            @PathVariable Long id,
            @RequestBody CourseRequestDTO courseRequestDTO) {
        return ResponseEntity.ok(courseService.updateCourse(id, courseRequestDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('journal.course_editor')")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
