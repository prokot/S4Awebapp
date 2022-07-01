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
public class GetFlightsDTO {

    private Integer departNo;
    private Integer arriveNo;
    private Integer departPieces;
    private Integer arrivePieces;

    public static GetFlightsDTO entityToDtoMapper(Date departureDate, String IATACode,FlightService flightService) {
        GetFlightsDTOBuilder response = GetFlightsDTO.builder();
        response
                .departNo(flightService.findByDateAndDepartureIATACode(departureDate,IATACode))
                .arriveNo(flightService.findByDateAndArrivalIATACode(departureDate,IATACode))
                .arrivePieces(flightService.sumPiecesByDateAndArrivalIATACode(departureDate,IATACode))
                .departPieces(flightService.sumPiecesByDateAndDepartureIATACode(departureDate, IATACode));
        return response.build();

    }
}
