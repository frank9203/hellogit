package com.miui.shell;

import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.*;

public class Test_0014_MixMatch extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	
	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0106_MixMatch() throws UiObjectNotFoundException{
		
		mm.log("Step 1 : Connect with WLAN.");
		mm.pressBack(3);
		mm.pressHome();
		cc.assertTrue("Connect to WLAN", connectWLAN("Public","miguest#1"));
		mm.pressBack(3);
		
		mm.log("Step 2 : Launch theme.");
		mm.clickOnText("主题风格");
		mm.waitFor(3);
		
		mm.log("Step 3 : Launch Category.");
		mm.getObjectByText("分类", "android.widget.TextView").click();
		cc.assertTrue("Check CategoryPage", CheckSelectedByText("分类", "android.widget.TextView"));
		mm.waitFor(2);
		mm.getObjectByText("混搭", "android.widget.Button").click();
//		cc.assertTrue("Check MixButton",CheckSelectedByText("混搭", "android.widget.Button"));
//		mm.clickOnText("混搭");
		mm.waitFor(3);
		mm.saveScreenshot("MixAndMatch.png");
		mm.waitFor(2);
		mm.pressBack(3);
		
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
			mm.waitFor(3);
			if(mm.getObjectByText("连接", "android.widget.Button").isEnabled()){
				mm.getObjectByText("连接", "android.widget.Button").click();
			}
			mm.waitFor(10);
		}
		if(mm.isTextExist("已连接")){
			checkSelected = true;
		}
		return checkSelected;
	}
	
//	private boolean CheckSelected(String TabClass, int i) throws UiObjectNotFoundException {
//		boolean checkSelected = false;
//		if (mm.getObjectByClass(TabClass, i).isSelected() == true) {
//			checkSelected = true;		
//		
//		}
//		
//		return checkSelected;
//	}  
	
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
