package security;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.securitytoken.model.AssumeRoleRequest;
import com.amazonaws.services.securitytoken.model.Credentials;
import datastorage.AwsConfigStorage;


public class CredentialsClient {


    public CredentialsClient() {
    }


    public AWSStaticCredentialsProvider getCredentials() throws Exception {
        AssumeRoleRequest assumeRole = new AssumeRoleRequest()
                .withRoleArn(AwsConfigStorage.getInstance().getRoleArn())
                .withRoleSessionName("cost-java-sdk");

        AWSSecurityTokenService sts = AWSSecurityTokenServiceClientBuilder.standard().withRegion("us-east-1").build();
        Credentials credentials = sts.assumeRole(assumeRole).getCredentials();

        BasicSessionCredentials sessionCredentials = new BasicSessionCredentials(
                credentials.getAccessKeyId(),
                credentials.getSecretAccessKey(),
                credentials.getSessionToken());

        return new AWSStaticCredentialsProvider(sessionCredentials);
    }
}
