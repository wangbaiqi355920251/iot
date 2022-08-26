package io.iti.common.utils;

public class ConvertParam {
    private String param;

    private String type;

    public ConvertParam(String param, String type) {
        this.param = param;
        this.type = type;
    }

    private Boolean checkParam() {
        if (this.param == null || this.param == "") return false;
        return true;
    }

    public Integer GetInteger() {
        if (!this.checkParam()) {
            return null;
        } else {
            try {
                Integer res = Integer.valueOf(this.param);
                return res;
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        }
    }

    public Float GetFloat() {
        if (!this.checkParam()) {
            return null;
        } else {
            try {
                Float res = Float.parseFloat(this.param);
                return res;
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        }
    }

    public Long GetLong() {
        if (!this.checkParam()) {
            return null;
        } else {
            try {
                Long res = Long.parseLong(this.param);
                return res;
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        }
    }
}
