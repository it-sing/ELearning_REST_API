package co.istad.elearningrestapi.features.country;

import co.istad.elearningrestapi.features.city.CityService;
import co.istad.elearningrestapi.features.city.dto.CityResponse;
import co.istad.elearningrestapi.features.country.dto.CountryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/countries")
public class CountryController {
    private final CountryService countryService;
    private final CityService cityService;

    @GetMapping
    public ResponseEntity<List<CountryResponse>> getAllCountries(@RequestParam(required = false) String name) {
        List<CountryResponse> countries = countryService.findAllCountries(name);
        return ResponseEntity.ok(countries);
    }

    @GetMapping("/{iso}/cities")
    public ResponseEntity<List<CityResponse>> findAllCitiesInCountry(@PathVariable String iso) {
        List<CityResponse> cities = countryService.findAllCitiesInCountry(iso);
        return ResponseEntity.ok(cities);
    }

}