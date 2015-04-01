package com.miui.player.v6;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;
import com.android.uiautomator.core.UiDevice;

public class Test_0007_Monkey extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	private static int connection;
	private String name;
	private UiDevice mDevice= null;
	private static final int NON_SUMMARY = 5;
	
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();	
	}
	
	public void monkey_Test() throws UiObjectNotFoundException {
		if(!mm.isScreenOn()){
			mm.wakeUp();
			mm.waitFor(2);
			mm.move(540, 1750, 540, 1400);
			mm.pressHome();
		}
		
		mm.log("发现音乐页 test begins。");
		mm.launchActivity("com.miui.player/.ui.MusicBrowserActivity");
		mm.shell("monkey -p com.miui.player --throttle 100 --pct-majornav 0 --pct-syskeys 0 20000");
		mm.pressHome();
		mm.pressMenu();
		UiObject delete = new UiObject(new UiSelector().className("android.widget.ImageView").index(0));
		delete.click();
		
		mm.log("我的音乐页 test begins。");
		mm.launchActivity("com.miui.player/.ui.MusicBrowserActivity");
		mm.clickOnText("我的音乐");
		mm.shell("monkey -p com.miui.player --throttle 100 --pct-majornav 0 --pct-syskeys 0 20000");
		mm.pressHome();
		mm.pressMenu();
		UiObject delete1 = new UiObject(new UiSelector().className("android.widget.ImageView").index(0));
		delete1.click();
		
		mm.log("正在播放页 test begins。");
		mm.launchActivity("com.miui.player/.ui.MusicBrowserActivity");
		UiObject bar = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(2));
		bar.click();
		mm.shell("monkey -p com.miui.player --throttle 100 --pct-majornav 0 --pct-syskeys 0 10000");
		
	}
	
	protected void tearDown() throws Exception {
		mm.finish();
		super.tearDown();
	}
}
