package com.miui.theme;

import android.os.RemoteException;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0003_MashupMore extends UiAutomatorTestCase{
	public Marmot mm ;
	public Checker cc ;
	
	
	@Override 
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass()) ;
		cc = new Checker() ;
	}
	
	public void test_0001_MashupMore() throws RemoteException, UiObjectNotFoundException{
		
		mm.log("Step 1 : Unlock Open");
		int DisplayWidth ;
		DisplayWidth =  this.getUiDevice().getDisplayWidth() ;
	//	this.getUiDevice().sleep(); 
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
		
		mm.log("Step 2 : Search Mashop Mare");
		mm.pressHome();
		mm.clickOnText("主题风格");
		mm.clickOnText("分类");
		mm.clickOnButton("混搭");
		mm.clickOnButton("更多");
		mm.waitFor(2);
		mm.saveScreenshot("More.png");
		mm.clickOnText("备份当前主题设置");
		mm.clickOnButton("取消");
		mm.waitFor(2);
		mm.clickOnButton("更多");
		mm.waitFor(2);
		
		UiObject Setting = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(3))
		.getChild(new UiSelector().className("android.widget.ListView").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(1)) ;
		Setting.click() ;
		
		
//		mm.clickOnText("设置");
		mm.waitFor(2);
		mm.pressBack(4);
				
		
	}
	
	
	
	@Override
	protected void tearDown() throws Exception{
		mm.finish();
		super.tearDown();
	}

	public static void main(String[] args) {
		
	}

}
