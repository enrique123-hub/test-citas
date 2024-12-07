package com.test.technicaltest.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.technicaltest.model.Appointment;
import com.test.technicaltest.model.Doctor;
import com.test.technicaltest.model.FiltersResponse;
import com.test.technicaltest.model.Office;
import com.test.technicaltest.service.AppointmentService;
import com.test.technicaltest.service.DoctorService;
import com.test.technicaltest.service.OfficeService;
import com.test.technicaltest.utis.Utils;

import java.util.List;

@Controller
public class Index {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private OfficeService officeService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/")
    public String showAppointmentForm(Model model) {
        List<Doctor> doctors = doctorService.getListDoctors();
        List<Office> offices = officeService.getListOffices();

        model.addAttribute("doctors", doctors);
        model.addAttribute("offices", offices);

        return "index";
    }

    @PostMapping("/create")
    public String createAppointment(
            @RequestParam("office") String officeId,
            @RequestParam("doctor") String doctorId,
            @RequestParam("appointmentTime") String appointmentTime,
            @RequestParam("patientName") String patientName,
            RedirectAttributes redirectAttributes) {

        Office office = officeService.getOfficeById(officeId);
        Doctor doctor = doctorService.getDoctorById(doctorId);

        try {
            if (appointmentService.validateAppointment(office, doctor, patientName,
                    Utils.formatTime(appointmentTime))) {

                Appointment appointment = new Appointment();

                appointment.setOffice(office);
                appointment.setDoctor(doctor);
                appointment.setAppointmentTime(Utils.formatTime(appointmentTime));
                appointment.setPatientName(patientName);

                appointmentService.createAppointment(appointment);

                redirectAttributes.addFlashAttribute("message", "Cita agendada con éxito!");
                return "redirect:/";
            }
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/";
        }

        return "redirect:/";
    }

    @GetMapping("/all")
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/filters")
    public FiltersResponse getFilters() {
        List<Doctor> doctors = doctorService.getListDoctors();
        List<Office> offices = officeService.getListOffices();
        return new FiltersResponse(doctors, offices);
    }

    @GetMapping("/citas")
    public String showCitas(@RequestParam(required = false) String date,
            @RequestParam(required = false) String officeId,
            @RequestParam(required = false) String doctorId,
            Model model) {

        List<Appointment> appointments = appointmentService.getFilteredAppointments(date, officeId, doctorId);
        model.addAttribute("appointments", appointments);

        List<Doctor> doctors = doctorService.getListDoctors();
        List<Office> offices = officeService.getListOffices();
        model.addAttribute("doctors", doctors);
        model.addAttribute("offices", offices);

        return "citas";
    }

    @PostMapping("/appointments/cancel/{appointmentId}")
    public String cancelAppointment(@PathVariable String appointmentId, RedirectAttributes redirectAttributes) {
        try {
            appointmentService.cancelAppointment(appointmentId);
            redirectAttributes.addFlashAttribute("message", "Cita cancelada con éxito.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error al cancelar la cita: " + e.getMessage());
        }
        return "redirect:/citas";
    }

    @GetMapping("/appointments/edit/{appointmentId}")
    public String editAppointmentForm(@PathVariable String appointmentId, Model model) {
        Appointment appointment = appointmentService.getAppointmentId(appointmentId);
        model.addAttribute("appointment", appointment);
        model.addAttribute("offices", officeService.getListOffices());
        model.addAttribute("doctors", doctorService.getListDoctors());
        return "edit-appointment";
    }
    

    @PostMapping("/appointments/edit/{appointmentId}")
    public String editAppointment(@PathVariable String appointmentId,
            @RequestParam("office") String officeId,
            @RequestParam("doctor") String doctorId,
            @RequestParam("appointmentTime") String appointmentTime,
            @RequestParam("patientName") String patientName,
            RedirectAttributes redirectAttributes) {
        try {
            Appointment existingAppointment = appointmentService.getAppointmentId(appointmentId);
            System.out.println(existingAppointment);

            Office office = officeService.getOfficeById(officeId);
            Doctor doctor = doctorService.getDoctorById(doctorId);

            if (appointmentService.validateAppointment(office, doctor, patientName,
                    appointmentTime)) {
                existingAppointment.setOffice(office);
                existingAppointment.setDoctor(doctor);
                existingAppointment.setAppointmentTime(Utils.formatTime(appointmentTime));
                existingAppointment.setPatientName(patientName);


                appointmentService.updateAppointment(existingAppointment);
                
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error al actualizar la cita: " + e.getMessage());
        }

        return "redirect:/citas";
    }

}