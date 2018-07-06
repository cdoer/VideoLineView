package yxy.videolineview;

import java.util.Date;

/**
 * Created by yang on 2018-07-05.
 */

public class VideoInfo {

    private Date startTime;
    private Date endTime;

    public VideoInfo(){}

    public VideoInfo(Date startTime, Date endTime){
        this.startTime = startTime;
        this.endTime =endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

}
