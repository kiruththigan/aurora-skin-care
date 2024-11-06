package src.services;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TimeSlotsServiceImpl implements TimeSlotsService {
        HashMap<DayOfWeek, List<LocalTime>> workingHours = new HashMap<>();

        public TimeSlotsServiceImpl() {
                workingHours.put(DayOfWeek.MONDAY, generateTimeSlots(LocalTime.of(10, 0), LocalTime.of(13, 00), 15));
                workingHours.put(DayOfWeek.WEDNESDAY, generateTimeSlots(LocalTime.of(14, 0), LocalTime.of(17, 00), 15));
                workingHours.put(DayOfWeek.FRIDAY, generateTimeSlots(LocalTime.of(16, 0), LocalTime.of(20, 00), 15));
                workingHours.put(DayOfWeek.SATURDAY, generateTimeSlots(LocalTime.of(9, 0), LocalTime.of(13, 00), 15));
        }

        private List<LocalTime> generateTimeSlots(LocalTime startTime, LocalTime endTime, int duration) {
                List<LocalTime> timeSlots = new ArrayList<>();

                while (startTime.isBefore(endTime)) {
                        timeSlots.add(startTime);
                        startTime = startTime.plusMinutes(duration);
                }

                return timeSlots;
        }

        public List<LocalTime> getTimeSlotsByDay(DayOfWeek day) {
                return workingHours.get(day);
        }
}
