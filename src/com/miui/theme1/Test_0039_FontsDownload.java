package com.miui.theme1;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0039_FontsDownload extends UiAutomatorTestCase {
	Marmot mm ;
	Checker cc ;
	
	@Override 
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass()) ;
		cc = new Checker() ;
	}
	
	public void test_0001_FontsDownload() throws UiObjectNotFoundException{
		mm.log("Step 1 : OpenUnlock");
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
			mm.waitFor(2);
		}


		mm.pressHome();
		mm.clickOnText("主题风格");
		//点击进入我的按钮


		//点击混搭

		mm.log("Step 2 :Check MashUp");
		mm.getObjectByText("混搭", "android.widget.TextView").click();
		mm.waitFor(2);
		mm.saveScreenshot("MashUp.png");
		mm.waitFor(1);

		// 点击字体
		mm.log("Step 3 Check Fonts");
		mm.getObjectByText("字体", "android.widget.TextView").click();
		mm.waitFor(1);
		mm.saveScreenshot("Fonts.png");
		mm.waitFor(1);
		//本地
		mm.log("Step 4 :delete Local ");
		UiObject  NewProducts = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(2));
		NewProducts.click() ;
		
		UiObject font = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(1));
		if(font.exists()){
			font.longClick() ;
			mm.waitFor(2);
			 if(mm.isTextExist("全选")){
				 mm.clickOnButton("全选"); 
			 }
			 mm.saveScreenshot("delete.png");
			 mm.waitFor(1);
			 mm.clickOnButton("删除");
			 mm.waitFor(1);
			 mm.clickOnButton("确定");
			 mm.waitFor(2);
			 
		}
		mm.pressBack();
		mm.waitFor(2);
		//分别点击排行
		mm.log("Step 5: Check Ranking");
		mm.getObjectByText("排行", "android.widget.TextView").click();
		mm.waitFor(1);
		mm.getObjectByText("免费", "android.widget.TextView").click();
		mm.waitFor(1);
		mm.saveScreenshot("free.png");
		mm.waitFor(1);

		mm.log("Step 6 :Download Fonts");
		//针对米3适配
		mm.click(284, 555);
		mm.waitFor(2);
		mm.clickOnButton("下载");
		mm.waitFor(3);
		mm.saveScreenshot("download.png");
		mm.waitFor(2);
		mm.pressBack(5);

	}
	
	
	@Override
	protected void tearDown() throws Exception{
		mm.finish();
		super.tearDown();
	}
      
}
