package com.miui.theme;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0002_PersonalAccounts extends UiAutomatorTestCase {
	public Marmot mm ;
	public Checker cc ;
	
	@Override
	protected void setUp() throws Exception{
		super.setUp();
		
		mm = new Marmot(this.getClass()) ;
		cc = new Checker() ;
	}
	
	public void test_0001_PersonalAccounts() throws UiObjectNotFoundException{
		
		
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
		
		
		mm.log("Step 2 : Check PersonalAccount");
		
		mm.pressHome();
		mm.clickOnText("主题风格");
		//点击进入我的按钮
		mm.clickOnImage(0);
		mm.waitFor(2);
		//判断小米账户是否登录
		if(mm.isTextExist("登录小米帐户")){
			mm.waitFor(1);
			mm.clickOnText("登录小米帐户");
			mm.clickOnButton("登录");
			mm.waitFor(2);
			
			UiObject username = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
			.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
			.getChild(new UiSelector().className("android.widget.EditText").index(0)) ;	
			username.setText("15321273480") ;
			mm.waitFor(2);
			
			UiObject Passward = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
			.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
			.getChild(new UiSelector().className("android.widget.LinearLayout").index(1)) ;
			Passward.setText("@huhu33521") ;
			mm.waitFor(1);
			mm.clickOnButton("登录");
			mm.waitFor(3);
			mm.clickOnButton("继续");
			mm.waitFor(2);
			mm.pressBack();
					
		}
		
		mm.clickOnText("我的帐户");
		mm.waitFor(1);
		mm.saveScreenshot("MyAccount.png");
		
		UiObject MyAccount = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(1));
		MyAccount.click() ;
//		mm.clickOnText("我的帐户");
		mm.waitFor(3);
		mm.pressBack();
		mm.clickOnText("已购列表");
		mm.waitFor(3);
		mm.saveScreenshot("PurchasedList.png");
		mm.pressBack(4);
	
	}	
	
	@Override
	protected void tearDown() throws Exception{
		mm.finish();
		super.tearDown();
	}

}
