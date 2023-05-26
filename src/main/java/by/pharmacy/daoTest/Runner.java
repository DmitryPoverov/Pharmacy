package by.pharmacy.daoTest;

import by.pharmacy.dao.Dao;
import by.pharmacy.dao.MedicineDao;
import by.pharmacy.entity.Medicine;

import java.util.Optional;

public class Runner {
    public static void main(String[] args) {

        Dao<Integer, Medicine> medicine = new MedicineDao();
        Optional<Medicine> byId = medicine.findById(25);
        System.out.println(byId.isPresent() && byId.get().getName()!=null ? byId.get().getName() : "Empty medicine.");
    }

}
