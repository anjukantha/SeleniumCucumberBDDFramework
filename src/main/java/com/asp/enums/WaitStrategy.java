package com.asp.enums;

/**
 * Enums to restrict the users to choose an appropriate waiting strategy before
 * operating an element.
 * 
 * @author Anjan S P
 * @see com.asp.factories.ExplicitWaitFactory
 * @see com.asp.pages.BasePage
 */
public enum WaitStrategy {
	CLICKABLE, VISIBILITY, INVISIBILITY, PAGELOAD, STALENESS, TEXTPRESENTINELEMENT, TEXTPRESENTINELEMENTVALUE;
}
