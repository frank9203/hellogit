package com.miuilite.theme;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0001_tab extends UiAutomatorTestCase{
	public Marmot mm;
	public Checker cc;
	
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0001_tab() throws UiObjectNotFoundException {
		mm.waitFor(5);		
		mm.log("Step 0 : Connect with WLAN.");
		mm.pressHome();
		cc.assertTrue("", connectWLAN("Public", "miguest#1"));
		mm.pressBack(3);
		
		mm.log("Step 1 : Launch Themes.");
		mm.pressHome();
		mm.clickOnText("主题风格");
		mm.waitFor(3);
		mm.move(420, 360, 120, 360, 3);
		
		mm.log("Step 2 : Featured.");
		mm.clickOnText("精品");
		mm.waitFor(3);
		mm.saveScreenshot("精品");
		mm.move(360, 540, 360, 280, 10);
		
		mm.log("Step 3 : Popular.");
		mm.clickOnText("排行");
		mm.waitFor(3);
		mm.saveScreenshot("排行");
		mm.clickOnText("免费");
		mm.clickOnText("收费");
		
		mm.log("Step 4 : Catagory.");
		mm.clickOnText("分类");
		mm.waitFor(3);
		mm.saveScreenshot("分类");
		mm.move(360, 540, 360, 280);
		mm.clickOnButton("混搭");
		found_FC("Mix");
		mm.pressBack();
		
		mm.log("Step 5 : Local.");
		mm.clickOnText("我的");
		mm.waitFor(2);
		mm.saveScreenshot("我的");
		found_FC("Click to Local");
		mm.pressHome();
	}
	
	private boolean connectWLAN (String wlanName, String password) throws UiObjectNotFoundException{
		boolean connected = false;
		
		mm.launchActivity("com.android.settings/com.android.settings.Settings$WifiSettingsActivity");
//		mm.clickOnText("WLAN");
			
		//Turn on WLAN.
		UiObject Switch = null;
		if(getUiDevice().getProductName()=="OPPO_13005")
			Switch = new UiObject(new UiSelector().className("com.oppo.widget.OppoSwitch"));
		else
			Switch = new UiObject(new UiSelector().className("android.widget.Switch"));
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
