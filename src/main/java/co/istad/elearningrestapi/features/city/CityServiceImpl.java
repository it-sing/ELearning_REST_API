package co.istad.elearningrestapi.features.city;

import co.istad.elearningrestapi.domain.City;
import co.istad.elearningrestapi.features.city.dto.CityResponse;
import co.istad.elearningrestapi.mapper.CityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public List<CityResponse> findAll(String name) {
        Sort sort = Sort.by(Sort.Direction.ASC,"name");
        List<City> cities = cityRepository.findAll(sort);
        if(cities.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "There is no cities!"
            );
        }

        return cityMapper.toDTO(cities);
    }


}
