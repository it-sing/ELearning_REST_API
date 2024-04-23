package co.istad.elearningrestapi.features.city;


import co.istad.elearningrestapi.features.city.dto.CityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cities")
public class CityController {

    private final CityService cityService;

    @GetMapping
    public ResponseEntity<List<CityResponse>> getAllCities(@RequestParam(required = false) String name) {
        List<CityResponse> cities = cityService.findAll(name);
        return ResponseEntity.ok(cities);
    }

}
