package by.pharmacy.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Builder
public class MedicineDto {

    int id;
    String name;
    int price;
    String country;

}
