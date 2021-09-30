
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
    "CapacityReservationPreference"
})

public class CapacityReservationSpecification implements Serializable
{

    @JsonProperty("CapacityReservationPreference")
    private String capacityReservationPreference;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 7544639372010445820L;

    @JsonProperty("CapacityReservationPreference")
    public String getCapacityReservationPreference() {
        return capacityReservationPreference;
    }

    @JsonProperty("CapacityReservationPreference")
    public void setCapacityReservationPreference(String capacityReservationPreference) {
        this.capacityReservationPreference = capacityReservationPreference;
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
