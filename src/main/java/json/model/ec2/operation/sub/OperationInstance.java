
package json.model.ec2.operation.sub;

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
    "CurrentState",
    "InstanceId",
    "PreviousState"
})

public class OperationInstance implements Serializable
{

    @JsonProperty("CurrentState")
    private CurrentState currentState;
    @JsonProperty("InstanceId")
    private String instanceId;
    @JsonProperty("PreviousState")
    private PreviousState previousState;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 7017965546294094859L;

    @JsonProperty("CurrentState")
    public CurrentState getCurrentState() {
        return currentState;
    }

    @JsonProperty("CurrentState")
    public void setCurrentState(CurrentState currentState) {
        this.currentState = currentState;
    }

    @JsonProperty("InstanceId")
    public String getInstanceId() {
        return instanceId;
    }

    @JsonProperty("InstanceId")
    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    @JsonProperty("PreviousState")
    public PreviousState getPreviousState() {
        return previousState;
    }

    @JsonProperty("PreviousState")
    public void setPreviousState(PreviousState previousState) {
        this.previousState = previousState;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(OperationInstance.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("currentState");
        sb.append('=');
        sb.append(((this.currentState == null)?"<null>":this.currentState));
        sb.append(',');
        sb.append("instanceId");
        sb.append('=');
        sb.append(((this.instanceId == null)?"<null>":this.instanceId));
        sb.append(',');
        sb.append("previousState");
        sb.append('=');
        sb.append(((this.previousState == null)?"<null>":this.previousState));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.instanceId == null)? 0 :this.instanceId.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.currentState == null)? 0 :this.currentState.hashCode()));
        result = ((result* 31)+((this.previousState == null)? 0 :this.previousState.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OperationInstance) == false) {
            return false;
        }
        OperationInstance rhs = ((OperationInstance) other);
        return (((((this.instanceId == rhs.instanceId)||((this.instanceId!= null)&&this.instanceId.equals(rhs.instanceId)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.currentState == rhs.currentState)||((this.currentState!= null)&&this.currentState.equals(rhs.currentState))))&&((this.previousState == rhs.previousState)||((this.previousState!= null)&&this.previousState.equals(rhs.previousState))));
    }

}
