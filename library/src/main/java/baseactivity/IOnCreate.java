package baseactivity;

import android.os.Bundle;

/**
 * Created by Gaojian on 2016/11/8.
 */
public interface IOnCreate {
    /**
     * 绑定布局文件
     *
     * @return
     */
    int bindLayout();

    /**
     * 初始化数据
     */
    void initData();

    /**
     * 初始化控件
     *
     * @param savedInstanceState
     */
    void initView(Bundle savedInstanceState);

    /**
     * 加载网络数据 oncreate方法自动调用
     */
    void loadData();

    /**
     * 网络请求成功
     */
    void requestSuccess(String json, int tag);
}
