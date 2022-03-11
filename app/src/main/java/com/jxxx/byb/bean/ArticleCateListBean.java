package com.jxxx.byb.bean;

import java.util.List;

public class ArticleCateListBean {

    /**
     * list : [{"id":7,"cate_id":4,"title":"asdsadas","image":"/uploads/20220302/395a5fd0ea4f57d3a7762036ffc7bdbd.jpg","createtime":1646622842,"updatetime":1646988653}]
     * count : 1
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
         * id : 7
         * cate_id : 4
         * title : asdsadas
         * image : /uploads/20220302/395a5fd0ea4f57d3a7762036ffc7bdbd.jpg
         * createtime : 1646622842
         * updatetime : 1646988653
         */

        private String id;
        private String cate_id;
        private String title;
        private String image;
        private String createtime;
        private String updatetime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }
    }
}
