package com.miui.pay;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0006_Mes_Charge extends UiAutomatorTestCase {
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

    public void test_0006_Message_Charge() throws UiObjectNotFoundException {
        if (!mm.isScreenOn()) {
            mm.wakeUp();
            mm.waitFor(2);
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

        mm.log("Step2: open charge page");
        mm.clickOnButton("立即充值");
        FC_ANR("charge");
        if (mm.isTextExist("正在加载")) {
            mm.waitFor(6);
        }
        mm.waitFor(3);
        cc.assertTextExist("米币充值");
        mm.waitFor(3);
        mm.clickOnText("短信充值");
        cc.assertTextExist("短信充值");
        mm.waitFor(2);
        mm.log("Step 3:在短信页面点击立即充值button");
        mm.getObjectByText("立即充值", "android.widget.Button").click();
        mm.waitFor(3);
        mm.log("Step 4: 点击确认充值button");
        mm.getObjectByText("确认充值", "android.widget.Button").click();
        mm.waitFor(50);
        cc.assertTextExist("短信充值");
        cc.assertUiObejctExist(new UiObject(new UiSelector().className(
                "android.widget.TextView").text("1米币充值成功")));

        mm.saveScreenshot("MMSuccess.png");
        mm.waitFor(3);
        mm.clickOnButton("完成");
        mm.waitFor(2);
        mm.clickOnText("充值记录");
        mm.waitFor(4);
        assertTrue(mm.getObjectByTextContain("联通短信充值",
                "android.widget.TextView").exists());
        FC_ANR("msg_charge");
        // 移动短代
        /*
         * UiObject msgcharge= new UiObject(new
         * UiSelector().className("android.widget.Button").text("确认支付"));
         * msgcharge.clickAndWaitForNewWindow(3000); if
         * (mm.isTextExist("正在查询支付结果")) { mm.waitFor(15); }
         * mm.saveScreenshot("移动短信充值结果页面"); mm.waitFor(3); if
         * (mm.isTextExist("购买成功")) { cc.assertTextExist("购买成功");
         * mm.log("Step 8：移动支付完成"); mm.clickOnText("返回"); mm.waitFor(3);
         * UiObject chargeresult = new UiObject(new UiSelector()
         * .className("android.widget.LinearLayout")
         * .packageName("com.android.mms").index(0)); if
         * (!chargeresult.exists()) { mm.waitFor(15); }
         * cc.assertUiObejctExist(chargeresult); mm.waitFor(2); UiObject close =
         * new UiObject(new UiSelector() .className("android.widget.ImageView")
         * .packageName("com.android.mms").index(2)); close.click();
         * mm.waitFor(2); mm.log("Step 9:验证充值成功后查询页面");
         * cc.assertTextExist("1米币充值成功"); mm.saveScreenshot("移动查询充值成功页面");
         * mm.clickOnText("返回"); mm.waitFor(6);
         * mm.log("Step10：返回到米币中心首页查看充值记录是否存在"); mm.clickOnText("充值记录");
         * mm.waitFor(3); cc.assertTextExist("移动短信充值"); }
         * mm.saveScreenshot("移动短信充值失败界面");
         */

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
