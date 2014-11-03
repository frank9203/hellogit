package com.miuilite.phone;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0002_CallLog extends UiAutomatorTestCase{
	public Marmot mm;
	public Checker cc;
	
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0002_CallLog() throws UiObjectNotFoundException {
		mm.waitFor(5);
		
		mm.log("Step 1 : Launch Phone.");
		mm.pressHome();
		mm.clickOnText("拨号");
		mm.waitFor(2);
		
		mm.log("Step 2 : about.");
		UiObject detail = new UiObject(new UiSelector().className("android.widget.ImageView").index(0));
		detail.click();
		found_FC("click to about");
		if(mm.isTextExist("详情")){
			mm.clickOnText("通话记录");
			found_FC("click call log");
			mm.clickOnText("详情");
			mm.pressBack();
			}
		else{
			mm.clickOnButton("新建联系人");
			mm.clickOnText("新建联系人");
			mm.pressBack();
			mm.clickOnButton("确定");
			detail.click();
			mm.clickOnButton("新建联系人");
			mm.clickOnText("添加到联系人");
			mm.clickOnButton("取消");
		}
		found_FC("About Details");
		
		mm.log("Step 3 : QuickContactBadge.");
		UiObject badge = null;
		try {
			badge = new UiObject(new UiSelector().className("android.widget.RelativeLayout").index(0))
			  .getChild(new UiSelector().className("miuifx.miui.widget.QuickContactBadge").index(0));
			badge.click();
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			mm.pressMenu();
			mm.clickOnText("设置");
			UiObject HideData_CheckButton = new UiObject(new UiSelector().className("android.widget.LinearLayout").index(1))
			  .getChild(new UiSelector().className("android.widget.CheckBox").index(0));
			HideData_CheckButton.click();
			mm.pressBack();
			badge.click();
		}
		found_FC("Click QuickContactBadge");
		
		mm.pressBack(2);
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
