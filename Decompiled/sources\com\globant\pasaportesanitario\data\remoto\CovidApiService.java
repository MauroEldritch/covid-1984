package com.globant.pasaportesanitario.data.remoto;

import com.globant.pasaportesanitario.data.remoto.modelo.AutorizacionUsuario;
import com.globant.pasaportesanitario.data.remoto.modelo.NuevaInformacionUsuario;
import com.globant.pasaportesanitario.data.remoto.modelo.Token;
import com.globant.pasaportesanitario.data.remoto.modelo.UltimoEstadoDeUsuarioRemoto;
import com.globant.pasaportesanitario.data.remoto.modelo.UsuarioRemoto;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CovidApiService {
    @PATCH("usuarios/{dni}")
    Call<UsuarioRemoto> actualizarUsuario(@Path("dni") String str, @Query("sexo") String str2, @Body NuevaInformacionUsuario nuevaInformacionUsuario);

    @POST("autorizacion")
    Call<Token> autorizacion(@Body AutorizacionUsuario autorizacionUsuario);

    @GET("usuarios/{dni}/estados/ultimo")
    Call<UltimoEstadoDeUsuarioRemoto> obtenerUltimoEstadoDeUsuario(@Path("dni") String str, @Query("sexo") String str2);

    @GET("usuarios/{dni}")
    Call<UsuarioRemoto> obtenerUsuario(@Path("dni") String str, @Query("sexo") String str2);

    @GET("versiones")
    Call<ResponseBody> obtenerVersion();
}
