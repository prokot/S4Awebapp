package S4A.Baggage.Entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class BaggageId implements Serializable {

    private Integer id;

    private Integer flightId;

    public BaggageId(Integer baggageId, Integer flightId){
        this.id = baggageId;
        this.flightId = flightId;
    }
}
