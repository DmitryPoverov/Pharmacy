package by.pharmacy.dto;

import by.pharmacy.entity.Role;
import lombok.*;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Builder
public class UserDto {

    Integer id;
    String name;
    String email;
    String password;
    Role role;
}
