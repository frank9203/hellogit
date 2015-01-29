package com.miui.voice;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0001_VoiceAssistantStressTest extends UiAutomatorTestCase{
	Marmot mm ;
	Checker cc ;


	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass()) ;
		cc = new Checker() ;
	}

	public void test_0001_VoiceAssistantStressTest() throws UiObjectNotFoundException{
		mm.log("Step 1 : OpenUnlock");
		int DisplayWidth ;
		int i = 0 ;
		int j = 0 ;
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

		mm.log("Step 2: Start VoiceAssistant Stress Test");
		mm.pressHome();
		mm.getObjectByText("系统工具", "android.widget.TextView").click();
		mm.waitFor(2);
		while(i<50){
			mm.getObjectByText("语音助手", "android.widget.TextView").click();
			if(mm.isTextExist("声明与条款")){
				mm.getObjectByText("同意并继续", "android.widget.Button").click();
			}
			mm.waitFor(2);
			mm.pressBack();
			i++ ;
		}

		/*mm.log("Step 3: Press Voice Stress Test");
		mm.getObjectByText("语音助手", "android.widget.TextView").click();
		while(j<5){
			UiObject voice = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
			.getChild(new UiSelector().className("android.widget.FrameLayout").index(0))
			.getChild(new UiSelector().className("android.widget.ImageView").index(2)) ;
			voice.click() ;
			mm.waitFor(6);
			j++ ;
			voice.click() ;
		}*/
			
		
		mm.pressBack(3);
	}

	public void found_FC(String str) throws UiObjectNotFoundException{
		mm.waitFor(2);
		if (mm.getObjectByText("报告 MIUI") != null){
			mm.log(str+" FC.");
			mm.clickOnButton("确定");
		}
	}


	@Override
	protected void tearDown() throws Exception{
		mm.finish();
		super.tearDown();

	}

}
