package com.github.signed.matcher.file;

import org.hamcrest.Description;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class MismatchDescription<T> implements Answer<T> {
    private final String text;

    public MismatchDescription(String description) {
        this.text = description;
    }

    @Override
    public T answer(InvocationOnMock invocation) throws Throwable {
        Description missmatch = (Description)invocation.getArguments()[1];
        missmatch.appendText(text);
        return null;
    }
}
