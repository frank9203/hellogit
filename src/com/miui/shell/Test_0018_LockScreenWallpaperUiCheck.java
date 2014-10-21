package com.miui.shell;

import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.*;

public class Test_0018_LockScreenWallpaperUiCheck extends UiAutomatorTestCase {
	public Marmot  mm;
	public Checker cc;
	
	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0136_LockScreenWallpaperUiCheck() throws UiObjectNotFoundException{
		mm.log("Step 1 : Connect with WLAN.");
		mm.pressHome();
		cc.assertTrue("Connect with WLAN", connectWLAN("Public", "miguest#1"));
		mm.pressBack(3);
		mm.pressHome();
		
		mm.log("Step 2 : launch theme.");
		mm.clickOnText("主题风格");
		mm.waitFor(3);
		mm.getObjectByText("分类", "android.widget.TextView").click();
		cc.assertTrue("CheckFeaturedPage", CheckSelectedByText("分类", "android.widget.TextView"));
		mm.waitFor(2);
		mm.getObjectByText("混搭", "android.widget.Button").click();
		mm.waitFor(2);
		
		mm.log("Step 3 : Check LockScreenWallpaper.");
		mm.getObjectByText("锁屏壁纸", "android.widget.TextView").click();
		mm.waitFor(3);
		UiScrollable scroll = new UiScrollable(new UiSelector().scrollable(true));
		if(!mm.getObjectByText("精品", "android.widget.TextView").isSelected()){
			mm.getObjectByText("精品", "android.widget.TextView").click();
			mm.waitFor(3);
		}
		scroll.scrollToEnd(10);
		mm.getObjectByText("排行", "android.widget.TextView").click();
		mm.waitFor(3);
		scroll.scrollToEnd(10);
		mm.getObjectByText("分类", "android.widget.TextView").click();
		mm.waitFor(3);
		mm.click(285, 382);
		mm.waitFor(3);
		if(!mm.getObjectByText("精品", "android.widget.TextView").isSelected()){
			mm.getObjectByText("精品", "android.widget.TextView").click();
			mm.waitFor(3);
		}
		scroll.scrollToEnd(10);	
		mm.getObjectByText("排行", "android.widget.TextView").click();
		mm.waitFor(3);
		scroll.scrollToEnd(10);
		mm.getObjectByText("新品", "android.widget.TextView").click();
		mm.waitFor(3);
		scroll.scrollToEnd(10);
		mm.pressBack();
		mm.click(60, 127);
		mm.waitFor(2);
		mm.pressBack(3);
		mm.pressHome();
		
	}
	private boolean connectWLAN(String wlanName, String password) throws UiObjectNotFoundException{
		boolean checkSelected = false;
		
		mm.launchActivity("com.android.settings/.MiuiSettings");
		mm.clickOnText("WLAN");
		
		if(!mm.isChecked(0)){
			mm.clickOnCheckBox(0);
			mm.waitFor(3);
		}
		if(!mm.isTextExist("已连接")){
			UiScrollable collectionObject  = new UiScrollable(new UiSelector().scrollable(true));
			if(collectionObject.exists()){
				UiObject scrollableObject = collectionObject.getChildByText(new UiSelector().className("android.widget.CheckedTextView"), wlanName);
				scrollableObject.click();
			}
			else{
				mm.clickOnText(wlanName);
			}
			mm.waitFor(2);
			mm.pressBack();
			mm.shell("input text " + password);
			mm.waitFor(6);
			if(mm.getObjectByText("连接", "android.widget.Button").isEnabled()){
				mm.getObjectByText("连接", "android.widget.Button").click();
			}
			mm.waitFor(15);
		}
		if(mm.isTextExist("已连接")){
			checkSelected = true;
		}
		return checkSelected;
	} 
	
	private boolean CheckSelectedByText(String textName, String classNmae) throws UiObjectNotFoundException{
		boolean checkSelected = false;
		if(mm.getObjectByText(textName, classNmae).isSelected() == true){
			checkSelected = true;
		}
		return checkSelected;
	}
	

	
}
