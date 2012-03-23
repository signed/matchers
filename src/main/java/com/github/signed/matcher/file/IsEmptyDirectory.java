package com.github.signed.matcher.file;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.io.File;

import static com.github.signed.matcher.file.IsADirectory.aDirectory;

public class IsEmptyDirectory extends TypeSafeMatcher<File> {
    private Matcher<File> isADirectory;

    @Factory
    public static Matcher<File> isAnEmptyDirectory() {
        return new IsEmptyDirectory(aDirectory());
    }

    public IsEmptyDirectory(Matcher<File> isADirectory) {
        this.isADirectory = isADirectory;
    }

    @Override
    protected boolean matchesSafely(File item) {
        boolean isADirectory = this.isADirectory.matches(item);
        return isADirectory && item.list().length == 0;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("an empty directory");
    }

    @Override
    protected void describeMismatchSafely(File directory, Description mismatchDescription) {
        if (!isADirectory.matches(directory)) {
            isADirectory.describeMismatch(directory, mismatchDescription);
        } else {
            mismatchDescription.appendValue(directory).appendText( " contains");
            appendFilesInDirectoryTo(mismatchDescription, directory);
        }
    }

    private void appendFilesInDirectoryTo(Description mismatchDescription, File item) {
        File[] files = item.listFiles();
        for (File file : files) {
            appendFileToDescription(mismatchDescription, file);
        }
    }

    private void appendFileToDescription(Description mismatchDescription, File file) {
        mismatchDescription.appendText("\n          |");
        mismatchDescription.appendText(file.getName());
        if(file.isDirectory()) {
            mismatchDescription.appendText("/");
        }
    }
}
