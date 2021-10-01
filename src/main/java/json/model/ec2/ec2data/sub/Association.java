
package json.model.ec2.ec2data.sub;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "IpOwnerId",
    "PublicDnsName",
    "PublicIp"
})

public class Association implements Serializable
{

    @JsonProperty("IpOwnerId")
    private String ipOwnerId;
    @JsonProperty("PublicDnsName")
    private String publicDnsName;
    @JsonProperty("PublicIp")
    private String publicIp;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 3862843157742619044L;

    @JsonProperty("IpOwnerId")
    public String getIpOwnerId() {
        return ipOwnerId;
    }

    @JsonProperty("IpOwnerId")
    public void setIpOwnerId(String ipOwnerId) {
        this.ipOwnerId = ipOwnerId;
    }

    @JsonProperty("PublicDnsName")
    public String getPublicDnsName() {
        return publicDnsName;
    }

    @JsonProperty("PublicDnsName")
    public void setPublicDnsName(String publicDnsName) {
        this.publicDnsName = publicDnsName;
    }

    @JsonProperty("PublicIp")
    public String getPublicIp() {
        return publicIp;
    }

    @JsonProperty("PublicIp")
    public void setPublicIp(String publicIp) {
        this.publicIp = publicIp;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
