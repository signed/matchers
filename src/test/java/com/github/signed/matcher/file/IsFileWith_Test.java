package com.github.signed.matcher.file;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static com.github.signed.matcher.file.IsFileWith.withName;
import static org.hamcrest.MatcherAssert.assertThat;

public class IsFileWith_Test {

    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();


    @Test
    public void combineTest2() throws Exception {
        assertThat(folder.newFile("number one"), withName("number one"));
    }
}
