package com.konzerra.selim_server.domain.gate_category;

import com.konzerra.selim_server.ApiPath;

public class GateCategoryApi {
    private static final String publicPath = ApiPath.publicPath+"/gate_category";
    private static final String protectedPath = ApiPath.protectedPath+"/gate_category";
    public static final String save = protectedPath;

    public static final String update = protectedPath;
    public static final String deleteById = protectedPath+"/{id}";
    public static final String getById = publicPath + "/{id}";

    public static final String getAll = publicPath;
    public static final String getAllPaginated = publicPath + "/{pageNumber}/{pageSize}/{sortDirection}/{sortField}";


}
