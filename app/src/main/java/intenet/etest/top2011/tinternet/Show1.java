package intenet.etest.top2011.tinternet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by top2011 on 2017/12/19.
 */

public class Show1 extends Fragment {

    public TextView tv_show;



    public static final String TAG = "Show1";
    private EveryThing everyThing;
    private View view;
    private FragmentActivity activity;
    private MyService service;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        everyThing = new EveryThing(getContext());
        service = new MyService();
        activity.registerReceiver(service,new IntentFilter("com.intent.receive"));
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_show1, container, false);
        tv_show = view.findViewById(R.id.tv_show);
        Button bt = view.findViewById(R.id.bt_start);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                everyThing.start();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        Button bt_stop = view.findViewById(R.id.bt_stop);
        bt_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                everyThing.stop();
            }
        });
        return view;
    }
    class MyService extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "收到", Toast.LENGTH_SHORT).show();
            ResultBean data = intent.getParcelableExtra("data");
            tv_show.append(data.result);
            tv_show.append("\n");
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (everyThing != null) {
            everyThing.stop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        activity.unregisterReceiver(service);
    }
}

