
package json.model.ec2.sub;
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
    "Association",
    "Attachment",
    "Description",
    "Groups",
    "Ipv6Addresses",
    "MacAddress",
    "NetworkInterfaceId",
    "OwnerId",
    "PrivateIpAddress",
    "PrivateIpAddresses",
    "SourceDestCheck",
    "Status",
    "SubnetId",
    "VpcId",
    "InterfaceType"
})

public class NetworkInterface implements Serializable
{

    @JsonProperty("Association")
    private Association association;
    @JsonProperty("Attachment")
    private Attachment attachment;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("Groups")
    private List<Group> groups = null;
    @JsonProperty("Ipv6Addresses")
    private List<Object> ipv6Addresses = null;
    @JsonProperty("MacAddress")
    private String macAddress;
    @JsonProperty("NetworkInterfaceId")
    private String networkInterfaceId;
    @JsonProperty("OwnerId")
    private String ownerId;
    @JsonProperty("PrivateIpAddress")
    private String privateIpAddress;
    @JsonProperty("PrivateIpAddresses")
    private List<PrivateIpAddress> privateIpAddresses = null;
    @JsonProperty("SourceDestCheck")
    private Boolean sourceDestCheck;
    @JsonProperty("Status")
    private String status;
    @JsonProperty("SubnetId")
    private String subnetId;
    @JsonProperty("VpcId")
    private String vpcId;
    @JsonProperty("InterfaceType")
    private String interfaceType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -8611626524197828627L;

    @JsonProperty("Association")
    public Association getAssociation() {
        return association;
    }

    @JsonProperty("Association")
    public void setAssociation(Association association) {
        this.association = association;
    }

    @JsonProperty("Attachment")
    public Attachment getAttachment() {
        return attachment;
    }

    @JsonProperty("Attachment")
    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    @JsonProperty("Description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("Description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("Groups")
    public List<Group> getGroups() {
        return groups;
    }

    @JsonProperty("Groups")
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @JsonProperty("Ipv6Addresses")
    public List<Object> getIpv6Addresses() {
        return ipv6Addresses;
    }

    @JsonProperty("Ipv6Addresses")
    public void setIpv6Addresses(List<Object> ipv6Addresses) {
        this.ipv6Addresses = ipv6Addresses;
    }

    @JsonProperty("MacAddress")
    public String getMacAddress() {
        return macAddress;
    }

    @JsonProperty("MacAddress")
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    @JsonProperty("NetworkInterfaceId")
    public String getNetworkInterfaceId() {
        return networkInterfaceId;
    }

    @JsonProperty("NetworkInterfaceId")
    public void setNetworkInterfaceId(String networkInterfaceId) {
        this.networkInterfaceId = networkInterfaceId;
    }

    @JsonProperty("OwnerId")
    public String getOwnerId() {
        return ownerId;
    }

    @JsonProperty("OwnerId")
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @JsonProperty("PrivateIpAddress")
    public String getPrivateIpAddress() {
        return privateIpAddress;
    }

    @JsonProperty("PrivateIpAddress")
    public void setPrivateIpAddress(String privateIpAddress) {
        this.privateIpAddress = privateIpAddress;
    }

    @JsonProperty("PrivateIpAddresses")
    public List<PrivateIpAddress> getPrivateIpAddresses() {
        return privateIpAddresses;
    }

    @JsonProperty("PrivateIpAddresses")
    public void setPrivateIpAddresses(List<PrivateIpAddress> privateIpAddresses) {
        this.privateIpAddresses = privateIpAddresses;
    }

    @JsonProperty("SourceDestCheck")
    public Boolean getSourceDestCheck() {
        return sourceDestCheck;
    }

    @JsonProperty("SourceDestCheck")
    public void setSourceDestCheck(Boolean sourceDestCheck) {
        this.sourceDestCheck = sourceDestCheck;
    }

    @JsonProperty("Status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("Status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("SubnetId")
    public String getSubnetId() {
        return subnetId;
    }

    @JsonProperty("SubnetId")
    public void setSubnetId(String subnetId) {
        this.subnetId = subnetId;
    }

    @JsonProperty("VpcId")
    public String getVpcId() {
        return vpcId;
    }

    @JsonProperty("VpcId")
    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    @JsonProperty("InterfaceType")
    public String getInterfaceType() {
        return interfaceType;
    }

    @JsonProperty("InterfaceType")
    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
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
