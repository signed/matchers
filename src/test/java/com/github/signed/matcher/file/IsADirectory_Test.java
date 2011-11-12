package com.github.signed.matcher.file;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static com.github.signed.matcher.file.IsADirectory.aDirectory;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class IsADirectory_Test {

    private File aFile;
    private File aNotExistingFile;
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void createAFile() throws Exception {
        aFile = folder.newFile();
    }

    @Before
    public void fabricateANotExistingDirectory() throws IOException {
        aNotExistingFile = folder.newFile();
        aNotExistingFile.delete();
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
    public void describeThatThePathIsAFile() throws Exception {
        assertThatReasonIsGivenFor(aFile, " is a file");
    }

    @Test
    public void describeThatThePathDoesNotExist() throws Exception {
        assertThatReasonIsGivenFor(aNotExistingFile, " does not exist");
    }

    private void assertThatReasonIsGivenFor(File theFile, String reason) {
        Matcher<File> matcher = aDirectory();
        Description description = mock(Description.class);
        matcher.describeMismatch(theFile, description);
        verify(description).appendValue(theFile);
        verify(description).appendText(reason);
    }
}