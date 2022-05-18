package com.example.borutoapp.presentation.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class SearchWidgetTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun openSearchWidget_addInputText_assertInputText() {
        var text by mutableStateOf("")
        composeTestRule.setContent {
            SearchWidget(
                text = text,
                onTextChange = {
                    text = it
                },
                onSearchClicked = {},
                onCloseClicked = {}
            )
        }
        composeTestRule.onNodeWithContentDescription("TextField")
            .performTextInput("echolocation19")
        composeTestRule.onNodeWithContentDescription("TextField")
            .assertTextEquals("echolocation19")
    }

    @Test
    fun openSearchWidget_addInputText_pressCloseButtonTwice_assertClosedState() {
        var text by mutableStateOf("")
        composeTestRule.setContent {
            SearchWidget(
                text = text,
                onTextChange = {
                    text = it
                },
                onSearchClicked = {},
                onCloseClicked = {}
            )
        }
        composeTestRule.onNodeWithContentDescription("TextField")
            .performTextInput("echolocation19")
        composeTestRule.onNodeWithContentDescription("CloseButton")
            .performClick()
        composeTestRule.onNodeWithContentDescription("CloseButton")
            .performClick()
        composeTestRule.onNodeWithContentDescription("SearchWidget")
            .assertDoesNotExist()
    }

    @Test
    fun openSearchWidget_addInputText_pressCloseButton_assertEmptyInputText() {
        var text by mutableStateOf("")
        val searchWidgetShown = mutableStateOf(true)
        composeTestRule.setContent {
            if (searchWidgetShown.value) {
                SearchWidget(
                    text = text,
                    onTextChange = {
                        text = it
                    },
                    onSearchClicked = {},
                    onCloseClicked = {}
                )
            }
        }
        composeTestRule.onNodeWithContentDescription("TextField")
            .performTextInput("echolocation19")
        composeTestRule.onNodeWithContentDescription("CloseButton")
            .performClick()
        composeTestRule.onNodeWithContentDescription("TextField")
            .assertTextContains("")
    }

    @Test
    fun openSearchWidget_pressCloseButtonOnceWhenInputIsEmpty_assertClosedState() {
        var text by mutableStateOf("")
        val searchWidgetShown = mutableStateOf(true)
        composeTestRule.setContent {
            if (searchWidgetShown.value) {
                SearchWidget(
                    text = text,
                    onTextChange = {
                        text = it
                    },
                    onSearchClicked = {},
                    onCloseClicked = {}
                )
            }
        }
        composeTestRule.onNodeWithContentDescription("CloseButton")
            .performClick()
        composeTestRule.onNodeWithContentDescription("TextField")
            .assertTextContains("")
    }

}