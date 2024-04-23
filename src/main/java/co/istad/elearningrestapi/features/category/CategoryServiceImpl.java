package co.istad.elearningrestapi.features.category;

import co.istad.elearningrestapi.domain.Category;
import co.istad.elearningrestapi.features.category.dto.CategoryParentResponse;
import co.istad.elearningrestapi.features.category.dto.CategoryRequest;
import co.istad.elearningrestapi.features.category.dto.CategoryResponse;
import co.istad.elearningrestapi.mapper.CategoryMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public void createNew(CategoryRequest categoryRequest) {
            Category category = categoryMapper.fromCategoryRequest(categoryRequest);
            category.setDeleted(false);

            categoryRepository.save(category);
    }

    @Override
    public Page<CategoryResponse> findList(int page, int size) {
        //validate page and size
        if (page < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Page number must be greater than or equal to zero"
            );
        }
        if (size < 1) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Page size must be greater than or equal to one"
            );
        }
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categories = categoryRepository.findByIsDeletedFalse(pageable);

        return categories.map(categoryMapper::toCategoryResponse);
    }

    @Override
    public List<CategoryParentResponse> findParentCategories() {
        List<Category> parentCategories = categoryRepository.findParentCategories();
        return categoryMapper.categoriesToCategoryParentResponses(parentCategories);
    }

    @Override
    public CategoryResponse findCategoryByAlias(String alias) {
        Category category = categoryRepository.findByAliasAndIsDeletedFalse(alias)
                .orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Category alias name is invalid"
                        ));
        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public CategoryResponse updateCategoryByAlias(String alias, CategoryRequest categoryRequest) {
        Category category= categoryRepository.findByAlias(alias)
                .orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Category alias name is invalid"
                        ));
        category.setAlias(categoryRequest.alias());
        category.setName(categoryRequest.name());
        category.setIcon(categoryRequest.icon());
        category = categoryRepository.save(category);

        return categoryMapper.toCategoryResponse(category);
    }

    @Transactional
    @Override
    public void disableCategoryByAlias(String alias) {
        if (!categoryRepository.existsByAlias(alias)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Category has not been found"
            );
        }
        try {
            categoryRepository.disableCategoryByAlias(alias);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Something went wrong"
            );
        }
    }

}
