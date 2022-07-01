package S4A.Parsing.Entity.CCargo;

import S4A.Baggage.Entity.Baggage;
import S4A.Cargo.Entity.Cargo;
import S4A.Flight.Entity.Flight;
import S4A.Flight.Service.FlightService;
import S4A.Parsing.Entity.BaggageParser.BaggageParser;
import S4A.Parsing.Entity.CargoParser.CargoParser;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CCargo implements Serializable {

    private Integer flightId;

    private List<BaggageParser> baggage;

    private List<CargoParser> cargo;

    public List<Baggage> getBaggage(FlightService service){
        Optional<Flight> flight = service.find(flightId);
        List<Baggage> baggages = null;

        if(flight.isPresent()){
            baggages = new ArrayList<Baggage>();

            for (BaggageParser p:
                 baggage) {
                baggages.add(new Baggage(p,flight.get()));
            }
        }
        return baggages;
    }


    public List<Cargo> getCargo(FlightService service){
        Optional<Flight> flight = service.find(flightId);
        List<Cargo> cargos = null;

        if(flight.isPresent()){
            cargos = new ArrayList<Cargo>();

            for (CargoParser p:
                    cargo) {
                cargos.add(new Cargo(p,flight.get()));
            }
        }
        return cargos;
    }

}
