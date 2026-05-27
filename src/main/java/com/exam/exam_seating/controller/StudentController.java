package com.exam.exam_seating.controller;

import com.exam.exam_seating.model.Student;
import com.exam.exam_seating.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("students", service.getAll());
        return "index";
    }

    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("student", new Student());
        return "add";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Student student) {
        service.save(student);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model) {
        model.addAttribute("students", service.search(keyword));
        model.addAttribute("keyword", keyword);

        return "index";
    }

    @GetMapping("/rooms")
    public String rooms(Model model) {
        model.addAttribute("rooms", service.getAllRooms());
        return "rooms";
    }

    @GetMapping("/room/{roomNo}")
    public String roomView(@PathVariable int roomNo, Model model) {
        List<Student> students = service.getStudentsByRoom(roomNo);

        Student[] seatMap = new Student[80];

        for (Student s : students) {
            if (s.getSeatNo() >= 1 && s.getSeatNo() <= 80) {
                seatMap[s.getSeatNo() - 1] = s;
            }
        }

        model.addAttribute("seatMap", seatMap);
        model.addAttribute("roomNo", roomNo);

        return "room-view";
    }
}
