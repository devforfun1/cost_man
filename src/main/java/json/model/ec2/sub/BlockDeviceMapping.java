
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
    "DeviceName",
    "Ebs"
})

public class BlockDeviceMapping implements Serializable
{

    @JsonProperty("DeviceName")
    private String deviceName;
    @JsonProperty("Ebs")
    private Ebs ebs;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -1167347172416574943L;

    @JsonProperty("DeviceName")
    public String getDeviceName() {
        return deviceName;
    }

    @JsonProperty("DeviceName")
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @JsonProperty("Ebs")
    public Ebs getEbs() {
        return ebs;
    }

    @JsonProperty("Ebs")
    public void setEbs(Ebs ebs) {
        this.ebs = ebs;
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
