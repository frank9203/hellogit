package com.miui.theme;
//hu

import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.*;
public class Test_0022_LockScreenWallpaperDetailsOnline extends UiAutomatorTestCase{
	public Marmot mm;
	public Checker cc;
	
	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0149_LockScreenWallpaperDetails() throws UiObjectNotFoundException{
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
		mm.waitFor(1);
		mm.getObjectByText("混搭", "android.widget.Button").click();
		mm.waitFor(2);
		
		mm.log("Step 3 : Check LockScreenWallpaper.");
		mm.getObjectByText("锁屏壁纸", "android.widget.TextView").click();
        mm.saveScreenshot("suopingbizhi.png");
		mm.waitFor(2);
		/*mm.click(197, 1234);
		mm.waitFor(2);
		if(mm.isTextExist("下载")){
			mm.getObjectByText("下载", "android.widget.Button").click();
			new UiObject(new UiSelector().text("应用")).waitForExists(5);
		
		}*/
		
		
		UiObject View = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0));
		View.click() ;
		mm.waitFor(2);
		
		mm.log("Step 4 : Check LockScreenWSallpaper Details");
//		mm.click(102, 1819);
		UiObject View1 = new UiObject(new UiSelector().className("android.widget.LinearLayout").index(2))
		.getChild(new UiSelector().className("android.widget.RelativeLayout").index(0))
		.getChild(new UiSelector().className("android.widget.ImageView").index(0)) ;
		View1.click() ;		
		mm.waitFor(2);
		mm.getObjectByText("关闭", "android.widget.Button").click();
		mm.waitFor(2);
		mm.saveScreenshot("close.png");
		mm.pressBack(4);
		mm.pressHome();	
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
