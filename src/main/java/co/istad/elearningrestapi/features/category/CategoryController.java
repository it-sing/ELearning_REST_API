package co.istad.elearningrestapi.features.category;

import co.istad.elearningrestapi.features.category.dto.CategoryParentResponse;
import co.istad.elearningrestapi.features.category.dto.CategoryRequest;
import co.istad.elearningrestapi.features.category.dto.CategoryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    //Create a new category
    @PostMapping
    void createNew(@Valid @RequestBody CategoryRequest categoryRequest) {
        categoryService.createNew(categoryRequest);
    }

    //Find all categories by pagination
    @GetMapping
    Page<CategoryResponse> findList(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") int size
    ){
        return categoryService.findList(page, size);
    }

    //Find all categories which have subcategories
    @GetMapping("/parents")
    List<CategoryParentResponse> findParentCategories() {
        return categoryService.findParentCategories();
    }

    //Find a category by alias
    @GetMapping("/{alias}")
    CategoryResponse findCategoryByAlias(@PathVariable String alias) {
        return categoryService.findCategoryByAlias(alias);
    }

    //Update a category’s information
    @PutMapping("/{alias}")
    CategoryResponse updateCategoryByAlias(@PathVariable String alias, @Valid @RequestBody CategoryRequest categoryRequest) {
        return categoryService.updateCategoryByAlias(alias, categoryRequest);
    }

    //Disable a category
    @PutMapping("/{alias}/disable")
    void disableCategoryByAlias(@PathVariable String alias) {
        categoryService.disableCategoryByAlias(alias);
    }

}
