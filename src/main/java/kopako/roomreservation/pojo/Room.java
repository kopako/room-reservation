package kopako.roomreservation.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
public @Data class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private long id;

	@Column(unique = true)
	private String name;
	private String backgroundColor;
	@OneToMany(mappedBy = "room")
	private List<Reservation> reservations;

	public Room() {
	}

	public Room(String name) {
		this.name = name;
	}

	public Room(String name, String backgroundColor) {
		this.name = name;
		this.backgroundColor = backgroundColor;
	}
}
