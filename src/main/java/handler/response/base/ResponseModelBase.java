package handler.response.base;

import util.DateUtil;

import java.time.LocalDateTime;

public abstract class ResponseModelBase {

    protected LocalDateTime ldt;

    public ResponseModelBase(LocalDateTime ldt) {
        this.ldt = ldt;
    }

    public LocalDateTime getLdt() {
        return ldt;
    }

    @Override
    public String toString() {
        return "ResponseBase{" +
                "ldt="+ DateUtil.ConvertDateTime(ldt) +
                '}';
    }
}
