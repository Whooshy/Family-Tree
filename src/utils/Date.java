package utils;

public class Date
{
	public int day, month;
	public long year;
	
	public Date(int day, int month, long year)
	{
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	public String getMonth()
	{
		switch(month)
		{
		case 1:
			return "January";
		case 2:
			return "February";
		case 3:
			return "March";
		case 4:
			return "April";
		case 5:
			return "May";
		case 6:
			return "June";
		case 7:
			return "July";
		case 8:
			return "August";
		case 9:
			return "September";
		case 10:
			return "October";
		case 11:
			return "November";
		case 12:
			return "December";
		default:
			return "INVALID";
		}
	}
	
	public String getFormal()
	{
		return getMonth() + " " + day + ", " + year;
	}
	
	public String getInformalMDY()
	{
		return String.format("%02d", month) + "/" + String.format("%02d", day) + "/" + year;
	}
	
	public String getInformalDMY()
	{
		return String.format("%02d", day) + "/" + String.format("%02d", month) + "/" + year;
	}
	
	public String getInformalYMD()
	{
		return year + "." + String.format("%02d", month) + "." + String.format("%02d", day);
	}
}
