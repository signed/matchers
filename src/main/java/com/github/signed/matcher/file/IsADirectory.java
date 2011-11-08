package com.github.signed.matcher.file;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.io.File;

public class IsADirectory extends TypeSafeMatcher<File>{

    @Factory
    public static Matcher<File> aDirectory() {
        return new IsADirectory();
    }

    @Override
    protected boolean matchesSafely(File file) {
        return file.isDirectory();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a directory");
    }

    @Override
    protected void describeMismatchSafely(File item, Description mismatchDescription) {
        mismatchDescription.appendValue(item);
        mismatchDescription.appendText(" is a file");
    }
}