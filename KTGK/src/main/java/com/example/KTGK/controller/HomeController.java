package com.example.KTGK.controller;

import com.example.KTGK.model.Doctor;
import com.example.KTGK.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final DoctorRepository doctorRepository;

    @GetMapping({"/", "/home"})
    public String home(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword,
                       Model model) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Doctor> doctorPage = keyword == null || keyword.isBlank()
                ? doctorRepository.findAll(pageable)
                : doctorRepository.findByNameContainingIgnoreCase(keyword.trim(), pageable);
        model.addAttribute("doctorPage", doctorPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "home";
    }
}
