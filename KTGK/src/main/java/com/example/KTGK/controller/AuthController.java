package com.example.KTGK.controller;

import com.example.KTGK.dto.RegisterForm;
import com.example.KTGK.model.Patient;
import com.example.KTGK.model.Role;
import com.example.KTGK.repository.PatientRepository;
import com.example.KTGK.repository.RoleRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final PatientRepository patientRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registerForm") RegisterForm form,
                           BindingResult bindingResult,
                           Model model) {
        if (patientRepository.existsByUsername(form.getUsername())) {
            bindingResult.rejectValue("username", "duplicate", "Username đã tồn tại");
        }
        if (patientRepository.existsByEmail(form.getEmail())) {
            bindingResult.rejectValue("email", "duplicate", "Email đã tồn tại");
        }
        if (bindingResult.hasErrors()) {
            return "register";
        }

        Role patientRole = roleRepository.findByName("PATIENT")
                .orElseThrow(() -> new IllegalStateException("Thiếu role PATIENT trong database"));

        Patient patient = new Patient();
        patient.setUsername(form.getUsername());
        patient.setPassword(passwordEncoder.encode(form.getPassword()));
        patient.setEmail(form.getEmail());
        patient.getRoles().add(patientRole);
        patientRepository.save(patient);

        model.addAttribute("successMessage", "Đăng ký thành công. Bạn có thể đăng nhập ngay.");
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }
}
