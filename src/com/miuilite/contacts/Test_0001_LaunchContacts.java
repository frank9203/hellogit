package com.miuilite.contacts;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0001_LaunchContacts extends UiAutomatorTestCase{
	public Marmot mm;
	public Checker cc;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0001_LaunchContacts() throws UiObjectNotFoundException {
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
		
		mm.log("Step 2 : slide." );
		mm.move(360, 540, 360, 280, 10);
		found_FC("slide");

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
