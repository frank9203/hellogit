package com.miuilite.mms;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0004_Menu extends UiAutomatorTestCase{
	public Marmot mm;
	public Checker cc;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}

	public void test_0004_Menu() throws UiObjectNotFoundException {
		mm.waitFor(5);
		
		mm.log("Step 1 : Launch.");
		mm.pressHome();
		mm.clickOnText("短信");
		found_FC("Launch Messaging");
		mm.waitFor(1);
		
		mm.log("Step 2 : AntiSpam.");
		mm.pressMenu();
		mm.clickOnText("骚扰拦截");
		found_FC("Click to AntiSpam");
		mm.pressBack();

		mm.log("Step 3 : Edit Messages.");
		mm.pressMenu();
		mm.clickOnText("编辑模式");
		found_FC("Click to EditMessages");
		if(mm.isTextExist("加载原有短信"))
			mm.pressBack();
		else
		{
			mm.clickOnText("全选");
			mm.clickOnText("全不选");
			mm.clickOnText("全选");
			mm.clickOnButton("置顶");
			mm.saveScreenshot("置顶");
			mm.pressMenu();
			mm.clickOnText("编辑模式");
			mm.clickOnText("全选");
			mm.clickOnButton("取消置顶");
			mm.saveScreenshot("取消置顶");
		}
		found_FC("Edit");
		
		mm.log("Step 3 : Settings.");
		mm.pressMenu();
		mm.clickOnText("设置");
		found_FC("Click to Settings");
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
