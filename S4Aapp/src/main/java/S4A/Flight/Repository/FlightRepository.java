package S4A.Flight.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import S4A.Flight.Entity.Flight;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface FlightRepository extends JpaRepository<Flight,Integer>{

    Integer countByDepartureDateAndDepartureAirportIATACode(Date departureDate, String IATACode);

    Integer countByDepartureDateAndArrivalAirportIATACode(Date departureDate, String IATACode);


    @Query("SELECT SUM(n.pieces) FROM Baggage n INNER JOIN n.flight f ON n.flightId = f.flightId WHERE " +
            "f.departureAirportIATACode = :IATACode  AND f.departureDate = :departureDate")
    Integer sumPiecesByDepartureDateAndDepartureIATACode(Date departureDate, String IATACode);

    @Query("SELECT SUM(n.pieces) FROM Baggage n INNER JOIN n.flight f ON n.flightId = f.flightId WHERE " +
            "f.arrivalAirportIATACode = :IATACode  AND f.departureDate = :departureDate")
    Integer sumPiecesByDepartureDateAndArrivalIATACode(Date departureDate, String IATACode);

    @Query("SELECT SUM(n.weight) FROM Cargo n INNER JOIN n.flight f ON n.flightId = f.flightId WHERE " +
            "f.flightNumber = :flightNumber AND f.departureDate = :departureDate AND n.weightUnit= :weightUnit")
    Optional<Integer> sumCargoWeightByFlightNumberAndDepartureDate(Integer flightNumber, Date departureDate, String weightUnit);

    @Query("SELECT SUM(n.weight) FROM Baggage n INNER JOIN n.flight f ON n.flightId = f.flightId WHERE " +
            "f.flightNumber = :flightNumber AND f.departureDate = :departureDate AND n.weightUnit= :weightUnit")
    Optional<Integer> sumBaggageWeightByFlightNumberAndDepartureDate(Integer flightNumber, Date departureDate,String weightUnit);


}
