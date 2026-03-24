package com.example.KTGK.controller;

import com.example.KTGK.dto.DoctorForm;
import com.example.KTGK.model.Department;
import com.example.KTGK.model.Doctor;
import com.example.KTGK.repository.DepartmentRepository;
import com.example.KTGK.repository.DoctorRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/doctors")
@RequiredArgsConstructor
public class AdminDoctorController {

    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;

    @GetMapping
    public String list(@RequestParam(defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Doctor> doctorPage = doctorRepository.findAll(pageable);
        model.addAttribute("doctorPage", doctorPage);
        model.addAttribute("currentPage", page);
        return "admin/doctor-list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("doctorForm", new DoctorForm());
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("isEdit", false);
        return "admin/doctor-form";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("doctorForm") DoctorForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", departmentRepository.findAll());
            model.addAttribute("isEdit", false);
            return "admin/doctor-form";
        }

        Department department = departmentRepository.findById(form.getDepartmentId()).orElseThrow();
        Doctor doctor = new Doctor();
        doctor.setName(form.getName());
        doctor.setImage(form.getImage());
        doctor.setSpecialty(form.getSpecialty());
        doctor.setDepartment(department);
        doctorRepository.save(doctor);
        return "redirect:/admin/doctors";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow();
        DoctorForm form = new DoctorForm();
        form.setName(doctor.getName());
        form.setImage(doctor.getImage());
        form.setSpecialty(doctor.getSpecialty());
        form.setDepartmentId(doctor.getDepartment().getId());

        model.addAttribute("doctorForm", form);
        model.addAttribute("doctorId", id);
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("isEdit", true);
        return "admin/doctor-form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("doctorForm") DoctorForm form,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("doctorId", id);
            model.addAttribute("departments", departmentRepository.findAll());
            model.addAttribute("isEdit", true);
            return "admin/doctor-form";
        }

        Doctor doctor = doctorRepository.findById(id).orElseThrow();
        Department department = departmentRepository.findById(form.getDepartmentId()).orElseThrow();
        doctor.setName(form.getName());
        doctor.setImage(form.getImage());
        doctor.setSpecialty(form.getSpecialty());
        doctor.setDepartment(department);
        doctorRepository.save(doctor);
        return "redirect:/admin/doctors";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        doctorRepository.deleteById(id);
        return "redirect:/admin/doctors";
    }
}
