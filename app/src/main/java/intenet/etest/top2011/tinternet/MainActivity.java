package intenet.etest.top2011.tinternet;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

public class MainActivity extends FragmentActivity {

    private FrameLayout fl_show;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fl_show = findViewById(R.id.fl_show);
        show();
    }

    private void show() {
        Show1 one=new Show1();
        FragmentManager sf = getSupportFragmentManager();
        FragmentTransaction st = sf.beginTransaction();
        st.replace(R.id.fl_show,one);
        st.commit();

    }
}
