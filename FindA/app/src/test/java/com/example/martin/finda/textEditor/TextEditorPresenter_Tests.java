package com.example.martin.finda.textEditor;

import com.example.martin.finda.base.BaseContracts;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mock;

/**
 * Created by Martin on 12.10.2017 г..
 */

public class TextEditorPresenter_Tests {
    @Mock
    TextEditorActivity activity;
    @Mock
    BaseContracts.View view;

    @Test
    public void ConstructorShould_CreateInstance() {
        // Arrange and Act
        TextEditorPresenter presenter = new TextEditorPresenter(activity);

        // Assert
        Assert.assertNotNull(presenter);
    }

    @Test
    public void SubscribeShould_AddsView() {
        // Arrange
        TextEditorPresenter presenter = new TextEditorPresenter(activity);
        // Act
        presenter.subscribe(view);
        // Assert
        Assert.assertSame(presenter.mView, view);
    }

    @Test
    public void UnSubscribeShould_RemovesView() {
        // Arrange
        TextEditorPresenter presenter = new TextEditorPresenter(activity);
        // Act
        presenter.subscribe(view);
        presenter.unsubscribe();
        // Assert
        Assert.assertEquals(presenter.mView, null);
    }
}
