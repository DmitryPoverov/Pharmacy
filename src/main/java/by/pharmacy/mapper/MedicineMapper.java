package by.pharmacy.mapper;

import by.pharmacy.dto.MedicineDto;
import by.pharmacy.entity.Medicine;

public class MedicineMapper {
    private static final MedicineMapper INSTANCE = new MedicineMapper();


    public MedicineDto mapFromMedicineToMedicineDto(Medicine medicine) {
        return MedicineDto.builder()
                .id(medicine.getId())
                .name(medicine.getName())
                .price(medicine.getPrice())
                .country(medicine.getCountry())
                .build();
    }

    public Medicine mapFromMedicineDtoToMedicine(MedicineDto object) {
        Medicine medicine = new Medicine();
        try {
            Medicine.builder().id(object.getId()).build();
        } catch (Exception e) {
            medicine = Medicine.builder()
                    .name(object.getName())
                    .price(object.getPrice())
                    .country(object.getCountry())
                    .build();
        }
        if (medicine.getName() == null) {
            medicine = Medicine.builder()
                    .id(object.getId())
                    .name(object.getName())
                    .price(object.getPrice())
                    .country(object.getCountry())
                    .build();
        }
        return medicine;
    }

    public static MedicineMapper getInstance() {
        return INSTANCE;
    }

}