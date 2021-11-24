package by.pharmacy.mapper;

import by.pharmacy.dto.UserDto;
import by.pharmacy.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

//@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    private static final UserMapper INSTANCE = new UserMapper();

    public User mapFrom(UserDto object) {
        return User.builder()
                .id(object.getId())
                .name(object.getName())
                .email(object.getEmail())
                .password(object.getPassword())
                .role(object.getRole())
                .build();
    }

    public UserDto mapFromUserDtoToUser(User object) {
        return UserDto.builder()
                .id(object.getId())
                .name(object.getName())
                .email(object.getEmail())
                .password(object.getPassword())
                .role(object.getRole())
                .build();
    }

    public static UserMapper getInstance() {
        return INSTANCE;
    }
}
