package com.miui.theme1;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0027_ClassificationTab extends UiAutomatorTestCase{
	Marmot mm ;
	Checker cc ;
	

	@Override 
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass()) ;
		cc = new Checker() ;
	}

	public void test_0001_ClassificationTab() throws UiObjectNotFoundException{

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
	    //点击分类
		mm.log("Step 2 : Check Classification");
		mm.getObjectByText("分类", "android.widget.TextView").click();
		mm.waitFor(1);
		mm.saveScreenshot("classification.png");
		mm.waitFor(2);
		for(int i=0;i<6;i++){	
		UiObject view = new UiObject (new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.view.View").index(0))
		.getChild(new UiSelector().className("android.widget.ImageView").index(i)) ;
		view.click() ;
		mm.waitFor(1);
		mm.saveScreenshot(i+"view.png");
		mm.waitFor(1);
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
