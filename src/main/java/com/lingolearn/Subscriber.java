package com.lingolearn;

@FunctionalInterface
public interface Subscriber {
    void update(SomeRandomViewModel someRandomViewModel);
}
