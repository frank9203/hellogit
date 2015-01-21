package com.miui.theme1;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0025_ClassificationDownload extends UiAutomatorTestCase {
	Marmot mm ;
	Checker cc ;


	@Override 
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass()) ;
		cc = new Checker() ;
	}

	public void test_0001_ClassificationDownload() throws UiObjectNotFoundException{

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



		mm.log("Step 2 : Check XiaoMiAccount");
		mm.pressHome();
		mm.clickOnText("主题风格");
		//点击进入我的按钮
		mm.clickOnImage(0);
		mm.waitFor(2);
		mm.pressBack();

		//删除本地主题
		mm.log("Step 3 :Delete Local Theme");
		mm.getObjectByText("本地", "android.widget.TextView").click();
		mm.waitFor(2);
		mm.saveScreenshot("Local.png");
		mm.waitFor(1);
		UiObject theme = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.ListView").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(1)) ;

		//	判断手机分辨率，测试针对MI3编写
		if(theme.exists()){
			theme.longClick() ;
			mm.waitFor(2);
			if(mm.isTextExist("全选")){
				mm.clickOnButton("全选"); 
			}
			mm.saveScreenshot("delete.png");
			mm.waitFor(1);
			mm.clickOnButton("删除");
			mm.waitFor(1);
			mm.clickOnButton("确定");
			mm.waitFor(3);
		}
		mm.pressBack();

		//点击分类
		mm.log("Step 2 : Check Classification");
		mm.getObjectByText("分类", "android.widget.TextView").click();
		mm.waitFor(1);
		mm.saveScreenshot("classification.png");
		mm.waitFor(2);
		UiObject view = new UiObject (new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.view.View").index(0))
		.getChild(new UiSelector().className("android.widget.ImageView").index(0)) ;
		view.click() ;
		mm.waitFor(1);
		mm.saveScreenshot("view.png");
		mm.waitFor(1);

		//UiCheck Classification
		mm.log("Step 5 : UiCheck Classification");
		UiScrollable scroll = new UiScrollable(new UiSelector().scrollable(true)) ;
		//	scroll.scrollToEnd(10) ;
		mm.saveScreenshot("Scroll.png");


		//分别点击排行
		mm.log("Step 6: Check Ranking");
		UiObject ranking = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0));
		ranking.click() ;
		mm.waitFor(2);
		mm.getObjectByText("免费", "android.widget.TextView").click();
		mm.waitFor(1);
		mm.saveScreenshot("free.png");
		mm.waitFor(1);
		//	scroll.scrollToEnd(10);
		mm.waitFor(2);
		mm.log("Step 7 :Download free theme") ;
		UiObject theme1 = new UiObject (new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.ListView").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0));
		theme1.click() ;
		mm.waitFor(1);

		//下载判断

		mm.clickOnButton("下载");
		mm.waitFor(2);
		mm.saveScreenshot("Download.png");
		mm.waitFor(5);
		mm.pressBack(5);

	}


	@Override
	protected void tearDown() throws Exception{
		mm.finish();
		super.tearDown();
	}

}
