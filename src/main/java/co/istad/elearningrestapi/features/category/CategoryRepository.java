package co.istad.elearningrestapi.features.category;

import co.istad.elearningrestapi.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    Page<Category> findByIsDeletedFalse(Pageable pageable);

    @Query("""
    SELECT c FROM Category c
    WHERE c.parentCategory IS NULL
    """)
    List<Category> findParentCategories();

    Optional<Category> findByAlias(String alias);

    @Modifying
    @Query("""
    UPDATE Category AS c
    SET c.isDeleted = TRUE
    WHERE c.alias = ?1
    """)
    void disableCategoryByAlias(String alias);

    Boolean existsByAlias(String alias);
    Optional<Category> findByAliasAndIsDeletedFalse(String alias);
}
