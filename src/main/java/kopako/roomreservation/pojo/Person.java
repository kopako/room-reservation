package kopako.roomreservation.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
public @Data class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "person")
    private List<Reservation> reservations;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Person[id=%d, name='%s']", id,
                name);
    }
}
