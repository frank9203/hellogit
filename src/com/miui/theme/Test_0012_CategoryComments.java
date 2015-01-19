package com.miui.theme;
//hu

import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.*;

public class Test_0012_CategoryComments extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	
	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0104_CategoryComments() throws UiObjectNotFoundException{
		
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
		cc.assertTrue("Connect with WLAN", connectWLAN("Public","miguest#1"));
		mm.pressBack(3);
		mm.pressHome();
		
		mm.log("Step 3 : launch theme.");
		mm.clickOnText("主题风格");
		mm.waitFor(3);
		
		mm.log("Step 4 : Check Tab.");
//		mm.getObjectByClass("android.app.ActionBar$Tab", 1).click();
		mm.getObjectByText("排行","android.widget.TextView").click();
		mm.waitFor(2);
		mm.saveScreenshot("Runking.png");
		UiObject First = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(1))
		.getChild(new UiSelector().className("android.widget.FrameLayout").index(0)) ;
		
		First.click() ;
	    
		if(!mm.isTextExist("查看详细评论")){
			mm.move(300, 1000, 300, 500);
			mm.clickOnButton("查看详细评论");
			mm.waitFor(2);	
			mm.saveScreenshot("Review.png");
			mm.clickOnButton("好评");
			mm.waitFor(3);
			mm.clickOnButton("差评");
			mm.waitFor(3);
			mm.clickOnButton("全部");
			mm.waitFor(3);	
		}
		
		
		
		/*mm.click(188, 716);
		mm.waitFor(3);
	
		UiScrollable collectionObject = new UiScrollable(new UiSelector().scrollable(true));
		
		if(collectionObject.exists()){
			UiObject scrollableObject = collectionObject.getChildByText(new UiSelector().className("android.widget.Button"), "查看详细评论");
			scrollableObject.click();
			mm.waitFor(2);			
		}
		else{
			mm.clickOnText("查看详细评论");
			mm.waitFor(2);
		}*/
	
		
		
		mm.pressBack(4);
	
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
	 protected void tearDown() throws Exception {
		mm .finish() ;
		super. tearDown();
    }
}
