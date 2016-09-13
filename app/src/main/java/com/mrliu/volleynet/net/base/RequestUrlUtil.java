package com.mrliu.volleynet.net.base;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 作者：liubin1 on 2016/9/2 14:09
 * 该类的说明：使用本类用来构造URL
 * 修改历史：
 */
public class RequestUrlUtil {
    private RequestUrlUtil() {}

    public static class Builder {
        public static final String HOSTURL = "http://hostPath";
        private boolean isHttp = true;

        private String hosturl;
        private String path;

        private HashMap<String, Object> params;

        public Builder(boolean isHttp) {
            this.isHttp = isHttp;
            params = new LinkedHashMap<>();
        }

        public Builder() {
            this(true);
        }

        public Builder setHost(String hosturl) {
            this.hosturl = hosturl;
            return this;
        }
        public Builder setHost() {
            this.hosturl = HOSTURL;
            return this;
        }


        public Builder setPath(String path) {
            this.path = path;
            return this;
        }

        public Builder addParam(String key, Object value) {
            params.put(key, value);
            return this;
        }

        public String build() {
            String url = hosturl + path + "?";
            int i = 0;
            Iterator iterator = params.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entity = (Map.Entry<String, Object>) iterator.next();
                if (entity != null) {
                    if (i == 0) {
                        url += entity.getKey() + "=" + entity.getValue();
                    }
                    else {
                        url += "&" + entity.getKey() + "=" + entity.getValue();
                    }
                }
                i++;
            }
            return url;
        }
    }
}
