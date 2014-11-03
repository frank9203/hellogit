package com.miuilite.phone;

import java.io.IOException;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0004_Menu extends UiAutomatorTestCase{
	public Marmot mm;
	public Checker cc;
	
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0004_Menu() throws UiObjectNotFoundException {
		mm.waitFor(5);
		
		mm.log("Step 1 : Filter.");
		mm.pressHome();
		mm.clickOnText("拨号");
		mm.pressMenu();
		mm.clickOnText("显示过滤");
		mm.getObjectByText("未接来电", "miuifx.miui.widget.CheckedTextView").click();
		found_FC("Filter");
		
		mm.log("Step 2 : Delete Batch.");
		mm.pressMenu();
		if(mm.isTextExist("删除未接来电记录"))
		{
			mm.clickOnText("删除未接来电记录");
			mm.clickOnText("全选");
			mm.clickOnButton("删除");
			mm.clickOnButton("删除");
		}
		else
			mm.pressBack();
		found_FC("Delete Batch");
		
		mm.log("Step 3 : AntiSpam.");
		mm.pressMenu();
		mm.clickOnText("骚扰拦截");
		mm.pressBack();
		found_FC("AntiSpam");
		
		mm.log("Step 4 : Settings.");
		mm.pressMenu();
		mm.clickOnText("设置");
		found_FC("Setting");

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
