package com.github.signed.matcher.file;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

import static com.github.signed.matcher.file.FileMatcherMother.match;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class IsEmptyDirectory_OnEmptyDirectoryTest {

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

    private boolean matcherAppliedTo(File aFile) {
        return new IsEmptyDirectory(match()).matches(aFile);
    }
}
