package com.lingolearnhub.category;

import com.lingolearnhub.vocabulary.VocabularySet;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Category {

    private String name;
    private List<VocabularySet> vocabularySets = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

}
