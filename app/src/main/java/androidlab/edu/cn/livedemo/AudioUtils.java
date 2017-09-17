package androidlab.edu.cn.livedemo;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;

/**
 * Created by dreamY on 2017/9/17.
 */
public class AudioUtils {
    private static AudioUtils mVoice;
    public static AudioUtils getInstance(){
        synchronized (AudioUtils.class){
            if(null==mVoice){
                mVoice=new AudioUtils();
            }
            return mVoice;
        }
    }

    // 语音合成对象
    private SpeechSynthesizer mTts;
    // 默认发音人
    private String voicer = "xiaoyan";
    //初始化监听器
    private InitListener Listener;
    //播放监听器
    private SynthesizerListener mSynthesizerListener;

    public void initmTts(Context context, final String msg){

        if(null==Listener){
            Listener=new InitListener() {
                @Override
                public void onInit(int code) {

                    if (code != ErrorCode.SUCCESS) {

                    } else {
                        // 初始化成功，之后可以调用startSpeaking方法
                        // 注：有的开发者在onCreate方法中创建完合成对象之后马上就调用startSpeaking进行合成，
                        // 正确的做法是将onCreate中的startSpeaking调用移至这里

                        mTts.startSpeaking(msg,  mSynthesizerListener);
                    }
                }
            };
        }

        if(null==mSynthesizerListener){
            mSynthesizerListener= new SynthesizerListener() {
                @Override
                public void onSpeakBegin() {

                }

                @Override
                public void onBufferProgress(int mI, int mI1, int mI2, String mS) {

                }

                @Override
                public void onSpeakPaused() {

                }

                @Override
                public void onSpeakResumed() {

                }

                @Override
                public void onSpeakProgress(int mI, int mI1, int mI2) {

                }

                @Override
                public void onCompleted(SpeechError mSpeechError) {

                }

                @Override
                public void onEvent(int mI, int mI1, int mI2, Bundle mBundle) {

                }
            };
        }

        if(null==mTts){
            mTts=SpeechSynthesizer.createSynthesizer(context, Listener);
            mTts.setParameter(SpeechConstant.VOICE_NAME,voicer);
            mTts.setParameter(SpeechConstant.SPEED,"50");
            mTts.setParameter(SpeechConstant.VOLUME,"200");
            mTts.setParameter(SpeechConstant.ENGINE_TYPE,SpeechConstant.TYPE_CLOUD);
        }else {
            mTts.startSpeaking(msg,  mSynthesizerListener);
        }
    }
}
