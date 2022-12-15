package main.models;

public class PostJSONObject {
    private int hour;
    private int minute;
    private String requestId;

    public PostJSONObject(int hour, int minute, String requestId) {
        this.hour = hour;
        this.minute = minute;
        this.requestId = requestId;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
