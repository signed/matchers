package com.github.signed.matcher.file;

import org.hamcrest.Matcher;
import org.mockito.Matchers;

import java.io.File;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class FileMatcherMother {
    
    public static Matcher<File> mismatch() {
        return createMock(Boolean.FALSE);
    }

    public static Matcher<File> match() {
        return createMock(Boolean.TRUE);
    }

    public static interface FileMatcher extends Matcher<File>{

    }

    private static Matcher<File> createMock(Boolean matches) {
        FileMatcher mock = mock(FileMatcher.class);
        doReturn(matches).when(mock).matches(Matchers.isA(File.class));
        return mock;
    }
}
