package com.miui.theme;
//hu

import com.android.uiautomator.core.*;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.*;

public class Test_0016_LockScreenStyleDownload extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	
	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0034_FeaturedDownloadFree() throws UiObjectNotFoundException{
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
		cc.assertTrue("Connect with WLAN", connectWLAN("Public", "miguest#1"));
		mm.pressBack(3);
		
		mm.log("Step 3 : lanuch theme.");
		mm.clickOnText("主题风格");
		mm.waitFor(3);
		mm.getObjectByText("分类", "android.widget.TextView").click();
		cc.assertTrue("CheckFeaturedPage", CheckSelectedByText("分类", "android.widget.TextView"));
		mm.waitFor(2);
		mm.saveScreenshot("fenlei.png");
		mm.getObjectByText("混搭", "android.widget.Button").click();
		mm.waitFor(2);
		mm.saveScreenshot("hunda.png");
		mm.getObjectByText("锁屏样式", "android.widget.TextView").click();
		mm.waitFor(3);
		mm.saveScreenshot("suopingyangshi.png");
		mm.getObjectByText("排行", "android.widget.TextView").click();
		mm.waitFor(1);
		mm.saveScreenshot("paihang.png");
		mm.getObjectByText("免费", "android.widget.TextView").click();
		cc.assertTrue("CheckFreePage",CheckSelectedByText("免费", "android.widget.TextView"));
		mm.waitFor(2);
		mm.saveScreenshot("Free.png");
		mm.log("Step 4 : Download Page.");
		mm.click(197, 652);
		mm.waitFor(2);
		
		if(mm.getObjectByText("应用")!=null){
			mm.pressBack();
		}
		if(mm.getObjectByText("下载")!=null){
			mm.clickOnButton("下载");
			mm.waitFor(5);
			mm.saveScreenshot("Download.png");
			mm.pressBack();
		}
		
	/*	String text = mm.getObjectByClass("android.widget.ScrollView").getChild(new UiSelector().className("android.widget.TextView").instance(0)).getText();
		if(mm.isTextExist("下载")){
			mm.clickOnButton("下载");
			mm.waitFor(30);
		}
	
		if(mm.isTextExist("应用") && mm.getObjectByText("应用","android.widget.Button").isEnabled() == true){
			cc.assertTrue("DownloadFinished", true);
			mm.waitFor(2);
			mm.clickOnButton("应用");
			mm.waitFor(3);
			mm.pressBack();
		}*/
	
		mm.log("Step 5 : Show In My List.");	
		mm.clickOnImage(0);
		mm.waitFor(2);
//		getUiDevice().pressHome();
	

//		cc.assertTrue("Show In List", mm.isTextExist(text));
		mm.pressBack(4);
		mm.pressHome();
		
	}

	private boolean connectWLAN(String wlanName, String password) throws UiObjectNotFoundException{
		boolean checkSelected = false;
		
		mm.launchActivity("com.android.settings/.MiuiSettings");
		mm.clickOnText("WLAN");
		mm.waitFor(3);
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
