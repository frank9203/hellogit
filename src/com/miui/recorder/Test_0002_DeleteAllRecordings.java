package com.miui.recorder;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0002_DeleteAllRecordings extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0002_DeleteAllRecordings() throws UiObjectNotFoundException{
		mm.log("Step 1 : Launch recoder app.");
		mm.pressHome();
		mm.launchActivity("com.android.soundrecorder/.SoundRecorder");
		mm.waitFor(3);
		
		mm.log("Step 2 : Look at recording list.");
		mm.clickOnImageButton("查看录音列表");
		mm.waitFor(2);
		if(mm.isTextExist("没有录音")){
			return ;
		}
				
		mm.log("Step 3 : Select all recordings.");
		mm.getChildObjectOfList(new UiSelector().className("android.widget.LinearLayout")).longClick();
		if(mm.isTextExist("全选")){
			mm.clickOnText("全选");
		}
		
		mm.log("Step 4 : Delete.");
		mm.clickOnButton("删除");
		mm.waitFor(1);
		mm.clickOnButton("确定");
		mm.waitFor(3);
		cc.assertTextExist("没有录音");
		
		mm.log("Step 4 : Quit");
		mm.pressBack(2);
	}
	
	@Override
	protected void tearDown() throws Exception {
		mm.finish();
		super.tearDown();
	}

}
