package json.model.cost_and_usages.sub;

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
        "BlendedCost",
        "UnblendedCost",
        "UsageQuantity"
})

public class Metrics implements Serializable {

    @JsonProperty("BlendedCost")
    private BlendedCost blendedCost;
    @JsonProperty("UnblendedCost")
    private UnblendedCost unblendedCost;
    @JsonProperty("UsageQuantity")
    private UsageQuantity usageQuantity;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("BlendedCost")
    public BlendedCost getBlendedCost() {
        return blendedCost;
    }

    @JsonProperty("BlendedCost")
    public void setBlendedCost(BlendedCost blendedCost) {
        this.blendedCost = blendedCost;
    }

    @JsonProperty("UnblendedCost")
    public UnblendedCost getUnblendedCost() {
        return unblendedCost;
    }

    @JsonProperty("UnblendedCost")
    public void setUnblendedCost(UnblendedCost unblendedCost) {
        this.unblendedCost = unblendedCost;
    }

    @JsonProperty("UsageQuantity")
    public UsageQuantity getUsageQuantity() {
        return usageQuantity;
    }

    @JsonProperty("UsageQuantity")
    public void setUsageQuantity(UsageQuantity usageQuantity) {
        this.usageQuantity = usageQuantity;
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

