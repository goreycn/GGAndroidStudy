package goreyjp.com.ggandroidstudy.activity.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface GankApiService {

    public static final String baseUrl = "http://gank.io/";

    @GET("api/search/query/listview/category/福利/count/10/page/{page}")
    Call<MeiziInfo> listMeizi(@Path("page") int page);
}



