package kopako.roomreservation.view;

import kopako.roomreservation.pojo.Reservation;
import kopako.roomreservation.pojo.Room;
import kopako.roomreservation.repository.RoomRepository;
import kopako.roomreservation.service.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class ReservationController {

    private final int timeGridPrecision = 15;
    private final LocalDate today = LocalDate.now();

    @Autowired
    ReservationServiceImpl reservationService;

    @Autowired
    RoomRepository roomRepository;

    //    TODO: rename model attribute to prevent misleading
    @ModelAttribute("allReservations")
    public List<Reservation> populateReservations() {
        return reservationService.findAllByDate(Date.valueOf(today));
    }

    @ModelAttribute("allRooms")
    public List<Room> populateRooms() {
        return (List<Room>) roomRepository.findAll();
    }

    @ModelAttribute("timeGrid")
    public List<Timestamp> populateTimeGrid() {
        return Stream.iterate(Timestamp.valueOf(today+" 08:00:00"),a-> addMinutes(a,timeGridPrecision))
                .limit(16*2)
                .collect(Collectors.toList());
    }

    private static Timestamp addMinutes(Timestamp time, int value) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time.getTime());
        cal.add(Calendar.MINUTE, value);
        time = new Timestamp(cal.getTime().getTime());
        return time;
    }

    @GetMapping("/")
    public String greeting(@RequestParam(name="dateToShow",required = false) LocalDate dateToShow,
                           Model model) {
        if (dateToShow == null) {
            dateToShow=today;
        }
        model.addAttribute("dateToShow", dateToShow);
        return "timesheet";
    }

}