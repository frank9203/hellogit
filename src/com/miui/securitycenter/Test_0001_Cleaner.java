package com.miui.security;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0001_Cleaner extends UiAutomatorTestCase{  
	
	public Marmot mm;
	public Checker cc;
	
	protected void setUp() throws Exception{
		super.setUp();
		
		mm = new Marmot(this.getClass());
		cc    =  new Checker();
		
	}
	
    /*
     *   test_0001_cleaner  一键清理
     * */
	
	
   public  void test_0001_cleaner() throws UiObjectNotFoundException{
		 //step 1唤起屏幕 解锁 清理后台进程
		if(!mm.isScreenOn()){
			  mm.wakeUp();
			  mm.waitFor(1);
			  mm.moveUp();  	
			 clearProcess();//删除进程
			
		  }else{
			  
             clearProcess();//删除进程
		  }
		 
		//step 2点击进入安全中心
		
		cc.assertUiObejctExist(mm.getObjectByText("安全中心","android.widget.TextView"));
		mm.getObjectByText("安全中心","android.widget.TextView").click();
		mm.waitFor(1);
		
		//检查网络开关
		networkChecked();
		//step 3 点击进入垃圾清理
		cc.assertUiObejctExist(mm.getObjectByText("垃圾清理","android.widget.TextView" ));
		mm.getObjectByText("垃圾清理","android.widget.TextView" ).click();
		//检查云扫描 
		updateChecked();
		
	    //step 4点击开始扫描  
	    cc.assertUiObejctExist(mm.getObjectByText("开始扫描", "android.widget.Button"));
	    mm.getObjectByText("开始扫描","android.widget.Button").click();
	    //mm.waitFor(10);
	    
	    //判读扫描完成
	    UiObject  viewdeep_tv = new UiObject(new UiSelector().resourceId("com.miui.securitycenter:id/view_deep"));

	    
	    while(!"深度清理".equals(viewdeep_tv.getText())){
	    	
	    	mm.waitFor(3);
	    }
	    
	    mm.waitFor(2);
	    
	    //step 5点击完成按钮或一键清理按钮
	    
	    UiObject scan_btn = new UiObject(new UiSelector().resourceId("com.miui.securitycenter:id/btn_action"));
	    cc.assertUiObejctExist(scan_btn);
	    String scanStr = scan_btn.getText();
	    
	    UiObject num_tv = new UiObject(new UiSelector().resourceId("com.miui.securitycenter:id/number"));
	    cc.assertUiObejctExist(num_tv);
	    String  numStr = num_tv.getText();
	    
	    if("完成".equals(scanStr)){
	    	
	    	if("0".equals(numStr)){
	    		mm.log("扫描完成，未发现垃圾");
	    		scan_btn.click();
	    		mm.waitFor(1);	
	    	}
	    }else{
	    	//垃圾数量不为零，一键清理
		    	scan_btn.click();
		    	mm.waitFor(3);
		    	if("0".equals(numStr)){
		    	//清理完成
		    	scan_btn.click();	
		    	}

	    }
	   
   }
   
      /*
       * test_0002_cleaner 逐项清理
       * */
   public void test_0002_cleaner() throws UiObjectNotFoundException{
	   
	   //step 1唤起屏幕 解锁 清理后台进程
		if(!mm.isScreenOn()){
			  mm.wakeUp();
			  mm.waitFor(1);
			  mm.moveUp();  	
			 clearProcess();//删除进程
			
		  }else{
			  
            clearProcess();//删除进程
		  }
		 
		//step 2点击进入安全中心
		
		cc.assertUiObejctExist(mm.getObjectByText("安全中心","android.widget.TextView"));
		mm.getObjectByText("安全中心","android.widget.TextView").click();
		mm.waitFor(1);
		
		//检查网络开关
		networkChecked();
		//step 3 点击进入垃圾清理
		cc.assertUiObejctExist(mm.getObjectByText("垃圾清理","android.widget.TextView" ));
		mm.getObjectByText("垃圾清理","android.widget.TextView" ).click();
		mm.waitFor(1);
		//检查云扫描 
		updateChecked();
		
	    //step 4点击开始扫描  
	    cc.assertUiObejctExist(mm.getObjectByText("开始扫描", "android.widget.Button"));
	    mm.getObjectByText("开始扫描","android.widget.Button").click();

	    
	    //判断扫描完成
	    UiObject  viewdeep_tv = new UiObject(new UiSelector().resourceId("com.miui.securitycenter:id/view_deep"));

	    
	    while(!"深度清理".equals(viewdeep_tv.getText())){
	    	
	    	mm.waitFor(3);
	    }
	    mm.waitFor(2);
	    
	    //缓存垃圾
	    UiObject  handle_item_cache = new UiObject(new UiSelector().resourceId("com.miui.securitycenter:id/handle_item_cache"));
	    handle_item_cache.click();
	    mm.waitFor(1);
	    
	    UiObject  cleanup_btn      = new UiObject(new UiSelector().resourceId("com.miui.securitycenter:id/cleanup"));
	  
	    if(!cleanup_btn.isClickable()){
	    	mm.pressBack();
	    	mm.waitFor(1);
	    }else{
	    	cleanup_btn.click();
	    	mm.waitFor(1);
	    	mm.pressBack();
	    	mm.waitFor(1);
	    }
	    
	    //广告文件
	    UiObject  handle_item_ad = new UiObject(new UiSelector().resourceId("com.miui.securitycenter:id/handle_item_ad"));
	    handle_item_ad.click();
	    mm.waitFor(1);
	    if(!cleanup_btn.isClickable()){
	    	mm.pressBack();
	    	mm.waitFor(1);
	    }else{
	    	cleanup_btn.click();
	    	mm.waitFor(1);
	    	mm.pressBack();
	    	mm.waitFor(1);
	    }
	    
	    //安装包
	    UiObject  handle_item_apk = new UiObject(new UiSelector().resourceId("com.miui.securitycenter:id/handle_item_apk"));
	    handle_item_apk.click();
	    mm.waitFor(1);
	    if(!cleanup_btn.isClickable()){
	    	mm.pressBack();
	    	mm.waitFor(1);
	    }else{
	    	cleanup_btn.click();
	    	mm.waitFor(1);
	    	mm.pressBack();
	    	mm.waitFor(1);
	    }
	    //卸载残留
	    UiObject  handle_item_residual = new UiObject(new UiSelector().resourceId("com.miui.securitycenter:id/handle_item_residual"));
	    handle_item_residual.click();
	    mm.waitFor(1);
	    if(!cleanup_btn.isClickable()){
	    	mm.pressBack();
	    	mm.waitFor(1);
	    }else{
	    	cleanup_btn.click();
	    	mm.waitFor(1);
	    	mm.pressBack();
	    	mm.waitFor(1);
	    }
	    
	    
	    UiObject resultNum_tv = new UiObject(new UiSelector().resourceId("com.miui.securitycenter:id/number"));
	    if("0".equals(resultNum_tv.getText())){
	    	mm.log("逐项清理结束");
	    }
	    
	    
	   
   }
  
	  //Menu清理进程
	public void clearProcess() throws UiObjectNotFoundException{
		
		UiObject clear_icon = new UiObject(new UiSelector()
		.className("android.widget.ImageView").index(0)
		.resourceId("com.android.systemui:id/clearButton"));
		mm.pressHome();
		mm.waitFor(1);
	    for (int i=0; i<2; i++){
		mm.pressMenu();
		mm.waitFor(1);
		clear_icon.click();
		mm.waitFor(2);
	    }
	}
	
	//安全中心联网检查
	
	  public void networkChecked() throws UiObjectNotFoundException{
		 
		  UiObject setting_btn = new UiObject(new UiSelector().resourceId("com.miui.securitycenter:id/settings"));
		  setting_btn.click();
		  mm.waitFor(1);
		  if(mm.isChecked(1)){
			  mm.pressBack();
			  mm.waitFor(1);
		  }else{
			  mm.clickOnButton(1);
			  mm.waitFor(1);
			  mm.pressBack();
			  mm.waitFor(1);
		  }
		  
	  }
	//检查云扫描 
	  public void updateChecked() throws UiObjectNotFoundException{
		  
		  UiObject setting_btn = new UiObject(new UiSelector().resourceId("com.miui.securitycenter:id/settings"));
		  setting_btn.click();
		  mm.waitFor(1);
		  
		  if(!mm.isChecked(1)){
              mm.clickOnCheckBox(1);
              mm.waitFor(1);
		  }
		  mm.pressBack();
		  mm.waitFor(1);
	  }
	  
		protected void tearDown() throws Exception{
			super.tearDown();
			mm.finish();
		}
	

}
