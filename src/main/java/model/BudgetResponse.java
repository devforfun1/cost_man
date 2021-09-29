package model;

import util.DateUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BudgetResponse {

    private BigDecimal amountUsed;
    private BigDecimal limit;
    private String unit;
    private LocalDateTime ldt;


    public BudgetResponse(BigDecimal amountUsed, BigDecimal limit, String unit, LocalDateTime ld) {
        this.amountUsed = amountUsed;
        this.limit = limit;
        this.unit = unit;
        this.ldt = ld;
    }

    public BigDecimal getAmountUsed() {
        return amountUsed;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public String getUnit() {
        return unit;
    }

    public LocalDateTime getLdt() {
        return ldt;
    }

    public boolean IsBudgetOverDue(){

     return amountUsed.compareTo(limit) > 0;
    }

    @Override
    public String toString() {
        return "BudgetResponse{" +
                "amountUsed=" + amountUsed +
                ", limit=" + limit +
                ", unit='" + unit + '\'' +
                ", ldt=" + DateUtil.ConvertDateTime(ldt) +
                '}';
    }
}
