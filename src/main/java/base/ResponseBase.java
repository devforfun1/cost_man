package base;

import util.DateUtil;

import java.time.LocalDateTime;

public abstract class ResponseBase {

    protected LocalDateTime ldt;

    public ResponseBase(LocalDateTime ldt) {
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
