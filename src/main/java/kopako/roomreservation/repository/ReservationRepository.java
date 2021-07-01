package kopako.roomreservation.repository;


import kopako.roomreservation.pojo.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    List<Reservation> findReservationByStartReservationBetween(Timestamp start,Timestamp end);
}
