package com.miuilite.phone;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0003_AntiSpam extends UiAutomatorTestCase{
	public Marmot mm;
	public Checker cc;
	
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0003_AntiSpam() throws UiObjectNotFoundException {
		mm.waitFor(5);
		
		mm.log("Step 1 : Launch Phone.");
		mm.pressHome();
		mm.clickOnText("拨号");
		mm.pressMenu();
		mm.clickOnText("骚扰拦截");
		found_FC("Launch AntiSpam");
		
		mm.log("Step 2 : Blocked Log.");
		mm.clickOnText("来电");
		mm.clickOnText("短信");
		found_FC("Tab");
		
		mm.log("Step 3 : Blocklist.");
		mm.clickOnText("黑名单");
		found_FC("click to Blocklist");
		mm.clickOnButton("输入号码");
		mm.clickOnButton("取消");
		mm.clickOnButton("联系人");
		mm.pressBack();
		found_FC("Edit Blocklist");
		
		mm.log("Step 4 : Setting.");
		UiObject Setting_Button =new UiObject(new UiSelector().className("android.widget.Button").index(2));
		Setting_Button.click();
		mm.clickOnText("设置短信过滤关键词");
		mm.clickOnButton("添加");
		UiObject FilterKeywords_Text =new UiObject(new UiSelector().className("android.widget.EditText").index(0));
		FilterKeywords_Text.setText("TextText");
		mm.clickOnButton("保存");
		found_FC("Setting");
		
		mm.pressBack(4);
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
