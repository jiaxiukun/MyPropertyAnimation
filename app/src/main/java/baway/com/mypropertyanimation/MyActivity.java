package baway.com.mypropertyanimation;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import baway.com.mypropertyanimation.model.bean.MyBean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyActivity extends AppCompatActivity implements MyAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private LinearLayoutManager linearLayoutManager;
    private Handler handler = new Handler();
    private String url = "http://120.27.23.105/product/getProducts?pscid=1";
    private MyBean bean;
    private List<MyBean.DataBean> list = new ArrayList<>();
    //最后一条可见条目
    private int findLastVisibleItemPosition;
    private String page = "1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        //给recyclerview增加分割线
        recyclerView.addItemDecoration(new MyItemDecoration());
        initData();
        //滑动监听
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                Intent intent=new Intent(MyActivity.this,ThreeActivity.class);
                startActivity(intent);

            }

        });

    }

    //OkHttp异步加载数据
    private void initData() {
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (request != null && response.isSuccessful() && response.body() != null) {
                    String str = response.body().string();
                 bean = MyBean.objectFromData(str);
                }
                list = bean.getData();
                //handler.post开启子线程
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(list + "");
                        myAdapter = new MyAdapter(MyActivity.this, list);
                        recyclerView.setAdapter(myAdapter);
                    }
                });
            }
        });

    }

    @Override
    public void onItemClickListener(int position, View view) {


    }

    //分割线类
    class MyItemDecoration extends RecyclerView.ItemDecoration {
        /**
         * @param outRect 边界
         * @param view    recyclerView ItemView
         * @param parent  recyclerView
         * @param state   recycler 内部数据管理
         */
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            //设定底部边距为1px
            outRect.set(0, 0, 0, 1);
        }
    }

}
