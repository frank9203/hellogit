package com.miui.theme;
//hu

import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.*;

public class Test_0026_LockScreenWallpaperSetDesktopWallpaper extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	
	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0156_LocoScreenWallpaperSetDesktopWallpaper() throws UiObjectNotFoundException{
		
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
		mm.saveScreenshot("mashup.png");
		mm.log("Step 3 : Check LockScreenWallpaper.");
		mm.getObjectByText("锁屏壁纸", "android.widget.TextView").click();
		mm.waitFor(2);
		
		mm.log("Step 4 : Check My List.");
		
//		mm.click(60, 127);//我的列表坐标
		mm.clickOnImage(0);
		mm.waitFor(1);
		mm.saveScreenshot("Mylist.png");
		UiObject View = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0)) ;
		View.click() ;
		
		mm.log("Step 5 : Apply LockScreenWakkpaper");
		UiObject View1 = new UiObject(new UiSelector().className("android.widget.LinearLayout").index(2))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.ImageView").index(2)) ;
		View1.click() ;
		
	/*	mm.click(197, 491);//第一张图片坐标
		mm.waitFor(1);
		UiScrollable scroll = new UiScrollable(new UiSelector());
		scroll.swipeLeft(5);
		mm.waitFor(1);
		mm.click(978, 1819);
		mm.waitFor(1);
		mm.click(655,1421);//点击设置成桌面
*/		mm.saveScreenshot("Wallpaper.png");
		mm.waitFor(2);
		mm.pressBack(6);
		mm.pressHome();
		mm.saveScreenshot("DesktopWallpaper.png");

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
