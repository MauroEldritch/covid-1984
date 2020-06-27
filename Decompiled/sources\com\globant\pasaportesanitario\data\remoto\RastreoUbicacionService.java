package com.globant.pasaportesanitario.data.remoto;

import com.globant.pasaportesanitario.data.remoto.modelo.RastreoUbicacion;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RastreoUbicacionService {
    @POST("/usuarios/{dni}/ubicaciones")
    Call<Object> enviarUbicacion(@Path("dni") String str, @Query("sexo") String str2, @Body RastreoUbicacion rastreoUbicacion);
}
