package com.miuilite.mms;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0003_MmsDetails extends UiAutomatorTestCase{
	public Marmot mm;
	public Checker cc;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}

	public void test_0003_MmsDetails() throws UiObjectNotFoundException {
		mm.waitFor(5);
		
		mm.log("Step 1 : Launch.");
		mm.pressHome();
		mm.clickOnText("短信");
		found_FC("Launch Messaging");
		mm.waitFor(1);
		
		mm.log("Step 2 : Click to MmsDetails.");
		mm.move(360, 300, 360, 720);
		UiObject LastMms = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
			.getChild(new UiSelector().className("android.view.View").index(1));
		LastMms.click();
		found_FC("Click to MmsDetails");
		if(mm.isTextExist("通知类短信"))
		{
			UiObject LastMms0 = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
				.getChild(new UiSelector().className("android.view.View").index(0));
			LastMms0.click();
			found_FC("Click to Notification Message Details");
		}

		mm.saveScreenshot("MmsDetails");
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
