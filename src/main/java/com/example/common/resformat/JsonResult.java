package com.example.common.resformat;

import lombok.Data;

@Data
public class JsonResult {
        /**
         * 状态码
         */
        private Integer code;
        /**
         * 状态文本
         */
        private String status;
        /**
         * 消息
         */
        private String msg;
        /**
         * 数据
         */
        private Object data;
        private Object roleList;

    private JsonResult(Integer code, String status, String msg, Object data) {
        this.code = code;
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
        private JsonResult(Integer code, String status, String msg, Object data,Object roleList) {
            this.code = code;
            this.status = status;
            this.msg = msg;
            this.data = data;
            this.roleList = roleList;
        }

        public static JsonResult ok() {
            return new JsonResult(1, "ok", "success", null);
        }

        public static JsonResult ok(String msg) {
            return new JsonResult(1, "ok", msg, null);
        }

        public static JsonResult ok(Object data) {
            return new JsonResult(1, "ok", "success", data);
        }

        public static JsonResult ok(String msg,Object data,Object roleList) {
            return new JsonResult(1, "ok", msg, data,roleList);
        }

        public static JsonResult error() {
            return new JsonResult(0, "error", "error", null);
        }

        public static JsonResult error(String msg) {
            return new JsonResult(0, "error", msg, null);
        }


}

