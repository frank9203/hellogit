package com.miui.shell;

import android.R.integer;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0002_ThemeDefault extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}


public void test_0002_ThemeDefault() throws UiObjectNotFoundException{
	mm.log("Step 1 : Connect with WLAN.");
	mm.pressHome();
	cc.assertTrue("Connect with WLAN", connectWLAN("Public", "miguest#1"));
	mm.pressBack(3);
	
	mm.log("Step 2 : lanuch theme.");
	mm.clickOnText("主题风格");
	mm.waitFor(3);
	
	mm.log("Step 3 : check default page");
	cc.assertTrue("CheckDefault", CheckDefaultPage("android.app.ActionBar$Tab", 0));
	
	mm.log("Step 4 : Quit from theme app.");
	mm.pressBack(3);
}

private boolean connectWLAN(String wlanName, String password) throws UiObjectNotFoundException{
	boolean checkSelected = false;
	
	mm.launchActivity("com.android.settings/.MiuiSettings");
	mm.clickOnText("WLAN");
	
	if(!mm.isChecked(0)){
		mm.clickOnCheckBox(0);
		mm.waitFor(3);
	}
	if(!mm.isTextExist("已连接")){
		UiScrollable collectionObject  = new UiScrollable(new UiSelector().scrollable(true));
		if(collectionObject.exists()){
			UiObject scrollableObject = collectionObject.getChildByText(new UiSelector().className("android.widget.CheckedTextView"), wlanName);
			scrollableObject.click();
		}
		else{
			mm.clickOnText(wlanName);
		}
		mm.waitFor(2);
		mm.pressBack();
		mm.shell("input text " + password);
		mm.waitFor(6);
		if(mm.getObjectByText("连接", "android.widget.Button").isEnabled()){
			mm.getObjectByText("连接", "android.widget.Button").click();
		}
		mm.waitFor(15);
	}
	if(mm.isTextExist("已连接")){
		checkSelected = true;
	}
	return checkSelected;
}

private boolean CheckDefaultPage(String TabClass, int i) throws UiObjectNotFoundException {
	boolean DefaultPage = false;
	if (mm.getObjectByClass(TabClass, i).isSelected() == true) {
		DefaultPage = true;
	}
	
	return DefaultPage;
}
@Override
protected void tearDown() throws Exception {
	mm.finish();
	super.tearDown();
}

}