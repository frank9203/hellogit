package com.miui.theme;
//hu

import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.*;

public class Test_0011_CategorySearch extends UiAutomatorTestCase {

	public Marmot mm;
	public Checker cc;
	
	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0100_CategorySearch() throws UiObjectNotFoundException{
		mm.log("Step 1 : Open Unlock");

		int DisplayWidth ;
		DisplayWidth = this.getUiDevice().getDisplayWidth() ;
		if(!mm.isScreenOn()){   
			mm.wakeUp();
			mm.waitFor(1);
			if(DisplayWidth <= 480){
				mm.move(300, 700, 300, 200);
			}
			if(DisplayWidth <=540){
				mm.move(300, 900, 300, 200);
			}
			if(DisplayWidth <=720){
				mm.move(300, 1000, 300, 500);
			}
			if(DisplayWidth <=1080){
				mm.move(500, 1700, 500, 500);
			}else{
				mm.move(700, 2000, 700, 500);
			}
		//	mm.move(500, 1500, 500, 700);
			mm.waitFor(2);
		}	
		
		
		mm.log("Step 2 : Connect with WLAN.");
		mm.pressBack(3);
		mm.pressHome();
		cc.assertTrue("Connect to WLAN", connectWLAN("Public","miguest#1"));
		mm.pressBack(3);
		
		mm.log("Step 2 : Launch theme.");
		mm.clickOnText("主题风格");
		mm.waitFor(3);
		
		mm.log("Step 3 : Launch category.");
//		mm.getObjectByClass("android.app.ActionBar$Tab", 2).click();
		mm.getObjectByText("分类", "android.widget.TextView").click();
		mm.waitFor(3);
		
		mm.clickOnButton("混搭");
		mm.waitFor(2);
		mm.saveScreenshot("MashUp.png");
		mm.pressBack(3);
		
		
		
		
		
		
		/*mm.click(279, 813);
		mm.waitFor(2);
		mm.click(1013, 135);
		mm.waitFor(2);
		
		mm.log("Step 4 : Search.");

		String author = new String("Bens");
		mm.shell("input text" + author);
		mm.waitFor(2);
		mm.getObjectByText("主题大赛", "android.widget.TextView").click();
//		cc.assertTrue("Input search message", search);
//	    mm.shell("input text" + author);
		mm.waitFor(2);
		mm.clickOnText("搜索");
		mm.waitFor(6);
		*/
		
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
	
	
	@Override
	protected void tearDown() throws Exception{
		mm.finish();
		super.tearDown();
	}
}
