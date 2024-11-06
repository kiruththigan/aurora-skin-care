package src.services;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public interface TimeSlotsService {
    public List<LocalTime> getTimeSlotsByDay(DayOfWeek day);
}
