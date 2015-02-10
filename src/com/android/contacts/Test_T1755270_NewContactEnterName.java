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


public class Test_T1755270_NewContactEnterName extends UiAutomatorTestCase {//1755270
	public Marmot mm;
	public Checker cc;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}

	public void test_1755270_newcontact_entername() throws UiObjectNotFoundException {
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
		mm.waitFor(1);

		mm.log("Step 2: Press New Contact button.");
		cc.assertUiObejctExist(mm.getObjectByText("新建联系人", "android.widget.Button"));
		mm.getObjectByText("新建联系人", "android.widget.Button").click();
		mm.waitFor(1);

		mm.log("Step 3: Enter names and press downwords button to enter first name,last name.");
		mm.log("Expected result: The name is entered.");
		UiObject down_icon = new UiObject(new UiSelector()
            		.className("android.widget.FrameLayout").index(1)
					.resourceId("com.android.contacts:id/expansion_view_container"));
		down_icon.click();
		mm.waitFor(1);	
		cc.assertUiObejctExist(mm.getObjectByText("姓氏", "android.widget.EditText"));
		mm.getObjectByText("姓氏", "android.widget.EditText").click();
		UiObject last_name = new UiObject(new UiSelector()
                	.className("android.widget.EditText").index(1)
					.text("姓氏"));
		last_name.setText(Utf7ImeHelper.e("王"));
		mm.waitFor(1);
		cc.assertUiObejctExist(mm.getObjectByText("名字", "android.widget.EditText"));
		mm.getObjectByText("名字", "android.widget.EditText").click();
		UiObject first_name = new UiObject(new UiSelector()
                	.className("android.widget.EditText").index(3)
                	.text("名字"));
		first_name.setText(Utf7ImeHelper.e("新建"));

		mm.log("Step 4: Press Ok button.");
		mm.log("Expected result: The contact name is list in the contact list.");
		mm.getObjectByText("确定", "android.widget.Button").click();
		mm.waitFor(1);
		mm.pressBack();
		cc.assertUiObejctExist(mm.getObjectByText("王新建", "android.widget.TextView"));
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
		UiObject contact_item = new UiObject(new UiSelector()
                	.className("android.widget.TextView").index(1)
                	.text("王新建"));
		contact_item.click();
		mm.waitFor(2);
		mm.getObjectByText("更多", "android.widget.Button").click();
		mm.waitFor(2);
		mm.getObjectByText("删除联系人", "android.widget.TextView").click();
		mm.waitFor(1);
		mm.getObjectByText("删除", "android.widget.Button").click();
		mm.waitFor(2);
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
