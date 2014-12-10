package com.example.pushme;

import re.notifica.Notificare;
import re.notifica.model.NotificareInboxItem;
import re.notifica.support.v7.app.ActionBarBaseActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class InboxActivity extends ActionBarBaseActivity {

	private ListView listView;
	protected ArrayAdapter<NotificareInboxItem> inboxListAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beacon);
		listView = (ListView)findViewById(R.id.beacon_list_view);
		inboxListAdapter = new InboxListAdapter(this, R.layout.beacon_list_cell);
		listView.setAdapter(inboxListAdapter);
		
		listView.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				NotificareInboxItem item = inboxListAdapter.getItem(position);
//				Notificare.shared().getInboxManager().markItem(item);
				Notificare.shared().getInboxManager().removeItem(item);
				inboxListAdapter.remove(item);
//				Notificare.shared().openNotification(InboxActivity.this, item.getNotification());
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.beacon, menu);
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		inboxListAdapter.clear();
		if (Notificare.shared().getInboxManager() != null) {
			for (NotificareInboxItem item : Notificare.shared().getInboxManager().getItems()) {
				inboxListAdapter.add(item);
			}
		}
	}
	
	/**
	 * List adapter to show a row per beacon
	 */
	public class InboxListAdapter extends ArrayAdapter<NotificareInboxItem> {

		private Activity context;
		private int resource;
		
		public InboxListAdapter(Activity context, int resource) {
			super(context, resource);
			this.context = context;
			this.resource = resource;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = context.getLayoutInflater();
			View rowView = convertView;
			if (rowView == null) {
				rowView = inflater.inflate(resource, null, true);
			}
			TextView nameView = (TextView)rowView.findViewById(R.id.name);
			TextView messageView = (TextView)rowView.findViewById(R.id.message);
			NotificareInboxItem item = getItem(position);
			nameView.setText(item.getNotification().getTime().toString());
			messageView.setText(item.getNotification().getMessage() + (item.getStatus() ? "" : "(unread)") + String.valueOf(Notificare.shared().getInboxManager().getUnreadCount()));
			return rowView;
		}
		
		
		
	}
	
	
}
