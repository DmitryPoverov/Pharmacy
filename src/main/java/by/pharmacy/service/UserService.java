package by.pharmacy.service;

import by.pharmacy.dao.UserDao;
import by.pharmacy.dto.UserDto;
import by.pharmacy.entity.User;
import by.pharmacy.mapper.UserMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService {

    private static final UserService INSTANCE = new UserService();

    private final UserMapper userMapper = UserMapper.getInstance();
    private final UserDao userDao = UserDao.getInstance();


    private UserService() {
    }

    public static UserService getInstance() {
        return INSTANCE;
    }

    public Integer create(UserDto userDto) {
        User userEntity = userMapper.mapFrom(userDto);
        User save = userDao.save(userEntity);
        return save.getId();
    }

    public List<UserDto> findAll() {
        return userDao.findAll().stream().map(user -> new UserDto(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getRole()))
                .collect(Collectors.toList());
    }

    public Optional<UserDto> login(String email, String password) {
        return userDao.findByEmailAndPassword(email, password)
                .map(user -> userMapper.mapFromUserDtoToUser(user));
    }
}
