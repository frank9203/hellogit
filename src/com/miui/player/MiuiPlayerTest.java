package com.miui.player;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class MiuiPlayerTest extends UiAutomatorTestCase{
	public Marmot mm;
	public Checker cc;
	public static int device_type;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
		device_type = get_Model();
	}	
	
	public int get_Model() throws UiObjectNotFoundException{
		String model = mm.shellReturn("getprop ro.product.model").replace("\n", "");
		mm.log(model);
	
		if(model.compareTo("MI 1")==0 || model.compareTo("MI 1S")==0){
			return 1;
		}else if(model.compareTo("MI 2A")==0 || model.compareTo("MI 2")==0 || model.compareTo("MI 2S")==0){
			return 2;
		}else if(model.compareTo("MI 3W")==0 || model.compareTo("MI 3TD")==0){
			return 3;
		}else{
			return 0;
		}
	}
	public void test_launch_music() throws UiObjectNotFoundException{
		
		mm.log(""+device_type);
	
	}
	
	@Override
	protected void tearDown() throws Exception {
		mm.finish();
		super.tearDown();
	}	
}
