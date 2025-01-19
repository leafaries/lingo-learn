package com.lingolearn.presententatioofthepubsub;

@FunctionalInterface
public interface Subscriber {
    void update(SomeRandomViewModel someRandomViewModel);
}
