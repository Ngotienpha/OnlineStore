package com.example.onlinestoreapp.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.onlinestoreapp.R;
import com.example.onlinestoreapp.activity.fragment.ViewerFragment;
import com.example.onlinestoreapp.model.MeetingModel;
import com.example.onlinestoreapp.retrofit.ApiStore;
import com.example.onlinestoreapp.retrofit.RetrofitClient;
import com.example.onlinestoreapp.utils.Utils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import live.videosdk.rtc.android.Meeting;
import live.videosdk.rtc.android.VideoSDK;
import live.videosdk.rtc.android.listeners.MeetingEventListener;

public class MeetingActivity extends AppCompatActivity {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiStore apiStore;
    private Meeting meeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_meeting);
        apiStore = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiStore.class);
        getMeetingIdFromServer();
    }

    private void getMeetingIdFromServer() {
        compositeDisposable.add(apiStore.getMeeting()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meetingModel -> {
                            if (meetingModel.isSuccess()){
                                String token = meetingModel.getResult().get(0).getToken();
                                String meetingId = meetingModel.getResult().get(0).getMeetingid();
                                String mode = "VIEWER";
                                boolean streamEnable = mode.equals("CONFERENCE");
                                // initialize VideoSDK
                                VideoSDK.initialize(getApplicationContext());

                                // Configuration VideoSDK with Token
                                VideoSDK.config(token);

                                // Initialize VideoSDK Meeting
                                meeting = VideoSDK.initMeeting(
                                        MeetingActivity.this, meetingId, "Online Store App",
                                        streamEnable, streamEnable, null, mode, false, null, null);

                                // join Meeting
                                meeting.join();
                                meeting.addEventListener(new MeetingEventListener() {
                                    @Override
                                    public void onMeetingJoined() {
                                        if (meeting != null) {
                                            if (mode.equals("VIEWER")) {
                                                getSupportFragmentManager()
                                                        .beginTransaction()
                                                        .replace(R.id.main, new ViewerFragment(), "viewerFragment")
                                                        .commit();
                                            }
                                        }
                                    }
                                });
                            }
                        },
                        throwable -> {
                            Log.d("logg", throwable.getMessage());
                        }
                ));
    }

    public Meeting getMeeting() {
        return meeting;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}