package com.github.xu.gank.bean;

import java.util.List;

/**
 * Created by xukankan on 2017/9/25.
 */

public class BoonBean {
    /**
     * "error": false,
     * "results": [
     * {
     * "_id": "59a35f6d421aa901b9dc4643",
     * "createdAt": "2017-08-28T08:10:21.141Z",
     * "desc": "8-28",
     * "publishedAt": "2017-08-29T12:19:18.634Z",
     * "source": "chrome",
     * "type": "\u798f\u5229",
     * "url": "https://ws1.sinaimg.cn/large/610dc034ly1fiz4ar9pq8j20u010xtbk.jpg",
     * "used": true,
     * "who": "\u4ee3\u7801\u5bb6"
     * },]
     */
    private boolean error;
    private List<BoonResultBoon> results;

    public void setError(boolean error) {
        this.error = error;
    }

    public void setResults(List<BoonResultBoon> results) {
        this.results = results;
    }

    public List<BoonResultBoon> getResults() {
        return results;
    }

    public boolean getError() {

        return error;
    }

    /**
     * * "_id": "59a35f6d421aa901b9dc4643",
     * "createdAt": "2017-08-28T08:10:21.141Z",
     * "desc": "8-28",
     * "publishedAt": "2017-08-29T12:19:18.634Z",
     * "source": "chrome",
     * "type": "\u798f\u5229",
     * "url": "https://ws1.sinaimg.cn/large/610dc034ly1fiz4ar9pq8j20u010xtbk.jpg",
     * "used": true,
     * "who": "\u4ee3\u7801\u5bb6"
     */
    public class BoonResultBoon {
        private String who;
        private String used;
        private String desc;
        private String _id;
        private String source;
        private String createdAt;
        private String publishedAt;
        private String type;
        private String url;

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public void setUsed(String used) {
            this.used = used;
        }

        public String get_Id() {
            return _id;
        }

        public String getWho() {
            return who;
        }

        public String getUsed() {
            return used;
        }

        public String getDesc() {
            return desc;
        }

        public String getSource() {
            return source;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public String getType() {
            return type;
        }

        public String getUrl() {
            return url;
        }
    }

}
