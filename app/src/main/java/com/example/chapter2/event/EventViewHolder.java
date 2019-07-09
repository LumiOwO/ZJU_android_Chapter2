package com.example.chapter2.event;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.chapter2.DetailActivity;
import com.example.chapter2.R;

public class EventViewHolder extends RecyclerView.ViewHolder
{
	private TextView mExprText;
	private TextView mDueTimeText;

	public EventViewHolder(@NonNull final View itemView)
	{
		super(itemView);
		mExprText = itemView.findViewById(R.id.exprText);
		mDueTimeText = itemView.findViewById(R.id.dueTimeText);

		itemView.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
//				Log.d("click", getAdapterPosition() + "");
				// get activity
				Context activity = itemView.getContext();
				// create intent
				Intent intent = new Intent(activity, DetailActivity.class);
				int index = getAdapterPosition();
				intent.putExtra("event", EventAdapter.mList.get(index));
				// change activity
				activity.startActivity(intent);
			}
		});
	}

	// setters
	public void setExprText(String text)
	{
		mExprText.setText(text);
	}

	public void setDueTimeText(String text)
	{
		mDueTimeText.setText(text);
	}

	public void setDueTimeColor(int colorID)
	{
		mDueTimeText.setTextColor(mExprText.getResources().getColor(colorID, null));
	}


}
