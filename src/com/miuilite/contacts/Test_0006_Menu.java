package com.miuilite.contacts;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0006_Menu extends UiAutomatorTestCase{
	public Marmot mm;
	public Checker cc;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}

	public void test_0006_Menu() throws UiObjectNotFoundException {
		mm.waitFor(5);
		mm.log("Step 1 : Launch.");
		mm.pressHome();
		mm.clickOnText("联系人");
		found_FC("Launch Contacts");
		mm.waitFor(1);
		
		mm.log("Step 2 : MiCloud.");
		mm.pressMenu();
		mm.clickOnText("小米云服务");
		found_FC("Click MiCloud");
		mm.pressBack();
		
		mm.log("Step 3 : Settings");
		mm.pressMenu();
		mm.clickOnText("设置");
		found_FC("Click Setting");
		mm.move(360, 960, 360, 180);
		mm.clickOnText("来电铃声");
		mm.waitFor(1);
		mm.move(480, 360, 90, 360, 3);
		mm.pressBack();
		found_FC("Ringtone");
		
		mm.clickOnText("联系人整理");
		found_FC("Click to Organize Contacts");
		mm.clickOnText("合并重复联系人");
		mm.waitFor(5);
		mm.clickOnButton("合并");
		mm.waitFor(7);
		if(!mm.isTextExist("没有发现重复联系人"))
			mm.clickOnButton("合并");
		mm.waitFor(20);
		mm.clickOnButton("完成");
		found_FC("Merge Duplicate Contacts");
		
		mm.clickOnText("批量删除联系人");
		mm.clickOnText("全选");
		mm.clickOnText("全不选");
		mm.clickOnText("最近");
		mm.clickOnText("全选");
		mm.clickOnButton("删除");
		mm.clickOnButton("删除");
		found_FC("Delete Batch");
		
		mm.pressBack(3);
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
