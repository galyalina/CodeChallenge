package com.iotta.challenge.model.api;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.iotta.challenge.utils.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Galya on 08/07/2017.
 */

public abstract class IApiGetCB<T> implements Callback<T> {

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {

        if (response.isSuccessful() && response.body()!= null) {
            handleResponse(response.body());
            return;
        }

        if(response.body()== null){
            Logger.warn("Request is successful but the body is empty " + response.message());
        }

        ResponseBody errorBody = response.errorBody();

        if (errorBody == null) {
            Logger.error("Request unsuccessful and also no error  body doesn't exist " + response.message());
            handleError(response.message());
            return;
        }

        try {

            String jsonString = errorBody.string();
            if (TextUtils.isEmpty(jsonString)) {
                Logger.error("Request unsuccessful and also no error message in error body " + response.message());
                handleError(response.message());
                return;
            }

            JSONObject jObjError = new JSONObject(jsonString);
            String errorMessage = jObjError.getString("message");
            handleError(errorMessage);
        } catch (JSONException e) {
            Logger.error("Failed to parse json error message", e);
            handleError(response.message());
        } catch (IOException e) {
            Logger.error(e.getMessage(), e);
            handleError(response.message());
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        Logger.error("Network request failed ", t);
        handleError(t.getMessage());
    }


    abstract public void handleResponse(@NonNull T responseObject);

    abstract public void handleError(@NonNull String message);
}