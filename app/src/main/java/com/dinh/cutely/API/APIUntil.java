package com.dinh.cutely.API;

public class APIUntil {
    private static String baseURL = "http://hinhnen.somee.com/api/"; // 192.168.1.3   192.168.43.10
    public static APIService getServer() {
        return APIClient.getApiClientLSP(baseURL).create(APIService.class);
    }
}
