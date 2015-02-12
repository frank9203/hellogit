package com.miui.lockscreen;

import android.os.RemoteException;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0001_UnLockscreenStressTest extends UiAutomatorTestCase{
	Marmot mm ;
	Checker cc ;
	
	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass()) ;
		cc = new Checker() ;
	}
	public void test_0001_UnloclscreenStressTest() throws RemoteException, UiObjectNotFoundException{
		mm.log("Step 1:OpenUnlock StreeTest 2000");
		int DisplayWidth ;
		int i = 0 ;
		DisplayWidth = this.getUiDevice().getDisplayWidth() ;
		while(i<2000){
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
				this.getUiDevice().sleep();
				mm.waitFor(2);
				i++ ;
			}
		}	
		mm.log(i+"");		
		
		
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
