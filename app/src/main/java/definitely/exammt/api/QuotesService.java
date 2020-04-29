package definitely.exammt.api;

import definitely.exammt.model.Raid;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuotesService {
    @GET("index")
    Call<Raid> getQuote(@Query("method") String method, @Query("format") String format, @Query("lang") String lang);
}
