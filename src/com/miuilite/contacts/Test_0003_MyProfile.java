package com.miuilite.contacts;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0003_MyProfile extends UiAutomatorTestCase{
	public Marmot mm;
	public Checker cc;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();

	}

	public void test_0003_MyProfile() throws UiObjectNotFoundException {
		mm.waitFor(5);
		mm.log("Step 1 : Launch.");
		mm.pressHome();
		mm.clickOnText("联系人");
		found_FC("Launch Contacts");
		mm.waitFor(1);
		
		mm.log("Step 2 : Set up my profile." );
		UiObject SetPro = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.ListView").index(0))
		.getChild(new UiSelector().className("android.widget.FrameLayout").index(0));
		SetPro.click();
		found_FC("click to MyProfile");
		mm.clickOnButton("取消");
		
		mm.log("Step 3 : My groups." );
		mm.getObjectByText("我的群组", "android.widget.TextView").click();
		found_FC("click to Mygroups");
		mm.clickOnButton("新建群组");
		UiObject EditText = new UiObject(new UiSelector().className("basefx.android.widget.EditText").index(0));
		EditText.setText("Test_MiuiLite0");
		mm.clickOnButton("确定");
		found_FC("NewGroups");
		cc.assertTextExist("Test_MiuiLite0");
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
