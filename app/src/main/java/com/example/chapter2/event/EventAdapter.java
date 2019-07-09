package com.example.chapter2.event;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chapter2.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter
{
	public static ArrayList<Event> mList;

	public EventAdapter()
	{
		Event[] array = {
				new Event("起床", "2019-07-09 02:30"),
				new Event("刷牙", "2019-07-09 08:35"),
				new Event("上课", "2019-07-09 09:54", "教学楼"),
				new Event("下课", "2019-07-09 12:00", "教学楼"),
				new Event("吃午饭", "2019-07-09 12:30", "食堂"),
				new Event("写作业", "2019-07-09 14:00", "机房"),
				new Event("吃晚饭", "2019-07-09 18:00", "在寝室吃外卖"),
				new Event("睡觉", "2019-07-11 22:30", "寝室"),
		};
		mList = new ArrayList<Event>(Arrays.asList(array));
	}

	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
	{
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(
				R.layout.event_view, viewGroup, false);

		return new EventViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i)
	{

	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List payloads)
	{
		EventViewHolder viewHolder = (EventViewHolder)holder;
		Event event = mList.get(position);

		if(payloads.isEmpty()) {
			// if no payloads, refresh all
			viewHolder.setExprText(event.getExprText());
		}
		// if has payloads, refresh remain time only
		String dueTime = event.getDueTimeText();
		int len = dueTime.length();
		// set text content
		viewHolder.setDueTimeText(dueTime.substring(0, len-1));
		// set text color
		int type = dueTime.charAt(len-1) - '0';
		int colorID = 0;
		if(type == Event.NORMAL)
			colorID = R.color.normal;
		else if(type == Event.WARNING)
			colorID = R.color.warning;
		else if(type == Event.URGENT)
			colorID = R.color.urgent;
		else if(type == Event.FINISHED)
			colorID = R.color.completed;
		viewHolder.setDueTimeColor(colorID);
	}

	@Override
	public int getItemCount()
	{
		return mList.size();
	}

	public void refresh()
	{
		for(int i=0; i<mList.size(); i++)
			notifyItemChanged(i, 1);
	}

	public void deleteItem(@NonNull Event event)
	{
		int position = mList.indexOf(event);
		if(position >= 0) {
			// remove event
			mList.remove(position);
			// animation
			notifyItemRemoved(position);
			// rebind
			notifyDataSetChanged();
		}
	}

	public void insertItem(@NonNull Event event)
	{
		int index;
		for (index = 0; index < mList.size(); index++)
			if (mList.get(index).isAfter(event))
				break;

		// add event
		mList.add(index, event);
		// animation
		notifyItemInserted(index);
		// rebind
		notifyDataSetChanged();
	}

}
