package com.konzerra.selim_server.domain.review;

import com.konzerra.selim_server.ApiPath;

public class ReviewApi {
    private static final String basePath = "/review";
    private static final String protectedPath = ApiPath.protectedPath + basePath;
    private static final String publicPath = ApiPath.publicPath + basePath;;

    public static final String getAll = publicPath;
    public static final String findById = publicPath + "/{id}";
    public static final String getAllByCategoryId = publicPath+"/{categoryId}";

    public static final String getAllPaginated = publicPath + "/{categoryId}/{pageNumber}/{pageSize}/{sortDirection}/{sortField}";

    public static final String save = protectedPath;
    public static final String update = protectedPath;
    public static final String deleteById = protectedPath + "/{id}";
}
