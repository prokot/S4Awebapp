package S4A.Baggage.Repository;

import S4A.Baggage.Entity.Baggage;
import S4A.Flight.Entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BaggageRepository extends JpaRepository<Baggage,Integer> {
    List<Baggage> findAllByFlight(Flight flight);
}
