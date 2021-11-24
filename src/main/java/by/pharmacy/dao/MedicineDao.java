package by.pharmacy.dao;

import by.pharmacy.entity.Medicine;
import by.pharmacy.util.ConnectionManager;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MedicineDao implements Dao<Integer, Medicine> {

    private final static MedicineDao INSTANCE = new MedicineDao();
    private static final String FIND_ALL = "select * from medicines";
    private static final String DELETE_BY_ID = """
            DELETE FROM medicines
            WHERE id = ?
            """;
    private static final String SAVE = """
            INSERT INTO medicines (name, price, country)
            VALUES (?, ?, ?)
            """;
    private static final String FIND_BY_ID = """
            SELECT *
            FROM medicines
            WHERE id = ?
            """;

    @Override
    @SneakyThrows
    public Optional<Medicine> findById(Integer id) {
        try(Connection connection = ConnectionManager.get();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Medicine medicine = new Medicine();
            if (resultSet.next()) {
                medicine.setId(resultSet.getObject("id", Integer.class));
                medicine.setName(resultSet.getObject("name", String.class));
                medicine.setPrice(resultSet.getInt("price"));
                medicine.setCountry(resultSet.getString("country"));

            }
            return Optional.ofNullable(medicine);
        }
    }

    @Override
    @SneakyThrows
    public Medicine save(Medicine entity) {
        try(Connection connection = ConnectionManager.get();
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, entity.getName());
            preparedStatement.setObject(2, entity.getPrice());
            preparedStatement.setObject(3, entity.getCountry());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getObject("id", Integer.class));
            return entity;
        }
    }

    @Override
    public List<Medicine> findAll() {
        try(Connection connection = ConnectionManager.get();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)){
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Medicine> listOfMedicine = new ArrayList();
            while (resultSet.next()) {
                listOfMedicine.add(buildMedicine(resultSet));
            }
            return listOfMedicine;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }//method

    private Medicine buildMedicine(ResultSet resultSet) throws SQLException {
        return new Medicine(
                resultSet.getObject("id", Integer.class),
                resultSet.getObject("name", String.class),
                resultSet.getObject("price", Integer.class),
                resultSet.getObject("country", String.class));
    }

    @Override
    @SneakyThrows
    public boolean delete(Integer id) {
        try (Connection connection = ConnectionManager.get()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate()!=0;
        }
    }

    public static MedicineDao getInstance() {
        return INSTANCE;
    }

    @Override
    public void update(Medicine entity) {
    }
}
