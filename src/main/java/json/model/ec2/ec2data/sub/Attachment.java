
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
    "AttachmentId",
    "DeleteOnTermination",
    "DeviceIndex",
    "Status",
    "NetworkCardIndex"
})

public class Attachment implements Serializable
{

    @JsonProperty("AttachTime")
    private String attachTime;
    @JsonProperty("AttachmentId")
    private String attachmentId;
    @JsonProperty("DeleteOnTermination")
    private Boolean deleteOnTermination;
    @JsonProperty("DeviceIndex")
    private Integer deviceIndex;
    @JsonProperty("Status")
    private String status;
    @JsonProperty("NetworkCardIndex")
    private Integer networkCardIndex;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 3865389125693912994L;

    @JsonProperty("AttachTime")
    public String getAttachTime() {
        return attachTime;
    }

    @JsonProperty("AttachTime")
    public void setAttachTime(String attachTime) {
        this.attachTime = attachTime;
    }

    @JsonProperty("AttachmentId")
    public String getAttachmentId() {
        return attachmentId;
    }

    @JsonProperty("AttachmentId")
    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    @JsonProperty("DeleteOnTermination")
    public Boolean getDeleteOnTermination() {
        return deleteOnTermination;
    }

    @JsonProperty("DeleteOnTermination")
    public void setDeleteOnTermination(Boolean deleteOnTermination) {
        this.deleteOnTermination = deleteOnTermination;
    }

    @JsonProperty("DeviceIndex")
    public Integer getDeviceIndex() {
        return deviceIndex;
    }

    @JsonProperty("DeviceIndex")
    public void setDeviceIndex(Integer deviceIndex) {
        this.deviceIndex = deviceIndex;
    }

    @JsonProperty("Status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("Status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("NetworkCardIndex")
    public Integer getNetworkCardIndex() {
        return networkCardIndex;
    }

    @JsonProperty("NetworkCardIndex")
    public void setNetworkCardIndex(Integer networkCardIndex) {
        this.networkCardIndex = networkCardIndex;
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
