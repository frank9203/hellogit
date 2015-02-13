package com.android.email;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;
import com.android.uiautomator.core.UiDevice;

///writer:wangweiwei1
//Created:20150208_M2W_Dev_5.2.5_4.4
//Compiled successfully:20150513_3W_Dev_5.2.11


public class Test_T1755825_LocalFolders_Inbox extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}

	public void test_1755825_local_folders_inbox() throws UiObjectNotFoundException {
		if(!mm.isScreenOn()){
			mm.wakeUp();
			mm.move(360, 1000, 360, 1280);
			mm.pressHome();
		}
		//connectWLAN("wangweiwei1@xiaomi.com", "123123123");	
		connectMount("8miuiautoCoder");
		mm.log("Preconditions: Email account is logged on.");
		deleteEmailAccount();
		UiObject clear_icon = new UiObject(new UiSelector()
    				.className("android.widget.ImageView").index(0)
    				.resourceId("com.android.systemui:id/clearButton"));
		mm.pressMenu();
		mm.waitFor(1);
		clear_icon.click();
		mm.waitFor(2);
		mm.launchActivity("com.android.email/com.kingsoft.email.activity.setup.AccountSetupBasics");
		addEmailAccount("testforemail@163.com","testtest");
		mm.pressMenu();
		clear_icon.click();
		mm.log("Step 1: Check the Inbox objects.");
		mm.log("Expected 1: Account/Inbox displays, unread items count displays.");
		mm.launchActivity("com.android.email/com.kingsoft.email2.ui.MailActivityEmail");
		mm.waitFor(1);
		cc.assertUiObejctExist(mm.getObjectByText("收件箱", "android.widget.TextView"));
		mm.getObjectByText("收件箱", "android.widget.TextView").click();
		mm.waitFor(2);
		cc.assertUiObejctExist(mm.getObjectByText("收件箱", "android.widget.TextView"));		
		mm.log("Expected 2: Pictures/Theme/Sender/Receive Time; Compose/Search/Attachment/More buttons.");
		//Not able to check Pictures/Theme/Sender/Receive Time,since the object can't be recognized.
		cc.assertUiObejctExist(mm.getObjectByText("写邮件", "android.widget.Button"));
		cc.assertUiObejctExist(mm.getObjectByText("搜索", "android.widget.Button"));
		cc.assertUiObejctExist(mm.getObjectByText("附件", "android.widget.Button"));
		cc.assertUiObejctExist(mm.getObjectByText("更多", "android.widget.Button"));

	}

	private boolean CheckCurrentView(String activityName) throws UiObjectNotFoundException{
		boolean enterView = false;
		String trueName = mm.getCurrentActivityName();
		if(trueName.equalsIgnoreCase(activityName)){
			enterView = true;
		}
		return enterView;
	}

	private void addEmailAccount(String emailId, String emailPwd) throws UiObjectNotFoundException{
		//input email username.
		//mm.shell("input text testtest111testtest@hotmail.com");
		//input password.
		//cc.assertUiObejctExist(mm.getObjectByText("电子邮件地址", "android.widget.EditText"));
		mm.getObjectByText("电子邮件地址", "android.widget.EditText").setText(emailId);
		mm.waitFor(1);
		//mm.getObjectByText("添加帐户", "android.widget.TextView").click();
		mm.click(540, 127);
		UiObject emailpwd_edit = new UiObject(new UiSelector()
                	.className("android.widget.EditText").index(0)
                	.resourceId("com.android.email:id/account_password"));
		cc.assertTrue("Password input field displays. ", emailpwd_edit.exists());
		emailpwd_edit.setText(emailPwd);
		mm.waitFor(1);
		cc.assertUiObejctExist(mm.getObjectByText("登录", "android.widget.Button"));
		mm.getObjectByText("登录", "android.widget.Button").click();
		mm.waitFor(10);
		cc.assertTrue("Account setup view displays.",CheckCurrentView("com.android.email/com.kingsoft.email.activity.setup.AccountSetupOptions"));
		UiObject finish_button = new UiObject(new UiSelector()
            .className("android.widget.Button").index(0)
            .text("完成"));
		cc.assertTrue("Check finish button displays or not!", finish_button.exists());
		finish_button.click();
		mm.waitFor(15);
		mm.pressHome();
	}


	private void deleteEmailAccount() throws UiObjectNotFoundException{
		//open email account view.
		mm.launchActivity("com.android.email/com.android.email.activity.setup.SystemAccountSettings");
		mm.waitFor(1);
		UiObject account_1 = new UiObject(new UiSelector()
                	.className("android.widget.TextView")
                	.text("帐户"));
		if (account_1.exists()){
			UiObject arrow_right = new UiObject(new UiSelector()
                .className("android.widget.ImageView").index(2)
                .resourceId("miui:id/arrow_right"));
			arrow_right.click();
			mm.waitFor(1);
			UiScrollable esList = new UiScrollable(new UiSelector().scrollable(true));
			UiObject delete_button = esList.getChildByText (new UiSelector().className("android.widget.LinearLayout"), "删除帐户", true);
			delete_button.click();
			mm.waitFor(1);
			//cc.assertUiObejctExist(mm.getObjectByText("删除帐户", "android.widget.TextView");
			//mm.getObjectByText("删除帐户", "android.widget.TextView".click();
			//mm.waitFor(1);
			mm.getObjectByText("确定", "android.widget.Button").click();
			mm.waitFor(5);
		}
		mm.pressHome();
	}

	private void connectWLAN (String userName, String passWord) throws UiObjectNotFoundException{
		//Turn on WLAN.
		mm.launchActivity("com.android.settings/com.android.settings.Settings$WifiSettingsActivity");
		UiObject wlanSwitcher = new UiObject(new UiSelector()
                .className("android.widget.CheckBox").instance(0)
                .resourceId("android:id/checkbox"));
		if ( !wlanSwitcher.isChecked() ){
			wlanSwitcher.click();
			mm.waitFor(10);
		}
		UiScrollable wlanList = new UiScrollable(new UiSelector().scrollable(true));
		UiObject wlan_mioffice = wlanList.getChildByText (new UiSelector().className("android.widget.CheckedTextView"), "MIOffice", true);
		//Connect with WLAN which named wlanName.
		if( !mm.isTextExist("已连接") ){
			wlan_mioffice.click();
			mm.waitFor(2);
			UiObject iD = new UiObject(new UiSelector()
                		.className("android.widget.EditText").instance(0)
                		.resourceId("com.android.settings:id/identity"));
			iD.setText(userName);
			mm.waitFor(1);
			UiObject pW = new UiObject(new UiSelector()
                		.className("android.widget.EditText").instance(0)
                		.resourceId("com.android.settings:id/l_password"));
			pW.setText(passWord);
			mm.waitFor(1);
			mm.clickOnButton("连接");
			mm.waitFor(9);
		}
		
		UiObject wlan_status = wlanList.getChildByText (new UiSelector().className("android.widget.TextView"), "已连接", true);
		if (wlan_status.exists()){
			mm.log("WLAN connected.");
		}
		else
			mm.log("connect failed");
	}

	private void connectMount (String passWord) throws UiObjectNotFoundException{
		//Turn on WLAN.
		mm.launchActivity("com.android.settings/com.android.settings.Settings$WifiSettingsActivity");
		UiObject wlanSwitcher = new UiObject(new UiSelector()
                .className("android.widget.CheckBox").instance(0)
                .resourceId("android:id/checkbox"));
		if ( !wlanSwitcher.isChecked() ){
			wlanSwitcher.click();
			mm.waitFor(10);
		}
		//Connect with WLAN which named wlanName.
		UiScrollable wlanList = new UiScrollable(new UiSelector().scrollable(true));
		UiObject mount_miuiauto_2 = wlanList.getChildByText (new UiSelector().className("android.widget.CheckedTextView"), "miuiauto_2", true);
		if( !mm.isTextExist("已连接") ){		
			mount_miuiauto_2.click();
			mm.waitFor(2);
			UiObject pW = new UiObject(new UiSelector()
                		.className("android.widget.EditText").instance(0)
                		.resourceId("com.android.settings:id/password"));
			pW.setText(passWord);
			mm.waitFor(1);
			mm.clickOnButton("连接");
			mm.waitFor(9);
		}
		
		UiObject wlan_status = wlanList.getChildByText (new UiSelector().className("android.widget.TextView"), "已连接", true);
		if (wlan_status.exists()){
			mm.log("WLAN connected.");
		}
		else
			mm.log("connect failed");
	}


	@Override
	protected void tearDown() throws Exception {
		deleteEmailAccount();
		mm.pressMenu();
		UiObject clear_icon = new UiObject(new UiSelector()
    				.className("android.widget.ImageView").index(0)
    				.resourceId("com.android.systemui:id/clearButton"));
		clear_icon.click();
		mm.finish();
		super.tearDown();
	}
}