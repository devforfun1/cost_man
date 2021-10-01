
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
    "AttachTime",
    "DeleteOnTermination",
    "Status",
    "VolumeId"
})

public class Ebs implements Serializable
{

    @JsonProperty("AttachTime")
    private String attachTime;
    @JsonProperty("DeleteOnTermination")
    private Boolean deleteOnTermination;
    @JsonProperty("Status")
    private String status;
    @JsonProperty("VolumeId")
    private String volumeId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 3376416534761706463L;

    @JsonProperty("AttachTime")
    public String getAttachTime() {
        return attachTime;
    }

    @JsonProperty("AttachTime")
    public void setAttachTime(String attachTime) {
        this.attachTime = attachTime;
    }

    @JsonProperty("DeleteOnTermination")
    public Boolean getDeleteOnTermination() {
        return deleteOnTermination;
    }

    @JsonProperty("DeleteOnTermination")
    public void setDeleteOnTermination(Boolean deleteOnTermination) {
        this.deleteOnTermination = deleteOnTermination;
    }

    @JsonProperty("Status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("Status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("VolumeId")
    public String getVolumeId() {
        return volumeId;
    }

    @JsonProperty("VolumeId")
    public void setVolumeId(String volumeId) {
        this.volumeId = volumeId;
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
