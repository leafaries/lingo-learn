package com.lingolearn.vocabulary.category.usecase;

import com.lingolearn.vocabulary.category.domain.CategoryHierarchyNode;
import com.lingolearn.vocabulary.category.domain.CategoryStatistics;
import com.lingolearn.vocabulary.category.domain.VocabularySetInfo;
import jdk.jfr.Category;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing Category entities and their relationships.
 */
public interface CategoryRepository {
    // Basic CRUD operations
    void save(Category category);
    Optional<Category> findById(Long id);
    List<Category> findAll();
    void deleteById(Long id);

    // Hierarchy operations
    List<Category> findChildren(Long parentId);
    List<Category> findRootCategories();
    Optional<CategoryHierarchyNode> getHierarchyTree(Long rootCategoryId);
    List<CategoryHierarchyNode> getAllHierarchyTrees();

    // Vocabulary Set operations
    List<VocabularySetInfo> findSetsInCategory(Long categoryId);
    List<VocabularySetInfo> findSetsInCategoryRecursive(Long categoryId);
    void assignSetsToCategory(Long categoryId, List<Long> setIds);
    void removeSetsFromCategory(Long categoryId, List<Long> setIds);

    // Statistics and Analysis
    CategoryStatistics getCategoryStatistics(Long categoryId);
    List<CategoryStatistics> getCategoryStatisticsRecursive(Long rootCategoryId);

    // Search and filtering
    List<Category> findByNameContaining(String searchTerm);
    List<Category> findByParentId(Long parentId);
    boolean existsById(Long id);

    // Validation
    boolean hasChildCategories(Long categoryId);
    boolean hasSets(Long categoryId);

    // Batch operations
    void saveAll(List<Category> categories);
    void deleteAll(List<Long> categoryIds);

    // Movement operations
    void moveCategory(Long categoryId, Long newParentId);
    void reorderCategories(List<Long> categoryIds, Long parentId);
}
