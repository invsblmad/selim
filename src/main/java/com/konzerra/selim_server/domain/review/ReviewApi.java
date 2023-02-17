package com.konzerra.selim_server.domain.review;

import com.konzerra.selim_server.ApiPath;

public class ReviewApi {
    private static final String basePath = "/review";
    private static final String protectedPath = ApiPath.protectedPath + basePath;
    private static final String publicPath = ApiPath.publicPath + basePath;;

    public static final String findByIdPath = publicPath + "/{id}";
    public static final String findAllPaginated  =  publicPath + "/{pageNumber}/{pageSize}";

    public static final String savePath = protectedPath;
    public static final String updatePath = protectedPath;
    public static final String deleteByIdPath = protectedPath + "/{id}";
}
