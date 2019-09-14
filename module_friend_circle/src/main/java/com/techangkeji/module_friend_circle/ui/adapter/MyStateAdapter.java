package com.techangkeji.module_friend_circle.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kcrason.highperformancefriendscircle.adapters.NineImageAdapter;
import com.kcrason.highperformancefriendscircle.beans.FriendCircleBean;
import com.kcrason.highperformancefriendscircle.beans.OtherInfoBean;
import com.kcrason.highperformancefriendscircle.widgets.EmojiPanelView;
import com.kcrason.highperformancefriendscircle.widgets.NineGridView;
import com.kcrason.highperformancefriendscircle.widgets.VerticalCommentWidget;
import com.techangkeji.module_friend_circle.R;

import java.util.List;

import ch.ielse.view.imagewatcher.ImageWatcher;
import me.goldze.mvvmhabit.utils.ToastUtil;

public class MyStateAdapter extends BaseQuickAdapter<FriendCircleBean, BaseViewHolder> {
    private ImageWatcher mImageWatcher;
    private RequestOptions mRequestOptions;
    private DrawableTransitionOptions mDrawableTransitionOptions;
    private EmojiPanelView emojiPanelView;

    public MyStateAdapter(int layoutResId, @Nullable List<FriendCircleBean> data, ImageWatcher mImageWatcher,EmojiPanelView emojiPanelView) {
        super(layoutResId, data);
        this.mImageWatcher = mImageWatcher;
        this.mRequestOptions = new RequestOptions().centerCrop();
        this.mDrawableTransitionOptions = DrawableTransitionOptions.withCrossFade();
        this.emojiPanelView=emojiPanelView;

    }

    @Override
    protected void convert(BaseViewHolder helper, FriendCircleBean item) {
        setContentShowState(helper, item);
        setTime(helper, item);
        setImage(helper, item);
        setComment(helper, item);
        setPraiseNum(helper, item);
        setPraise(helper,item);

    }

    //设置展示全文
    private void setContentShowState(BaseViewHolder helper, FriendCircleBean friendCircleBean) {
        TextView txtState = helper.getView(R.id.txt_state);
        TextView txtContent = helper.getView(R.id.txt_content);
        txtContent.setText(friendCircleBean.getContentSpan());

        if (friendCircleBean.isShowCheckAll()) {
            txtState.setVisibility(View.VISIBLE);
            setTextState(helper, friendCircleBean.isExpanded());
            txtState.setOnClickListener(v -> {
                if (friendCircleBean.isExpanded()) {
                    friendCircleBean.setExpanded(false);
                } else {
                    friendCircleBean.setExpanded(true);
                }
                setTextState(helper, friendCircleBean.isExpanded());
            });
        } else {
            txtState.setVisibility(View.GONE);
            txtContent.setMaxLines(Integer.MAX_VALUE);
        }
    }

    //设置文字内容
    private void setTextState(BaseViewHolder helper, boolean isExpand) {
        TextView txtState = helper.getView(R.id.txt_state);
        TextView txtContent = helper.getView(R.id.txt_content);
        if (isExpand) {
            txtContent.setMaxLines(Integer.MAX_VALUE);
            txtState.setText("收起");
        } else {
            txtContent.setMaxLines(4);
            txtState.setText("全文");
        }
    }

    //设置时间
    private void setTime(BaseViewHolder helper, FriendCircleBean friendCircleBean) {
        TextView txtPublishTime = helper.getView(R.id.txt_publish_time);
        OtherInfoBean otherInfoBean = friendCircleBean.getOtherInfoBean();

        if (otherInfoBean != null) {
            txtPublishTime.setText(otherInfoBean.getTime());
        }
    }

    //设置九宫格图片
    private void setImage(BaseViewHolder helper, FriendCircleBean friendCircleBean) {
        NineGridView nineGridView = helper.getView(R.id.nine_grid_view);
        nineGridView.setOnImageClickListener((position1, view) ->
                mImageWatcher.show((ImageView) view, nineGridView.getImageViews(),
                        friendCircleBean.getImageUrls()));
        nineGridView.setAdapter(new NineImageAdapter(mContext, mRequestOptions,
                mDrawableTransitionOptions, friendCircleBean.getImageUrls()));
    }

    //设置评论
    private void setComment(BaseViewHolder helper, FriendCircleBean friendCircleBean) {
        VerticalCommentWidget verticalCommentWidget = helper.getView(R.id.vertical_comment_widget);
        if (friendCircleBean.isShowComment()) {
            verticalCommentWidget.setVisibility(View.VISIBLE);
            verticalCommentWidget.addComments(friendCircleBean.getCommentBeans(), false);
        } else {
            verticalCommentWidget.setVisibility(View.GONE);
        }
    }

    //设置点赞人数
    private void setPraiseNum(BaseViewHolder helper, FriendCircleBean friendCircleBean) {
        TextView txtPraiseContent = helper.getView(R.id.praise_content);

        if (friendCircleBean.isShowPraise()) {
            txtPraiseContent.setVisibility(View.VISIBLE);
            txtPraiseContent.setText(friendCircleBean.getPraiseSpan());
        } else {
            txtPraiseContent.setVisibility(View.GONE);
        }
    }

    //点击点赞
    private void setPraise(BaseViewHolder helper, FriendCircleBean friendCircleBean) {
        ImageView iv_praise = helper.getView(R.id.iv_praise);
        ImageView img_click_praise_or_comment=helper.getView(R.id.img_click_praise_or_comment);
        iv_praise.setOnClickListener(v -> {
            ToastUtil.normalToast(helper.itemView.getContext(), "点赞成功");
        });
        img_click_praise_or_comment.setOnClickListener(v -> emojiPanelView.showEmojiPanel());
    }
}
