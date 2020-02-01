package bluemwall.mhussain96.com.bluemwall.Retrofit;

import bluemwall.mhussain96.com.bluemwall.Model.AnalyzeModel.ComputerVision;
import bluemwall.mhussain96.com.bluemwall.Model.AnalyzeModel.URLUpload;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Muhtamim Fuwad Nahid on 3/16/2018.
 */

public interface IComputerVision {
    @Headers({
            "Content-Type:application/json",
            "Ocp-Apim-Subscription-Key:a99edef471044c59bdbd5e1142dd186d"
    })
    @POST
    Call<ComputerVision> analyzeImage(@Url String apiEndPoint, @Body URLUpload url);
}
