package com.example.chapter2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.chapter2.event.Event;

public class NewEventActivity extends AppCompatActivity
{
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newevent);

		// set confirm button callback
		Button button = findViewById(R.id.confirm_button);
		button.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// get input content
				EditText exprTextView = findViewById(R.id.new_exprText);
				EditText timeTextView = findViewById(R.id.new_timeText);
				EditText placeTextView = findViewById(R.id.new_placeText);

				String exprText = exprTextView.getText().toString();
				String timeText = timeTextView.getText().toString();
				String placeText = placeTextView.getText().toString();

				// create new event
				if(!exprText.isEmpty() && !timeText.isEmpty() && !placeText.isEmpty()
					&& timeText.matches("[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}")) {

					Event event = new Event(exprText, timeText, placeText);
					// send intent to main activity
					Intent intent = new Intent(NewEventActivity.this, MainActivity.class);
					intent.putExtra("insert", event);

					startActivity(intent);
				}
			}
		});
	}
}
