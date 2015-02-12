package com.miui.weather;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0005_DeleteWeatherCity extends UiAutomatorTestCase{
	Marmot mm ;
	Checker cc ;
	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass()) ;
		cc = new Checker() ;
	}


	public void test_0001_AddCityWeather() throws UiObjectNotFoundException{
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

		mm.log("Step 2 : Connect with WLAN");
		mm.pressHome();
		cc.assertTrue("Connect with WLAN", connectWLAN("miuiauto_2", "8miuiautoCoder"));
		mm.pressBack(3);

		mm.log("Step 3: Open weather app");
		mm.pressHome();
		UiObject scroll = new UiObject(new UiSelector());
		scroll.swipeLeft(DisplayWidth) ;
		mm.waitFor(2);
		mm.getObjectByText("天气", "android.widget.TextView").click();
		mm.waitFor(3);
		mm.saveScreenshot("weather1.png");

		UiObject view = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(1))
		.getChild(new UiSelector().className("android.widget.FrameLayout").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(1))
		.getChild(new UiSelector().className("android.widget.ImageView").index(0));
		view.click() ;
		mm.waitFor(2);
		mm.getObjectByText("添加", "android.widget.Button").click() ;
		mm.waitFor(2);
		mm.getObjectByText("定位", "android.widget.TextView").click();
		mm.pressBack();
		mm.waitFor(3);
		/*mm.saveScreenshot("city1.png");
		UiObject view1 = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0)) ;
		view1.click() ;
		mm.waitFor(3);
		mm.saveScreenshot("City2.png");
		mm.waitFor(2);
		view.click() ;*/
		mm.clickOnButton("编辑");
		mm.waitFor(2);
		mm.saveScreenshot("view.png");
		mm.waitFor(1);
		//删除一个或多个城市
		UiObject view2 = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0)) 
		.getChild(new UiSelector().className("android.widget.ImageButton").index(0));
		view2.click() ;
		
		mm.clickOnButton("确定");
		mm.waitFor(3);
		mm.saveScreenshot("view2.png");
		mm.waitFor(2);
		mm.pressBack(3);

	}



	//判断网络链接
	private boolean connectWLAN(String wlanName, String password) throws UiObjectNotFoundException{
		boolean checkSelected = false;
		mm.launchActivity("com.android.settings/.MiuiSettings");
		mm.clickOnText("WLAN");


		if(!mm.isChecked(0)){
			mm.clickOnCheckBox(0) ;
			mm.waitFor(3);
		}
		if(!mm.isTextExist("已连接")){
			UiScrollable collectionObject = new UiScrollable(new UiSelector().scrollable(true)) ;
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


	@Override 
	protected void tearDown() throws Exception{
		mm.finish(); 
		super.tearDown();
	}

}
