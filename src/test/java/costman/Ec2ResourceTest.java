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

    /** Both instances cost the same but ec2CeDataModel2_instance_id has lesser usage
     *
     * @throws BudgetNotSupportedException
     */
    @Test
    public void CalculateEc2InstancesToShutdown_CloseToLimit_SingleElementSolution_Test() throws BudgetNotSupportedException {

        BigDecimal amountUsed = new BigDecimal(18);
        BigDecimal limit =new BigDecimal(20);

        LocalDate startDate = LocalDate.now();
        LocalDate endDate =LocalDate.now().plusDays(16);


        responseModelMock = new BudgetResponseModel(timeOfResponse, amountUsed, limit, unit, startDate, endDate);

        Ec2CeDataModel ec2CeDataModel1 = new Ec2CeDataModel(130, 2);
        Ec2CeDataModel ec2CeDataModel2 = new Ec2CeDataModel(10, 2);


        ec2SumValesDict.put("ec2CeDataModel1_instance_id", ec2CeDataModel1);
        ec2SumValesDict.put("ec2CeDataModel2_instance_id", ec2CeDataModel2);


        List<String> instanceIdsToShutdown = ec2Resource.CalculateEc2InstancesToShutdown(ec2SumValesDict,
                BudgetStatus.CLOSE_TO_LIMIT, responseModelMock);

        String expected = "ec2CeDataModel2_instance_id";
        String actual = instanceIdsToShutdown.get(0);

        Assert.assertEquals(expected, actual);

    }

    /**
     * Even though ec2CeDataModel4 has high usages it's the only instance that won't break budget
     * @throws BudgetNotSupportedException
     */

    @Test
    public void CalculateEc2InstancesToShutdown_CloseToLimit_SingleElementSolution2_Test() throws BudgetNotSupportedException {

        BigDecimal amountUsed = new BigDecimal(18);
        BigDecimal limit =new BigDecimal(20);

        LocalDate startDate = LocalDate.now();
        LocalDate endDate =LocalDate.now().plusDays(16);


        responseModelMock = new BudgetResponseModel(timeOfResponse, amountUsed, limit, unit, startDate, endDate);

        Ec2CeDataModel ec2CeDataModel1 = new Ec2CeDataModel(10, 0.5);
        Ec2CeDataModel ec2CeDataModel2 = new Ec2CeDataModel(50, 0.5);
        Ec2CeDataModel ec2CeDataModel3 = new Ec2CeDataModel(30, 0.5);
        Ec2CeDataModel ec2CeDataModel4 = new Ec2CeDataModel(40, 3);


        ec2SumValesDict.put("ec2CeDataModel1_instance_id", ec2CeDataModel1);
        ec2SumValesDict.put("ec2CeDataModel2_instance_id", ec2CeDataModel2);
        ec2SumValesDict.put("ec2CeDataModel3_instance_id", ec2CeDataModel3);
        ec2SumValesDict.put("ec2CeDataModel4_instance_id", ec2CeDataModel4);

        List<String> instanceIdsToShutdown = ec2Resource.CalculateEc2InstancesToShutdown(ec2SumValesDict,
                BudgetStatus.CLOSE_TO_LIMIT, responseModelMock);

        String expected ="ec2CeDataModel4_instance_id";

        String  actual = instanceIdsToShutdown.get(0);

        Assert.assertEquals(expected,actual);
    }

    /**
     * Predicted budget will only be met by if one of the two instances with cost 3$ shuts down
     * Usage won't matter in this case
     * @throws BudgetNotSupportedException
     */

   @Test
    public void CalculateEc2InstancesToShutdown_CloseToLimit_CombinedElementSolution_Test() throws BudgetNotSupportedException {

        BigDecimal amountUsed = new BigDecimal(18);
        BigDecimal limit =new BigDecimal(20);

        LocalDate startDate = LocalDate.now();
        LocalDate endDate =LocalDate.now().plusDays(16);


        responseModelMock = new BudgetResponseModel(timeOfResponse, amountUsed, limit, unit, startDate, endDate);

        Ec2CeDataModel ec2CeDataModel1 = new Ec2CeDataModel(10, 3);
        Ec2CeDataModel ec2CeDataModel2 = new Ec2CeDataModel(20, 3);
        Ec2CeDataModel ec2CeDataModel3 = new Ec2CeDataModel(30, 0.1);
        Ec2CeDataModel ec2CeDataModel4 = new Ec2CeDataModel(40, 0.1);



        ec2SumValesDict.put("ec2CeDataModel1_instance_id", ec2CeDataModel1);
        ec2SumValesDict.put("ec2CeDataModel2_instance_id", ec2CeDataModel2);
        ec2SumValesDict.put("ec2CeDataModel3_instance_id", ec2CeDataModel3);
        ec2SumValesDict.put("ec2CeDataModel4_instance_id", ec2CeDataModel4);


        List<String> instanceIdsToShutdown = ec2Resource.CalculateEc2InstancesToShutdown(ec2SumValesDict,
                BudgetStatus.CLOSE_TO_LIMIT, responseModelMock);

        String[] expectedEc2InstanceIds ={"ec2CeDataModel1_instance_id","ec2CeDataModel2_instance_id"};


        System.out.println("instance id size "+instanceIdsToShutdown.size());

    instanceIdsToShutdown.forEach(i -> System.out.println("instance ids test 3 -> "+i));
       boolean actual = false;

        if(instanceIdsToShutdown.size() == 2){

            if(instanceIdsToShutdown.contains(expectedEc2InstanceIds[0]) && instanceIdsToShutdown.contains(expectedEc2InstanceIds[1]))
                actual = true;
        }


        Assert.assertTrue(actual);

    }
}
