package com.example.chapter2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chapter2.event.Event;

public class DetailActivity extends AppCompatActivity
{
	private Event event;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		// get event from intent
		event = (Event)getIntent().getSerializableExtra("event");

		// get widgets
		TextView exprView = findViewById(R.id.detail_expr);
		TextView timeView = findViewById(R.id.detail_time);
		TextView placeView = findViewById(R.id.detail_place);

		// set text content
		exprView.setText(event.getExprText());
		timeView.setText(event.getRealTimeText());
		placeView.setText(event.getPlaceText());

		// add button callback
		Button button = findViewById(R.id.delete_button);
		button.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(DetailActivity.this, MainActivity.class);
				intent.putExtra("delete", event);

				startActivity(intent);
			}
		});

	}
}
