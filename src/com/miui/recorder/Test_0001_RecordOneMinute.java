package com.miui.recorder;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0001_RecordOneMinute extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0001_RecordOneMinute() throws UiObjectNotFoundException{
		mm.log("Step 1 : Launch recoder app.");
		mm.pressHome();
		mm.launchActivity("com.android.soundrecorder/.SoundRecorder");
		mm.waitFor(3);
				
		mm.log("Step 2 : Play sound recorder.");
		mm.clickOnImageButton("开始录音");
		mm.waitFor(10);
		
		mm.log("Step 3 : Stop.");
		mm.clickOnImageButton("停止录音");
		mm.waitFor(1);
		
		mm.log("Step 4 : Look at sound list.");
		mm.clickOnImageButton("查看录音列表");
		mm.waitFor(2);
		
		mm.log("Step 5 : Quit from sound recorder app.");
		mm.pressBack(2);
	}
	
	@Override
	protected void tearDown() throws Exception {
		mm.finish();
		super.tearDown();
	}

}
