package S4A.Cargo.Entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString
public class CargoId implements Serializable {
    private Integer id;

    private Integer flightId;

    public CargoId(Integer cargoId, Integer flightId){
        this.id = cargoId;
        this.flightId = flightId;
    }

}
