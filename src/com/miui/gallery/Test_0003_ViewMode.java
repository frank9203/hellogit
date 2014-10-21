package com.miui.gallery;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0003_ViewMode extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_View_Mode() throws UiObjectNotFoundException {
		if(!mm.isScreenOn()){
			mm.wakeUp();
			mm.move(360, 1000, 360, 1280);
			mm.pressHome();
		}
		
		mm.log("Step 1 : Launch Gallery Activity.");
		mm.pressHome();
		mm.launchActivity("com.miui.gallery/.app.Gallery");
		mm.waitFor(1);
		found_FC("Launch");
		mm.waitFor(1);
		
		mm.log("Step 2 : Click in the local photo list.");
		mm.clickOnText("本地图片");
		found_FC("进入本地图册列表页");
		mm.waitFor(1);
		
		mm.log("Step 3 : View mode.");
		UiObject obj = new UiObject(new UiSelector().className("android.widget.TableRow").index(0))
	      .getChild(new UiSelector().className("android.widget.ImageView").index(2));
		obj.click();
		found_FC("viewmode");
		mm.clickOnText("DCIM");
		found_FC("viewmode");
		mm.waitFor(1);
		
		mm.log("Step 4 : Edit mode");
		mm.getObjectByText("100ANDRO", "android.widget.TextView").longClick();
		mm.clickOnCheckBox(1);
		mm.clickOnCheckBox(2);
		mm.clickOnText("全选");
		mm.clickOnText("全不选");
		found_FC("编辑模式选择");
		mm.pressBack();
		mm.waitFor(1);
		
		mm.log("Step 5 : Press back");
		UiObject back = new UiObject(new UiSelector().className("android.widget.TableRow").index(0))
		      .getChild(new UiSelector().className("android.widget.ImageView").index(0));
		back.click();
		found_FC("列表返回");
		mm.waitFor(1);
		
		mm.log("Step 6 : End.");
		mm.pressBack();
		
	}
	
	public void found_FC(String str) throws UiObjectNotFoundException{
		mm.waitFor(2);
		if (mm.getObjectByText("报告 MIUI") != null){
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
