package com.github.signed.matcher.file;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class IsDirectoryContaining extends TypeSafeMatcher<File> {
        public static Matcher<File> directoryContaining(final Matcher<Iterable< ? super File>> fileMatcher){
            return new IsDirectoryContaining(fileMatcher);
        }

        private final Matcher<Iterable<? super File>> fileMatcher;
        private Matcher<File> directoryMatcher = IsADirectory.aDirectory();

        private Matcher<File> mismatch;

        public IsDirectoryContaining(Matcher<Iterable<? super File>> fileMatcher) {
            this.fileMatcher = fileMatcher;
        }

        @Override
        protected boolean matchesSafely(File item) {
            if(directoryMatcher.matches(item)){
                return fileMatcher.matches(files(item));
            }else{
                mismatch = directoryMatcher;
                return false;
            }
        }

        @Override
        public void describeTo(Description description) {
            directoryMatcher.describeTo(description);
            description.appendText(" containing ").appendDescriptionOf(this.fileMatcher);

        }

        @Override
        protected void describeMismatchSafely(File item, Description mismatchDescription) {
            if(null != this.mismatch){
                this.mismatch.describeMismatch(item, mismatchDescription);
            }else {
                mismatchDescription.appendValue(item).appendText( " contained\n");
                List<File> files = files(item);
                for (File file : files) {
                    mismatchDescription.appendValue(file.getName()).appendText("\n");
                }
            }
        }

        private List<File> files(File item) {
            File[] files = item.listFiles();
            if( null == files){
                return Collections.emptyList();
            }else {
                return Arrays.asList(files);
            }
        }
    }