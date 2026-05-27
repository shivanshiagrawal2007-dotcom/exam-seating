package com.exam.exam_seating.service;

import com.exam.exam_seating.model.Student;
import com.exam.exam_seating.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    private static final int ROOM_CAPACITY = 80;

    public List<Student> getAll() {
        List<Student> list = repo.findAll();

        list.sort(Comparator
                .comparing(Student::getRoomNo)
                .thenComparing(Student::getSeatNo));

        return list;
    }

    public void save(Student student) {

        List<Student> all = repo.findAll();

        int total = all.size();

        int room = (total / ROOM_CAPACITY) + 1;
        int seat = (total % ROOM_CAPACITY) + 1;

        student.setRoomNo(room);
        student.setSeatNo(seat);

        repo.save(student);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Student> search(String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            return repo.findAll();
        }

        return repo.findByNameContainingIgnoreCase(keyword);
    }

    public List<Integer> getAllRooms() {

        int count = (int) repo.count();
        int totalRooms = (count / ROOM_CAPACITY) + 1;

        List<Integer> rooms = new ArrayList<>();

        for (int i = 1; i <= totalRooms; i++) {
            rooms.add(i);
        }

        return rooms;
    }

    public List<Student> getStudentsByRoom(int roomNo) {

        List<Student> all = repo.findAll();

        return all.stream()
                .filter(s -> s.getRoomNo() == roomNo)
                .collect(Collectors.toList());
    }
}
