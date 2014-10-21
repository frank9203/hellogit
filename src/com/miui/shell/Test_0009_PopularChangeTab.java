package com.miui.shell;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiScrollable;

import com. android.uiautomator .testrunner. UiAutomatorTestCase;
import com. miui.marmot .lib. Checker;
import com. miui.marmot .lib. Marmot;

public class Test_0009_PopularChangeTab extends UiAutomatorTestCase{
	public Marmot mm;
	public Checker cc;
	
	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0062_PopularChangeTab() throws UiObjectNotFoundException{
		
		mm.log("Step 1 : Connect with WLAN.");
		mm.pressHome();
		cc.assertTrue("Connect with WLAN", connectWLAN("Public","miguest#1"));
		mm.pressBack(3);
		
		mm.log("Step 2 : launch theme.");
		mm.clickOnText("主题风格");
		mm.waitFor(3);
//		mm.getObjectByClass("android.app.ActionBar$Tab", 1).click();
		mm.getObjectByText("排行","android.widget.TextView").click();
		mm.waitFor(2);
		if(mm.getObjectByText("收费", "android.widget.TextView").isClickable()){
			cc.assertTrue("CheckFreeTab", mm.getObjectByText("免费", "android.widget.TextView").click());
			mm.waitFor(3);
			cc.assertTrue("CheckPaidTab", mm.getObjectByText("收费","android.widget.TextView").click());
			mm.waitFor(2);
			mm.pressBack(3);
		}
		else
			cc.assertTrue("CheckDefaultPaidShow", false);
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
//			UiObject ui = new UiObject(new UiSelector());
//			ui.setText("input text" + password);
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
	
	@Override
    protected void tearDown() throws Exception {
		mm .finish() ;
		super. tearDown();
    }

}
