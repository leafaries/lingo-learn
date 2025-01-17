package horizontallayers.interfaceadapters.dtos.requests;

public record CreatVocabularySetRequest(
        String name,
        String description,
        String categoryName
) {
}
