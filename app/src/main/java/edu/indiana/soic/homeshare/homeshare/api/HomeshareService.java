package edu.indiana.soic.homeshare.homeshare.api;

import edu.indiana.soic.homeshare.homeshare.data.model.Data;
import edu.indiana.soic.homeshare.homeshare.data.model.Survey;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HomeshareService {

    @GET("actitem/listAll?status=all")
    Call<Data> getInteviewsSurveys(@Query("pId") String pId);

    @POST("actitem/modaction?type=1")
    Call<Survey> updateSurvey(@Query("pId") String pId, @Body SurveyStatus status);


    @POST("token/create")
    Call<ParticipantToken> updateToken(@Body ParticipantToken participantToken);

    @GET("participant/askhelp")
    Call<ResponseBody> contactResearcher(@Query("pId") String pId);

}
