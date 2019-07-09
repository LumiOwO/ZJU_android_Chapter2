package com.example.chapter2;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.chapter2.event.Event;
import com.example.chapter2.event.EventAdapter;

public class MainActivity extends AppCompatActivity
{
	private EventAdapter mAdapter = new EventAdapter();

	// refresh handler
	private final Handler handler = new Handler();
	private final Runnable task = new Runnable()
	{
//		int cnt = 0;
		@Override
		public void run()
		{
			handler.postDelayed(task, 1000 * 60);
			mAdapter.refresh();
//			Log.d("refresh", cnt++ + "");
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// get recyclerView
		RecyclerView recyclerView = findViewById(R.id.recycle_view);

		// set layout manager
		LinearLayoutManager manager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(manager);

		// set adapter
		recyclerView.setAdapter(mAdapter);
		// set decoration
		DividerItemDecoration decoration = new DividerItemDecoration(
				this, DividerItemDecoration.VERTICAL);
		recyclerView.addItemDecoration(decoration);

		// set callback for newEvent button
		Button button = findViewById(R.id.new_button);
		button.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(MainActivity.this, NewEventActivity.class);

				startActivity(intent);
			}
		});
	}

	@Override
	protected void onStart()
	{
		super.onStart();
		// deal with intent
		Intent intent = getIntent();
		Event event = (Event)intent.getSerializableExtra("delete");
		if(event != null)
		{
			mAdapter.deleteItem(event);
			// use the intent only once
			intent.removeExtra("delete");
		}
		event = (Event)getIntent().getSerializableExtra("insert");
		if(event != null)
		{
			mAdapter.insertItem(event);
			// use the intent only once
			intent.removeExtra("insert");
		}
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		// refresh remain time
		handler.post(task);
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		// stop refresh
		handler.removeCallbacks(task);
	}

	// Singleton for main activity
	@Override
	protected void onNewIntent(Intent intent)
	{
		super.onNewIntent(intent);
		setIntent(intent);
	}
}
