package src.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import src.models.Dermatologist;

public interface DermatologistService {
    public ArrayList<Dermatologist> getAllDermatologist();

    public boolean isDermatologistExist(Dermatologist dermatologist);

    public void addBookedDateTime(Dermatologist dermatologist, LocalDateTime dateTime);

    public void removeBookedDateTime(Dermatologist dermatologist, LocalDateTime dateTime);

    public List<LocalTime> getBookedTimeSlotsByDermatologistAndDate(LocalDate date, Dermatologist dermatologist);
}
