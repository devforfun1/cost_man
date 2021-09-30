
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
    "GroupName",
    "GroupId"
})

public class Group implements Serializable
{

    @JsonProperty("GroupName")
    private String groupName;
    @JsonProperty("GroupId")
    private String groupId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 8908908024087196243L;

    @JsonProperty("GroupName")
    public String getGroupName() {
        return groupName;
    }

    @JsonProperty("GroupName")
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @JsonProperty("GroupId")
    public String getGroupId() {
        return groupId;
    }

    @JsonProperty("GroupId")
    public void setGroupId(String groupId) {
        this.groupId = groupId;
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
