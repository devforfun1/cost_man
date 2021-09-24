package singleton;

public class DataStorage {

    private static DataStorage instance;

    private String awsAccountNr;
    private String roleArn;

    private DataStorage() {}

    public static synchronized DataStorage getInstance(){

        if(instance == null){

            instance = new DataStorage();
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
}
