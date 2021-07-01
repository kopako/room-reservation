package kopako.roomreservation.service;

import kopako.roomreservation.pojo.Person;
import kopako.roomreservation.pojo.Reservation;
import kopako.roomreservation.pojo.Room;
import kopako.roomreservation.repository.PersonRepository;
import kopako.roomreservation.repository.ReservationRepository;
import kopako.roomreservation.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final PersonRepository personRepository;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;


    public ReservationServiceImpl(PersonRepository personRepository, RoomRepository roomRepository, ReservationRepository reservationRepository) {
        this.personRepository = personRepository;
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }

    public synchronized void reserve(long personId, String roomName, Timestamp start, Timestamp end){
        Room room = roomRepository.findByNameIgnoreCase(roomName).stream().findFirst().get();
        Person person = personRepository.findById(personId).get();
        if(roomIsFree(room, start, end)){
            reservationRepository.save(new Reservation(room,person,start,end));
        }
    }

    private boolean roomIsFree(Room room, Timestamp start, Timestamp end) {
//        List<Reservation> reservations = room.getReservations();
        return true;
    }

    public List<Reservation> findAll() {
        return (List<Reservation>) reservationRepository.findAll();
    }

    @Override
    public List<Reservation> findAllByDate(Date date) {
        Timestamp timestampStart = new Timestamp(date.getTime());
        Timestamp timestampEnd = new Timestamp(date.getTime()+(1000*60*60*24)-1);
        return reservationRepository.findReservationByStartReservationBetween(timestampStart,timestampEnd);
    }
}
