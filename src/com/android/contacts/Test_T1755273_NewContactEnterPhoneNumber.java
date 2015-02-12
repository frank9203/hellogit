package com.android.contacts;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;
import com.android.uiautomator.core.UiDevice;
import jp.jun_nama.test.utf7ime.helper.Utf7ImeHelper;
import junit.framework.Assert;

///writer:wangweiwei1
//Created:20150202_M3W_Alpha_5.1.30
//Compiled successfully:20150512_3W_Dev_5.2.11


public class Test_T1755273_NewContactEnterPhoneNumber extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}

	public void test_1755273_newcontact_enterphonenumber() throws UiObjectNotFoundException {
		if(!mm.isScreenOn()){
			mm.wakeUp();
			mm.move(360, 1000, 360, 1280);
			mm.pressHome();
		}
		if (CheckMiAccountLogStatus()){
			LogInXiaoMiAccount("testforinput@163.com", "Justdoit123");
		}
		mm.log("Step 1: Enter Conatcts homeview from Launcher by pressing Contacts button.");
		mm.log("Expected result: Enter Contact homeview.");
		mm.pressHome();
		cc.assertUiObejctExist(mm.getObjectByText("联系人", "android.widget.TextView"));
		mm.getObjectByText("联系人", "android.widget.TextView").click();
		mm.waitFor(3);
		cc.assertTrue("Enter the contact main view!", CheckCurrentView("com.android.contacts/com.android.contacts.activities.PeopleActivity"));

		mm.log("Step 2: Press New Contact button to enter new contact view, select each kind of number by clicking the switcher button.");
		cc.assertUiObejctExist(mm.getObjectByText("新建联系人", "android.widget.Button"));
		mm.getObjectByText("新建联系人", "android.widget.Button").click();
		mm.waitFor(1);
		cc.assertTrue("Enter the new contact view!", CheckCurrentView("com.android.contacts/com.android.contacts.activities.ContactEditorActivity"));
		UiObject item_selector = new UiObject(new UiSelector()
                	.className("android.widget.TextView").index(0)
                	.resourceId("android:id/text1"));
		for (int i=0; i<8;i++){
			item_selector.click();
			mm.waitFor(2);
			UiObject item = new UiObject(new UiSelector()
                		.className("android.widget.CheckedTextView").instance(i));
			String text = item.getText();
			mm.log(text);
			mm.getObjectByClass("android.widget.CheckedTextView", i).click();
			mm.waitFor(1);
			UiObject switcher = new UiObject(new UiSelector()
                	.className("android.widget.TextView").instance(1));
			String switcher_text = switcher.getText();
			mm.log(switcher_text);
			cc.assertTrue("The switcher doesn't work!", text.equalsIgnoreCase (switcher_text));
		}
		cc.assertUiObejctExist(mm.getObjectByText("其他", "android.widget.TextView"));
		mm.getObjectByText("其他", "android.widget.TextView").click();
		//mm.getObjectByClass("android.widget.Spinner", 0).click();
		mm.waitFor(2);
		mm.getObjectByClass("android.widget.CheckedTextView",8).click();
		mm.waitFor(1);
		UiObject custom_dialog = new UiObject(new UiSelector()
                	.className("android.widget.EditText").index(0)
                	.resourceId("com.android.contacts:id/custom_dialog_content"));
			custom_dialog.setText(Utf7ImeHelper.e("阿阿"));
			mm.waitFor(1);
			mm.getObjectByText("确定", "android.widget.Button").click();

		mm.log("Step 3: Enter the number.");
		mm.log("Expected: The number is added as expected.");
		UiObject phone_number = new UiObject(new UiSelector()
                	.className("android.widget.EditText").instance(3));
			phone_number.setText("11111111111");
		cc.assertTextExist("11111 11111 1");
			mm.waitFor(1);
		cc.assertUiObejctExist(mm.getObjectByText("取消", "android.widget.Button"));
		mm.getObjectByText("取消", "android.widget.Button").click();
		mm.waitFor(1);
		cc.assertUiObejctExist(mm.getObjectByText("确定", "android.widget.Button"));
		mm.getObjectByText("确定", "android.widget.Button").click();
	}

	private boolean CheckMiAccountLogStatus() throws UiObjectNotFoundException{
		boolean unlogStatus = true;
		mm.launchActivity("com.android.settings/com.android.settings.MiuiSettings");
		mm.waitFor(1);
		mm.move(360, 1000, 360, 900, 4);
		mm.waitFor(1);
		mm.getObjectByText("小米帐号", "android.widget.TextView").click();
		mm.waitFor(2);
		if (CheckCurrentView("com.xiaomi.account/com.xiaomi.account.ui.WelcomeActivity")){
			mm.log("Mi account isn't logged in.");
		}
		else{
			mm.log("Mi account is logged in.");
			unlogStatus = false;
		}
		return unlogStatus;
	}

	private boolean CheckCurrentView(String activityName) throws UiObjectNotFoundException{
		boolean enterView = false;
		String trueName = mm.getCurrentActivityName();
		if(trueName.equalsIgnoreCase(activityName)){
			enterView = true;
		}
		return enterView;
	}

	private void LogInXiaoMiAccount(String accountName, String accountPassword) throws UiObjectNotFoundException{
		mm.getObjectByText("登录", "android.widget.Button").click();
		mm.waitFor(2);
		UiObject username_bar = new UiObject(new UiSelector()
            .className("android.widget.EditText").index(0)
            .resourceId("com.xiaomi.account:id/et_account_name"));
		UiObject password_bar = new UiObject(new UiSelector()
            .className("android.widget.EditText").index(0)
            .resourceId("com.xiaomi.account:id/et_account_password"));
		username_bar.setText(accountName);
		mm.waitFor(1);
		password_bar.click();
		mm.waitFor(1);
		password_bar.setText(accountPassword);
		//cc.assertUiObejctExist(mm.getObjectByText("登录", "android.widget.Button"));
		//mm.getObjectByText("登录", "android.widget.Button").click();
		mm.click(540, 781);
		mm.waitFor(3);
		cc.assertUiObejctExist(mm.getObjectByText("开启小米云服务", "android.widget.TextView"));
		mm.getObjectByText("开启小米云服务", "android.widget.TextView").click();
		mm.waitFor(1);
		cc.assertUiObejctExist(mm.getObjectByText("开启网络短信", "android.widget.TextView"));
		mm.getObjectByText("开启网络短信", "android.widget.TextView").click();
		mm.waitFor(1);
		cc.assertUiObejctExist(mm.getObjectByText("继续", "android.widget.Button"));
		mm.getObjectByText("继续", "android.widget.Button").click();
		mm.waitFor(1);
		mm.pressBack(3);
		mm.waitFor(2);
	}

	@Override
	protected void tearDown() throws Exception {
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