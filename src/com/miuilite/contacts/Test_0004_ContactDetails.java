package com.miuilite.contacts;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0004_ContactDetails extends UiAutomatorTestCase{
	public Marmot mm;
	public Checker cc;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}

	public void test_0004_ContactDetails() throws UiObjectNotFoundException {
		mm.waitFor(5);
		mm.log("Step 1 : Launch.");
		mm.pressHome();
		mm.clickOnText("联系人");
		mm.waitFor(1);
		mm.move(360, 540, 360, 90, 10);
		UiObject contact = new UiObject(new UiSelector().className("android.view.View").index(3));
		contact.click();
		found_FC("Click to Contact Details");
		
		mm.log("Step 2 : Send.");
		mm.clickOnButton("发送");
		mm.saveScreenshot("发送");
		mm.pressBack();
		found_FC("Send");
		
		mm.log("Step 3 : Favorites.");
		if(mm.isTextExist("取消收藏")){
			mm.clickOnButton("取消收藏");
			mm.clickOnButton("收藏");
		}
		else{
			mm.clickOnButton("收藏");
			mm.clickOnButton("取消收藏");
		}
		found_FC("Favorites");
		
		mm.log("Step 3 : Edit.");
		mm.clickOnButton("编辑");
		UiObject Name = new UiObject(new UiSelector().className("android.widget.LinearLayout").index(1))
		.getChild(new UiSelector().className("android.widget.EditText").index(0));
		Name.setText("123abc%");
		mm.clickOnButton("确定");
		found_FC("Edit");
		cc.assertTextExist("123abc%");
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
