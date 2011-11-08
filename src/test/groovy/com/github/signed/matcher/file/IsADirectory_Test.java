package com.github.signed.matcher.file;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

import static com.github.signed.matcher.file.IsADirectory.aDirectory;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class IsADirectory_Test {

    private File aFile;
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void createAFile() throws Exception {
        aFile = folder.newFile();
    }

    @Test
    public void someTest() throws Exception {
        assertThat(folder.getRoot(), is(aDirectory()));
    }

    @Test
    public void returnFalseForAFile() throws Exception {
        assertThat(aFile, is(not(aDirectory())));
    }

    @Test
    public void describesMismatch() throws Exception {
        Matcher<File> matcher = aDirectory();
        Description description = mock(Description.class);
        matcher.describeMismatch(aFile, description);
        verify(description).appendValue(aFile);
        verify(description).appendText(" is a file");
    }

}