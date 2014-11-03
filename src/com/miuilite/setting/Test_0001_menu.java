package com.miuilite.setting;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0001_menu extends UiAutomatorTestCase{
	public Marmot mm;
	public Checker cc;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}

	public void test_0001_menu() throws UiObjectNotFoundException {
		mm.waitFor(5);
		if(!mm.isScreenOn()){
			mm.wakeUp();
			mm.move(360, 720, 360, 5);
		}
		
		mm.log("Step 1 : Click EditHomeScreen.");
		mm.pressHome();
		mm.pressMenu();
		mm.clickOnText("编辑模式");
		mm.waitFor(2);
		found_FC("EditHomeScreen");
		mm.pressBack();
		
		mm.log("Step 2 : Click Wallpaper.");
		mm.pressMenu();
		mm.clickOnText("修改壁纸");
		mm.waitFor(3);
		found_FC("Wallpaper");
		mm.pressBack();
		
		mm.log("Step 3 : Click Theme.");
		mm.pressMenu();
		mm.clickOnText("主题美化");
		mm.waitFor(3);
		found_FC("Theme");
		mm.pressBack();
					
		mm.log("Step 4 : Click Font.");
		mm.pressMenu();
		mm.clickOnText("修改字体");
		mm.waitFor(3);
		found_FC("Font");
		mm.pressBack();
		
		mm.log("Step 5 : Click PhoneSettings.");
		mm.pressMenu();
		mm.clickOnText("手机设置");
		mm.waitFor(2);
		found_FC("PhoneSettings");
		mm.pressBack();
		
		mm.log("Step 5 : Click MIUI ExpressSetting.");
		mm.pressMenu();
		mm.clickOnText("小米系统设置");
		mm.waitFor(2);
		found_FC("MIUI ExpressSetting");
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
