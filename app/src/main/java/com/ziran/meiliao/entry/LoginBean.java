package com.ziran.meiliao.entry;

import com.ziran.meiliao.common.okhttp.Result;

/**
 * Created by Administrator on 2016/12/26.
 */

public class LoginBean extends Result {


    /**
     * access_token : 243c3b34-8540-4cac-b89e-075baedb344c
     * token_type : bearer
     * refresh_token : c1877110-6ec9-47eb-8c66-374511dacd8a
     * expires_in : 2580231
     * scope : server
     * tenant_id : 1
     * license : made by ziran
     * data : {"tenant_id":1,"license":"made by ziran","user_id":13,"active":true,"id":"cb9143bfbd79c420a1859ceb039e982e","dept_id":2,"username":"13883151092"}
     * user_id : 13
     * active : true
     * id : cb9143bfbd79c420a1859ceb039e982e
     * dept_id : 2
     * username : 13883151092
     */

    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;
    private int tenant_id;
    private String license;
    private DataBean data;
    private int user_id;
    private boolean active;
    private String id;
    private int dept_id;
    private String username;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public int getTenant_id() {
        return tenant_id;
    }

    public void setTenant_id(int tenant_id) {
        this.tenant_id = tenant_id;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static class DataBean {
        /**
         * tenant_id : 1
         * license : made by ziran
         * user_id : 13
         * active : true
         * id : cb9143bfbd79c420a1859ceb039e982e
         * dept_id : 2
         * username : 13883151092
         */

        private int tenant_id;
        private String license;
        private int user_id;
        private boolean active;
        private String id;
        private int dept_id;
        private String username;

        public int getTenant_id() {
            return tenant_id;
        }

        public void setTenant_id(int tenant_id) {
            this.tenant_id = tenant_id;
        }

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getDept_id() {
            return dept_id;
        }

        public void setDept_id(int dept_id) {
            this.dept_id = dept_id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
