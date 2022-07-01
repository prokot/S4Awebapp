package S4A.Cargo.Repository;

import S4A.Cargo.Entity.Cargo;
import S4A.Flight.Entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CargoRepository extends JpaRepository<Cargo,Integer> {
    List<Cargo> findAllByFlight(Flight flight);
}
