package com.example.pushme;

import java.util.List;

import re.notifica.Notificare;
import re.notifica.beacon.BeaconRangingListener;
import re.notifica.model.NotificareBeacon;
import re.notifica.support.v7.app.ActionBarBaseActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class BeaconActivity extends ActionBarBaseActivity implements BeaconRangingListener {

	private ListView listView;
	protected ArrayAdapter<NotificareBeacon> beaconListAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beacon);
		listView = (ListView)findViewById(R.id.beacon_list_view);
		beaconListAdapter = new BeaconListAdapter(this, R.layout.beacon_list_cell);
		listView.setAdapter(beaconListAdapter);
		if (Notificare.shared().getBeaconClient() != null) {
			Notificare.shared().getBeaconClient().addRangingListener(this);
		}
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				NotificareBeacon beacon = beaconListAdapter.getItem(position);
				if (beacon != null && beacon.getNotification() != null) {
					Notificare.shared().openNotification(BeaconActivity.this, beacon.getNotification());
				}
			}
		});
	}

	@Override
	public void onRangingBeacons(final List<NotificareBeacon> beacons) {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				beaconListAdapter.clear();
				for (NotificareBeacon beacon : beacons) {
					beaconListAdapter.add(beacon);						
				}
			}
			
		});
	}
	
	/**
	 * List adapter to show a row per beacon
	 */
	public class BeaconListAdapter extends ArrayAdapter<NotificareBeacon> {

		private Activity context;
		private int resource;
		
		public BeaconListAdapter(Activity context, int resource) {
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
			ImageView iconView = (ImageView)rowView.findViewById(R.id.icon);
			NotificareBeacon beacon = getItem(position);
			nameView.setText(beacon.getName());
			if (beacon.getNotification() != null) {
//				messageView.setText(beacon.getNotification().getMessage());
				messageView.setText(String.format("%.2f / %d", getItem(position).getCurrentDistance(), getItem(position).getCurrentProximity()));
			} else {
				messageView.setText("");
			}
			if (beacon.getBeaconData() != null) {
				iconView.setContentDescription(String.format("%.2f", getItem(position).getCurrentDistance()));				
			} else {
				iconView.setContentDescription("n/a");
			}
			return rowView;
		}
		
		
		
	}
	
	
}
