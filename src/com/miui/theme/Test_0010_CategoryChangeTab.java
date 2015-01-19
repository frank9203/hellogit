package com.miui.theme;
//hu

import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.*;

public class Test_0010_CategoryChangeTab extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	
	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0095_CategoryChangeTab() throws UiObjectNotFoundException{
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
		mm.pressHome();
		cc.assertTrue("Connect with WLAN", connectWLAN("Public","miguest#1"));
		mm.pressBack(3);
		
		mm.log("Step 3 : launch theme.");
		mm.clickOnText("主题风格");
		mm.waitFor(3);
		
		mm.log("Step 4 : Check Category.");
		mm.getObjectByText("分类", "android.widget.TextView").click();
		mm.waitFor(2);
		mm.saveScreenshot("Classification.png");
		
		for(int i=0;i<8;i++){
			  UiObject View = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
			    .getChild(new UiSelector().className("android.widget.ImageView").index(i)) ;
			    View.click() ;
			    mm.waitFor(1);
			    mm.pressBack();
		}
	
		/*mm.click(279, 493);
		mm.waitFor(2);
		for(int i = 0; i < 3; i++){   //
			mm.getObjectByClass("android.app.ActionBar$Tab", i).click();
			mm.waitFor(3);
			if(i==1){
				cc.assertTrue("CheckPaidTab", mm.getObjectByText("收费", "android.widget.TextView").click());
				mm.waitFor(3);
				cc.assertTrue("CheckFreeTab",mm.getObjectByText("免费", "android.widget.TextView").click());
				mm.waitFor(3);
			}
		}*/
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
				UiObject scrollableObject = collectionObject.getChildByText(new UiSelector().className("android.widget.TextView"), wlanName);
				scrollableObject.click();
			}
			else{
				mm.clickOnText(wlanName);
			}
			mm.waitFor(2);
			mm.shell("input text " + password);
			mm.waitFor(6);
			if(mm.getObjectByText("连接", "android.widget.Button").isEnabled()){
				mm.clickOnButton("连接");
//				mm.getObjectByText("连接", "android.widget.Button").click();
			}
			
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
