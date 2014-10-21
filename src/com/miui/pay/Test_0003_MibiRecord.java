package com.miui.pay;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0003_MibiRecord extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	public static int device_type;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
		device_type = Get_device();
	}

	public void test_003_Charge_Cost_Record() throws UiObjectNotFoundException {
		if (!mm.isScreenOn()) {
			mm.wakeUp();
			mm.waitFor(2);
		}
		if (device_type == 2) {
			mm.moveUp();
			mm.move(360, 1280, 360, 1000);
			mm.pressHome();
			mm.pressMenu();
			mm.clickOnImage(0);
		} else if (device_type == 3) {
			mm.moveUp();
			mm.move(540, 1900, 540, 1460);
			mm.pressMenu();
			mm.clickOnImage(0);
		}
		mm.pressHome();
		mm.log("Step 1 : Launch mibi app.");
		mm.launchActivity("com.xiaomi.payment/.MiliCenterEntryActivity");
		FC_ANR("open mibicenter");
		mm.waitFor(3);

		if (!mm.isTextExist("我的米币")) {
			mm.waitFor(2);
		}

		mm.log("Step2:show charge Record");
		mm.clickOnText("充值记录");
		FC_ANR("chargeRecord");
		if (mm.isTextExist("正在加载")) {
			mm.waitFor(4);
		}
		cc.assertTextExist("米币");
		mm.waitFor(3);
		mm.saveScreenshot("record.png");
		mm.log("Step3:open one alipay charge record");
		UiScrollable chargelist = new UiScrollable(new UiSelector().className(
				"android.widget.ListView").index(0));
		chargelist.scrollTextIntoView("支付宝");
		mm.clickOnText("支付宝");
		mm.waitFor(3);
		mm.saveScreenshot("chargedetail.png");
		cc.assertTextExist("充值订单号");
		mm.pressBack(2);
		mm.waitFor(2);

		mm.log("Step 4: open the costs record");
		mm.clickOnText("消费记录");
		if (mm.isTextExist("正在加载")) {
			mm.waitFor(4);
		}
		cc.assertTextExist("消费记录");

		mm.log("Step5: open one cost record details page");
		UiScrollable costlist = new UiScrollable(new UiSelector().className(
				"android.widget.ListView").index(0));
		costlist.scrollTextIntoView("1米币");
		mm.clickOnText("1米币");
		mm.waitFor(3);
		if (mm.isTextExist("正在加载")) {
			mm.waitFor(3);
		}
		mm.saveScreenshot("costdetail.png");
		cc.assertTextExist("消费订单号");
		mm.pressBack(2);
	}

	public int Get_device() {
		String model = mm.shellReturn("getprop ro.product.name").replace("\n",
				"");
		mm.log("device model" + model);
		if (model.compareTo("mione_plus") == 0) {
			return 1;
		} else if (model.compareTo("taurus") == 0
				|| model.compareTo("aries") == 0) {
			return 2;
		} else if (model.compareTo("cancro") == 0
				|| model.compareTo("pisces") == 0) {
			return 3;
		} else {
			return 0;
		}
	}

	public void FC_ANR(String str) throws UiObjectNotFoundException {
		mm.waitFor(2);
		if (mm.getObjectByText("报告 MIUI") != null) {
			mm.log(str + " FC.");
			mm.clickOnButton("确定");
		}
	}

	@Override
	protected void tearDown() throws Exception {
		mm.finish();
		super.tearDown();
	}

}
