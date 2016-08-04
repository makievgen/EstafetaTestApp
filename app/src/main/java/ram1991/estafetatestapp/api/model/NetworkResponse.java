package ram1991.estafetatestapp.api.model;

import android.support.annotation.Nullable;

public class NetworkResponse {
    @Nullable
    private Object mData;
    private int mState;

    public NetworkResponse() {
    }

    @Nullable
    public <T> T getTypedAnswer() {
        if (mData == null) {
            return null;
        }
        return (T) mData;
    }

    public NetworkResponse setData(@Nullable Object answer) {
        mData = answer;
        return this;
    }

    public void setState(@Nullable int state) {
        mState = state;
    }

    public int getState() {
        return mState;
    }
}
