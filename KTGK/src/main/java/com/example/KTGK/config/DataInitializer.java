package com.example.KTGK.config;

import com.example.KTGK.model.Appointment;
import com.example.KTGK.model.Doctor;
import com.example.KTGK.model.Patient;
import com.example.KTGK.model.Role;
import com.example.KTGK.repository.AppointmentRepository;
import com.example.KTGK.repository.DoctorRepository;
import com.example.KTGK.repository.PatientRepository;
import com.example.KTGK.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final RoleRepository roleRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner seedUsers() {
        return args -> {
            Role adminRole = roleRepository.findByName("ADMIN").orElseThrow();
            Role patientRole = roleRepository.findByName("PATIENT").orElseThrow();

            if (!patientRepository.existsByUsername("admin")) {
                Patient admin = new Patient();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setEmail("admin@qlbv.vn");
                admin.getRoles().add(adminRole);
                patientRepository.save(admin);
            }

            if (!patientRepository.existsByUsername("benhnhan1")) {
                Patient patient = new Patient();
                patient.setUsername("benhnhan1");
                patient.setPassword(passwordEncoder.encode("patient123"));
                patient.setEmail("benhnhan1@qlbv.vn");
                patient.getRoles().add(patientRole);
                patientRepository.save(patient);
            }

            if (!patientRepository.existsByUsername("benhnhan2")) {
                Patient patient = new Patient();
                patient.setUsername("benhnhan2");
                patient.setPassword(passwordEncoder.encode("patient123"));
                patient.setEmail("benhnhan2@qlbv.vn");
                patient.getRoles().add(patientRole);
                patientRepository.save(patient);
            }

            if (!patientRepository.existsByUsername("benhnhan3")) {
                Patient patient = new Patient();
                patient.setUsername("benhnhan3");
                patient.setPassword(passwordEncoder.encode("patient123"));
                patient.setEmail("benhnhan3@qlbv.vn");
                patient.getRoles().add(patientRole);
                patientRepository.save(patient);
            }

            Patient p1 = patientRepository.findByUsername("benhnhan1").orElseThrow();
            Patient p2 = patientRepository.findByUsername("benhnhan2").orElseThrow();
            Patient p3 = patientRepository.findByUsername("benhnhan3").orElseThrow();
            Doctor d1 = doctorRepository.findById(1L).orElseThrow();
            Doctor d3 = doctorRepository.findById(3L).orElseThrow();
            Doctor d8 = doctorRepository.findById(8L).orElseThrow();
            Doctor d11 = doctorRepository.findById(11L).orElseThrow();
            Doctor d17 = doctorRepository.findById(17L).orElseThrow();

            if (appointmentRepository.findByPatientUsernameOrderByAppointmentDateDesc("benhnhan1").isEmpty()) {
                appointmentRepository.save(createAppointment(p1, d1, LocalDateTime.now().plusDays(1)));
                appointmentRepository.save(createAppointment(p1, d3, LocalDateTime.now().plusDays(3)));
            }

            if (appointmentRepository.findByPatientUsernameOrderByAppointmentDateDesc("benhnhan2").isEmpty()) {
                appointmentRepository.save(createAppointment(p2, d11, LocalDateTime.now().plusDays(2)));
                appointmentRepository.save(createAppointment(p2, d17, LocalDateTime.now().plusDays(4)));
            }

            if (appointmentRepository.findByPatientUsernameOrderByAppointmentDateDesc("benhnhan3").isEmpty()) {
                appointmentRepository.save(createAppointment(p3, d8, LocalDateTime.now().plusDays(5)));
            }
        };
    }

    private Appointment createAppointment(Patient patient, Doctor doctor, LocalDateTime dateTime) {
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(dateTime);
        return appointment;
    }
}
