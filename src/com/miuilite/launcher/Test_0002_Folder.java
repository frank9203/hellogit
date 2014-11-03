package com.miuilite.launcher;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0002_Folder extends UiAutomatorTestCase{
	public Marmot mm;
	public Checker cc;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}

	public void test_0002_Folder() throws UiObjectNotFoundException {
		mm.waitFor(5);
		mm.log("Step 1 : Slide Right.");
		mm.pressHome();
		mm.move(480, 360, 90, 360);
		found_FC("slide");
		
		mm.log("Step 2 : Click SystemTools.");
		mm.clickOnText("系统工具");
		found_FC("open folder");
		mm.waitFor(1);
		mm.pressBack();
		found_FC("Open SystemTools");
		
		mm.log("Step 3 : Click Tools");
		mm.clickOnText("小工具");
		mm.clickOnText("文件管理");
		mm.move(360, 680, 360, 300);
		mm.pressBack();
		found_FC("Open a App");
		mm.pressHome();
		
		//仅适用于4.3/4.4的手机，暂时删去
//		mm.log("Step 4 : Drag into Folder");
//		UiObject SysTools = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1));
//		UiObject app = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(5));
//		app.dragTo(SysTools, 40);
//	
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
