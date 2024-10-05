package com.sgu.givingsgu.service;

import com.sgu.givingsgu.model.Faculty;
import com.sgu.givingsgu.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public Faculty getFacultyById(Long id) {
        return facultyRepository.findById(id).orElse(null); // Hoặc bạn có thể ném ngoại lệ nếu không tìm thấy
    }

    public Faculty saveFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    public Faculty updateFaculty(Long id, Faculty facultyDetails) {
        Faculty faculty = facultyRepository.findById(id).orElse(null);
        if (faculty != null) {
            faculty.setName(facultyDetails.getName());
            return facultyRepository.save(faculty);
        }
        return null; // Hoặc bạn có thể ném ngoại lệ nếu không tìm thấy
    }
}
