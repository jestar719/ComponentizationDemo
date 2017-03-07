package cn.jestar.girl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import cn.jestar.common.base.BaseActivity;
import cn.jestar.common.image.ImageManager;
import cn.jestar.projectlibrary.NavigationPath;

/**
 * Created by jestar on 17-3-6.
 */
@Route(path = NavigationPath.GIRL_PHOTO)
public class PhotoActivity extends BaseActivity {
    @BindView(R.id.girl_iv_photo)
    public ImageView mImageView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_photo;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        ImageManager.loadImage(mImageView, url);
    }
}
