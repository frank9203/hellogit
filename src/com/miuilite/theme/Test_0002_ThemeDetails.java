package com.miuilite.theme;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0002_ThemeDetails extends UiAutomatorTestCase{
	public Marmot mm;
	public Checker cc;
	
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0002_ThemeDetails() throws UiObjectNotFoundException {
		mm.waitFor(5);
		mm.log("Step 0 : Connect with WLAN.");
		mm.pressHome();
		cc.assertTrue("", connectWLAN("Public", "miguest#1"));
		mm.pressBack(3);
		
		mm.log("Step 1 : Launch Themes.");
		mm.pressHome();
		mm.clickOnText("主题风格");
		mm.waitFor(3);
		
		mm.log("Step 2 : 推荐位.");
		UiObject Rec = new UiObject(new UiSelector().className("android.widget.LinearLayout").index(0))
	      .getChild(new UiSelector().className("android.widget.FrameLayout").index(1));
		Rec.click();
		found_FC("click a Rec");
		mm.pressBack();
		
		mm.log("Step 3 : 新品推荐.");
		UiObject newTheme = new UiObject(new UiSelector().className("android.widget.LinearLayout").index(1))
	      .getChild(new UiSelector().className("android.widget.LinearLayout").index(2));
		newTheme.click();
		mm.saveScreenshot("ThemeDetails");
		mm.move(540, 360, 90, 360, 8);
		found_FC("slide");
		
		mm.log("Step 4 : reviews.");
		mm.move(360, 800, 360, 200);
		mm.waitFor(3);
		mm.clickOnButton("查看详细评论");
		mm.waitFor(1);
		found_FC("Click to View");
		mm.pressBack(3);
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
