package com.miui.theme;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0057_NotificationRingtoneDownload extends UiAutomatorTestCase{
    Marmot mm ;
    Checker cc ;
     
    @Override
    protected void setUp() throws Exception{
 	   super.setUp();
 	   mm = new Marmot(this.getClass()) ;
 	   cc = new Checker() ;
    }
    
    public void test_0001_NotifiactionRingtoneDownload() throws UiObjectNotFoundException{
    	
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
		
		
    	
    	mm.log("Step 1 : launch theme.");
		mm.pressHome();
		mm.clickOnText("主题风格");
		mm.waitFor(2);
		mm.getObjectByText("分类", "android.widget.TextView").click();
		cc.assertTrue("CheckFeaturedPage", CheckSelectedByText("分类", "android.widget.TextView"));
		mm.getObjectByText("混搭", "android.widget.Button").click();
		mm.waitFor(2);
		
		mm.log("Step 2 : check NotifiactionRingtone page.");
		mm.getObjectByText("通知铃声", "android.widget.TextView").click();
		new UiObject(new UiSelector().className("android.widget.ImageButton")).waitForExists(10);
		
		mm.log("Step 3 : download ringtone.");
		if(!mm.getObjectByText("精品", "android.widget.TextView").isSelected()){
			mm.getObjectByText("精品", "android.widget.TextView").click();
			mm.waitFor(2);
			mm.saveScreenshot("Boutique.png");
		}
	
		UiObject object = new UiObject(new UiSelector().className("android.widget.ImageButton").instance(0));
		UiScrollable scroll = new UiScrollable(new UiSelector());
		scroll.scrollIntoView(new UiSelector().className("android.widget.ImageButton").instance(0));
		object.click();//点击第一个可下载资源
		mm.waitFor(0.5);
		mm.saveScreenshot("NotifiactionRingtone.png");
		object.waitUntilGone(10);
		mm.waitFor(1);
		
		mm.log("Set 4 : Show in my list.");
		mm.click(60, 127);//点击我的列表
		mm.waitFor(2);
		mm.saveScreenshot("NotifiactionRingtone.png");
		mm.waitFor(1);
		mm.pressBack(4);

	}

	private boolean CheckSelectedByText(String textName,String className) throws UiObjectNotFoundException{
		boolean checkSelected = false;
		if(mm.getObjectByText(textName, className).isSelected()){
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
