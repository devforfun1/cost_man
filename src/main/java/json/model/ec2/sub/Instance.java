
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
import software.amazon.awssdk.services.ec2.model.CapacityReservationSpecification;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "AmiLaunchIndex",
    "ImageId",
    "InstanceId",
    "InstanceType",
    "KeyName",
    "LaunchTime",
    "Monitoring",
    "Placement",
    "PrivateDnsName",
    "PrivateIpAddress",
    "ProductCodes",
    "PublicDnsName",
    "PublicIpAddress",
    "State",
    "StateTransitionReason",
    "SubnetId",
    "VpcId",
    "Architecture",
    "BlockDeviceMappings",
    "ClientToken",
    "EbsOptimized",
    "EnaSupport",
    "Hypervisor",
    "NetworkInterfaces",
    "RootDeviceName",
    "RootDeviceType",
    "SecurityGroups",
    "SourceDestCheck",
    "Tags",
    "VirtualizationType",
    "CpuOptions",
    "CapacityReservationSpecification",
    "HibernationOptions",
    "MetadataOptions",
    "EnclaveOptions"
})

public class Instance implements Serializable
{

    @JsonProperty("AmiLaunchIndex")
    private Integer amiLaunchIndex;
    @JsonProperty("ImageId")
    private String imageId;
    @JsonProperty("InstanceId")
    private String instanceId;
    @JsonProperty("InstanceType")
    private String instanceType;
    @JsonProperty("KeyName")
    private String keyName;
    @JsonProperty("LaunchTime")
    private String launchTime;
    @JsonProperty("Monitoring")
    private Monitoring monitoring;
    @JsonProperty("Placement")
    private Placement placement;
    @JsonProperty("PrivateDnsName")
    private String privateDnsName;
    @JsonProperty("PrivateIpAddress")
    private String privateIpAddress;
    @JsonProperty("ProductCodes")
    private List<Object> productCodes = null;
    @JsonProperty("PublicDnsName")
    private String publicDnsName;
    @JsonProperty("PublicIpAddress")
    private String publicIpAddress;
    @JsonProperty("State")
    private State state;
    @JsonProperty("StateTransitionReason")
    private String stateTransitionReason;
    @JsonProperty("SubnetId")
    private String subnetId;
    @JsonProperty("VpcId")
    private String vpcId;
    @JsonProperty("Architecture")
    private String architecture;
    @JsonProperty("BlockDeviceMappings")
    private List<BlockDeviceMapping> blockDeviceMappings = null;
    @JsonProperty("ClientToken")
    private String clientToken;
    @JsonProperty("EbsOptimized")
    private Boolean ebsOptimized;
    @JsonProperty("EnaSupport")
    private Boolean enaSupport;
    @JsonProperty("Hypervisor")
    private String hypervisor;
    @JsonProperty("NetworkInterfaces")
    private List<NetworkInterface> networkInterfaces = null;
    @JsonProperty("RootDeviceName")
    private String rootDeviceName;
    @JsonProperty("RootDeviceType")
    private String rootDeviceType;
    @JsonProperty("SecurityGroups")
    private List<SecurityGroup> securityGroups = null;
    @JsonProperty("SourceDestCheck")
    private Boolean sourceDestCheck;
    @JsonProperty("Tags")
    private List<Tag> tags = null;
    @JsonProperty("VirtualizationType")
    private String virtualizationType;
    @JsonProperty("CpuOptions")
    private CpuOptions cpuOptions;
    @JsonProperty("CapacityReservationSpecification")
    private CapacityReservationSpecification capacityReservationSpecification;
    @JsonProperty("HibernationOptions")
    private HibernationOptions hibernationOptions;
    @JsonProperty("MetadataOptions")
    private MetadataOptions metadataOptions;
    @JsonProperty("EnclaveOptions")
    private EnclaveOptions enclaveOptions;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -8922759849779303188L;

    @JsonProperty("AmiLaunchIndex")
    public Integer getAmiLaunchIndex() {
        return amiLaunchIndex;
    }

