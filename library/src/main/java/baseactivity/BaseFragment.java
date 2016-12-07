package baseactivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import utils.OkHttpUtils;

/**
 * Created by Gaojian on 2016/11/10.
 */
public abstract class BaseFragment extends Fragment implements IOnCreate {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), bindLayout(), null);
        ButterKnife.bind(view);
        initView(savedInstanceState);
        initData();
        loadData();
        return view;
    }

    @Nullable
    @Override
    public View getView() {
        return view;
    }

    /**
     * 找控件
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T findview(int viewId) {
        return (T) view.findViewById(viewId);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(getActivity());
    }

    public String parserAssets(String fileName) {
        try {
            InputStream in = getActivity().getAssets().open(fileName);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = in.read(b)) != -1) {
                os.write(b, 0, len);
            }
            Log.e("parserAssets json", os.toString());
            return os.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getAyn(String url, final int tag) {
        OkHttpUtils.get(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
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
}
