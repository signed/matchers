package com.github.signed.matcher.file;

import org.hamcrest.StringDescription;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class IsEmptyDirectory_OnDirectoryWithContainingTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    private File emptyDirectory;

    @Before
    public void getTheDirectory() throws Exception {
        emptyDirectory = folder.getRoot();
    }

    @Test
    public void matchesAnEmptyDirectory() throws Exception {
        assertThat(matcherAppliedTo(emptyDirectory), is(true));
    }

    @Test
    public void reportAMismatchIfItContainsAFile() throws Exception {
        folder.newFile();
        assertThat(matcherAppliedTo(emptyDirectory), is(false));
    }

    @Test
    public void anotherDirectoryReportMismatch() throws Exception {
        folder.newFolder();
        assertThat(matcherAppliedTo(emptyDirectory), is(false));
    }

    @Test
    public void anotherDirectoryAppendSlashToDirectoryInMismatchDescription() throws Exception {
        folder.newFolder("folder");
        StringDescription description = new StringDescription();
        new IsEmptyDirectory(FileMatcherMother.match()).describeMismatch(emptyDirectory, description);
        assertThat(description.toString(), containsString("folder/"));
    }

    @Test
    public void listDirectoryInMissmatch() throws Exception {
        folder.newFile("file.txt");
        StringDescription description = new StringDescription();
        new IsEmptyDirectory(FileMatcherMother.match()).describeMismatch(emptyDirectory, description);
        assertThat(description.toString(), containsString("file.txt"));
    }

    private boolean matcherAppliedTo(File aFile) {
        return new IsEmptyDirectory(FileMatcherMother.match()).matches(aFile);
    }
}
