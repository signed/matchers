package com.github.signed.matcher.file;

import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class IsFile_Test {

    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();
    private final IsFile isFile = new IsFile();
    private File notExistingFile;

    @Before
    public void setUp() throws Exception {
        notExistingFile = new File(folder.getRoot(), "not-existing");
    }

    @Test
    public void mismatchOnNotExistingFile() throws Exception {
        assertThat(isFile.matchesSafely(notExistingFile), is(false));
    }

    @Test
    public void matchOnExistingFile() throws Exception {
        File existingFile = folder.newFile();
        assertThat(isFile.matchesSafely(existingFile), is(true));
    }

    @Test
    public void describeYourselfAppropriately() throws Exception {
        Description description = mock(Description.class);
        isFile.describeTo(description);

        verify(description).appendText(" a file");
    }

    @Test
    public void describeNotExistingFile() throws Exception {
        Description description = mock(Description.class);
        isFile.describeMismatch(notExistingFile, description);

        verify(description).appendValue(notExistingFile);
        verify(description).appendText(" does not exist");
    }

    @Test
    public void describeIsDirectory() throws Exception {
        Description description = mock(Description.class);
        File file = folder.newFolder();
        isFile.describeMismatch(file, description);

        verify(description).appendText(" is a directory");
    }
}