package com.globant.pasaportesanitario.data.remoto;

import com.globant.pasaportesanitario.data.remoto.modelo.UsuarioRemoto;
import com.globant.pasaportesanitario.data.remoto.modelo_autodiagnostico.AutoevaluacionRemoto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AutoevaluacionService {
    @POST("/usuarios/{dni}/autoevaluaciones")
    Call<UsuarioRemoto> confirmarAutoevaluacion(@Path("dni") String str, @Query("sexo") String str2, @Body AutoevaluacionRemoto autoevaluacionRemoto);
}
