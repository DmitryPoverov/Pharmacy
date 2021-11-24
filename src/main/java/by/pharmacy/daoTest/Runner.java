package by.pharmacy.daoTest;

import by.pharmacy.dao.MedicineDao;
import by.pharmacy.entity.Medicine;

import java.util.Optional;

public class Runner {
    public static void main(String[] args) {

        MedicineDao medicine = new MedicineDao();
        Optional<Medicine> byId = medicine.findById(70);
        System.out.println(byId.get());
    }

}
