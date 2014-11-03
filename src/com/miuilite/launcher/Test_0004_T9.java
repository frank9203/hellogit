package com.miuilite.launcher;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0004_T9 extends UiAutomatorTestCase{
	public Marmot mm;
	public Checker cc;
	
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0004_T9() throws UiObjectNotFoundException {
		mm.waitFor(5);
		
		mm.log("Step 1 : Launch T9.");
		mm.pressHome();
		mm.move(360, 700, 360, 300);
		mm.waitFor(1);
		found_FC("Launch");
		
		mm.log("Step 2 : Search MiCloud.");
		UiObject DEF = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
	      .getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
	      .getChild(new UiSelector().className("android.widget.ImageView").index(4));	
		UiObject MNO = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
	      .getChild(new UiSelector().className("android.widget.LinearLayout").index(1))
	      .getChild(new UiSelector().className("android.widget.LinearLayout").index(1))
	      .getChild(new UiSelector().className("android.widget.ImageView").index(4));
		UiObject WXYZ = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
	      .getChild(new UiSelector().className("android.widget.LinearLayout").index(2))
	      .getChild(new UiSelector().className("android.widget.ImageView").index(4));
		WXYZ.click();
		MNO.click();
		WXYZ.click();
		DEF.click();
		WXYZ.click();
		mm.saveScreenshot("Search");
		found_FC("T9_Search");
		
		mm.log("Step 3 : .");
		mm.clickOnText("小米云服务");
		mm.saveScreenshot("MiCloud");
		mm.pressBack();
		found_FC("Click MiCloud");
		mm.pressHome();
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
