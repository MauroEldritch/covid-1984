package com.globant.pasaportesanitario.flujos.identificacion;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import ar.gob.coronavirus.R;
import b.m.d.c;
import b.o.r;
import b.o.z;
import c.c.a.e.c.b;
import com.globant.pasaportesanitario.data.DniEntidad;
import com.globant.pasaportesanitario.flujos.autodiagnostico.AutodiagnosticoActivity;
import com.globant.pasaportesanitario.flujos.inicio.InicioTerminosFragment;
import com.globant.pasaportesanitario.flujos.pantallaprincipal.PantallaPrincipalActivity;
import com.globant.pasaportesanitario.utils.dialogs.PantallaCompletaDialog;
import com.globant.pasaportesanitario.utils.dialogs.PantallaCompletaDialog.a;
import com.google.android.material.textfield.TextInputLayout;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public class IdentificacionDniManualFragment extends Fragment implements TraceFieldInterface {
    public static final String DNI_SCAN_LOG = "Escaneo DNI";
    public static final int SOLICITAR_PERMISO_CAMARA = 100;
    public Trace _nr_trace;
    public TextView botonEscanear;
    public TextView botonSiguiente;
    public CheckBox checkBox;
    public TextView comoObtenerNoTramite;
    public EditText dniEt;
    public TextInputLayout dniIL;
    public RadioButton femeninoRb;
    public IdentificacionViewModel identificacionViewModel;
    public Dialog loaderDialog;
    public RadioButton masculinoRb;
    public LinearLayout mensajeErrorContainer;
    public TextInputLayout noTramiteIL;
    public EditText numeroTramiteEt;
    public RadioGroup radioGroupSexo;
    public TextView textViewErrorSexo;
    public TextView txtTyC;

    /* renamed from: com.globant.pasaportesanitario.flujos.identificacion.IdentificacionDniManualFragment$13 reason: invalid class name */
    public static /* synthetic */ class AnonymousClass13 {
        public static final /* synthetic */ int[] $SwitchMap$com$globant$pasaportesanitario$flujos$identificacion$NavegacionFragments;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x000e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0015 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x001c */
        static {
            int[] iArr = new int[NavegacionFragments.values().length];
            $SwitchMap$com$globant$pasaportesanitario$flujos$identificacion$NavegacionFragments = iArr;
            NavegacionFragments navegacionFragments = NavegacionFragments.IDENTIFICACION;
            iArr[1] = 1;
            int[] iArr2 = $SwitchMap$com$globant$pasaportesanitario$flujos$identificacion$NavegacionFragments;
            NavegacionFragments navegacionFragments2 = NavegacionFragments.AUTODIAGNOSTICO;
            iArr2[2] = 2;
            int[] iArr3 = $SwitchMap$com$globant$pasaportesanitario$flujos$identificacion$NavegacionFragments;
            NavegacionFragments navegacionFragments3 = NavegacionFragments.PRINCIPAL;
            iArr3[3] = 3;
            try {
                int[] iArr4 = $SwitchMap$com$globant$pasaportesanitario$flujos$identificacion$NavegacionFragments;
                NavegacionFragments navegacionFragments4 = NavegacionFragments.ERROR;
                iArr4[0] = 4;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public enum TipoMensajeError {
        VACIO,
        INVALIDO,
        SIN_ERROR
    }

    /* access modifiers changed from: private */
    public void crearDialogoInternet() {
        final PantallaCompletaDialog a2 = PantallaCompletaDialog.a(getString(R.string.hubo_error), getString(R.string.no_hay_internet), getString(R.string.cerrar).toUpperCase(), R.drawable.ic_error);
        a2.f4589b = new a() {
            public void onClick(View view) {
                a2.dismiss();
            }
        };
        a2.show(getParentFragmentManager(), "TAG");
    }

    /* access modifiers changed from: private */
    public void deshabilidatBotones() {
        this.botonEscanear.setEnabled(false);
        this.botonSiguiente.setEnabled(false);
    }

    /* access modifiers changed from: private */
    public void habilitarBonotes() {
        this.botonEscanear.setEnabled(true);
        this.botonSiguiente.setEnabled(true);
    }

    private void iniciarEventos() {
        this.botonSiguiente.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (a.a.a.b.a.c(IdentificacionDniManualFragment.this.getContext())) {
                    IdentificacionDniManualFragment.this.mensajeErrorContainer.setVisibility(8);
                    String obj = IdentificacionDniManualFragment.this.dniEt.getText().toString();
                    String obj2 = IdentificacionDniManualFragment.this.numeroTramiteEt.getText().toString();
                    if (IdentificacionDniManualFragment.this.validarDatosEntrada(obj, obj2).booleanValue()) {
                        IdentificacionDniManualFragment.this.identificacionViewModel.registrarUsuario(obj, obj2, IdentificacionDniManualFragment.this.obtenerValorRadioGroupSexo());
                        IdentificacionDniManualFragment.this.loaderDialog.show();
                    }
                    IdentificacionDniManualFragment.this.dniIL.clearFocus();
                    IdentificacionDniManualFragment.this.noTramiteIL.clearFocus();
                    IdentificacionDniManualFragment.this.botonSiguiente.requestFocus();
                    return;
                }
                IdentificacionDniManualFragment.this.crearDialogoInternet();
            }
        });
        this.botonEscanear.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                IdentificacionDniManualFragment.this.mensajeErrorContainer.setVisibility(8);
                if (IdentificacionDniManualFragment.this.revisarPermisoCamara().booleanValue()) {
                    IdentificacionDniManualFragment identificacionDniManualFragment = IdentificacionDniManualFragment.this;
                    c.d.c.t.a.a aVar = new c.d.c.t.a.a(identificacionDniManualFragment.getActivity());
                    aVar.f4220b = identificacionDniManualFragment;
                    aVar.a();
                }
            }
        });
        this.comoObtenerNoTramite.setOnClickListener(new OnClickListener() {
            public void onClick(final View view) {
                view.setClickable(false);
                b bVar = new b(IdentificacionDniManualFragment.this.getActivity());
                if (bVar.f3113a != null) {
                    Dialog dialog = new Dialog(bVar.f3113a);
                    bVar.f3114b = dialog;
                    dialog.requestWindowFeature(1);
                    bVar.f3114b.setContentView(R.layout.tramite_dialog_layout);
                    bVar.f3114b.setCancelable(true);
                    bVar.f3114b.getWindow().setBackgroundDrawableResource(17170445);
                    ((TextView) bVar.f3114b.findViewById(R.id.boton_cerrar)).setOnClickListener(new c.c.a.e.c.a(bVar));
                    bVar.f3114b.show();
                }
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        view.setClickable(true);
                    }
                }, 500);
            }
        });
    }

    private void iniciarObservers() {
        this.identificacionViewModel.getDniEntidadLiveData().a(getViewLifecycleOwner(), new r<DniEntidad>() {
            public void onChanged(DniEntidad dniEntidad) {
                if (dniEntidad.tieneDatosBasicosCompletos()) {
                    IdentificacionDniManualFragment.this.insertarDatosEnLaVista(dniEntidad);
                    return;
                }
                Context context = IdentificacionDniManualFragment.this.getContext();
                AnonymousClass1 r2 = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                };
                Builder builder = new Builder(context);
                builder.setMessage(R.string.mensaje_error_escanear_dni);
                builder.setPositiveButton(R.string.aceptar, r2);
                builder.setCancelable(false);
                builder.show();
            }
        });
        this.identificacionViewModel.obtenerRegistrarUsuarioLiveData().a(getViewLifecycleOwner(), new r<c.c.a.e.e.a<NavegacionFragments>>() {
            public void onChanged(c.c.a.e.e.a<NavegacionFragments> aVar) {
                if (aVar.a() != null) {
                    int ordinal = ((NavegacionFragments) aVar.f3123a).ordinal();
                    if (ordinal == 0) {
                        IdentificacionDniManualFragment.this.mensajeErrorContainer.setVisibility(0);
                    } else if (ordinal == 1) {
                        ((IdentificacionActivity) IdentificacionDniManualFragment.this.getActivity()).navegarAIdentificacionConfirmacionDatosFragment();
                    } else if (ordinal == 2) {
                        AutodiagnosticoActivity.iniciar(IdentificacionDniManualFragment.this.getContext(), false);
                        IdentificacionDniManualFragment.this.getActivity().finish();
                    } else if (ordinal == 3) {
                        PantallaPrincipalActivity.iniciar(IdentificacionDniManualFragment.this.getContext(), false);
                        IdentificacionDniManualFragment.this.getActivity().finish();
                    }
                }
                IdentificacionDniManualFragment.this.loaderDialog.dismiss();
            }
        });
        this.identificacionViewModel.obtenerLimpiarLogin().a(requireActivity(), new r<c.c.a.e.e.a<Boolean>>() {
            public void onChanged(c.c.a.e.e.a<Boolean> aVar) {
                if (aVar.a() != null && ((Boolean) aVar.f3123a).booleanValue()) {
                    IdentificacionDniManualFragment.this.limpiarVista();
                }
            }
        });
    }

    private void iniciarViews() {
        this.botonSiguiente = (TextView) getView().findViewById(R.id.btn_siguiente_dni_manual_identificacion_fragment);
        this.botonEscanear = (TextView) getView().findViewById(R.id.btn_escanear_dni_identificacion_fragment);
        this.dniIL = (TextInputLayout) getView().findViewById(R.id.ti_numero_dni_identificacion);
        this.noTramiteIL = (TextInputLayout) getView().findViewById(R.id.ti_numero_tramite_identificacion);
        this.dniEt = this.dniIL.getEditText();
        this.numeroTramiteEt = this.noTramiteIL.getEditText();
        this.masculinoRb = (RadioButton) getView().findViewById(R.id.rb_masculino_identificacion_fragment);
        this.femeninoRb = (RadioButton) getView().findViewById(R.id.rb_femenino_identificacion_fragment);
        this.textViewErrorSexo = (TextView) getView().findViewById(R.id.error_sexo_radio_group);
        this.radioGroupSexo = (RadioGroup) getView().findViewById(R.id.radioGroupSexo);
        this.mensajeErrorContainer = (LinearLayout) getView().findViewById(R.id.ln_mensaje_error_container);
        this.comoObtenerNoTramite = (TextView) getView().findViewById(R.id.tv_como_obtengo_numero_tramite_identificacion_fragment);
        this.checkBox = (CheckBox) getView().findViewById(R.id.checkBoxAceptarCondiciones);
        this.txtTyC = (TextView) getView().findViewById(R.id.txtTerminosYCondiciones);
        setTyCTextoFormateado();
        this.dniEt.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    IdentificacionDniManualFragment.this.dniIL.setErrorEnabled(false);
                }
            }
        });
        this.numeroTramiteEt.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    IdentificacionDniManualFragment.this.noTramiteIL.setErrorEnabled(false);
                }
            }
        });
        this.radioGroupSexo.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (IdentificacionDniManualFragment.this.textViewErrorSexo != null) {
                    IdentificacionDniManualFragment.this.textViewErrorSexo.setVisibility(4);
                }
                a.a.a.b.a.a((Activity) IdentificacionDniManualFragment.this.requireActivity());
            }
        });
        this.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    IdentificacionDniManualFragment.this.habilitarBonotes();
                } else {
                    IdentificacionDniManualFragment.this.deshabilidatBotones();
                }
                a.a.a.b.a.a((Activity) IdentificacionDniManualFragment.this.requireActivity());
            }
        });
        this.checkBox.setChecked(true);
        this.txtTyC.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                new InicioTerminosFragment().show(IdentificacionDniManualFragment.this.getParentFragmentManager(), "TyC");
            }
        });
        c activity = getActivity();
        getActivity().getLayoutInflater();
        this.loaderDialog = a.a.a.b.a.a((Context) activity);
    }

    /* access modifiers changed from: private */
    public void insertarDatosEnLaVista(DniEntidad dniEntidad) {
        this.dniEt.setText(dniEntidad.getId());
        this.numeroTramiteEt.setText(dniEntidad.getTramite());
        if (dniEntidad.getSexo().equals("M")) {
            this.masculinoRb.setChecked(true);
        } else {
            this.femeninoRb.setChecked(true);
        }
    }

    /* access modifiers changed from: private */
    public void limpiarVista() {
        String str = "";
        this.dniEt.setText(str);
        this.numeroTramiteEt.setText(str);
        this.radioGroupSexo.clearCheck();
    }

    /* access modifiers changed from: private */
    public String obtenerValorRadioGroupSexo() {
        switch (this.radioGroupSexo.getCheckedRadioButtonId()) {
            case R.id.rb_femenino_identificacion_fragment /*2131296616*/:
                return "F";
            case R.id.rb_masculino_identificacion_fragment /*2131296617*/:
                return "M";
            default:
                return "";
        }
    }

    /* access modifiers changed from: private */
    public Boolean revisarPermisoCamara() {
        String str = "android.permission.CAMERA";
        boolean z = true;
        if (b.h.f.a.a(requireContext(), str) != 0) {
            z = false;
            requestPermissions(new String[]{str}, 100);
        }
        return Boolean.valueOf(z);
    }

    private void setTyCTextoFormateado() {
        this.txtTyC.setText(Html.fromHtml(getString(R.string.acepto_terminos)));
    }

    /* access modifiers changed from: private */
    public Boolean validarDatosEntrada(String str, String str2) {
        if (validarDni(str) == TipoMensajeError.SIN_ERROR && validarNumeroDeTramite(str2) == TipoMensajeError.SIN_ERROR && !obtenerValorRadioGroupSexo().isEmpty()) {
            return Boolean.valueOf(true);
        }
        if (this.textViewErrorSexo != null) {
            if (obtenerValorRadioGroupSexo().isEmpty()) {
                this.textViewErrorSexo.setVisibility(0);
            } else {
                this.textViewErrorSexo.setVisibility(4);
            }
        }
        if (validarDni(str) == TipoMensajeError.VACIO) {
            this.dniIL.setError(getString(R.string.erro_dni));
        } else if (validarDni(str) == TipoMensajeError.INVALIDO) {
            this.dniIL.setError(getString(R.string.erro_dni_invalido));
        } else {
            this.dniIL.setErrorEnabled(false);
        }
        if (validarNumeroDeTramite(str2) == TipoMensajeError.VACIO) {
            this.noTramiteIL.setError(getString(R.string.erro_numero_tramite));
        } else if (validarNumeroDeTramite(str2) == TipoMensajeError.INVALIDO) {
            this.noTramiteIL.setError(getString(R.string.erro_numero_tramite_invalido));
        } else {
            this.noTramiteIL.setErrorEnabled(false);
        }
        return Boolean.valueOf(false);
    }

    private TipoMensajeError validarDni(String str) {
        if (str.isEmpty()) {
            return TipoMensajeError.VACIO;
        }
        if (str.length() < 7 || str.length() > 9) {
            return TipoMensajeError.INVALIDO;
        }
        return TipoMensajeError.SIN_ERROR;
    }

    private TipoMensajeError validarNumeroDeTramite(String str) {
        if (str.isEmpty()) {
            return TipoMensajeError.VACIO;
        }
        return TipoMensajeError.SIN_ERROR;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        iniciarViews();
        this.identificacionViewModel = (IdentificacionViewModel) new z(getActivity()).a(IdentificacionViewModel.class);
        iniciarObservers();
        iniciarEventos();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        c.d.c.t.a.b a2 = c.d.c.t.a.a.a(i, i2, intent);
        if (a2 != null) {
            String str = a2.f4223a;
            if (str != null) {
                this.identificacionViewModel.procesarCodigoQr(str);
            }
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        String str = "IdentificacionDniManualFragment#onCreateView";
        try {
            TraceMachine.enterMethod(this._nr_trace, str, null);
        } catch (NoSuchFieldError unused) {
            TraceMachine.enterMethod(null, str, null);
        }
        View inflate = layoutInflater.inflate(R.layout.identificacion_pregunta_dni_manual_fragment, viewGroup, false);
        TraceMachine.exitMethod();
        return inflate;
    }

    public void onPause() {
        super.onPause();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 100 && iArr.length > 0 && iArr[0] == 0) {
            c.d.c.t.a.a aVar = new c.d.c.t.a.a(getActivity());
            aVar.f4220b = this;
            aVar.a();
        }
    }

    public void onStart() {
        super.onStart();
    }

    public void onStop() {
        super.onStop();
    }
}
