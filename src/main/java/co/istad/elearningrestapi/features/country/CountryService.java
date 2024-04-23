package co.istad.elearningrestapi.features.country;

import co.istad.elearningrestapi.features.city.dto.CityResponse;
import co.istad.elearningrestapi.features.country.dto.CountryResponse;

import java.util.List;

public interface CountryService {
    List<CountryResponse> findAllCountries(String name);

    List<CityResponse> findAllCitiesInCountry(String iso);
}
