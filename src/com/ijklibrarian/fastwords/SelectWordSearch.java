package com.ijklibrarian.fastwords;

import android.os.Bundle;
import android.app.Activity;
import android.app.ExpandableListActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.ClipboardManager;
import android.view.Menu;
import android.view.MenuItem;

public class SelectWordSearch extends ExpandableListActivity {
	
	ExpandableListAdapterSearch searchAdapter;
	public ClipboardManager clipboard;

    @SuppressWarnings("deprecation")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //ComponentName c=
        startService(new Intent(this,NotifyService.class));
       
    	clipboard=(android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

    	setContentView(R.layout.activity_select_word_search);
        
        searchAdapter=new ExpandableListAdapterSearch(this);
        setListAdapter(searchAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_select_word_search, menu);
        return true;
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.menu_settings:
			return false;
		case R.id.menu_quit:
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

    
}
