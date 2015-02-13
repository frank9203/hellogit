package com.android.fileexplorer;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;
import com.android.uiautomator.core.UiDevice;
import java.io.File;
import java.io.IOException;

///writer:wangweiwei1
//Created:20150205_M2W_Dev_5.2.5_4.4
////Compiled successfully:20150512_3W_Dev_5.2.11(Kuaipan has bug in dev version.)


public class Test_T1693190_KuaipanUploadAndDownload extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}

	public void test_1693190_kuaipan_upload_and_download() throws UiObjectNotFoundException {
		if(!mm.isScreenOn()){
			mm.wakeUp();
			mm.move(360, 1000, 360, 1280);
			mm.pressHome();
		}
		UiObject clear_icon = new UiObject(new UiSelector()
    				.className("android.widget.ImageView").index(0)
    				.resourceId("com.android.systemui:id/clearButton"));
		connectWLAN("wangweiwei1@xiaomi.com", "123Justdoit123");
		if (!CheckMiAccountLogStatus()){
			LogInXiaoMiAccount("testforinput@163.com","Justdoit123");
			mm.pressHome();
			mm.pressMenu();
			mm.waitFor(1);
			clear_icon.click();
		}
		mm.launchActivity("com.xiaomi.account/com.xiaomi.account.ui.AccountSettingsActivity");
		UiObject mine_account = new UiObject(new UiSelector()
    				.className("android.widget.TextView").index(0)
    				.text("497143450"));
		if (!mine_account.exists()){
			mm.log("This is not my testing account! Someone should log it out first!!!!!!");
		}
		mm.pressHome();
		mm.pressMenu();
		mm.waitFor(1);
		clear_icon.click();
		mm.log("Step1: 启动文件管理->快盘，上传文件/下载文件");
		mm.log("Step1 Expected: 1.可上传/下载文件，均可操作成功,且传输过程中无异常");
		mm.launchActivity("com.android.fileexplorer/.FileExplorerTabActivity");
		mm.getObjectByText("快盘", "android.widget.TextView").click();
		mm.waitFor(3);
		UiObject kuaipan_sbutton = new UiObject(new UiSelector()
    				.className("ndroid.widget.Button").index(0)
    				.text("开始使用"));
		if(kuaipan_sbutton.exists()){
			kuaipan_sbutton.click();
		}
		mm.waitFor(5);
		cc.assertUiObejctExist(mm.getObjectByText("快盘", "android.widget.TextView"));
		mm.getObjectByText("快盘", "android.widget.TextView").click();
		mm.waitFor(3);
		boolean sstatus = false;
		String file_dir = "/storage/emulated/legacy/Screenshot_1693190.png"; 
		File screen_file = new File(file_dir);
		sstatus = getUiDevice().takeScreenshot(screen_file);//capture the fileexplorer main view.
		mm.waitFor(2);
		cc.assertTrue("Capture the screen", sstatus);
		mm.waitFor(3);
		cc.assertUiObejctExist(mm.getObjectByText("手机", "android.widget.TextView"));
		mm.getObjectByText("手机", "android.widget.TextView").click();
		mm.waitFor(3);
		UiObject more_button = new UiObject(new UiSelector()
    				.className("android.widget.Button").index(0)
    				.resourceId("miui:id/more"));
		UiObject paste_button = new UiObject(new UiSelector()
    				.className("android.widget.Button").index(0)
    				.text("粘贴"));
		cc.assertTrue("More button diesplays or not.", more_button.exists());
		more_button.click();
		mm.getObjectByText("刷新", "android.widget.TextView").click();
		mm.waitFor(3);
		UiScrollable fileList = new UiScrollable(new UiSelector().scrollable(true));
		UiObject screenshot_1693190 = fileList.getChildByText (new UiSelector().className("android.widget.TextView"), "Screenshot_1693190.png", true);
		screenshot_1693190.longClickBottomRight();
		cc.assertUiObejctExist(mm.getObjectByText("复制", "android.widget.Button"));
		mm.getObjectByText("复制", "android.widget.Button").click();
		mm.waitFor(1);
		mm.getObjectByText("手机", "android.widget.TextView").click();
		mm.waitFor(1);
		mm.getObjectByText("快盘", "android.widget.TextView").click();
		paste_button.click();//click "Paste" button. Depends on the coordinate.
		mm.waitFor(5);
		cc.assertTrue("screenshot displays or not!",screenshot_1693190.exists());//judge whether contain a screenshot.
		screenshot_1693190.longClickBottomRight();
		mm.waitFor(3);
		mm.getObjectByText("复制", "android.widget.Button").click();
		mm.waitFor(1);
		mm.getObjectByText("快盘", "android.widget.TextView").click();
		mm.waitFor(1);
		mm.getObjectByText("手机", "android.widget.TextView").click();
		mm.waitFor(1);
		cc.assertUiObejctExist(mm.getObjectByText("粘贴", "android.widget.Button"));
		mm.getObjectByText("粘贴", "android.widget.Button").click();
		mm.waitFor(5);
		more_button.click();
		mm.waitFor(1);
		mm.getObjectByText("刷新", "android.widget.TextView").click();
		cc.assertUiObejctExist(mm.getObjectByTextContain("Screenshot_1693190 1","android.widget.TextView"));//judge whether contain a screenshot.
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

	private boolean CheckCurrentView(String activityName) throws UiObjectNotFoundException{
		boolean enterView = false;
		String trueName = mm.getCurrentActivityName();
		if(trueName.equalsIgnoreCase(activityName)){
			enterView = true;
		}
		return enterView;
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
		mm.finish();
		super.tearDown();
	}
}
