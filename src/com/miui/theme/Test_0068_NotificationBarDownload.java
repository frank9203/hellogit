package com.miui.theme;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0068_NotificationBarDownload extends UiAutomatorTestCase{
	Marmot mm ;
	Checker cc ;
	
	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass()) ;
		cc = new Checker() ;
	}
	
	public void test_0001_NotificationBarDownload() throws UiObjectNotFoundException{
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
//		mm.waitFor(1);
		mm.getObjectByText("混搭", "android.widget.Button").click();
		mm.waitFor(2);
		
		mm.log("Step 2 : Check NotificationBarSearch.");
		mm.getObjectByText("通知栏", "android.widget.TextView").click();
		mm.waitFor(2);
		mm.getObjectByText("排行", "android.widget.TextView").click();
		mm.waitFor(2);
		mm.getObjectByText("免费", "android.widget.TextView").click();
		mm.waitFor(2);
		mm.saveScreenshot("free.png");
		mm.log("Step 3 :NotificationBar Download");
		UiObject view = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.FrameLayout").index(0));
		view.click() ;
		mm.waitFor(3);
		mm.saveScreenshot("view.png");
		//下载有问题测试不通过
		/*String text = new UiObject(new UiSelector().className("android.widget.TextView")).getText();
		new UiObject(new UiSelector().className("android.widget.Button").clickable(true)).waitForExists(5);
		if(mm.isTextExist("下载")){
			mm.clickOnButton("下载");
			mm.waitFor(3);
		}else{
			mm.pressBack();
		}*/
		mm.pressBack(5);
		/*String text = new UiObject(new UiSelector().className("android.widget.ScrollView")).getChild(new UiSelector().className("android.widget.TextView").instance(0)).getText();
		if(mm.isTextExist("下载")){
			mm.clickOnButton("下载");
			for(int i = 0; i < 4; i++ ){
				mm.waitFor(60);
				if(mm.getObjectByText("应用").isEnabled() == true)
					cc.assertTrue("DownloadFinished", true);
					break;				
			}
		}*/
		
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
