package me.goldze.mvvmhabit.http.net;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.google.gson.JsonParseException;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.goldze.mvvmhabit.base.BaseApplication;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.http.net.entity.BaseEntity;
import me.goldze.mvvmhabit.utils.ToastUtil;
import me.goldze.mvvmhabit.utils.ZLog;
import me.goldze.mvvmhabit.utils.constant.TipsConstants;
import retrofit2.HttpException;

import static me.goldze.mvvmhabit.http.net.DefaultObserver.ExceptionReason.BAD_NETWORK;
import static me.goldze.mvvmhabit.http.net.DefaultObserver.ExceptionReason.CONNECT_ERROR;
import static me.goldze.mvvmhabit.http.net.DefaultObserver.ExceptionReason.CONNECT_TIMEOUT;
import static me.goldze.mvvmhabit.http.net.DefaultObserver.ExceptionReason.PARSE_ERROR;
import static me.goldze.mvvmhabit.http.net.DefaultObserver.ExceptionReason.UNKNOWN_ERROR;
import static me.goldze.mvvmhabit.http.net.RequestCodeUtil.SUCCESS;


/**
 * description: 对请求的操作封装
 * author: Andy
 * date: 2019/9/9 0009 16:20
 */
public abstract class DefaultObserver<T extends BaseEntity> implements Observer<T> {
    private List<Disposable> disposableList;
    private SmartRefreshLayout smartRefreshLayout;
    private BaseViewModel baseViewModel;
    private ShimmerRecyclerView shimmerRecyclerView;

    public DefaultObserver() {
    }

    public DefaultObserver(ShimmerRecyclerView shimmerRecyclerView) {
        this.shimmerRecyclerView = shimmerRecyclerView;
    }

    public DefaultObserver(BaseViewModel baseViewModel) {
        this.baseViewModel = baseViewModel;
    }


    public DefaultObserver(List<Disposable> disposableList, BaseViewModel baseViewModel) {
        this.disposableList = disposableList;
        this.baseViewModel = baseViewModel;
    }


    public DefaultObserver(List<Disposable> disposableList) {
        this.disposableList = disposableList;
    }

    public DefaultObserver(SmartRefreshLayout smartRefreshLayout) {
        this.smartRefreshLayout = smartRefreshLayout;
    }

    public DefaultObserver(List<Disposable> disposableList, SmartRefreshLayout smartRefreshLayout) {
        this.disposableList = disposableList;
        this.smartRefreshLayout = smartRefreshLayout;
    }

    public DefaultObserver(ShimmerRecyclerView shimmerRecyclerView, SmartRefreshLayout smartRefreshLayout) {
        this.smartRefreshLayout = smartRefreshLayout;
        this.shimmerRecyclerView = shimmerRecyclerView;
    }


    @Override
    public void onSubscribe(Disposable d) {
        if (disposableList != null) {
            disposableList.add(d);
        }
    }


    @Override
    public void onNext(T response) {
        ZLog.e(response);
        dismissProgress();
        //是否获取数据成功
        if (response.getCode()==SUCCESS) {
            onSuccess(response);
        } else {
            onFail(response);
        }
    }


    @Override
    public void onError(Throwable e) {
        ZLog.e(e.toString());
        dismissProgress();
        if (e instanceof HttpException) {     //   HTTP错误
            onException(BAD_NETWORK);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {   //   连接错误
            onException(CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {   //  连接超时
            onException(CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {   //  解析错误
            onException(PARSE_ERROR);
        } else {
            onException(UNKNOWN_ERROR);
        }
    }

    @Override
    public void onComplete() {
    }

    /**
     * 请求成功
     *
     * @param response 服务器返回的数据
     */
    abstract public void onSuccess(T response);

    /**
     * 服务器返回数据，但响应码不为40001
     *
     * @param response 服务器返回的数据
     */
    public void onFail(T response) {
        ToastUtil.errorToast(BaseApplication.getInstance().getBaseContext(), response.getMsg(), false);
    }

    /**
     * 请求异常
     *
     * @param reason
     */
    public void onException(ExceptionReason reason) {
        ToastUtil.errorToast(BaseApplication.getInstance().getBaseContext(), setErrorText(reason), false);
    }

    /**
     * 请求网络失败原因
     */
    public enum ExceptionReason {
        /**
         * 解析数据失败
         */
        PARSE_ERROR,
        /**
         * 网络问题
         */
        BAD_NETWORK,
        /**
         * 连接错误
         */
        CONNECT_ERROR,
        /**
         * 连接超时
         */
        CONNECT_TIMEOUT,
        /**
         * 未知错误
         */
        UNKNOWN_ERROR,
    }

    /**
     * 缺省页
     *
     * @author Andy
     * created at 2019/3/26 0026 11:50
     */
    private void errorPage(int image, String hint) {
//        if (emptyLayout != null) {
//            emptyLayout.showError(image, hint);
//        } else {
//            new TipsPopup(null, hint, R.mipmap.tips_icon).showPopupWindow();
//        }
    }

    private void dismissProgress() {
        if (smartRefreshLayout != null) {
            smartRefreshLayout.finishLoadMore();
            smartRefreshLayout.finishRefresh();
        }
        if (baseViewModel != null) {
            baseViewModel.dismissDialog();
        }
        if (shimmerRecyclerView != null) {
            shimmerRecyclerView.hideShimmerAdapter();
        }
    }

    public String setErrorText(DefaultObserver.ExceptionReason reason) {
        switch (reason) {
            case CONNECT_ERROR:
                return TipsConstants.CONNECT_ERROR;
            case CONNECT_TIMEOUT:
                return TipsConstants.CONNECT_TIMEOUT;

            case BAD_NETWORK:
                return TipsConstants.BAD_NETWORK;

            case PARSE_ERROR:
                return TipsConstants.PARSE_ERROR;
            case UNKNOWN_ERROR:
                return TipsConstants.UNKNOWN_ERROR;
        }
        return "";
    }


}
