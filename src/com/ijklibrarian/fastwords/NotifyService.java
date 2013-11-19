package com.ijklibrarian.fastwords;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.StrictMode;

public class NotifyService extends Service {
	private static final int ONGOING_NOTIFICATION = 1;
	
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate() {
		super.onCreate();
		
		// TODO: remove this and make network access async!
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy); 
		
		Notification notification = new Notification(R.drawable.ic_action_search, getText(R.string.app_name),
		        System.currentTimeMillis());
		Intent notificationIntent = new Intent(this, SelectWordSearch.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
		notification.setLatestEventInfo(this, getText(R.string.app_name),
		        getText(R.string.app_name), pendingIntent);
		startForeground(ONGOING_NOTIFICATION, notification);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
