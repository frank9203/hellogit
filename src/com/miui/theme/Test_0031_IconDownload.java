package com.miui.theme;

import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.*;

public class Test_0031_IconDownload extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	
	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0163_IconDownload() throws UiObjectNotFoundException{
		
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
		
		mm.log("Step 2 : launch theme.");
		mm.pressHome();
		mm.clickOnText("主题风格");
		mm.waitFor(2);
		mm.getObjectByText("分类", "android.widget.TextView").click();
		cc.assertTrue("CheckFeaturedPage", CheckSelectedByText("分类", "android.widget.TextView"));
//		mm.waitFor(1);
		mm.getObjectByText("混搭", "android.widget.Button").click();
		mm.waitFor(2);
		
		mm.log("Step 3 : Check Icon.");
		mm.getObjectByText("图标", "android.widget.TextView").click();
		mm.waitFor(2);
		mm.saveScreenshot("icon.png");
		mm.getObjectByText("排行", "android.widget.TextView").click();
		cc.assertTrue("CheckFeaturedButton", CheckSelectedByText("排行", "android.widget.TextView"));
		mm.waitFor(1);
		mm.saveScreenshot("ranking.png");
		mm.getObjectByText("免费", "android.widget.TextView").click();
		mm.waitFor(2);
		mm.saveScreenshot("free.png");
		UiObject View = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.FrameLayout").index(0)) ;
		View.click() ;
	//	mm.clickOnButton("下载");
		
		//mm.click(282, 596);
        
		if(mm.getObjectByText("应用")!=null){
			mm.pressBack();
		}
		if(mm.getObjectByText("下载")!=null){
			mm.clickOnButton("下载");
			mm.waitFor(5);
			mm.saveScreenshot("download.png");
			mm.pressBack();
		}
		
	/*	String text = new UiObject(new UiSelector().className("android.widget.ScrollView")).getChild(new UiSelector().className("android.widget.TextView").instance(0)).getText();
		if(mm.isTextExist("下载")){
			mm.clickOnButton("下载");
			for(int i = 0; i < 4; i++ ){
				mm.waitFor(60);
				//屏幕唤醒
		//		int DisplayWidth ;
		//		DisplayWidth = this.getUiDevice().getDisplayWidth() ;
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
				if(mm.getObjectByText("应用").isEnabled() == true)
					cc.assertTrue("DownloadFinished", true);
					break;				
			}
		}*/
	//	mm.pressBack();
		mm.waitFor(2);
		
		mm.log("Step 3 : Show In My List.");	
		mm.click(60, 127);//点击我的列表
		mm.waitFor(2);
		mm.saveScreenshot("Mylist.png");
//		cc.assertTrue("Show In List", mm.isTextExist(text));
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
