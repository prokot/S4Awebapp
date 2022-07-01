package S4A.Parsing.Entity.CargoParser;


import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CargoParser {
    private Integer id;

    private Integer weight;

    private String weightUnit;

    private Integer pieces;

}
