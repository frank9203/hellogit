package com.miuilite.mms;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0007_NewMultimediaMessage extends UiAutomatorTestCase{
	public Marmot mm;
	public Checker cc;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}

	public void test_0007_NewMultimediaMessage() throws UiObjectNotFoundException {
		mm.waitFor(5);
		
		mm.log("Step 1 : Creat NewMessages.");
		mm.pressHome();
		mm.clickOnText("短信");
		found_FC("Launch Messaging");
		mm.clickOnButton("写短信");
		
		mm.log("Step 2 : Send To.");
		UiObject Recent_Cont = new UiObject(new UiSelector().className("android.widget.TextView").index(0));
		Recent_Cont.click();
		
		mm.log("Step 3 : Emoticon.");
		UiObject Attached = new UiObject(new UiSelector().descriptionMatches("添加附件"));
		Attached.click();
		mm.clickOnText("表情");
		mm.pressBack();
		found_FC("Emoticon");
		
		mm.log("Step 4 : Contact.");
		mm.clickOnText("联系人");
		mm.move(360, 540, 360, 90); 
		UiObject ChooseAContact = new UiObject(new UiSelector().className("android.view.View").index(2));
		ChooseAContact.click();
		mm.clickOnButton("确定");
		found_FC("Contact");

		mm.log("Step 5 : Pictures & Tack Photo.");
		Attached.click();
		mm.clickOnText("图片");
		mm.pressBack();
		mm.clickOnText("拍照");
		mm.pressBack();
		found_FC("Pictures or Tack Photo");

		mm.log("Step 6 : Nickname.");
		mm.clickOnText("群发称呼");
		if(mm.isTextExist("编辑群发称呼"))
			mm.clickOnButton("确定");
		found_FC("Nickname");
		
		mm.log("Step 7 : Timed.");
		Attached.click();
		mm.clickOnText("定时");
		mm.clickOnButton("确定");
		found_FC("Timed");
		
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
