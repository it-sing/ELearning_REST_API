package co.istad.elearningrestapi.features.category.dto;

public record CategoryParentResponse(
        String alias,
        String name,
        String icon,
        CategoryResponse parentCategory
) {
}
