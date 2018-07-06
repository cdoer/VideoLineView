package yxy.videolineview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    VideoLineView videoLineView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoLineView = findViewById(R.id.videoLineView);
        List<VideoInfo> videoInfoList = new ArrayList<VideoInfo>();
        /*
        try {
            videoInfoList.add(new VideoInfo(DateUtils.formatDate("2018-06-29 08:30:20",
                    DateUtils.DEFAULT_FORMAT_DATE),DateUtils.formatDate("2018-06-29 10:26:18",DateUtils.DEFAULT_FORMAT_DATE)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        */
        videoInfoList.add(new VideoInfo(new Date(new Date().getTime()-1000*60*60),new Date()));

        videoLineView.setVideoInfos(videoInfoList);
        videoLineView.setVideolineChangeListener(new VideoLineViewChangeListener() {
            @Override
            public void onChange(Date date, VideoLineView videoLineView) {
                Toast.makeText(getApplicationContext(),DateUtils.formatDate(date,DateUtils.DEFAULT_FORMAT_DATE),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
