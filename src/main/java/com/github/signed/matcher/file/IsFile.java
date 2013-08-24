package com.github.signed.matcher.file;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.io.File;

public class IsFile extends TypeSafeMatcher<File> {

    @Factory
    public static Matcher<File> isAFile(){
        return new IsFile();
    }

    @Override
    protected boolean matchesSafely(File item) {
        return item.isFile();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(" a file");
    }

    @Override
    protected void describeMismatchSafely(File item, Description mismatchDescription) {
        mismatchDescription.appendValue(item);
        if (item.isDirectory()) {
            mismatchDescription.appendText(" is a directory");
        } else {
            mismatchDescription.appendText(" does not exist");
        }
    }
}