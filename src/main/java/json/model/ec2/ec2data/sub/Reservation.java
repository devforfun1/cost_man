
package json.model.ec2.ec2data.sub;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Groups",
    "Instances",
    "OwnerId",
    "ReservationId"
})

public class Reservation implements Serializable
{

    @JsonProperty("Groups")
    private List<Object> groups = null;
    @JsonProperty("Instances")
    private List<Instance> instances = null;
    @JsonProperty("OwnerId")
    private String ownerId;
    @JsonProperty("ReservationId")
    private String reservationId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -5233105345359135310L;

    @JsonProperty("Groups")
    public List<Object> getGroups() {
        return groups;
    }

    @JsonProperty("Groups")
    public void setGroups(List<Object> groups) {
        this.groups = groups;
    }

    @JsonProperty("Instances")
    public List<Instance> getInstances() {
        return instances;
    }

    @JsonProperty("Instances")
    public void setInstances(List<Instance> instances) {
        this.instances = instances;
    }

    @JsonProperty("OwnerId")
    public String getOwnerId() {
        return ownerId;
    }

    @JsonProperty("OwnerId")
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @JsonProperty("ReservationId")
    public String getReservationId() {
        return reservationId;
    }

    @JsonProperty("ReservationId")
    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
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
