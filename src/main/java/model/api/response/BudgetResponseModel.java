package model.api.response;

import model.api.response.base.ResponseModelBase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public class BudgetResponseModel extends ResponseModelBase {

    private BigDecimal amountUsed;
    private BigDecimal limit;
    private String unit;


    public BudgetResponseModel(LocalDateTime ldt, BigDecimal amountUsed, BigDecimal limit, String unit) {
        super(ldt);
        this.amountUsed = amountUsed;
        this.limit = limit;
        this.unit = unit;
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



    public boolean IsBudgetOverDue(){

     return amountUsed.compareTo(limit) > 0;
    }

    public Double GetPercentageLeft(){

     return amountUsed.divide(limit, RoundingMode.HALF_UP).doubleValue();
    }


    @Override
    public String toString() {
        return "BudgetResponse{" +
                "ldt=" + ldt +
                ", amountUsed=" + amountUsed +
                ", limit=" + limit +
                ", unit='" + unit + '\'' +
                '}';
    }
}
