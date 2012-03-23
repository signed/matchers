package com.github.signed.matcher.file;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mockito;

import java.io.File;

import static com.github.signed.matcher.file.FileMatcherMother.mismatch;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doAnswer;

public class IsEmptyDirectory_OnARegularFileTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    private File aFile;

    @Before
    public void createARegularFile() throws Exception {
        aFile = folder.newFile("filename.txt");
    }

    @Test
    public void doesNotMatchAFile() throws Exception {
        assertThat(matcherAppliedToAFile(), is(false));
    }

    @Test
    public void writeFileName() throws Exception {
        assertThat(mismatchDescription(), is("mismatch description from IsADirectory"));
    }

    private String mismatchDescription() {
        Matcher<File> isADirectory = mismatch();
        doAnswer(new MismatchDescription<File>("mismatch description from IsADirectory")).when(isADirectory).describeMismatch(Mockito.any(), Mockito.isA(Description.class));
        Description description = new StringDescription();
        createMatcherUnderTest(isADirectory).describeMismatch(aFile, description);
        return description.toString();
    }

    private boolean matcherAppliedToAFile() {
        return createMatcherUnderTest(mismatch()).matches(aFile);
    }

    private IsEmptyDirectory createMatcherUnderTest(Matcher<File> isADirectory) {
        return new IsEmptyDirectory(isADirectory);
    }

}
