package com.sjy.akita.beans;

import java.util.List;

/**
 * Created by sjy on 2018/5/26.
 */

public class ResBean {

    /**
     * error : 0
     * info : success
     * data : [{"id":"","name":"全部"},{"id":"1","name":"直销理念"},{"id":"2","name":"励志成长"},{"id":"3","name":"产品推广"},{"id":"4","name":"健康生活"},{"id":"5","name":"问候祝福"},{"id":"6","name":"社会热点"},{"id":"7","name":"个人营销"},{"id":"8","name":"早安分享"},{"id":"9","name":"晚安分享"}]
     */

    private int error;
    private String info;
    private List<DataBean> data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id :
         * name : 全部
         */

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
