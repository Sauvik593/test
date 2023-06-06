package com.albaco.depositorigination.websecurity;

import com.albaco.depositorigination.common.Constants;
import com.albaco.depositorigination.common.utils.CommonUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class SessionFilter extends OncePerRequestFilter {

    private static final ThreadLocal<DeviceDataRequestHeader> DEVICE_DATA_HOLDER = new ThreadLocal<DeviceDataRequestHeader>();
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        long startTime = System.currentTimeMillis();

        // Setting the device data
        setDeviceData(request);

        filterChain.doFilter(request, response);

        clearDeviceData();
    }
    private void setDeviceData(HttpServletRequest request)
    {
        DeviceDataRequestHeader deviceData = new DeviceDataRequestHeader();
        deviceData.setUid(request.getHeader(Constants.REQUEST_HEADER_UID_KEY));
        deviceData.setIpAddress(getRequestIP(request));
        deviceData.setSource(request.getHeader(Constants.REQUEST_HEADER_SOURCE_KEY));
        deviceData.setPlatform(request.getHeader(Constants.REQUEST_HEADER_PLATFORM_KEY));
        deviceData.setLatitude(request.getHeader(Constants.REQUEST_HEADER_LATITUDE_KEY));
        deviceData.setLongitude(request.getHeader(Constants.REQUEST_HEADER_LONGITUDE_KEY));
        deviceData.setModel(request.getHeader(Constants.MODEL_HEADER_NAME));
        deviceData.setBrand(request.getHeader(Constants.BRAND_HEADER_NAME));
        deviceData.setOsVersion(request.getHeader(Constants.OS_VERSION_NAME));
        deviceData.setOsName(request.getHeader(Constants.OS_NAME_KEY));
        deviceData.setUserAgent(request.getHeader(Constants.USER_AGENT));

        DEVICE_DATA_HOLDER.set(deviceData);
    }

    public static DeviceDataRequestHeader getDeviceDataSpecificRequest()
    {
        return DEVICE_DATA_HOLDER.get();
    }

    private void clearDeviceData()
    {
        DEVICE_DATA_HOLDER.remove();
    }

    private String getRequestIP(HttpServletRequest request)
    {
        String ip = request.getHeader(Constants.REQUEST_HEADER_X_FORWARDED_FOR_KEY);

        if (CommonUtils.isStringEmpty(ip))
        {
            ip = request.getRemoteAddr();
        }
        else
        {
            ip = StringUtils.trim(StringUtils.substringBefore(ip, ","));
        }
        return ip;
    }

    public static String getRequestIp()
    {
        DeviceDataRequestHeader data = getDeviceDataSpecificRequest();
        return (data == null) ? null : data.getIpAddress();
    }

    public static String getRequestSourceOsName()
    {
        DeviceDataRequestHeader data = getDeviceDataSpecificRequest();
        return data == null ? "Android" : data.getOsName();
    }

    public static String getDeviceId()
    {
        DeviceDataRequestHeader data = getDeviceDataSpecificRequest();
        return (data == null) ? null : data.getUid();
    }

    public static Double getLatitude()
    {
        DeviceDataRequestHeader data = getDeviceDataSpecificRequest();
        return (data == null) ? null : CommonUtils.getDoubleForStringNullable(data.getLatitude());
    }


    public static boolean isLocationMissing()
    {
        return getLatitude() == null || getLongitude() == null;
    }

    public static Double getLongitude()
    {
        DeviceDataRequestHeader data = getDeviceDataSpecificRequest();
        return (data == null) ? null : CommonUtils.getDoubleForStringNullable(data.getLongitude());
    }

    public static String getUserAgent()
    {
        DeviceDataRequestHeader data = getDeviceDataSpecificRequest();
        return (data == null) ? null : data.getUserAgent();
    }
}
