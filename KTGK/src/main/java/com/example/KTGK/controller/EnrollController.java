package com.example.KTGK.controller;

import com.example.KTGK.model.Appointment;
import com.example.KTGK.model.Doctor;
import com.example.KTGK.model.Patient;
import com.example.KTGK.repository.AppointmentRepository;
import com.example.KTGK.repository.DoctorRepository;
import com.example.KTGK.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class EnrollController {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @GetMapping("/enroll")
    public String myAppointments(Model model, Principal principal) {
        model.addAttribute("appointments", appointmentRepository.findByPatientUsernameOrderByAppointmentDateDesc(principal.getName()));
        return "my-appointments";
    }

    @PostMapping("/enroll/create")
    public String createAppointment(@RequestParam Long doctorId,
                                    @RequestParam String appointmentDate,
                                    Principal principal) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Patient patient = patientRepository.findByUsername(principal.getName()).orElseThrow();

        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentDate(LocalDate.parse(appointmentDate).atStartOfDay());
        appointmentRepository.save(appointment);

        return "redirect:/enroll";
    }
}
