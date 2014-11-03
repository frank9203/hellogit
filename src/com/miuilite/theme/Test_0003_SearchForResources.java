package com.miuilite.theme;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0003_SearchForResources extends UiAutomatorTestCase{
	public Marmot mm;
	public Checker cc;
	
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0003_SearchForResources() throws UiObjectNotFoundException {
		mm.waitFor(5);
		mm.log("Step 0 : Connect with WLAN.");
		mm.pressHome();
		cc.assertTrue("", connectWLAN("Public", "miguest#1"));
		mm.pressBack(3);
		
		mm.log("Step 1 : Launch Themes.");
		mm.pressHome();
		mm.clickOnText("主题风格");
		mm.waitFor(3);
		found_FC("Launch Themes");
		
		mm.log("Step 2 : Search.");
		mm.clickOnText("精品");
		UiObject Search = new UiObject(new UiSelector().className("android.view.View").index(0))
	      .getChild(new UiSelector().className("android.widget.ImageView").index(2));
		Search.click();
		UiObject EditText = new UiObject(new UiSelector().className("android.widget.EditText").index(0));
		EditText.click();
		EditText.setText("MIUI 6");
		UiObject SearchButton = new UiObject(new UiSelector().className("android.widget.ImageButton").index(1));
		SearchButton.click();
		found_FC("search");
		
		mm.log("Step 3 : ThmemDetails.");
		mm.saveScreenshot("V6主题");
//		EditText.clearTextField();
		cc.assertTextExist("MIUI 6尝鲜版");
		
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
