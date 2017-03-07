package cn.jestar.girl;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.jestar.common.image.ImageManager;
import cn.jestar.projectlibrary.base.BaseModel;

/**
 * Created by jestar on 17-3-6.
 */

class GirlsPhotoAdapter extends BaseQuickAdapter<BaseModel, BaseViewHolder> {
    private int mWidth;

    public GirlsPhotoAdapter() {
        super(R.layout.view_girl_card);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mWidth = recyclerView.getWidth() / 2;
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseModel item) {
        final ImageView view = helper.getView(R.id.girl_iv_girl);
        String url = String.format("%s?imageView2/2/w/%s", item.getUrl(), mWidth);
        ImageManager.loadImage(view, url);
    }

}
