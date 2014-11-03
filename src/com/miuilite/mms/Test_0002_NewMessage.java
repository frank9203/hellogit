package com.miuilite.mms;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0002_NewMessage extends UiAutomatorTestCase{
	public Marmot mm;
	public Checker cc;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}

	public void test_0002_NewMessage() throws UiObjectNotFoundException {
		mm.waitFor(5);
		
		mm.log("Step 1 : Creat NewMessages.");
		mm.pressHome();
		mm.clickOnText("短信");
		found_FC("Launch Messaging");
		mm.clickOnButton("写短信");
		
		mm.log("Step 2 : Send To.");
		UiObject Receiver = new UiObject(new UiSelector().className("basefx.android.widget.EditText").index(0));
		Receiver.setText("12345678900");
			
		UiObject contacts = new UiObject(new UiSelector().className("android.widget.LinearLayout").index(2))
			.getChild(new UiSelector().className("android.widget.Button").index(0));
		contacts.click();
		UiObject Search = new UiObject(new UiSelector().textStartsWith("搜索"));
		Search.setText("1");
		UiObject Search_Cont = new UiObject(new UiSelector().className("android.view.View").index(2));
		Search_Cont.click();
		mm.clickOnButton("确定");
		
		UiObject Recent_Cont = new UiObject(new UiSelector().className("android.widget.TextView").index(0));
		Recent_Cont.click();
		found_FC("Send to");

		mm.log("Step 3 : Text.");
		UiObject Text = new UiObject(new UiSelector().text("短信"));
		Text.setText("123abc@~!_^.,;?http://www.mi.com");
		found_FC("Edit MmsText");
		
		mm.log("Step 4 : Send.");
		UiObject SendButton = new UiObject(new UiSelector().className("android.widget.LinearLayout").index(1))
			.getChild(new UiSelector().className("android.widget.LinearLayout").index(2))
			.getChild(new UiSelector().className("android.widget.Button").index(0));
		SendButton.click();
		found_FC("Click SendButton");
		mm.waitFor(10);
		
		mm.log("Step 5 : Creat newMms.");
		mm.pressHome();
		mm.clickOnText("短信");
		mm.clickOnButton("写短信");
		Receiver.setText("13436312171");
		Text.setText("hello test");
		SendButton.click();
		mm.waitFor(5);
		found_FC("Creat a NewMms");
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
