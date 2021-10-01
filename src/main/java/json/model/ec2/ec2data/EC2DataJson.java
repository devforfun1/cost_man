
package json.model.ec2.ec2data;

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
import json.model.ec2.ec2data.sub.Reservation;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Reservations"
})

public class EC2DataJson implements Serializable
{

    @JsonProperty("Reservations")
    private List<Reservation> reservations = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 7106973286157697594L;

    @JsonProperty("Reservations")
    public List<Reservation> getReservations() {
        return reservations;
    }

    @JsonProperty("Reservations")
    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
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
