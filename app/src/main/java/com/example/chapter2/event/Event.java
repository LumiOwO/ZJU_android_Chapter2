package com.example.chapter2.event;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event implements Serializable
{
	public static final int NORMAL = 0;
	public static final int WARNING = 1;
	public static final int URGENT = 2;
	public static final int FINISHED = 3;

	private String mExprText;
	private String mPlaceText;
	private LocalDateTime mTime;

	public Event(String exprText, String timeStr, String placeText)
	{
		mExprText = exprText;
		mPlaceText = placeText;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		mTime = LocalDateTime.parse(timeStr, formatter);
	}
	public Event(String exprText, String timeStr)
	{
		this(exprText, timeStr, "");
	}

	// getters
	public String getExprText()
	{
		return mExprText;
	}

	public String getPlaceText()
	{
		return mPlaceText;
	}

	public String getDueTimeText()
	{
		LocalDateTime nowTime = LocalDateTime.now();

		Duration duration = Duration.between(nowTime, mTime);
		long times[] = {
				duration.toDays(),
				duration.toHours(),
				duration.toMinutes(),
		};
		String str[] = {
				"天", "小时", "分钟"
		};
		int size = 3;

		String ret = "已结束" + size;
		for (int i = 0; i < size; i++)
			if (times[i] != 0) {
				ret = times[i] > 0 ?
						times[i] + str[i] + "后" + i :
						"已结束" + size;
				break;
			}

		return ret;
	}

	public String getRealTimeText()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm");
		return mTime.format(formatter);
	}

	@Override
	public boolean equals(Object obj)
	{
		Event event = (Event)obj;
		return mPlaceText.equals(event.mPlaceText)
			&& mTime.equals(event.mTime)
			&& mExprText.equals(event.mExprText);
	}

	public boolean isAfter(Event event)
	{
		return mTime.isAfter(event.mTime);
	}
}
