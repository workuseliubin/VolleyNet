package com.mrliu.volleynet.net.base;

import android.text.TextUtils;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 作者：liubin1 on 2016/8/31 11:07
 * 该类的说明：
 * 修改历史：
 */
public class BaseRequest extends Request<BaseResponse> {

    private String TAG = "BaseRequest";
    private BaseConnection baseConnection;
    private Map<String, String> mPostValue;

    public BaseRequest(int method, String url, Response.ErrorListener listener, BaseConnection bc) {
        super(method, url, listener);
        this.baseConnection = bc;
    }

    public BaseRequest(int method, String url, Map<String, String> postValue, Response.ErrorListener listener, BaseConnection bc) {
        super(method, url, listener);
        this.mPostValue = postValue;
        this.baseConnection = bc;
    }

    @Override
    protected Response<BaseResponse> parseNetworkResponse(NetworkResponse networkResponse) {
        String parsed;
        try {
            parsed = new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
        } catch (UnsupportedEncodingException var4) {
            parsed = new String(networkResponse.data);
        }
        BaseResponse baseResponse = new BaseResponse();
        try {
            JSONTokener jsonParser = new JSONTokener(parsed);
            JSONObject json = null;
            json = (JSONObject) jsonParser.nextValue();
            if (json != null) {
                baseResponse.setResult(json.optInt("result"));
                baseResponse.setErrno(json.optInt("errno"));
                baseResponse.setMsg(json.optString("msg"));
                baseResponse.setErrorMsgs(json.optString("errorMsgs"));
            }
            String data = json.optString("data");
            baseResponse.setJsonStr(parsed);
            baseConnection.parseResponse(baseResponse, data == null ? "" : data);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "json解析失败:" + ">>>>>>>>该json字符串为：" + parsed + "\n" +
                    "+<<<<<<<<<<");
        }
        return Response.success(baseResponse, HttpHeaderParser.parseCacheHeaders(networkResponse));
    }

    @Override
    protected void deliverResponse(BaseResponse baseReponse) {
        baseConnection.onResponse(baseReponse);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        if (mPostValue != null) {
            for (Map.Entry<String, String> entry : mPostValue.entrySet()) {
                //使用post方式,保证value不为空
                if (TextUtils.isEmpty(entry.getValue())) {
                    entry.setValue("");
                }
            }
        }
        return mPostValue;
    }
}
