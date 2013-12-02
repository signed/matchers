package com.github.signed.matcher.file;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;

public class IsFileWith extends TypeSafeMatcher<File>{

    private final Matcher<String> nameMatcher;

    public static Matcher<File> withName(String name){
        return withName(is(name));
    }

    public static Matcher<File> withName(Matcher<String> nameMatcher) {
        return new IsFileWith(nameMatcher);
    }

    public static Matcher<File> isAFile(Matcher<File> file){
        return CoreMatchers.allOf(IsFile.isAFile(), file);
    }

    public IsFileWith(Matcher<String> nameMatcher) {
        this.nameMatcher = nameMatcher;
    }

    @Override
    protected boolean matchesSafely(File item) {
        return nameMatcher.matches(item.getName());
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("name ");
        nameMatcher.describeTo(description);
    }

    @Override
    protected void describeMismatchSafely(File item, Description mismatchDescription) {
        this.nameMatcher.describeMismatch(item.getName(), mismatchDescription);
    }
}
