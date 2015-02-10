package com.android.deskclock;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;
import com.android.uiautomator.core.UiDevice;
///writer:wangweiwei1
//Created:20150205_M4W_Dev_5.2.5
//Compiled: Successfully_0209


public class Test_T1693293_StopwatchTab extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}

	public void test_1693293_stopwatch_tab() throws UiObjectNotFoundException {
		if(!mm.isScreenOn()){
			mm.wakeUp();
			mm.move(360, 1000, 360, 1280);
			mm.pressHome();
		}
		mm.log("Preconditions: 进入秒表界面");
		mm.launchActivity("com.android.deskclock/.DeskClockTabActivity");
		mm.getObjectByText("秒表", "android.widget.TextView").click();
		mm.waitFor(2);
		cc.assertTrue("进入到秒表界面!", CheckCurrentView("com.android.deskclock/com.android.deskclock.DeskClockTabActivity"));

		mm.log("Step1: 秒表界面");
		mm.log("Step1 Excepted: 秒表图案居中显示，画面显示清晰，无重叠乱码等现象（图片对比暂时无法自动化实现，需要手动check）");
		cc.assertUiObejctExist(mm.getObjectByText("00:00.0", "android.widget.TextView"));
		
		mm.log("Step2: 点击“开始”，开始计时");
		mm.log("Step2 Excepted: 计时开始后，秒表动画显示(动画显示无法自动化实现，需要手动check)");
		mm.getObjectByText("开始", "android.widget.Button").click();
		mm.waitFor(2);
		//cc.assertUiObjectNotExist(mm.getObjectByText("00:00.0", "android.widget.TextView"));

		mm.log("Step3: 点击“计次”");
		mm.log("Step3 Excepted: 分次显示秒表计时");
		mm.getObjectByText("计次", "android.widget.Button").click();//这一步无法抓取到计次的对象
		mm.waitFor(2);
		cc.assertUiObejctExist(mm.getObjectByText("1", "android.widget.TextView")); //显示1
		cc.assertUiObejctExist(mm.getObjectByText("计次", "android.widget.TextView"));//显示计次

		mm.log("Step4: 点击“暂停”");
		mm.log("Step4 Excepted: 秒表暂停，动画停止");
		mm.getObjectByText("暂停", "android.widget.Button").click();

		mm.log("Step5: 点击“开始”");
		mm.log("Step5 Excepted: 计时继续");
		mm.getObjectByText("开始", "android.widget.Button").click();
		mm.waitFor(2);

		mm.log("Step6: 点击“重置”");
		mm.log("Step6 Excepted: 计时从零开始");
		mm.getObjectByText("暂停", "android.widget.Button").click();
		mm.waitFor(2);
		//cc.assertUiObjectNotExist(mm.getObjectByText("00:00.0", "android.widget.TextView"));

		mm.log("Step7: 计时列表上滑");
		mm.log("Step7 Excepted: 时钟表盘被隐藏(表盘被隐藏无法自动化实现，需要手动check)");
		mm.move(540,1650,540,650);
		mm.waitFor(2);

		mm.log("Step8: 上下滑动计时列表");
		mm.log("Step8 Excepted: 列表滑动动画流畅，不会出现卡顿，死机等现象");
		mm.move(540,650,540,1650);
		mm.waitFor(2);
		mm.move(540,1650,540,650);
	
	//private boolean CheckTextEqual(String textA, String textB) throws UiObjectNotFoundException{
	//	boolean equalResult = false;
	//
	//	if(textA equalsIgnoreCase textB){
	//		equalResult = true;
	//	}
	//	return equalResult;
	//}
	
	}

	private boolean CheckCurrentView(String activityName) throws UiObjectNotFoundException{
		boolean enterView = false;

		String trueName = mm.getCurrentActivityName();
		if(trueName.equalsIgnoreCase(activityName)){
			enterView = true;
		}
		return enterView;
	}

	@Override
	protected void tearDown() throws Exception {
		mm.finish();
		super.tearDown();
	}
}