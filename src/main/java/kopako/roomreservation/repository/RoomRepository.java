package kopako.roomreservation.repository;

import kopako.roomreservation.pojo.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

	List<Room> findByName(@Param("name") String name);

	List<Room> findByNameIgnoreCase(@Param("name") String name);


}
