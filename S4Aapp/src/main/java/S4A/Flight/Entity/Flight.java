package S4A.Flight.Entity;

import S4A.Baggage.Entity.Baggage;
import S4A.Cargo.Entity.Cargo;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name="flight")
public class Flight implements Serializable{

    @Id
    private Integer flightId;

    private Integer flightNumber;

    private String departureAirportIATACode;

    private String arrivalAirportIATACode;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departureDate;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Cargo> cargos;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Baggage> baggages;

}
