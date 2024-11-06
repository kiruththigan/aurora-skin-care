package src.services;

import java.util.HashMap;

import src.models.Appointment;

public interface AppointmentService {
    public void addAppointment();

    public void updateAppointment();

    public void viewAllAppointments();

    public void searchAppointments(String key);

    public void generateInvoice();

    public HashMap<Integer, Appointment> getAllAppointments();

}
