package baseactivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import utils.OkHttpUtils;

/**
 * Created by Gaojian on 2016/11/8.
 */
public abstract class BaseActivity extends AppCompatActivity implements IOnCreate {
    private View mRootView;
    private String TAG = "BaseActivity";
    private FragmentManager manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (bindLayout() != 0) {
            mRootView = View.inflate(this, bindLayout(), null);
            setContentView(mRootView);
            manager = getSupportFragmentManager();
          /* StatusBarUtil.setTranslucent(this,50);
           StatusBarUtil.setTransparent(this);*/
            initView(savedInstanceState);
            initData();
            loadData();
        } else {

        }
    }

    public View getView() {
        return mRootView;
    }

    /* public void getAyn(String url, final Handler handler, final int tag) {
         OkHttpUtils.get(url, new Callback() {
             @Override
             public void onFailure(Call call, IOException e) {

             }

             @Override
             public void onResponse(Call call, Response response) throws IOException {
                 String str = response.body().string();
                 Log.e(TAG, "msg" + str);
                 Message msg = Message.obtain();
                 msg.obj = str;
                 msg.arg1 = tag;
                 handler.sendMessage(msg);
             }
         });
     }*/
    public void getAyn(String url, final int tag) {
        OkHttpUtils.get(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Log.e(TAG, "msg" + str);
                Message msg = Message.obtain();
                msg.obj = str;
                msg.arg1 = tag;
                handler.sendMessage(msg);
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            requestSuccess((String) msg.obj, msg.arg1);
        }
    };

    public void getPost(String url, Callback callback) {
        OkHttpUtils.get(url, callback);
    }

    public void showToast(CharSequence text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void startAct(Class<? extends BaseActivity> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    public void startAct(Class<? extends BaseActivity> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public <T extends View> T findview(int viewid) {
        return (T) findViewById(viewid);
    }

    @Override
    protected void onDestroy() {
        mRootView = null;
        super.onDestroy();
    }

    /**
     * 沉浸式状态栏
     */
    private void initState() {
        //Build.VERSION.SDK_INT进行版本判断
        //Build.VERSION_CODES.KITKAT      4.4版
       /* Google从Android kitkat(Android 4.4)开始,给我们开发者提供了一套能透明的系统ui样式给状态栏和导航栏，
        这样的话就不用向以前那样每天面对着黑乎乎的上下两条黑栏了，
        还可以调成跟Activity一样的样式，形成一个完整的主题,和IOS7.0以上系统一样了。*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * 添加
     */
    public void addFragment(BaseFragment basefragment, int layout) {

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(layout, basefragment);
        transaction.commit();
    }

    /**
     * 替换
     */
    public void replaceFragment(BaseFragment basefragment, int layout) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(layout, basefragment);
        transaction.commit();
    }

    public FragmentManager getManager() {
        return manager;
    }
}
