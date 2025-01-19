package com.lingolearn.vocabulary.category.domain;

import java.util.List;

public record CategoryHierarchyNode(
        Long id,
        String name,
        String description,
        List<CategoryHierarchyNode> children,
        int depth,
        int totalWords,
        int totalSets
) {
    public boolean isLeaf() {
        return children.isEmpty();
    }

    public boolean hasChildren() {
        return !children.isEmpty();
    }

    public int getSubtreeWordCount() {
        return totalWords + children.stream()
                .mapToInt(CategoryHierarchyNode::getSubtreeWordCount)
                .sum();
    }

    public int getSubtreeSetCount() {
        return totalSets + children.stream()
                .mapToInt(CategoryHierarchyNode::getSubtreeSetCount)
                .sum();
    }
}
