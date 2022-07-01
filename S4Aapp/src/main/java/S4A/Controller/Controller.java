package S4A.Controller;


import S4A.Flight.DTO.GetFlightDTO;
import S4A.Flight.DTO.GetFlightsDTO;
import S4A.Flight.Service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@CrossOrigin(origins = "http://127.0.0.1:8083", maxAge = 3600)
@RestController
@RequestMapping("api/flights")

public class Controller {


    private final FlightService flightService;

    @Autowired
    public Controller(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping(params = {"departureDate","IATACode"})
    public ResponseEntity<GetFlightsDTO> getFlights(@RequestParam Date departureDate, @RequestParam String IATACode) {
        return ResponseEntity
                .ok(GetFlightsDTO.entityToDtoMapper(departureDate,IATACode,flightService));
    }

    @GetMapping(params = {"departureDate","flightNumber"})
    public ResponseEntity<GetFlightDTO> getFlight(@RequestParam Date departureDate, @RequestParam Integer flightNumber) {
        return ResponseEntity
                .ok(GetFlightDTO.entityToDtoMapper(flightNumber,departureDate,flightService));
    }
}
