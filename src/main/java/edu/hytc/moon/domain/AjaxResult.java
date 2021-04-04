package edu.hytc.moon.domain;

import lombok.Data;

@Data
public class AjaxResult {
    private boolean success;

    public AjaxResult(boolean success) {
        this.success = success;
    }

    public AjaxResult() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
