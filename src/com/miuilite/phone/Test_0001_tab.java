package com.miuilite.phone;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0001_tab extends UiAutomatorTestCase{
	public Marmot mm;
	public Checker cc;
	
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0001_tab() throws UiObjectNotFoundException {
		mm.waitFor(5);
		mm.log("Step 1 : Launch Phone.");
		mm.pressHome();
		mm.clickOnText("拨号");
		mm.waitFor(2);
		mm.saveScreenshot("Phone");
		found_FC("Launch Phone");
		
		mm.log("Step 2 : Slide to Contacts.");
		mm.move(540, 360, 90, 360);
		mm.saveScreenshot("Contacts");
		mm.move(360, 1000, 360, 128);
		found_FC("Slide");
		
		mm.log("Step 3 : Slide to Directory.");
		mm.move(540, 360, 90, 360);
		mm.saveScreenshot("Directory");
		found_FC("Slide to Directory");
		
		mm.log("Step 4 : click to Phone.");
		mm.clickOnText("拨号");
		mm.clickOnButton("拨号");
		mm.getObjectByDescription("关闭拨号键盘").click();
		found_FC("Close T9");
		mm.pressBack();
	}
	
	public void found_FC(String str) throws UiObjectNotFoundException{
		mm.waitFor(2);
		if (mm.getObjectByText("很抱歉，“小米系统”已停止运行。") != null){
			mm.log(str+" FC.");
			mm.clickOnButton("确定");
		}
	}
	
	@Override
	protected void tearDown() throws Exception {
		mm.finish();
		super.tearDown();
	}

}
