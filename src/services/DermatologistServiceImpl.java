package src.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import src.models.Dermatologist;

public class DermatologistServiceImpl implements DermatologistService {
    ArrayList<Dermatologist> dermatologists = new ArrayList<>();
    HashMap<String, List<LocalDateTime>> bookedTimeSlots = new HashMap<>();

    public DermatologistServiceImpl() {
        dermatologists.add(new Dermatologist("Dr Maali"));
        dermatologists.add(new Dermatologist("Dr Kumari"));
    }

    public ArrayList<Dermatologist> getAllDermatologist() {
        return dermatologists;
    }

    public boolean isDermatologistExist(Dermatologist dermatologist) {
        return dermatologists.stream()
                .anyMatch(d -> d.getName().equals(dermatologist.getName()));
    }

    public void addBookedDateTime(Dermatologist dermatologist, LocalDateTime dateTime) {
        List<LocalDateTime> bookedDates = bookedTimeSlots.get(dermatologist.getName());

        if (bookedDates == null) {
            bookedDates = new ArrayList<>();
            bookedTimeSlots.put(dermatologist.getName(), bookedDates);
        }

        bookedDates.add(dateTime);
    }

    public void removeBookedDateTime(Dermatologist dermatologist, LocalDateTime dateTime) {
        List<LocalDateTime> bookedDates = bookedTimeSlots.get(dermatologist.getName());

        if (bookedDates != null) {
            bookedDates.remove(dateTime);
        }
    }

    public List<LocalTime> getBookedTimeSlotsByDermatologistAndDate(LocalDate date, Dermatologist dermatologist) {
        List<LocalDateTime> bookedDates = bookedTimeSlots.get(dermatologist.getName());
        if (bookedDates == null) {
            return null;
        }
        List<LocalTime> bookedTimeSlots = new ArrayList<>();
        for (LocalDateTime localDateTime : bookedDates) {
            LocalDate localDate = localDateTime.toLocalDate();
            if (localDate.equals(date)) {
                bookedTimeSlots.add(localDateTime.toLocalTime());
            }
        }

        return bookedTimeSlots;
    }

}
