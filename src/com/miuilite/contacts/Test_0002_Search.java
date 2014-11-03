package com.miuilite.contacts;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0002_Search extends UiAutomatorTestCase{
	public Marmot mm;
	public Checker cc;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}

	public void test_0002_Search() throws UiObjectNotFoundException {
		mm.waitFor(5);
		if(!mm.isScreenOn()){
			mm.wakeUp();
			mm.move(360, 720, 360, 5);
		}
		mm.log("Step 1 : Launch.");
		mm.pressHome();
		mm.clickOnText("联系人");
		found_FC("Launch Contacts");
		mm.waitFor(1);
		
		mm.log("Step 2 : Search.");
		UiObject Search = new UiObject(new UiSelector().textStartsWith("搜索"));
		Search.setText("183");
		found_FC("search");
		if(!mm.isTextExist("没有找到联系人")){
			UiObject contView_183 = new UiObject(new UiSelector().className("android.view.View").index(1));
			contView_183.clickAndWaitForNewWindow();
			mm.pressBack();
		}
		UiObject Back = new UiObject(new UiSelector().className("android.widget.ImageView").index(0));
		Back.click();
		found_FC("ClickBack");
		
		Search.setText("cs");
		if(!mm.isTextExist("没有找到联系人")){
			UiObject contView_cs = new UiObject(new UiSelector().className("android.view.View").index(1));
			contView_cs.clickAndWaitForNewWindow();
			mm.pressBack();
		}
		
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
