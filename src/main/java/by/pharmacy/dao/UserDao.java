package by.pharmacy.dao;

import by.pharmacy.entity.Role;
import by.pharmacy.entity.User;
import by.pharmacy.util.ConnectionManager;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<Integer, User> {

    private static final UserDao INSTANCE = new UserDao();
    private static final String FIND_ALL = "select * from users";
    private static final String SAVE = """
                            INSERT INTO users (name, email, password, role)
                            VALUES (?, ?, ?, ?)
            """;
    private static final String FIND_BY_EMAIL_AND_PASSWORD = """
            SELECT *
            FROM users
            WHERE email=? AND password=?
            """;

    public static UserDao getInstance() {
        return INSTANCE;
    }

    @SneakyThrows
    public Optional<User> findByEmailAndPassword(String email, String password) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_EMAIL_AND_PASSWORD)) {
            preparedStatement.setObject(1, email);
            preparedStatement.setObject(2, password);
            var resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = buildEntity(resultSet);
            }
            return Optional.ofNullable(user);
        }
    }

    private User buildEntity(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getObject("id", Integer.class))
                .name(resultSet.getObject("name", String.class))
                .email(resultSet.getObject("email", String.class))
                .password(resultSet.getObject("password", String.class))
                .role(Role.valueOf(resultSet.getObject("role", String.class)))
                .build();
    }

    @Override
    @SneakyThrows
    public User save(User entity) {
        try (Connection connection = ConnectionManager.get()) {
            PreparedStatement statement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS);
            statement.setObject(1, entity.getName());
            statement.setObject(2, entity.getEmail());
            statement.setObject(3, entity.getPassword());
            statement.setObject(4, entity.getRole().name());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getObject("id", Integer.class));
            return entity;
        }
    }

    @Override
    public List<User> findAll() {
        try(Connection connection = ConnectionManager.get();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)){
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> listOfUsers = new ArrayList<>();
            while (resultSet.next()) {
                listOfUsers.add(buildUser(resultSet));
            }
            return listOfUsers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    private User buildUser(ResultSet resultSet) {
        return new User(
                resultSet.getObject("id", Integer.class),
                resultSet.getObject("name", String.class),
                resultSet.getObject("email", String.class),
                resultSet.getObject("password", String.class),
                Role.valueOf(resultSet.getObject("role", String.class))
        );
    }


    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(User entity) {

    }
}
