
package json.model.ec2.sub;

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
    "Association",
    "Primary",
    "PrivateIpAddress"
})

public class PrivateIpAddress implements Serializable
{

    @JsonProperty("Association")
    private Association association;
    @JsonProperty("Primary")
    private Boolean primary;
    @JsonProperty("PrivateIpAddress")
    private String privateIpAddress;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 354862826772933952L;

    @JsonProperty("Association")
    public Association getAssociation() {
        return association;
    }

    @JsonProperty("Association")
    public void setAssociation(Association association) {
        this.association = association;
    }

    @JsonProperty("Primary")
    public Boolean getPrimary() {
        return primary;
    }

    @JsonProperty("Primary")
    public void setPrimary(Boolean primary) {
        this.primary = primary;
    }

    @JsonProperty("PrivateIpAddress")
    public String getPrivateIpAddress() {
        return privateIpAddress;
    }

    @JsonProperty("PrivateIpAddress")
    public void setPrivateIpAddress(String privateIpAddress) {
        this.privateIpAddress = privateIpAddress;
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
