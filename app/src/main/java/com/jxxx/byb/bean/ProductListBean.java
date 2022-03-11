package com.jxxx.byb.bean;

import java.util.List;

public class ProductListBean {


    /**
     * list : [{"id":3,"name":"产品1","image":"/uploads/20220307/21ec45cd45721431241b0552adbf639b.jpg","money":"300.00","type":"1","tag":"精神鼓励,演讲,讲故事","createtime":"2022-03-02 17:46:40","tag_arr":["精神鼓励","演讲","讲故事"]},{"id":2,"name":"产品2","image":"/uploads/20220307/afbdd8c40abca4b230270c6a749655ff.jpg","money":"200.00","type":"0","tag":"","createtime":"2022-03-02 17:42:22","tag_arr":[]}]
     * count : 2
     */

    private int count;
    private List<ListBean> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 3
         * name : 产品1
         * image : /uploads/20220307/21ec45cd45721431241b0552adbf639b.jpg
         * money : 300.00
         * type : 1
         * tag : 精神鼓励,演讲,讲故事
         * createtime : 2022-03-02 17:46:40
         * tag_arr : ["精神鼓励","演讲","讲故事"]
         */

        private int id;
        private String name;
        private String image;
        private String money;
        private String type;
        private String tag;
        private String createtime;
        private List<String> tag_arr;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public List<String> getTag_arr() {
            return tag_arr;
        }

        public void setTag_arr(List<String> tag_arr) {
            this.tag_arr = tag_arr;
        }
    }
}
