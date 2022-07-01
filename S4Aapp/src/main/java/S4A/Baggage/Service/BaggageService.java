package S4A.Baggage.Service;

import S4A.Baggage.Entity.Baggage;
import S4A.Baggage.Repository.BaggageRepository;
import S4A.Flight.Entity.Flight;
import S4A.Flight.Repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Service
public class BaggageService implements Serializable {
    private FlightRepository repository;

    private BaggageRepository baggageRepository;

    @Autowired
    public BaggageService(BaggageRepository baggageRepository,FlightRepository repository) {
        this.repository = repository;
        this.baggageRepository = baggageRepository;
    }

    public List<Baggage> findByFlight(Flight flight) {
        return baggageRepository.findAllByFlight(flight);
    }

    @Transactional
    public Baggage create(Baggage baggage) {
        return baggageRepository.save(baggage);
    }
    @Transactional
    public void update(Baggage baggage) {
        baggageRepository.save(baggage);
    }
    @Transactional
    public void delete(Integer baggageId) {
        baggageRepository.deleteById(baggageId);
    }
}
