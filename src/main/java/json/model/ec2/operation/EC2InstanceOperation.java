
package json.model.ec2.operation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.fasterxml.jackson.annotation.*;
import json.model.ec2.operation.sub.OperationInstance;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "StoppingInstances"
})

public class EC2InstanceOperation implements Serializable
{

    @JsonAlias("StartingInstances")
    @JsonProperty("StoppingInstances")
    private List<OperationInstance> operationInstances = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 5595594493670930954L;

    @JsonAlias("StartingInstances")
    @JsonProperty("StoppingInstances")
    public List<OperationInstance> getOperationInstances() {
        return operationInstances;
    }

    @JsonAlias("StartingInstances")
    @JsonProperty("StoppingInstances")
    public void setOperationInstances(List<OperationInstance> operationInstances) {
        this.operationInstances = operationInstances;
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
        sb.append(EC2InstanceOperation.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("operationInstances");
        sb.append('=');
        sb.append(((this.operationInstances == null)?"<null>":this.operationInstances));
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
        result = ((result* 31)+((this.operationInstances == null)? 0 :this.operationInstances.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof EC2InstanceOperation) == false) {
            return false;
        }
        EC2InstanceOperation rhs = ((EC2InstanceOperation) other);
        return (((this.operationInstances == rhs.operationInstances)||((this.operationInstances != null)&&this.operationInstances.equals(rhs.operationInstances)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
