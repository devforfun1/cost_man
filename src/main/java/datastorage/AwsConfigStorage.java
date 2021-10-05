package datastorage;

import annonation.Singleton;

@Singleton
public class AwsConfigStorage {

    private static AwsConfigStorage instance;

    private String awsAccountNr;
    private String roleArn;
    private String budgetName;

    private AwsConfigStorage() {}

    public static synchronized AwsConfigStorage getInstance(){

        if(instance == null){

            instance = new AwsConfigStorage();
        }

        return instance;
    }

    public String getAwsAccountNr() {
        return awsAccountNr;
    }

    public void setAwsAccountNr(String awsAccountNr) {
        this.awsAccountNr = awsAccountNr;
    }

    public String getRoleArn() {
        return roleArn;
    }

    public void setRoleArn(String roleArn) {
        this.roleArn = roleArn;
    }

    public String getBudgetName() {
        return budgetName;
    }

    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }
}
