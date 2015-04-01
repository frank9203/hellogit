package com.miui.compass;

/**
 * Project name : Marmot
 * Package name : com.miui.compass
 * Created by jiahuixing
 * Created on 2015-03-27
 * Created at 19:25
 */

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.miui.marmot.lib.Marmot;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class Test_Compass extends UiAutomatorTestCase {

	private static Marmot marmot;
	private static int isFirstLaunch = 0;

	public static final String LOCK_SCREEN = "com.android.keyguard";
	public static final String WLAN_NAME = "Frame_Privacy";
	public static final String WLAN_NAME_5G = "Frame_Privacy_5G";
	public static final String WLAN_PASSWORD = "jiahuixing";
	public static final String SETTINGS_ACTIVITY = "com.android.settings/.MiuiSettings";

	private static final String[] productNames = { "aries", "dior", };

	private static final String COMPASS_APP_NAME = "指南针";
	private static final String COMPASS_ACTIVITY_NAME = "com.miui.compass/.CompassActivity";

	public static boolean isWantedProduct(Marmot marmot) {
		String productName;
		productName = getProductName(marmot);
		int length;
		length = productNames.length;
		for (int i = 0; i < length; i++) {
			if (productNames[i].equals(productName)) {
				return true;
			}
		}
		return false;
	}

	public static String getProductName(Marmot marmot) {
		String productName;
		productName = marmot.shellReturn("getprop ro.build.product")
				.replace("\n", "").replace("\r", "");
		return productName;
	}

	public static void unlockPhone(Marmot marmot) throws InterruptedException {
		if (!marmot.isScreenOn()
				|| marmot.getCurrentPackageName().equals(LOCK_SCREEN)) {
			marmot.wakeUp();
			int startX, startY, endX, endY;
			startX = marmot.getDisplayWidth() / 2;
			startY = marmot.getDisplayWidth() * 9 / 10;
			endX = marmot.getDisplayWidth() / 2;
			endY = marmot.getDisplayWidth() * 2 / 3;
			marmot.move(startX, startY, endX, endY);
			marmot.pressHome();
		}
	}

	public static void clearAppData(Marmot marmot, String appName)
			throws UiObjectNotFoundException, InterruptedException {
		marmot.launchActivity(SETTINGS_ACTIVITY);
		UiScrollable settings;
		settings = new UiScrollable(
				new UiSelector().className("android.widget.ListView"));
		UiObject otherAppsManageSettings;
		String otherAppsManageSettingsText = "其他应用管理";
		otherAppsManageSettings = settings.getChildByText(
				new UiSelector().className("android.widget.TextView"),
				otherAppsManageSettingsText, true);
		otherAppsManageSettings.click();
		marmot.waitFor(1);
		UiScrollable allApps;
		allApps = new UiScrollable(
				new UiSelector().className("android.widget.ListView"));
		UiObject theApp;
		theApp = allApps.getChildByText(
				new UiSelector().className("android.widget.TextView"), appName,
				true);
		theApp.click();
		marmot.waitFor(1);
		UiScrollable scrollable;
		scrollable = new UiScrollable(
				new UiSelector().className("android.widget.ListView"));
		String clearDataText = "清除数据";
		UiObject clearAppData;
		clearAppData = scrollable.getChildByText(
				new UiSelector().resourceId("android:id/button1"),
				clearDataText);
		int i = 0;
		while (i < 5) {
			if (clearAppData.isEnabled()) {
				clearAppData.click();
				marmot.waitFor(1);
				UiObject confirm;
				confirm = new UiObject(new UiSelector().resourceId(
						"android:id/button1").textContains("确定"));
				if (confirm.exists()) {
					confirm.click();
					marmot.waitFor(1);
					marmot.pressHome();
				}
				break;
			} else {
				i += 1;
				marmot.waitFor(1);
			}
		}
		marmot.pressHome();
		// clearRecentApps(marmot);
	}

	public static void clearRecentApps(Marmot marmot)
			throws UiObjectNotFoundException {
		marmot.pressHome();
		marmot.pressMenu();
		UiObject clearMemory;
		clearMemory = marmot.getObjectByClass("android.widget.ImageView");
		clearMemory.click();
		marmot.waitFor(1);
	}

	public static void connectWlan(Marmot marmot) throws InterruptedException,
			UiObjectNotFoundException {
		marmot.launchActivity(SETTINGS_ACTIVITY);
		UiScrollable settings;
		settings = new UiScrollable(
				new UiSelector().className("android.widget.ListView"));
		UiObject wlan;
		String wlanText = "WLAN";
		wlan = settings.getChildByText(
				new UiSelector().className("android.widget.TextView"),
				wlanText, true);
		wlan.click();
		marmot.waitFor(1);
		UiObject wlanChecker;
		wlanChecker = new UiObject(new UiSelector().className(
				"android.widget.CheckBox").resourceId("android:id/checkbox"));
		if (!wlanChecker.isChecked()) {
			wlanChecker.click();
			marmot.waitFor(5);
		}
		String wlanConnectingText = "已连接";
		UiObject wlanConnecting;
		wlanConnecting = marmot.getObjectByText(wlanConnectingText,
				"android.widget.TextView");
		if (!wlanConnecting.exists()) {
			UiScrollable wlanList;
			wlanList = new UiScrollable(
					new UiSelector().className("android.widget.ListView"));
			UiObject myWlan;
			myWlan = wlanList.getChildByText(new UiSelector()
					.className("android.widget.CheckedTextView"), WLAN_NAME_5G,
					true);
			if (!myWlan.exists()) {
				myWlan = wlanList.getChildByText(new UiSelector()
						.className("android.widget.CheckedTextView"),
						WLAN_NAME, true);
			}
			myWlan.click();
			marmot.waitFor(1);
			UiObject passwordEditor;
			passwordEditor = new UiObject(new UiSelector().className(
					"android.widget.EditText").resourceId(
					"com.android.settings:id/password"));
			if (passwordEditor.exists()) {
				if (!passwordEditor.isFocused()) {
					passwordEditor.click();
				}
				passwordEditor.setText(WLAN_PASSWORD);
				UiObject connectButton;
				String connectButtonText = "连接";
				connectButton = new UiObject(new UiSelector().className(
						"android.widget.Button").text(connectButtonText));
				connectButton.click();
				marmot.waitFor(4);
			}
		}
		clearRecentApps(marmot);
	}

	public static void disConnectWlan(Marmot marmot)
			throws UiObjectNotFoundException {
		marmot.launchActivity(SETTINGS_ACTIVITY);
		UiScrollable settings;
		settings = new UiScrollable(
				new UiSelector().className("android.widget.ListView"));
		UiObject wlan;
		String wlanText = "WLAN";
		wlan = settings.getChildByText(
				new UiSelector().className("android.widget.TextView"),
				wlanText, true);
		wlan.click();
		marmot.waitFor(1);
		UiObject wlanChecker;
		wlanChecker = new UiObject(new UiSelector().className(
				"android.widget.CheckBox").resourceId("android:id/checkbox"));
		if (wlanChecker.isChecked()) {
			wlanChecker.click();
			marmot.waitFor(2);
		}
		clearRecentApps(marmot);
	}

	private void setIsFirstLaunch(int isFirstLaunch) {
		Test_Compass.isFirstLaunch = isFirstLaunch;
	}

	private int getIsFirstLaunch() {
		return isFirstLaunch;
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		marmot = new Marmot(this.getClass());
		init();
	}

	private void init() throws InterruptedException, UiObjectNotFoundException {
		marmot.log("unlock phone");
		unlockPhone(marmot);
		marmot.log("disconnect wlan");
		disConnectWlan(marmot);
		marmot.log("clear app data" + COMPASS_ACTIVITY_NAME);
		clearAppData(marmot, COMPASS_APP_NAME);
		setIsFirstLaunch(0);
	}

	public void test_Compass() throws UiObjectNotFoundException,
			InterruptedException {
		marmot.log("launch compass");
		marmot.launchActivity(COMPASS_ACTIVITY_NAME);
		if (getIsFirstLaunch() == 0) {
			marmot.log("is first launch");
			UiObject confirm;
			confirm = new UiObject(new UiSelector().resourceId(
					"android:id/button1").textContains("同意并继续"));
			if (confirm.exists()) {
				confirm.click();
				setIsFirstLaunch(1);
			}
		}
		if (isWantedProduct(marmot)) {
			marmot.log("is wanted product add calibration compass");
			calibrationCompass();
			marmot.moveLeft();
			marmot.pressHome();
			marmot.log("clear recent apps");
			clearRecentApps(marmot);
			marmot.log("connect wlan");
			connectWlan(marmot);
			marmot.log("re launch compass");
			marmot.launchActivity(COMPASS_ACTIVITY_NAME);
			marmot.log("re calibration compass");
			calibrationCompass();
		}
		marmot.pressBack(2);
	}

	private void calibrationCompass() throws UiObjectNotFoundException {
		UiObject calibration;
		calibration = new UiObject(new UiSelector().resourceId(
				"android:id/btn_calibrate_altitude").textContains("校准海拔"));
		if (calibration.exists()) {
			calibration.click();
			marmot.waitFor(1);
			UiObject confirm;
			confirm = new UiObject(new UiSelector().resourceId(
					"android:id/button1").textContains("确定"));
			if (confirm.exists()) {
				confirm.click();
			}
		}
	}

	@Override
	protected void tearDown() throws Exception {
		marmot.log("tearDown");
		marmot.finish();
		super.tearDown();
	}
}
