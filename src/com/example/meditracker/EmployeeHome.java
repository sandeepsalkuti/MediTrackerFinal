package com.example.meditracker;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
public class EmployeeHome extends Activity {
	Button Logout,Viewpatientdetails,update;
	SQLiteDatabase db;
	TextView gid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
		setContentView(R.layout.activity_employee_home);
		Button Logout,Viewpatients,Viewemployee,update;
		Logout=(Button)findViewById(R.id.button3);
		Viewpatientdetails=(Button)findViewById(R.id.button1);
		update=(Button)findViewById(R.id.button2);
		gid=(TextView)findViewById(R.id.textView3);
		gid.setText(globalvariabel.GetUserName().toString());
		db=openOrCreateDatabase("MEDITRACKERDB", Context.MODE_PRIVATE, null);
		
		Logout.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	 			// TODO Auto-generated method stub
				Intent ad= new Intent(EmployeeHome.this,Login.class);
				startActivity(ad);
				finish();
			}
        	 });
		
		Viewpatientdetails.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View va) {
				
		    		Cursor c=db.rawQuery("SELECT * FROM Patient", null);
		    		if(c.getCount()==0)
		    		{
		    			showMessage("Error", "No records found");
		    			return;
		    		}
		    		StringBuffer buffer=new StringBuffer();
		    		while(c.moveToNext())
		    		{
		    			buffer.append("Name: "+c.getString(0)+"\n");
		    			buffer.append("Disease: "+c.getString(2)+"\n");
		    			buffer.append("Emailid: "+c.getString(3)+"\n");
		    			buffer.append("Mobilenumber: "+c.getString(5)+"\n");
		    			buffer.append("Dateofadmission: "+c.getString(6)+"\n");
		    			buffer.append("------------------------\n");
		    			
		    		}
		    		showMessage("Patient Details", buffer.toString());
			}
        	 });
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.employee_home, menu);
		return true;
	}
	public void showMessage(String title,String message)
    {
    	Builder builder=new Builder(this);
    	builder.setCancelable(true);
    	builder.setTitle(title);
    	builder.setMessage(message);
    	builder.show();
	}
    public void clearText()
    {
    	
    }

}
