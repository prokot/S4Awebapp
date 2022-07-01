package S4A.Flight.DTO;

import S4A.Flight.Service.FlightService;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetFlightDTO {

    private Integer cargoWeight;
    private Integer baggageWeight;
    private Integer totalWeight;

    public static GetFlightDTO entityToDtoMapper(Integer flightNumber, Date departureDate, FlightService flightService) {
        GetFlightDTO.GetFlightDTOBuilder response = GetFlightDTO.builder();
        response
                .cargoWeight(flightService.sumCargoWeight(departureDate, flightNumber))
                .baggageWeight(flightService.sumBaggageWeight(departureDate, flightNumber))
                .totalWeight(response.cargoWeight + response.baggageWeight);
        return response.build();
    }
}
