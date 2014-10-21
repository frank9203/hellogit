package com.miliao.common;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.miui.marmot.lib.Marmot;

public class Common {

	public Common() {
	}
	
	public void clickLink(Marmot mm) throws UiObjectNotFoundException {
		UiObject link = new UiObject(new UiSelector().textContains("http:"));
		link.click();
		mm.waitFor(5);
	}
	
	public void goBackToMiliaoHomePage(Marmot mm) {
		int count = 0;
		while(count<10 && !new UiObject(new UiSelector().text("圈子")).exists()){
			mm.pressBack();
		}
	}
	
	public void atXiaoBing(Marmot mm) throws UiObjectNotFoundException {
		mm.waitFor(1);
		mm.clickOnText("小小(小冰)");
		mm.waitFor(1);
		mm.clickOnText("确定");
		mm.waitFor(1);
	}

}
