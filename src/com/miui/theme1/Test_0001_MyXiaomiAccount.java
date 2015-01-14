package com.miui.theme1;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0001_MyXiaomiAccount extends UiAutomatorTestCase{
	Marmot mm ;
	Checker cc ;
	
	@Override 
	protected void setUp() throws Exception{
		super.setUp();
        mm = new Marmot(this.getClass()) ;
        cc = new Checker() ;
	}
	
	
	public void test_0001_MyAccount() throws UiObjectNotFoundException{
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
		
		mm.log("Step 3 : Check XiaoMiAccount");
		mm.pressHome();
		mm.clickOnText("主题风格");
		//点击进入我的按钮
		mm.clickOnImage(0);
		mm.waitFor(2);
		//判断小米账户是否登录
		if(mm.isTextExist("未登录")){
			mm.waitFor(2);
	
			UiObject head = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
			.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
			.getChild(new UiSelector().className("android.widget.ImageView").index(0)) ;
			head.click() ;
			mm.waitFor(2);
			mm.clickOnButton("登录");
			mm.waitFor(2);
			UiObject username = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
			.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
			.getChild(new UiSelector().className("android.widget.EditText").index(0)) ;	
			username.setText("huhu33521@sina.com") ;
			mm.waitFor(2);
			
			UiObject Passward = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
			.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
			.getChild(new UiSelector().className("android.widget.LinearLayout").index(1)) ;
			Passward.setText("huhu33521") ;
			mm.waitFor(1);
			mm.clickOnButton("登录");
			mm.waitFor(3);
			mm.clickOnButton("继续");
			mm.waitFor(2);
			mm.pressBack();				
		}
		//我的收藏	   
		mm.clickOnText("我的收藏");
		mm.waitFor(1);
		mm.saveScreenshot("Myfavorites.png");
		mm.waitFor(2);
		//判断没有收藏的主题
	    if(mm.isTextExist("您还没有收藏的主题哦")){
	    	mm.pressBack();
	    }
		//查看收藏的主题
		if(mm.isTextExist("主题")){
			mm.clickOnText("主题");
			mm.saveScreenshot("theme.png");
			mm.waitFor(2);
			mm.pressBack();
			mm.waitFor(1);
		}
		//查看收藏的字体
		if(mm.isTextExist("字体")){
			mm.clickOnText("字体");
			mm.saveScreenshot("Fonts.png");
			mm.waitFor(2);
			mm.pressBack(2);
			mm.waitFor(1);
		
		}
		
		//已购列表
		mm.clickOnText("已购列表");
		mm.waitFor(1);
		mm.saveScreenshot("Purchasedlist.png");
		mm.waitFor(1);
		mm.pressBack();
		mm.waitFor(1);
		
		//米币中心
	   mm.clickOnText("米币中心");
	   mm.waitFor(1);
	   mm.saveScreenshot("McoinsCenter.png");
	   mm.waitFor(2);
	   mm.pressBack();
	   mm.waitFor(1);
	   
	   //我的礼物
	   mm.clickOnText("我的礼物");
	   mm.waitFor(1);
	   mm.saveScreenshot("Mygifts.png");
	   mm.waitFor(1);
	   
	   mm.getObjectByText("收到", "android.widget.TextView").click();
	   mm.saveScreenshot("Receivegift.png");
	   mm.waitFor(2);
	   mm.getObjectByText("购买", "android.widget.TextView").click();
	   mm.saveScreenshot("Buggifts.png");
	   mm.waitFor(2);
	   mm.pressBack();
		
	   mm.pressBack();		
		
	}
	

	
	//判断网络链接
	private boolean connectWLAN(String wlanName, String password) throws UiObjectNotFoundException{
		boolean checkSelected = false;
		mm.launchActivity("com.android.settings/.MiuiSettings");
		mm.clickOnText("WLAN");
	
		
		if(!mm.isChecked(0)){
			mm.clickOnCheckBox(0) ;
			mm.waitFor(3);
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
