package com.example.himalaya.presenters;

import android.util.Log;

import com.example.himalaya.base.BaseApplication;
import com.example.himalaya.interfances.IPlayerCallBack;
import com.example.himalaya.interfances.IPlayerPresenter;
import com.example.himalaya.utils.LogUtil;
import com.ximalaya.ting.android.opensdk.model.PlayableModel;
import com.ximalaya.ting.android.opensdk.model.advertis.Advertis;
import com.ximalaya.ting.android.opensdk.model.advertis.AdvertisList;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.player.XmPlayerManager;
import com.ximalaya.ting.android.opensdk.player.advertis.IXmAdsStatusListener;
import com.ximalaya.ting.android.opensdk.player.service.IXmPlayerStatusListener;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayerException;

import java.util.ArrayList;
import java.util.List;

public class PlayerPresenter implements IPlayerPresenter, IXmAdsStatusListener, IXmPlayerStatusListener {


    private List<IPlayerCallBack> mIPlayerCallbacks = new ArrayList<>();


    private XmPlayerManager mPlayerManager;

    private PlayerPresenter() {

        mPlayerManager = XmPlayerManager.getInstance(BaseApplication.getAppContext());
        //廣告相關的接口
        mPlayerManager.addAdsStatusListener(this);

        //註冊播放器狀態相關的接口
        mPlayerManager.addPlayerStatusListener(this);

    }

    private static PlayerPresenter sPlayerPresenter;

    public static PlayerPresenter getPlayerPresenter() {
        if (sPlayerPresenter == null) {
            synchronized (PlayerPresenter.class) {
                if (sPlayerPresenter == null) {
                    sPlayerPresenter = new PlayerPresenter();
                }
            }
        }
        return sPlayerPresenter;
    }

    private boolean isPlayListSet = false;

    public void setPlayList(List<Track> list, int playIndex) {

        if (mPlayerManager != null) {
            mPlayerManager.setPlayList(list, playIndex);
            isPlayListSet = true;
        } else {
            LogUtil.v("jeff", "mPlayerManager is null");
        }
    }


    @Override
    public void play() {

        if (isPlayListSet) {
            mPlayerManager.play();
        }
    }

    @Override
    public void pause() {

        if (mPlayerManager != null) {

            mPlayerManager.pause();

        }
    }

    @Override
    public void stop() {

    }

    @Override
    public void playPre() {
        //播放上一首
        if (mPlayerManager!=null) {
            mPlayerManager.playPre();
        }
    }

    @Override
    public void playNext() {
        //播放下一首
        if (mPlayerManager!=null) {
            mPlayerManager.playNext();
        }
    }

    @Override
    public void switchPlayMode(XmPlayListControl.PlayMode mode) {

    }

    @Override
    public void getPlayList() {

    }

    @Override
    public void playIndex(int index) {

    }

    @Override
    public void SeekTo(int progress) {

        //更新播放器的進度
        mPlayerManager.seekTo(progress);
    }

    @Override
    public boolean isPlay() {
        //返回當前是否正在播放

        return mPlayerManager.isPlaying();
    }

    @Override
    public void registerViewCallBack(IPlayerCallBack iPlayerCallBack) {

        if (!mIPlayerCallbacks.contains(iPlayerCallBack)) {

            mIPlayerCallbacks.add(iPlayerCallBack);
        }
    }

    @Override
    public void unRegisterViewCallBack(IPlayerCallBack iPlayerCallBack) {

        mIPlayerCallbacks.remove(iPlayerCallBack);
    }


    //====================================== 廣告相關的回調方法 start ===============================
    @Override
    public void onStartGetAdsInfo() {

        LogUtil.v("jeff", "onStartGetAdsInfo");

    }

    @Override
    public void onGetAdsInfo(AdvertisList advertisList) {

        LogUtil.v("jeff", "onGetAdsInfo");

    }

    @Override
    public void onAdsStartBuffering() {

        LogUtil.v("jeff", "onAdsStartBuffering");

    }

    @Override
    public void onAdsStopBuffering() {

        LogUtil.v("jeff", "onAdsStopBuffering");

    }

    @Override
    public void onStartPlayAds(Advertis advertis, int i) {

        LogUtil.v("jeff", "onStartPlayAds");

    }

    @Override
    public void onCompletePlayAds() {

        LogUtil.v("jeff", "onCompletePlayAds");

    }

    @Override
    public void onError(int what, int extra) {

        LogUtil.v("jeff", "onError what = " + what + "onError extra = " + extra);

    }
    //====================================== 廣告相關的回調方法 end ===============================

    //

    //=====================================播放器相關的回調方法 start============================
    @Override
    public void onPlayStart() {

        LogUtil.v("jeff", "onPlayStart   ");

        for (IPlayerCallBack iPlayerCallback : mIPlayerCallbacks) {
            iPlayerCallback.onPlayStart();
        }
    }

    @Override
    public void onPlayPause() {

        LogUtil.v("jeff", "onPlayPause");

        for (IPlayerCallBack iPlayerCallback : mIPlayerCallbacks) {
            iPlayerCallback.onPlayPause();
        }

    }

    @Override
    public void onPlayStop() {

        LogUtil.v("jeff", "onPlayStop");

        for (IPlayerCallBack iPlayerCallback : mIPlayerCallbacks) {
            iPlayerCallback.onPlayStop();
        }

    }

    @Override
    public void onSoundPlayComplete() {

        LogUtil.v("jeff", "onSoundPlayComplete");

    }

    @Override
    public void onSoundPrepared() {

        LogUtil.v("jeff", "onSoundPrepared");

    }

    @Override
    public void onSoundSwitch(PlayableModel playableModel, PlayableModel playableModel1) {

        LogUtil.v("jeff", "");

    }

    @Override
    public void onBufferingStart() {

        LogUtil.v("jeff", "onBufferingStart");

    }

    @Override
    public void onBufferingStop() {

        LogUtil.v("jeff", "onBufferingStop");

    }

    @Override
    public void onBufferProgress(int progress) {

        LogUtil.v("jeff", "onBufferProgress " + progress);

    }

    @Override
    public void onPlayProgress(int currentPos, int duration) {

        //單位是毫秒
        //LogUtil.v("jeff","  currentPos --> " + currentPos + "  duration -->" + duration);

        for (IPlayerCallBack iPlayerCallback : mIPlayerCallbacks) {

            iPlayerCallback.onProgressChange(currentPos, duration);
        }
    }

    @Override
    public boolean onError(XmPlayerException e) {

        LogUtil.v("jeff", "onError e ---> " + e);

        return false;
    }
    //=====================================播放器相關的回調方法 end============================


}
