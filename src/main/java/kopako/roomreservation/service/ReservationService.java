package kopako.roomreservation.service;

import kopako.roomreservation.pojo.Reservation;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface ReservationService {

    List<Reservation> findAll();
    List<Reservation> findAllByDate(Date date);

    void reserve(long personId, String roomName, Timestamp start, Timestamp end);


    }
