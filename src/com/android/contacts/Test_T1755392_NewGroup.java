package com.android.contacts;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;
import com.android.uiautomator.core.UiDevice;
import jp.jun_nama.test.utf7ime.helper.Utf7ImeHelper;
///writer:wangweiwei1
//Created:20150205_M4W_Dev_5.2.5
//Complied: Successfully_0209


public class Test_T1755392_NewGroup extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}

	public void test_1755392_new_group() throws UiObjectNotFoundException {
		if(!mm.isScreenOn()){
			mm.wakeUp();
			mm.move(360, 1000, 360, 1280);
			mm.pressHome();
		}
		mm.log("Step 1: Enter Contact app from Launcher by pressing Contact button.");
		mm.pressHome();
		mm.waitFor(1);
		mm.getObjectByText("鑱旂郴浜�", "android.widget.TextView").click();
		mm.waitFor(1);
		CheckCurrentView("com.android.contacts/com.android.contacts.activities.PeopleActivity");

		mm.log("Step 2: Enter Group view by clicking Group item.");
		mm.getObjectByText("鎴戠殑缇ょ粍", "android.widget.TextView").click();
		mm.waitFor(1);
		CheckCurrentView("com.android.contacts/com.android.contacts.activities.ContactsGroupActivity");

		mm.log("Step 3: Click New Group button, input group name, and confirm.");
		mm.log("Expected result: Group is new created successfully and list in Group list, group exists dailog pops if ");
		mm.log("the group exists already.");
		mm.getObjectByText("鏂板缓缇ょ粍", "android.widget.Button").click();
		mm.waitFor(1);
		UiObject group_name = new UiObject(new UiSelector()
                .className("android.widget.EditText").index(0));
		group_name.setText(Utf7ImeHelper.e("缇ょ粍1"));
		//mm.shell("input text 缇ょ粍1");
		mm.waitFor(1);
		mm.getObjectByText("纭畾", "android.widget.Button").click();
		mm.waitFor(1);
		mm.pressBack();
		mm.waitFor(1);
		cc.assertUiObejctExist(mm.getObjectByText("缇ょ粍1", "android.widget.TextView"));
		mm.getObjectByText("鏂板缓缇ょ粍", "android.widget.Button").click();
		mm.waitFor(1);
		group_name.setText(Utf7ImeHelper.e("缇ょ粍1"));
		mm.waitFor(1);
		mm.getObjectByText("纭畾", "android.widget.Button").click();
		mm.waitFor(5);
		mm.waitFor(1);
		//cc.assertTextExist("缇ょ粍宸插瓨鍦�");not able to recognize the toast text.
		cc.assertUiObejctExist(mm.getObjectByText("纭畾", "android.widget.Button"));
	}

	private boolean CheckCurrentView(String activityName) throws UiObjectNotFoundException{
		boolean enterView = false;
		if(mm.getCurrentActivityName() == activityName){
			enterView = true;
		}
		return enterView;
	}

	@Override
	protected void tearDown() throws Exception {
		mm.getObjectByText("鍙栨秷", "android.widget.Button").click();
		mm.getObjectByText("缇ょ粍1", "android.widget.TextView").click();
		mm.waitFor(2);
		mm.getObjectByText("鍒犻櫎", "android.widget.Button").click();
		mm.waitFor(1);
		mm.getObjectByText("纭畾", "android.widget.Button").click();
		mm.waitFor(1);
		mm.pressHome();
		mm.waitFor(1);
		UiObject clear_icon = new UiObject(new UiSelector()
    		.className("android.widget.ImageView").index(0)
    		.resourceId("com.android.systemui:id/clearButton"));
		for (int i=0; i<2; i++){
			mm.pressMenu();
			mm.waitFor(1);
			clear_icon.click();
			mm.waitFor(2);
		}
		mm.finish();
		super.tearDown();
	}
}