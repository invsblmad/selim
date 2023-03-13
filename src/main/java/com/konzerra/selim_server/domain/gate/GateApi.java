package com.konzerra.selim_server.domain.gate;

import com.konzerra.selim_server.ApiPath;

public class GateApi {
    private static final String publicPath = ApiPath.publicPath+"/gate";
    private static final String protectedPath = ApiPath.protectedPath+"/gate";
    public static final String save = protectedPath;

    public static final String update = protectedPath;
    public static final String deleteById = protectedPath+"/{id}";
    public static final String getAllByCategoryId = publicPath + "/{categoryId}";

    public static final String getAll = publicPath;
    public static final String getAllPaginated = publicPath + "/{categoryId}/{pageNumber}/{pageSize}/{sortDirection}/{sortField}";
}
