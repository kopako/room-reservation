package kopako.roomreservation.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Entity
public @Data class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    private Timestamp startReservation;
    private Timestamp endReservation;

    public Reservation() {
    }

    public Reservation(Room room, Person person, Timestamp startReservation, Timestamp endReservation) {
        this.room = room;
        this.person = person;
        this.startReservation = startReservation;
        this.endReservation = endReservation;
    }
}
