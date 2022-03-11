package com.jxxx.byb.bean;

public class LoginData {

    /**
     * userinfo : {"id":13,"username":"17774004352","nickname":"177****4352","mobile":"17774004352","avatar":"data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZlcnNpb249IjEuMSIgaGVpZ2h0PSIxMDAiIHdpZHRoPSIxMDAiPjxyZWN0IGZpbGw9InJnYigxNzQsMjI5LDE2MCkiIHg9IjAiIHk9IjAiIHdpZHRoPSIxMDAiIGhlaWdodD0iMTAwIj48L3JlY3Q+PHRleHQgeD0iNTAiIHk9IjUwIiBmb250LXNpemU9IjUwIiB0ZXh0LWNvcHk9ImZhc3QiIGZpbGw9IiNmZmZmZmYiIHRleHQtYW5jaG9yPSJtaWRkbGUiIHRleHQtcmlnaHRzPSJhZG1pbiIgZG9taW5hbnQtYmFzZWxpbmU9ImNlbnRyYWwiPjE8L3RleHQ+PC9zdmc+","birthday":null,"money":"0.00","score":0,"code":"BtdNpWjvxf","pid":0,"is_examine":0,"token":"d1877e51-5ec2-42fd-9f33-5954a0766e5d","user_id":13,"createtime":1646984367,"expiretime":1649576367,"expires_in":2592000}
     */

    private UserinfoBean userinfo;

    public UserinfoBean getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserinfoBean userinfo) {
        this.userinfo = userinfo;
    }

    public static class UserinfoBean {
        /**
         * id : 13
         * username : 17774004352
         * nickname : 177****4352
         * mobile : 17774004352
         * avatar : data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZlcnNpb249IjEuMSIgaGVpZ2h0PSIxMDAiIHdpZHRoPSIxMDAiPjxyZWN0IGZpbGw9InJnYigxNzQsMjI5LDE2MCkiIHg9IjAiIHk9IjAiIHdpZHRoPSIxMDAiIGhlaWdodD0iMTAwIj48L3JlY3Q+PHRleHQgeD0iNTAiIHk9IjUwIiBmb250LXNpemU9IjUwIiB0ZXh0LWNvcHk9ImZhc3QiIGZpbGw9IiNmZmZmZmYiIHRleHQtYW5jaG9yPSJtaWRkbGUiIHRleHQtcmlnaHRzPSJhZG1pbiIgZG9taW5hbnQtYmFzZWxpbmU9ImNlbnRyYWwiPjE8L3RleHQ+PC9zdmc+
         * birthday : null
         * money : 0.00
         * score : 0
         * code : BtdNpWjvxf
         * pid : 0
         * is_examine : 0
         * token : d1877e51-5ec2-42fd-9f33-5954a0766e5d
         * user_id : 13
         * createtime : 1646984367
         * expiretime : 1649576367
         * expires_in : 2592000
         */

        private String id;
        private String username;
        private String nickname;
        private String mobile;
        private String avatar;
        private Object birthday;
        private String money;
        private String score;
        private String code;
        private String pid;
        private String is_examine;
        private String token;
        private String user_id;
        private String createtime;
        private String expiretime;
        private String expires_in;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public Object getBirthday() {
            return birthday;
        }

        public void setBirthday(Object birthday) {
            this.birthday = birthday;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getIs_examine() {
            return is_examine;
        }

        public void setIs_examine(String is_examine) {
            this.is_examine = is_examine;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getExpiretime() {
            return expiretime;
        }

        public void setExpiretime(String expiretime) {
            this.expiretime = expiretime;
        }

        public String getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(String expires_in) {
            this.expires_in = expires_in;
        }
    }
}

