package com.albaco.depositorigination.websecurity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DeviceDataRequestHeader
{
    private String uid;
    private String ipAddress;
    private String source;
    private String platform;
    private String latitude;
    private String longitude;
    private String model;
    private String brand;
    private String osVersion;
    private String osName;
    private String userAgent;
}