    @JsonProperty("AmiLaunchIndex")
    public void setAmiLaunchIndex(Integer amiLaunchIndex) {
        this.amiLaunchIndex = amiLaunchIndex;
    }

    @JsonProperty("ImageId")
    public String getImageId() {
        return imageId;
    }

    @JsonProperty("ImageId")
    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    @JsonProperty("InstanceId")
    public String getInstanceId() {
        return instanceId;
    }

    @JsonProperty("InstanceId")
    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    @JsonProperty("InstanceType")
    public String getInstanceType() {
        return instanceType;
    }

    @JsonProperty("InstanceType")
    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }

    @JsonProperty("KeyName")
    public String getKeyName() {
        return keyName;
    }

    @JsonProperty("KeyName")
    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    @JsonProperty("LaunchTime")
    public String getLaunchTime() {
        return launchTime;
    }

    @JsonProperty("LaunchTime")
    public void setLaunchTime(String launchTime) {
        this.launchTime = launchTime;
    }

    @JsonProperty("Monitoring")
    public Monitoring getMonitoring() {
        return monitoring;
    }

    @JsonProperty("Monitoring")
    public void setMonitoring(Monitoring monitoring) {
        this.monitoring = monitoring;
    }

    @JsonProperty("Placement")
    public Placement getPlacement() {
        return placement;
    }

    @JsonProperty("Placement")
    public void setPlacement(Placement placement) {
        this.placement = placement;
    }

    @JsonProperty("PrivateDnsName")
    public String getPrivateDnsName() {
        return privateDnsName;
    }

    @JsonProperty("PrivateDnsName")
    public void setPrivateDnsName(String privateDnsName) {
        this.privateDnsName = privateDnsName;
    }

    @JsonProperty("PrivateIpAddress")
    public String getPrivateIpAddress() {
        return privateIpAddress;
    }

    @JsonProperty("PrivateIpAddress")
    public void setPrivateIpAddress(String privateIpAddress) {
        this.privateIpAddress = privateIpAddress;
    }

    @JsonProperty("ProductCodes")
    public List<Object> getProductCodes() {
        return productCodes;
    }

    @JsonProperty("ProductCodes")
    public void setProductCodes(List<Object> productCodes) {
        this.productCodes = productCodes;
    }

    @JsonProperty("PublicDnsName")
    public String getPublicDnsName() {
        return publicDnsName;
    }

    @JsonProperty("PublicDnsName")
    public void setPublicDnsName(String publicDnsName) {
        this.publicDnsName = publicDnsName;
    }

    @JsonProperty("PublicIpAddress")
    public String getPublicIpAddress() {
        return publicIpAddress;
    }

    @JsonProperty("PublicIpAddress")
    public void setPublicIpAddress(String publicIpAddress) {
        this.publicIpAddress = publicIpAddress;
    }

    @JsonProperty("State")
    public State getState() {
        return state;
    }

    @JsonProperty("State")
    public void setState(State state) {
        this.state = state;
    }

    @JsonProperty("StateTransitionReason")
    public String getStateTransitionReason() {
        return stateTransitionReason;
    }

    @JsonProperty("StateTransitionReason")
    public void setStateTransitionReason(String stateTransitionReason) {
        this.stateTransitionReason = stateTransitionReason;
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

    @JsonProperty("Architecture")
    public String getArchitecture() {
        return architecture;
    }

    @JsonProperty("Architecture")
    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    @JsonProperty("BlockDeviceMappings")
    public List<BlockDeviceMapping> getBlockDeviceMappings() {
        return blockDeviceMappings;
    }

    @JsonProperty("BlockDeviceMappings")
    public void setBlockDeviceMappings(List<BlockDeviceMapping> blockDeviceMappings) {
        this.blockDeviceMappings = blockDeviceMappings;
    }

    @JsonProperty("ClientToken")
    public String getClientToken() {
        return clientToken;
    }

    @JsonProperty("ClientToken")
    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    @JsonProperty("EbsOptimized")
    public Boolean getEbsOptimized() {
        return ebsOptimized;
    }

    @JsonProperty("EbsOptimized")
    public void setEbsOptimized(Boolean ebsOptimized) {
        this.ebsOptimized = ebsOptimized;
    }

    @JsonProperty("EnaSupport")
    public Boolean getEnaSupport() {
        return enaSupport;
    }

    @JsonProperty("EnaSupport")
    public void setEnaSupport(Boolean enaSupport) {
        this.enaSupport = enaSupport;
    }

    @JsonProperty("Hypervisor")
    public String getHypervisor() {
        return hypervisor;
    }

    @JsonProperty("Hypervisor")
    public void setHypervisor(String hypervisor) {
        this.hypervisor = hypervisor;
    }

    @JsonProperty("NetworkInterfaces")
    public List<NetworkInterface> getNetworkInterfaces() {
        return networkInterfaces;
    }

    @JsonProperty("NetworkInterfaces")
    public void setNetworkInterfaces(List<NetworkInterface> networkInterfaces) {
        this.networkInterfaces = networkInterfaces;
    }

    @JsonProperty("RootDeviceName")
    public String getRootDeviceName() {
        return rootDeviceName;
    }

    @JsonProperty("RootDeviceName")
    public void setRootDeviceName(String rootDeviceName) {
        this.rootDeviceName = rootDeviceName;
    }

    @JsonProperty("RootDeviceType")
    public String getRootDeviceType() {
        return rootDeviceType;
    }

    @JsonProperty("RootDeviceType")
    public void setRootDeviceType(String rootDeviceType) {
        this.rootDeviceType = rootDeviceType;
    }

    @JsonProperty("SecurityGroups")
    public List<SecurityGroup> getSecurityGroups() {
        return securityGroups;
    }

    @JsonProperty("SecurityGroups")
    public void setSecurityGroups(List<SecurityGroup> securityGroups) {
        this.securityGroups = securityGroups;
    }

    @JsonProperty("SourceDestCheck")
    public Boolean getSourceDestCheck() {
        return sourceDestCheck;
    }

    @JsonProperty("SourceDestCheck")
    public void setSourceDestCheck(Boolean sourceDestCheck) {
        this.sourceDestCheck = sourceDestCheck;
    }

    @JsonProperty("Tags")
    public List<Tag> getTags() {
        return tags;
    }

    @JsonProperty("Tags")
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @JsonProperty("VirtualizationType")
    public String getVirtualizationType() {
        return virtualizationType;
    }

    @JsonProperty("VirtualizationType")
    public void setVirtualizationType(String virtualizationType) {
        this.virtualizationType = virtualizationType;
    }

    @JsonProperty("CpuOptions")
    public CpuOptions getCpuOptions() {
        return cpuOptions;
    }

    @JsonProperty("CpuOptions")
    public void setCpuOptions(CpuOptions cpuOptions) {
        this.cpuOptions = cpuOptions;
    }

    @JsonProperty("CapacityReservationSpecification")
    public CapacityReservationSpecification getCapacityReservationSpecification() {
        return capacityReservationSpecification;
    }

    @JsonProperty("CapacityReservationSpecification")
    public void setCapacityReservationSpecification(CapacityReservationSpecification capacityReservationSpecification) {
        this.capacityReservationSpecification = capacityReservationSpecification;
    }

    @JsonProperty("HibernationOptions")
    public HibernationOptions getHibernationOptions() {
        return hibernationOptions;
    }

    @JsonProperty("HibernationOptions")
    public void setHibernationOptions(HibernationOptions hibernationOptions) {
        this.hibernationOptions = hibernationOptions;
    }

    @JsonProperty("MetadataOptions")
    public MetadataOptions getMetadataOptions() {
        return metadataOptions;
    }

    @JsonProperty("MetadataOptions")
    public void setMetadataOptions(MetadataOptions metadataOptions) {
        this.metadataOptions = metadataOptions;
    }

    @JsonProperty("EnclaveOptions")
    public EnclaveOptions getEnclaveOptions() {
        return enclaveOptions;
    }

    @JsonProperty("EnclaveOptions")
    public void setEnclaveOptions(EnclaveOptions enclaveOptions) {
        this.enclaveOptions = enclaveOptions;
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
