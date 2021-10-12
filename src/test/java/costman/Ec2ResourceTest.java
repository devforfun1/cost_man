package costman;


import Enum.budget.BudgetStatus;
import exception.BudgetNotSupportedException;
import handler.response.model.BudgetResponseModel;
import handler.response.model.Ec2CeDataModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class Ec2ResourceTest {

    private Dictionary<String, Ec2CeDataModel> ec2SumValesDict;

    private LocalDateTime timeOfResponse;

    private String unit;


    /**
     * Mock Model of important data from the response object from AWS budget API
     */
    private BudgetResponseModel responseModelMock;

    private Ec2Resource ec2Resource;

    @Before
    public void Init() {

        ec2SumValesDict = new Hashtable<>();

        timeOfResponse = LocalDateTime.of(2021, 9, 15, 8, 0);
        unit = "usd";

        ec2Resource = new Ec2Resource();
    }

    @Test
    public void CalculateEc2InstancesToShutdown_CloseToLimit_Test() throws BudgetNotSupportedException {

        BigDecimal amountUsed = new BigDecimal(18);
        BigDecimal limit =new BigDecimal(20);

        LocalDate startDate = LocalDate.now();
        LocalDate endDate =LocalDate.now().plusDays(16);

        System.out.println("a"+amountUsed.doubleValue());

        responseModelMock = new BudgetResponseModel(timeOfResponse, amountUsed, limit, unit, startDate, endDate);

        Ec2CeDataModel ec2CeDataModel1 = new Ec2CeDataModel(130, 1.5);
        Ec2CeDataModel ec2CeDataModel2 = new Ec2CeDataModel(150, 0.8);


        ec2SumValesDict.put("ec2CeDataModel1_instance_id", ec2CeDataModel1);
        ec2SumValesDict.put("ec2CeDataModel2_instance_id", ec2CeDataModel2);


        List<String> instanceIdsToShutdown = ec2Resource.CalculateEc2InstancesToShutdown(ec2SumValesDict,
                BudgetStatus.CLOSE_TO_LIMIT, responseModelMock);

        String expected = "ec2CeDataModel2_instance_id";
        String actual = instanceIdsToShutdown.get(0);

        Assert.assertEquals(expected, actual);

    }
}
