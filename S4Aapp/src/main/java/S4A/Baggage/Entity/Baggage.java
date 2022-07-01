package S4A.Baggage.Entity;


import S4A.Flight.Entity.Flight;
import S4A.Parsing.Entity.BaggageParser.BaggageParser;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

import javax.persistence.*;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name="baggages")
@IdClass(BaggageId.class)
public class Baggage implements Serializable {

    @Id
    private Integer id;

    private Integer weight;

    private String weightUnit;

    private Integer pieces;

    @ManyToOne
    @JoinColumn(name = "baggages")
    private Flight flight;

    @Id
    private Integer flightId;

    public Baggage(BaggageParser p, Flight flight){
        this.id = p.getId();
        this.flight =flight;
        this.pieces = p.getPieces();
        this.weight = p.getWeight();
        this.weightUnit = p.getWeightUnit();
        this.flightId = flight.getFlightId();
    }
}
