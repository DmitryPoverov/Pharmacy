package by.pharmacy.mapper;

import by.pharmacy.dto.MedicineDto;
import by.pharmacy.entity.Medicine;

public class SaveMedicineMapper {

    private static final SaveMedicineMapper INSTANCE = new SaveMedicineMapper();

    public static SaveMedicineMapper getInstance() {
        return INSTANCE;
    }

    public Medicine mapFromMedicineDtoToMedicine (MedicineDto medicineDto) {
        return Medicine.builder()
                .name(medicineDto.getName())
                .price(medicineDto.getPrice())
                .country(medicineDto.getCountry())
                .build();
    }
}
