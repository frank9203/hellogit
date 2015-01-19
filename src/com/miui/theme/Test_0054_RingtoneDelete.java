package com.miui.theme;

import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.*;
import com.miui.marmot.lib.*;
public class Test_0054_RingtoneDelete extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;

	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0227_RingtoneDelete() throws UiObjectNotFoundException{
		
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
		
		
		
		mm.log("Step 1 : launch theme.");
		mm.pressHome();
		mm.clickOnText("主题风格");
		mm.waitFor(2);
		mm.getObjectByText("分类", "android.widget.TextView").click();
		cc.assertTrue("CheckFeaturedPage", CheckSelectedByText("分类", "android.widget.TextView"));
		mm.getObjectByText("混搭", "android.widget.Button").click();
		mm.waitFor(2);
		
		mm.log("Step 2 : check ringtone page.");
		mm.getObjectByText("来电铃声", "android.widget.TextView").click();
		mm.waitFor(2);
		
		mm.log("Step 3 : launch my list.");
		mm.click(60, 127);//点击我的列表
		mm.saveScreenshot("MyList.png");
		//UiObject object = new UiObject(new UiSelector().className("android.widget.TextView"));
		/*UiObject object = new UiObject(new UiSelector().resourceId("android:id/title"));
		object.longClick();
		mm.waitFor(2);
		mm.click(476, 473);
		mm.waitFor(2);
		mm.getObjectByText("删除", "android.widget.Button").click();
		mm.waitFor(2);
		mm.getObjectByText("确定", "android.widget.Button").click();
		mm.waitFor(1);*/
		mm.saveScreenshot("RingtoneDelete.png");
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
