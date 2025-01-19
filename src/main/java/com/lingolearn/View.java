package com.lingolearn;

public class View implements Subscriber {
    SomeRandomViewModel someRandomViewModel;

    public View(SomeRandomViewModel someRandomViewModel) {
        this.someRandomViewModel = someRandomViewModel;
        someRandomViewModel.subscribe(this);
    }

    @Override
    public void update(SomeRandomViewModel someRandomViewModel) {
        // Update the UI
        System.out.println("Updating the UI with the new data: " + someRandomViewModel.getData());
    }
}
