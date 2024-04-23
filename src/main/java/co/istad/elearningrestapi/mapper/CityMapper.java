package co.istad.elearningrestapi.mapper;

import co.istad.elearningrestapi.domain.City;
import co.istad.elearningrestapi.features.city.dto.CityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {
    @Mapping(source = "country.name", target = "country")
    CityResponse toDTO(City city);
    List<CityResponse> toDTO(List<City> cities);
}
