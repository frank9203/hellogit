package com.miuilite.launcher;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0001_AppIconLayout extends UiAutomatorTestCase{
	public Marmot mm;
	public Checker cc;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}

	public void test_0001_AppIconLayout() throws UiObjectNotFoundException {
		mm.waitFor(5);
		mm.log("Step 1 : Launch Launcher.");
		mm.pressHome();
		mm.pressMenu();
		mm.clickOnText("小米系统设置");
		found_FC("MIUI ExpressSetting");
		mm.waitFor(1);
		mm.clickOnText("桌面");
		found_FC("Click Launcher");
		
		mm.log("Step 2 : Click AppIconLayout.");
		mm.clickOnText("选择桌面布局");
		found_FC("Click AppIconLayout");
		UiObject List_num = new UiObject(new UiSelector().className("android.widget.ListView").index(0));
		int AppIconLayout_num = List_num.getChildCount();
		String a = "" + AppIconLayout_num;
		mm.log(a);
		mm.clickOnButton("取消");
		mm.pressHome();
		
		switch(AppIconLayout_num){
		case 6:
			//5*6桌面布局
			mm.log("Step : Layout_6*6.");
			mm.pressMenu();
			mm.clickOnText("小米系统设置");
			mm.waitFor(1);
			mm.clickOnText("桌面");
			mm.clickOnText("选择桌面布局");
			if (mm.isTextExist("6列x6行")){
				UiObject Layout3 = new UiObject(new UiSelector().className("miuifx.miui.widget.CheckedTextView").index(5));
				Layout3.click();
				found_FC("Layout_6*6");
				mm.waitFor(15);
				mm.pressHome();
				mm.move(540, 360, 90, 360);
				mm.saveScreenshot("6*6.png");
			}
			mm.pressHome();
		case 5:
			//5*5桌面布局
			mm.log("Step : Layout_6*5.");
			mm.pressMenu();
			mm.clickOnText("小米系统设置");
			mm.waitFor(1);
			mm.clickOnText("桌面");
			mm.clickOnText("选择桌面布局");
			if (mm.isTextExist("6列x5行")){
				UiObject Layout2 = new UiObject(new UiSelector().className("miuifx.miui.widget.CheckedTextView").index(4));
				Layout2.click();
				found_FC("Layout_6*5");
				mm.waitFor(15);
				mm.pressHome();
				mm.move(540, 360, 90, 360);
				mm.saveScreenshot("6*5.png");
			}
			mm.pressHome();
		case 4:
			//5*6桌面布局
			mm.log("Step : Layout_5*6.");
			mm.pressMenu();
			mm.clickOnText("小米系统设置");
			mm.waitFor(1);
			mm.clickOnText("桌面");
			mm.clickOnText("选择桌面布局");
			if (mm.isTextExist("5列x6行")){
				UiObject Layout3 = new UiObject(new UiSelector().className("miuifx.miui.widget.CheckedTextView").index(3));
				Layout3.click();
				found_FC("Layout_5*6");
				mm.waitFor(15);
				mm.pressHome();
				mm.move(540, 360, 90, 360);
				mm.saveScreenshot("5*6.png");
			}
			mm.pressHome();
		case 3:
			//5*5桌面布局
			mm.log("Step : Layout_5*5.");
			mm.pressMenu();
			mm.clickOnText("小米系统设置");
			mm.waitFor(1);
			mm.clickOnText("桌面");
			mm.clickOnText("选择桌面布局");
			if (mm.isTextExist("5列x5行")){
				UiObject Layout2 = new UiObject(new UiSelector().className("miuifx.miui.widget.CheckedTextView").index(2));
				Layout2.click();
				found_FC("Layout_5*5");
				mm.waitFor(15);
				mm.pressHome();
				mm.move(540, 360, 90, 360);
				mm.saveScreenshot("5*5.png");
			}
			mm.pressHome();
		case 2:
			mm.log("Step : Layout_4*4.");
			mm.pressMenu();
			mm.clickOnText("小米系统设置");
			mm.waitFor(1);
			mm.clickOnText("桌面");
			mm.clickOnText("选择桌面布局");
			UiObject Layout0 = new UiObject(new UiSelector().className("miuifx.miui.widget.CheckedTextView").index(0));
			Layout0.click();
			found_FC("Layout_4*4");
			mm.waitFor(15);
			mm.pressHome();
			mm.move(540, 360, 90, 360);
			mm.saveScreenshot("4*4.png");
			mm.pressHome();
		default:
			mm.log("Step : Layout_4*5.");
			mm.pressMenu();
			mm.clickOnText("小米系统设置");
			mm.waitFor(1);
			mm.clickOnText("桌面");
			mm.clickOnText("选择桌面布局");
			UiObject Layout1= new UiObject(new UiSelector().className("miuifx.miui.widget.CheckedTextView").index(1));
			Layout1.click();
			found_FC("Layout_4*5");
			mm.waitFor(15);
			mm.pressHome();
			mm.move(540, 360, 90, 360);
			mm.saveScreenshot("4*5.png");
			mm.pressHome();
		}
		found_FC("AppIconLayout");
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
