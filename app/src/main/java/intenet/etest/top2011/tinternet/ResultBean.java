package intenet.etest.top2011.tinternet;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by top2011 on 2017/12/20.
 */

public class ResultBean implements Parcelable {
    public int code;
    public String result;

    public ResultBean(){}
    public ResultBean(int code, String result) {
        this.code = code;
        this.result = result;
    }

    protected ResultBean(Parcel in) {
        code = in.readInt();
        result = in.readString();
    }

    public static final Creator<ResultBean> CREATOR = new Creator<ResultBean>() {
        @Override
        public ResultBean createFromParcel(Parcel in) {
            return new ResultBean(in);
        }

        @Override
        public ResultBean[] newArray(int size) {
            return new ResultBean[size];
        }
    };

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(code);
        dest.writeString(result);
    }
}
