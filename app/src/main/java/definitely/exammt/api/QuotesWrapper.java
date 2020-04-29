package definitely.exammt.api;

import definitely.exammt.model.Raid;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuotesWrapper {

    private static final String BASE_URL = "http://api.forismatic.com/api/1.0/";
    private static QuotesWrapper wrapper;
    private QuotesService service;

    private QuotesWrapper() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory
                        .create())
                .build();
        service = retrofit.create(QuotesService.class);
    }

    public static QuotesWrapper getInstance() {
        if (wrapper == null) {
            return wrapper = new QuotesWrapper();
        } else {
            return wrapper;
        }
    }

    public void getCurrentQuote(String method, String format, String lang, final DataListener<Raid> listener) {
        service.getQuote(method, format, lang).enqueue(new Callback<Raid>() {
            @Override
            public void onResponse(Call<Raid> call, Response<Raid> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onSuccess(response.body());
                } else {
                    listener.onFailureError();
                }
            }

            @Override
            public void onFailure(Call<Raid> call, Throwable t) {

            }
        });
    }

    public interface DataListener<T> {
        void onSuccess(T data);

        void onFailureError();
    }
}
