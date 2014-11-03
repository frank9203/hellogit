package com.miuilite.theme;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0004_Popular extends UiAutomatorTestCase{
	public Marmot mm;
	public Checker cc;
	
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0004_Popular() throws UiObjectNotFoundException {
		mm.waitFor(5);
		mm.log("Step 0 : Connect with WLAN.");
		mm.pressHome();
		cc.assertTrue("", connectWLAN("Public", "miguest#1"));
		mm.pressBack(3);
		
		mm.log("Step 1 : Launch Themes.");
		mm.pressHome();
		mm.clickOnText("主题风格");
		mm.waitFor(3);
		mm.clickOnText("排行");
		mm.waitFor(3);
		found_FC("Click to Popular");
	
		mm.log("Step 2 : Free.");
		mm.clickOnText("免费");
		mm.waitFor(2);
		UiObject Theme = new UiObject(new UiSelector().className("android.widget.LinearLayout").index(2))
			.getChild(new UiSelector().className("android.widget.LinearLayout").index(2));
		Theme.click();
		if(mm.isTextExist("下载"))
			mm.clickOnButton("下载");
		else
			cc.assertTextExist("应用");
		mm.pressBack();
		found_FC("Free");
		
		mm.log("Step 3 : Paid.");
		mm.clickOnText("收费");
		mm.waitFor(2);
		Theme.click();
		UiObject MiDollar = new UiObject(new UiSelector().textContains("米币"));
		MiDollar.click();
		mm.waitFor(8);
		mm.pressBack(2);
		Theme.click();
		if(mm.isTextExist("免费试用"))
			mm.clickOnButton("免费试用");
		mm.waitFor(2);
		found_FC("Paid");
		
		mm.pressHome(2);
	}
	
	private boolean connectWLAN (String wlanName, String password) throws UiObjectNotFoundException{
		boolean connected = false;
		
		mm.launchActivity("com.android.settings/com.android.settings.Settings$WifiSettingsActivity");
//		mm.clickOnText("WLAN");
			
		//Turn on WLAN.
		UiObject Switch = new UiObject(new UiSelector().className("android.widget.Switch"));
		if(!Switch.isChecked()){
			Switch.click();
			mm.waitFor(3);
		}
		
		//Connect with WLAN which named wlanName.
		if( !mm.isTextExist("已连接") ){
			mm.clickOnText(wlanName);
			mm.waitFor(2);
			mm.shell("input text " + password);
			mm.waitFor(3);
			mm.pressBack();
			mm.clickOnButton("连接");
			mm.waitFor(9);
		}
		
		if( mm.isTextExist("已连接") ){
			connected = true;
			mm.log("connected");
		}
		else
			mm.log("connect failed");
				
		return connected;
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
