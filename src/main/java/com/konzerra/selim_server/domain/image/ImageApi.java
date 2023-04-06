package com.konzerra.selim_server.domain.image;

import com.konzerra.selim_server.ApiPath;

public class ImageApi {
    private static final String publicPath = ApiPath.publicPath+"/image";
    private static final String protectedPath = ApiPath.protectedPath+"/image";
    public static final String save = protectedPath;

    public static final String update = protectedPath;
    public static final String getByName = publicPath+"/{fileName}";

}
