package com.miuilite.mms;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0006_NotificationMessages extends UiAutomatorTestCase{
	public Marmot mm;
	public Checker cc;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}

	public void test_0006_NotificationMessages() throws UiObjectNotFoundException {
		mm.waitFor(5);
		
		mm.log("Step 1 : Launch.");
		mm.pressHome();
		mm.clickOnText("短信");
		found_FC("Launch Messaging");
		mm.waitFor(1);
		if(mm.isTextExist("免费短信"))
			mm.clickOnText("立即启用");
		
		mm.log("Step 2 : Notification Messages.");
		if(!mm.isTextExist("通知类短信"))
		{
			mm.clickOnButton("写短信");
			UiObject Receiver = new UiObject(new UiSelector().className("basefx.android.widget.EditText").index(0));
			Receiver.setText("10086");
			UiObject Text = new UiObject(new UiSelector().text("短信"));
			Text.setText("hello test");
			found_FC("Creat a NotificationMessage");
			mm.pressHome();
			mm.clickOnText("短信");
		}
		mm.clickOnText("通知类短信");
		found_FC("Click to NotificationMessages");
		UiObject Readed = new UiObject(new UiSelector().className("android.widget.ImageView").index(2));
		Readed.click();
		mm.clickOnButton("确定");
		found_FC("Readed");
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
