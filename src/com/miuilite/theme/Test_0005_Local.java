package com.miuilite.theme;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0005_Local extends UiAutomatorTestCase{
	public Marmot mm;
	public Checker cc;
	
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0005_Local() throws UiObjectNotFoundException {
		mm.waitFor(5);
		mm.log("Step 0 : Connect with WLAN.");
		mm.pressHome();
		cc.assertTrue("", connectWLAN("Public", "miguest#1"));
		mm.pressBack(3);
		
		mm.log("Step 1 : Launch Themes.");
		mm.pressHome();
		mm.clickOnText("主题风格");
		mm.waitFor(3);
		mm.clickOnText("我的");
		mm.waitFor(2);
		found_FC("Click to Local");

		mm.log("Step 2 : Xiaomi Account.");
		UiObject Account = new UiObject(new UiSelector().className("android.widget.TextView").index(1));
		Account.click();
		mm.pressBack();
		found_FC("click to XiaomiAccount");
		
		mm.log("Step 3 : Local Themes.");
		UiObject LinearLayout0 = new UiObject(new UiSelector().className("android.widget.LinearLayout").index(1))
			.getChild(new UiSelector().className("android.widget.LinearLayout").index(0));
		found_FC("click to ThemeDetails");
		if( LinearLayout0.getChildCount() > 1 )
		{
			UiObject Theme_del = LinearLayout0.getChild(new UiSelector().className("android.widget.LinearLayout").index(1));
			Theme_del.click();
			mm.move(360, 600, 360, 280);
			mm.clickOnButton("删除");
			mm.clickOnButton("确定");
			mm.waitFor(3);
			found_FC("Delete a Theme");
		}		
		UiObject Theme_V6 = LinearLayout0.getChild(new UiSelector().className("android.widget.ImageView").index(0));
		Theme_V6.click();
		mm.clickOnButton("应用");
		mm.waitFor(16);
		found_FC("Apply V6");
	
		mm.pressHome();
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
