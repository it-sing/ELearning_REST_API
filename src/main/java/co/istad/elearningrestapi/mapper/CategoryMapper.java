package co.istad.elearningrestapi.mapper;

import co.istad.elearningrestapi.domain.Category;
import co.istad.elearningrestapi.features.category.dto.CategoryParentResponse;
import co.istad.elearningrestapi.features.category.dto.CategoryRequest;
import co.istad.elearningrestapi.features.category.dto.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "parentCategory", source = "request.parentCategory", qualifiedByName = "mapParentCategory")
    Category fromCategoryRequest(CategoryRequest request);
    @Named("mapParentCategory")
    default Category mapParentCategory(Long parentId) {
        if (parentId == null) {
            return null;
        }
        Category category = new Category();
        category.setId(parentId);
        return category;
    }

    CategoryResponse toCategoryResponse(Category category);

    List<CategoryParentResponse> categoriesToCategoryParentResponses(List<Category> parentCategories);
}
