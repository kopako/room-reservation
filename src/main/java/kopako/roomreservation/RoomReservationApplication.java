package kopako.roomreservation;

import kopako.roomreservation.pojo.Person;
import kopako.roomreservation.pojo.Room;
import kopako.roomreservation.repository.PersonRepository;
import kopako.roomreservation.repository.RoomRepository;
import kopako.roomreservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
public class RoomReservationApplication {

	private static final Logger log = LoggerFactory.getLogger(RoomReservationApplication.class);

	@Autowired
	RoomRepository roomRepository;
	@Autowired
	PersonRepository personRepository;
	@Autowired
	ReservationService reservationService;

	public static void main(String[] args) {
		SpringApplication.run(RoomReservationApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(PersonRepository personRepository) {
		return (args) -> {
			List<Person> persons = new LinkedList<>();
			for (int i = 0; i < 1000; i++) {
						persons.add(new Person("Person "+ i));
			}
			personRepository.saveAll(persons);

			roomRepository.save(new Room("Blue","#1259B2"));
			roomRepository.save(new Room("Green","#009900"));
			roomRepository.save(new Room("Yellow", "#FFDC00"));
			roomRepository.save(new Room("Orange", "#c35500"));

			String today = String.valueOf(LocalDate.now());

			reservationService.reserve(1,"Blue",
					Timestamp.valueOf(today +" 13:00:00"),
					Timestamp.valueOf(today + " 12:00:00"));
			reservationService.reserve(15,"Blue",
					Timestamp.valueOf(today + " 10:00:00"),
					Timestamp.valueOf(today + " 10:30:00"));
			reservationService.reserve(5,"Green",
					Timestamp.valueOf(today + " 08:00:00"),
					Timestamp.valueOf(today + " 09:00:00"));
			reservationService.reserve(9,"Yellow",
					Timestamp.valueOf(today + " 08:15:00"),
					Timestamp.valueOf(today + " 09:15:00"));
			reservationService.reserve(9,"Orange",
					Timestamp.valueOf(today + " 11:15:00"),
					Timestamp.valueOf(today + " 13:15:00"));

//			reservationService.findAllByDate(Date.valueOf(today + "")).forEach(System.out::println);
		};
	}
}
