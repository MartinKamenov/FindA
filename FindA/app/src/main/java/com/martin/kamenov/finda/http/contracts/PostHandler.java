package com.martin.kamenov.finda.http.contracts;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Martin on 27.11.2018 г..
 */

public interface PostHandler {
    void handlePost(Call call, Response response);

    void handleError(Call call, Exception ex);
}
