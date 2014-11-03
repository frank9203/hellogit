package com.miuilite.launcher;

import com.android.uiautomator.core.UiObjectNotFoundException;
//import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0003_widget extends UiAutomatorTestCase{
	public Marmot mm;
	public Checker cc;
	
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0003_widget() throws UiObjectNotFoundException {
		mm.waitFor(5);		
		mm.log("Step 1 : Launch Gallery Activity.");
		mm.pressHome(2);
		mm.pressMenu();
		mm.clickOnText("编辑模式");
		mm.waitFor(1);
		found_FC("Launch");
		
		mm.log("Step 2 : Slide Left.");
		mm.move(100, 400, 400, 400);
		mm.waitFor(1);
		found_FC("slide");
		mm.move(100, 400, 400, 400);
		mm.waitFor(1);
		
		mm.log("Step 3 : Add widgets");
		mm.clickOnText("时钟天气4x2");
		mm.clickOnText("文件夹");
		mm.clickOnText("桌面工具箱");
		mm.clickOnText("一键锁屏");
		mm.pressBack(2);
		found_FC("Add Widgets");
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
