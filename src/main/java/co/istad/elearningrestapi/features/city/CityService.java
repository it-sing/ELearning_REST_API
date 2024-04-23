package co.istad.elearningrestapi.features.city;

import co.istad.elearningrestapi.features.city.dto.CityResponse;
import java.util.List;

public interface CityService {
    List<CityResponse> findAll(String name);

}
