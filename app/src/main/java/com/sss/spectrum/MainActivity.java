package com.sss.spectrum;

import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.hjq.permissions.OnPermission;
import com.hjq.permissions.XXPermissions;
import com.sss.view.BesselView;
import com.sss.view.CircleRoundView;
import com.sss.view.ColumnarView;
import com.sss.view.GridPointView;
import com.sss.view.HexagramView;
import com.sss.view.RotatingCircleView;
import com.sss.view.AiVoiceView;
import com.sss.view.SpeedometerView;
import com.sss.view.WaveformView;
import com.sss.view.SlipBallView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{
    private Visualizer visualizer;
    private MediaPlayer player;
    private long time;

    private ColumnarView columnar;
    private WaveformView waveform;
    private RotatingCircleView rotatingCircle;
    private AiVoiceView aiVoice;
    private GridPointView gridPointView;
    private SpeedometerView speedometerView;
    private CircleRoundView circleRoundView;
    private BesselView besselView;
    private HexagramView hexagramView;
    private SlipBallView slipBallView;

    private Visualizer.OnDataCaptureListener dataCaptureListener = new Visualizer.OnDataCaptureListener() {


        @Override
        public void onWaveFormDataCapture(Visualizer visualizer, final byte[] data, int samplingRate) {
//            if (System.currentTimeMillis() - time > 1000 / AppConstant.FPS) {
//                byte[] newData = new byte[AppConstant.LUMP_COUNT];
//                byte abs;
//                for (int i = 0; i < AppConstant.LUMP_COUNT; i++) {
//                    abs = (byte) Math.abs(data[i]);
//                    //Math.abs -128时越界
//                    newData[i] = abs < 0 ? 127 : abs;
//                }
//                columnar.setWaveData(newData);
//                waveform.setWaveData(newData);
//                rotatingCircle.setWaveData(newData);
//                aiVoice.setWaveData(newData);
//                gridPointView.setWaveData(newData);
//                speedometerView.setWaveData(newData);
//                time = System.currentTimeMillis();
//            }
        }

        @Override
        public void onFftDataCapture(Visualizer visualizer, final byte[] fft, int samplingRate) {
//            Log.e("SSSSS", System.currentTimeMillis() - time + "---" + (2000 / AppConstant.FPS));
            if (System.currentTimeMillis() - time < 2000 / AppConstant.FPS) {
                return;
            }
            byte[] newData = new byte[AppConstant.LUMP_COUNT];
            byte abs;
            for (int i = 0; i < AppConstant.LUMP_COUNT; i++) {
                abs = (byte) Math.abs(fft[i]);
                //Math.abs -128时越界
                newData[i] = abs < 0 ? 127 : abs;
            }
            columnar.setWaveData(newData);
            waveform.setWaveData(newData);
            rotatingCircle.setWaveData(newData);
            aiVoice.setWaveData(newData);
            gridPointView.setWaveData(newData);
            speedometerView.setWaveData(newData);
            circleRoundView.setWaveData(newData);
            besselView.setWaveData(newData);
            hexagramView.setWaveData(newData);
            slipBallView.setWaveData(newData);
            time = System.currentTimeMillis();
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        columnar = findViewById(R.id.columnar);
        waveform = findViewById(R.id.waveform);
        rotatingCircle = findViewById(R.id.rotatingCircle);
        aiVoice = findViewById(R.id.aiVoice);
        gridPointView = findViewById(R.id.gridPointView);
        speedometerView = findViewById(R.id.speedometerView);
        circleRoundView = findViewById(R.id.circleRoundView);
        besselView = findViewById(R.id.besselView);
        hexagramView=findViewById(R.id.hexagramView);
        slipBallView=findViewById(R.id.slipBall);

        ((Switch)findViewById(R.id.switch_slip_ball)).setOnCheckedChangeListener(this);
        ((Switch)findViewById(R.id.switch_hexagramView)).setOnCheckedChangeListener(this);
        ((Switch)findViewById(R.id.switch_columnar)).setOnCheckedChangeListener(this);
        ((Switch)findViewById(R.id.switch_besselView)).setOnCheckedChangeListener(this);
        ((Switch)findViewById(R.id.switch_aiVoice)).setOnCheckedChangeListener(this);
        ((Switch)findViewById(R.id.switch_waveform)).setOnCheckedChangeListener(this);
        ((Switch)findViewById(R.id.switch_circleRoundView)).setOnCheckedChangeListener(this);
        ((Switch)findViewById(R.id.switch_speedometerView)).setOnCheckedChangeListener(this);
        ((Switch)findViewById(R.id.switch_rotatingCircle)).setOnCheckedChangeListener(this);
        ((Switch)findViewById(R.id.switch_gridPointView)).setOnCheckedChangeListener(this);
        XXPermissions.with(this)
                .permission("android.permission.RECORD_AUDIO")
                .request(new OnPermission() {
                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {

                        play();

                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {

                    }
                });


    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.switch_hexagramView:
                hexagramView.setEnable(isChecked);
                break;
            case R.id.switch_slip_ball:
                slipBallView.setEnable(isChecked);
                break;
            case R.id.switch_columnar:
                columnar.setEnable(isChecked);
                break;
            case R.id.switch_besselView:
                besselView.setEnable(isChecked);
                break;
            case R.id.switch_aiVoice:
                aiVoice.setEnable(isChecked);
                break;
            case R.id.switch_waveform:
                waveform.setEnable(isChecked);
                break;
            case R.id.switch_circleRoundView:
                circleRoundView.setEnable(isChecked);
                break;
            case R.id.switch_speedometerView:
                speedometerView.setEnable(isChecked);
                break;
            case R.id.switch_rotatingCircle:
                rotatingCircle.setEnable(isChecked);
                break;
            case R.id.switch_gridPointView:
                gridPointView.setEnable(isChecked);
                break;
        }
    }

    private void play() {
        player = MediaPlayer.create(MainActivity.this, R.raw.demo);
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
        player.setLooping(true);
        player.start();


        int mediaPlayerId = player.getAudioSessionId();
        if (visualizer == null) {
            visualizer = new Visualizer(mediaPlayerId);
        } else {
            visualizer.release();
        }
        //可视化数据的大小： getCaptureSizeRange()[0]为最小值，getCaptureSizeRange()[1]为最大值
        int captureSize = Visualizer.getCaptureSizeRange()[0];
        int captureRate = Visualizer.getMaxCaptureRate() * 3 / 4;

        visualizer.setCaptureSize(captureSize);
        visualizer.setDataCaptureListener(dataCaptureListener, captureRate, false, true);
        visualizer.setScalingMode(Visualizer.SCALING_MODE_NORMALIZED);
        visualizer.setEnabled(true);
    }
}
