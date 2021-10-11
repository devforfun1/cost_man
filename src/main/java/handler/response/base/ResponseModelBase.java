package handler.response.base;

import util.DateUtil;

import java.time.LocalDateTime;

public abstract class ResponseModelBase {

    protected LocalDateTime timeOfResponse;

    public ResponseModelBase(LocalDateTime timeOfResponse) {
        this.timeOfResponse = timeOfResponse;
    }

    public LocalDateTime getTimeOfResponse() {
        return timeOfResponse;
    }

    @Override
    public String toString() {
        return "ResponseBase{" +
                "ldt="+ DateUtil.ConvertDateTime(timeOfResponse) +
                '}';
    }
}
