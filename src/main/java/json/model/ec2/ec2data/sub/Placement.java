
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
    "AvailabilityZone",
    "GroupName",
    "Tenancy"
})

public class Placement implements Serializable
{

    @JsonProperty("AvailabilityZone")
    private String availabilityZone;
    @JsonProperty("GroupName")
    private String groupName;
    @JsonProperty("Tenancy")
    private String tenancy;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 2202970782079386855L;

    @JsonProperty("AvailabilityZone")
    public String getAvailabilityZone() {
        return availabilityZone;
    }

    @JsonProperty("AvailabilityZone")
    public void setAvailabilityZone(String availabilityZone) {
        this.availabilityZone = availabilityZone;
    }

    @JsonProperty("GroupName")
    public String getGroupName() {
        return groupName;
    }

    @JsonProperty("GroupName")
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @JsonProperty("Tenancy")
    public String getTenancy() {
        return tenancy;
    }

    @JsonProperty("Tenancy")
    public void setTenancy(String tenancy) {
        this.tenancy = tenancy;
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
