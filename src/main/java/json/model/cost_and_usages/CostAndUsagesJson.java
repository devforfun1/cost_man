package json.model.cost_and_usages;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;


import java.util.List;

import annonation.TopJSON;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import json.model.cost_and_usages.sub.GroupDefinition;
import json.model.cost_and_usages.sub.ResultsByTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "DimensionValueAttributes",
        "GroupDefinitions",
        "ResultsByTime"
})
@TopJSON
public class CostAndUsagesJson implements Serializable{

    @JsonProperty("DimensionValueAttributes")
    private List<Object> dimensionValueAttributes = null;
    @JsonProperty("GroupDefinitions")
    private List<GroupDefinition> groupDefinitions = null;
    @JsonProperty("ResultsByTime")
    private List<ResultsByTime> resultsByTime = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("DimensionValueAttributes")
    public List<Object> getDimensionValueAttributes() {
        return dimensionValueAttributes;
    }

    @JsonProperty("DimensionValueAttributes")
    public void setDimensionValueAttributes(List<Object> dimensionValueAttributes) {
        this.dimensionValueAttributes = dimensionValueAttributes;
    }

    @JsonProperty("GroupDefinitions")
    public List<GroupDefinition> getGroupDefinitions() {
        return groupDefinitions;
    }

    @JsonProperty("GroupDefinitions")
    public void setGroupDefinitions(List<GroupDefinition> groupDefinitions) {
        this.groupDefinitions = groupDefinitions;
    }

    @JsonProperty("ResultsByTime")
    public List<ResultsByTime> getResultsByTime() {
        return resultsByTime;
    }

    @JsonProperty("ResultsByTime")
    public void setResultsByTime(List<ResultsByTime> resultsByTime) {
        this.resultsByTime = resultsByTime;
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
        return "CostAndUsagesJson{" +
                "dimensionValueAttributes=" + dimensionValueAttributes +
                ", groupDefinitions=" + groupDefinitions +
                ", resultsByTime=" + resultsByTime +
                ", additionalProperties=" + additionalProperties +
                '}';
    }


}
