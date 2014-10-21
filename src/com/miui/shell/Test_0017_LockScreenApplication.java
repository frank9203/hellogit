package com.miui.shell;

import android.os.RemoteException;

import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.*;

public class Test_0017_LockScreenApplication extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	
	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0113_ScreenApplication() throws UiObjectNotFoundException{
		mm.log("Step 1 : Connect with WLAN.");
		mm.pressHome();
		cc.assertTrue("Connect with WLAN", connectWLAN("Public", "miguest#1"));
		mm.pressBack(3);
		
		mm.log("Step 2 : launch theme.");
		mm.clickOnText("主题风格");
		mm.waitFor(2);
		mm.getObjectByText("分类", "android.widget.TextView").click();
		cc.assertTrue("CheckFeaturedPage", CheckSelectedByText("分类", "android.widget.TextView"));
		mm.waitFor(1);
		mm.getObjectByText("混搭", "android.widget.Button").click();
		mm.waitFor(2);
		mm.getObjectByText("锁屏样式", "android.widget.TextView").click();
		mm.waitFor(3);
		mm.click(60, 127);
		mm.waitFor(2);
		mm.click(540, 491);
		mm.waitFor(2);
		
		mm.log("Step 3 : Application Screen.");

		mm.clickOnButton("应用");
		mm.waitFor(3);
		mm.pressBack(4);
		UiDevice device = getUiDevice();
		device.pressHome();
		try {
			device.sleep();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mm.waitFor(3);
		try {
			device.wakeUp();
			mm.waitFor(1);
			mm.saveScreenshot("ScreenTheme.png");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
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
	
	@Override
	protected void tearDown() throws Exception{
		mm.finish();
		super.tearDown();
	}
}
