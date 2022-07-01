package S4A.Flight.Service;

import S4A.Flight.Entity.Flight;
import S4A.Flight.Repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import javax.transaction.Transactional;


@Service
public class FlightService {
    private FlightRepository repository;


    @Autowired
    public FlightService(FlightRepository repository) {
        this.repository = repository;
    }

    public Optional<Flight> find(Integer id) {
        return repository.findById(id);
    }


    public Integer findByDateAndDepartureIATACode(Date departureDate, String IATACode) {
        return repository.countByDepartureDateAndDepartureAirportIATACode(departureDate,IATACode);
    }

    public Integer findByDateAndArrivalIATACode(Date departureDate, String IATACode) {
        return repository.countByDepartureDateAndArrivalAirportIATACode(departureDate,IATACode);
    }

    public Integer sumPiecesByDateAndDepartureIATACode(Date departureDate, String IATACode) {
        return repository.sumPiecesByDepartureDateAndDepartureIATACode(departureDate,IATACode);
    }

    public Integer sumPiecesByDateAndArrivalIATACode(Date departureDate, String IATACode) {
        return repository.sumPiecesByDepartureDateAndArrivalIATACode(departureDate,IATACode);
    }

    public Integer sumCargoWeight(Date departureDate, Integer flightNumber) {
        Optional<Integer> lbWeight = repository.sumCargoWeightByFlightNumberAndDepartureDate(flightNumber,departureDate,"lb");
        Optional<Integer> kgWeight = repository.sumCargoWeightByFlightNumberAndDepartureDate(flightNumber,departureDate,"kg");

        return (int)(lbWeight.orElseGet(() -> 0)*0.453592) + kgWeight.orElseGet(() -> 0);
    }

    public Integer sumBaggageWeight(Date departureDate, Integer flightNumber) {
        Optional<Integer> lbWeight = repository.sumBaggageWeightByFlightNumberAndDepartureDate(flightNumber,departureDate,"lb");
        Optional<Integer> kgWeight = repository.sumBaggageWeightByFlightNumberAndDepartureDate(flightNumber,departureDate,"kg");

        return (int)(lbWeight.orElseGet(() -> 0)*0.453592) + kgWeight.orElseGet(() -> 0);
    }

    @Transactional
    public void create(Flight flight) {
        repository.save(flight);
    }

    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
