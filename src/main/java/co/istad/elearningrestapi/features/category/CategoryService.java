package co.istad.elearningrestapi.features.category;

import co.istad.elearningrestapi.features.category.dto.CategoryParentResponse;
import co.istad.elearningrestapi.features.category.dto.CategoryRequest;
import co.istad.elearningrestapi.features.category.dto.CategoryResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {

    void createNew(CategoryRequest categoryRequest);
    Page<CategoryResponse> findList(int page, int size);
    List<CategoryParentResponse> findParentCategories();
    CategoryResponse findCategoryByAlias(String alias);
    CategoryResponse updateCategoryByAlias(String alias, CategoryRequest categoryRequest);
    void disableCategoryByAlias(String alias);
}
