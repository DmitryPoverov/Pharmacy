package by.pharmacy.service;

import by.pharmacy.dao.MedicineDao;
import by.pharmacy.dto.MedicineDto;
import by.pharmacy.entity.Medicine;
import by.pharmacy.mapper.MedicineMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MedicineService {

    private static final MedicineService INSTANCE = new MedicineService();
    private final MedicineDao medicineDao = MedicineDao.getInstance();
    private final MedicineMapper medicineMapper = MedicineMapper.getInstance();

    private MedicineService() {
    }

    public static MedicineService getInstance() {
        return INSTANCE;
    }

    public MedicineDto findById(int id) {
        Optional<Medicine> byId = medicineDao.findById(id);
        Medicine medicine = byId.get(); // здесь нет проверки на null, т.к. в нашем случае его не может быть
        return medicineMapper.mapFromMedicineToMedicineDto(medicine);
    }

    public String save (MedicineDto medicineDto) {
        Medicine medicine = medicineMapper.mapFromMedicineDtoToMedicine(medicineDto);
        Medicine save = medicineDao.save(medicine);
        return save!=null? "Successfully saving" : "Was not saved";
    }

    public List<MedicineDto> findAll () {
        List<Medicine> medicines = medicineDao.findAll();
        return medicines
                .stream()
                .map(medicine -> new MedicineDto(
                medicine.getId(),
                medicine.getName(),
                medicine.getPrice(),
                medicine.getCountry()))
                .collect(Collectors.toList());
    }

    public String delete (MedicineDto medicineDto) {
        Medicine medicine = medicineMapper.mapFromMedicineDtoToMedicine(medicineDto);
        boolean delete = medicineDao.delete(medicine.getId());
        if (delete) {
            return "Successfully deleted";
        } else {
            return "Was not deleted";
        }
    }
}
