package com.miui.theme1;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0040_DesktopWallpaperDownload extends UiAutomatorTestCase{
	Marmot mm ;
	Checker cc ;

	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass()) ;
		cc = new Checker() ;
	}

	public void test_0001_DesktopWallpaperDownload() throws UiObjectNotFoundException{
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

		//点击混搭

		mm.log("Step 2 Check MashUp");
		UiObject mashup = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(3));
		mashup.click() ;
		mm.saveScreenshot("MashUp.png");
		mm.waitFor(1);

		// 点击桌面壁纸
		mm.log("Step 3 Check DesktopWallpaper");

		UiObject DesktopWallpaper = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.GridView").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(3)) ;
		DesktopWallpaper.click() ;//点击无效使用坐标点击
		//	mm.click(798, 437);

		mm.waitFor(1);
		mm.saveScreenshot("DesktopWallpaper.png");
		mm.waitFor(1);

		//分别点击排行
		mm.log("Step 4 : Check Ranking");
		UiObject ranking = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0));
		ranking.click() ;
		mm.waitFor(1);
		
		//点击第一个图片
		UiObject view = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.ListView").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0)) ;
		view.click() ;
		mm.waitFor(1);
		mm.saveScreenshot("Preview.png");
		mm.waitFor(1);
		
		//资源详情
		mm.log("Step 5:Check Detail DesktopWallpaper");
		UiObject view1 = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(2))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(1))
		.getChild(new UiSelector().className("android.widget.RelativeLayout").index(0))
		.getChild(new UiSelector().className("android.widget.ImageView").index(0));
		view1.click() ;
		mm.saveScreenshot("Detail.png");
		mm.waitFor(2);
		mm.clickOnButton("关闭");
		mm.waitFor(1);
		
		mm.log("Step 6:Download DesktopWallpaper");
		if(mm.isTextExist("下载")){
			mm.clickOnButton("下载");
			mm.saveScreenshot("Download.png");
			mm.waitFor(2);
		}
		if(mm.isTextExist("应用")){
			mm.pressBack();
		}
		
		
		mm.pressBack(4);

	}


	@Override
	protected void tearDown() throws Exception{
		mm.finish();
		super.tearDown();
	}

}
