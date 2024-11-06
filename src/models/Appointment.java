package src.models;

import java.time.LocalDateTime;

public class Appointment {
    private int id;
    private LocalDateTime dateTime;
    private Patient patient;
    private Treatment treatment;
    private Dermatologist dermatologist;
    private static final double registrationFee = 500.00;

    public Appointment() {
    }

    public Appointment(
            int id,
            LocalDateTime dateTime,
            Patient patient,
            Treatment treatment,
            Dermatologist dermatologist) {
        this.id = id;
        this.dateTime = dateTime;
        this.patient = patient;
        this.treatment = treatment;
        this.dermatologist = dermatologist;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Treatment getTreatment() {
        return this.treatment;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    public Dermatologist getDermatologist() {
        return this.dermatologist;
    }

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }

    public double getRegistrationFee() {
        return registrationFee;
    }
}
