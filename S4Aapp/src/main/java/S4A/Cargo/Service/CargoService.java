package S4A.Cargo.Service;

import S4A.Cargo.Entity.Cargo;
import S4A.Cargo.Repository.CargoRepository;
import S4A.Flight.Entity.Flight;
import S4A.Flight.Repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CargoService {

    private FlightRepository repository;

    private CargoRepository cargoRepository;

    @Autowired
    public CargoService(CargoRepository cargoRepository,FlightRepository repository) {
        this.repository = repository;
        this.cargoRepository = cargoRepository;
    }

    public List<Cargo> findByFlight(Flight flight) {
        return cargoRepository.findAllByFlight(flight);
    }

    @Transactional
    public Cargo create(Cargo cargo) {
        return cargoRepository.save(cargo);
    }
    @Transactional
    public void update(Cargo cargo) {
        cargoRepository.save(cargo);
    }
    @Transactional
    public void delete(Integer cargoId) {
        cargoRepository.deleteById(cargoId);
    }

}
