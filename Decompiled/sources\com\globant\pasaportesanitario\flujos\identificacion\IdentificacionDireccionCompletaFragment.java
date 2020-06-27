package com.globant.pasaportesanitario.flujos.identificacion;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import androidx.fragment.app.Fragment;
import ar.gob.coronavirus.R;
import b.m.d.c;
import b.o.r;
import b.o.z;
import c.c.a.e.h.a;
import com.globant.pasaportesanitario.data.Localidad;
import com.globant.pasaportesanitario.data.Provincia;
import com.globant.pasaportesanitario.data.Provincias;
import com.globant.pasaportesanitario.utils.dialogs.PantallaCompletaDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.List;

@Instrumented
public class IdentificacionDireccionCompletaFragment extends Fragment implements TraceFieldInterface {
    public Trace _nr_trace;
    public TextView botonSiguiente;
    public TextInputLayout calleTIL;
    public TextInputEditText calleTie;
    public TextInputLayout codigoPostalTIL;
    public TextInputEditText codigoPostalTie;
    public IdentificacionViewModel identificacionViewModel;
    public Dialog loaderDialog;
    public a<Localidad> localidadAdapter;
    public AutoCompleteTextView localidadDropDown;
    public TextInputLayout localidadTIL;
    public TextInputLayout numeroTIL;
    public TextInputEditText numeroTie;
    public TextInputEditText otrosTie;
    public TextInputEditText pisoTie;
    public a<Provincia> provinciaAdapter;
    public TextInputLayout provinciaTIL;
    public AutoCompleteTextView provinciasDropDown;
    public TextInputEditText puertaTie;

    /* access modifiers changed from: private */
    public void crearDialogoInternet() {
        final PantallaCompletaDialog a2 = PantallaCompletaDialog.a(getString(R.string.hubo_error), getString(R.string.no_hay_internet), getString(R.string.cerrar).toUpperCase(), R.drawable.ic_error);
        a2.f4589b = new PantallaCompletaDialog.a() {
            public void onClick(View view) {
                a2.dismiss();
            }
        };
        a2.show(getParentFragmentManager(), "TAG");
    }

    private void iniciarListeners() {
        this.botonSiguiente.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (IdentificacionDireccionCompletaFragment.this.validarFormulario().booleanValue()) {
                    IdentificacionDireccionCompletaFragment.this.identificacionViewModel.crearDomicilioRemoto(IdentificacionDireccionCompletaFragment.this.provinciasDropDown.getText().toString(), IdentificacionDireccionCompletaFragment.this.calleTie.getText().toString(), IdentificacionDireccionCompletaFragment.this.numeroTie.getText().toString(), IdentificacionDireccionCompletaFragment.this.pisoTie.getText().toString(), IdentificacionDireccionCompletaFragment.this.puertaTie.getText().toString(), IdentificacionDireccionCompletaFragment.this.codigoPostalTie.getText().toString(), IdentificacionDireccionCompletaFragment.this.otrosTie.getText().toString());
                    if (a.a.a.b.a.c(IdentificacionDireccionCompletaFragment.this.getContext())) {
                        IdentificacionDireccionCompletaFragment.this.identificacionViewModel.actualizarUsuario();
                        IdentificacionDireccionCompletaFragment.this.loaderDialog.show();
                        return;
                    }
                    IdentificacionDireccionCompletaFragment.this.crearDialogoInternet();
                }
            }
        });
    }

    private void iniciarObservers() {
        this.identificacionViewModel.crearObjetoProvinciaDesdeString();
        this.identificacionViewModel.obtenerProvinciasLiveData().a(getViewLifecycleOwner(), new r<Provincias>() {
            public void onChanged(Provincias provincias) {
                IdentificacionDireccionCompletaFragment.this.provinciaAdapter = new a(IdentificacionDireccionCompletaFragment.this.getContext(), R.layout.lista_item_dropdown, provincias.getProvincias());
                IdentificacionDireccionCompletaFragment.this.provinciasDropDown.setAdapter(IdentificacionDireccionCompletaFragment.this.provinciaAdapter);
                IdentificacionDireccionCompletaFragment.this.provinciasDropDown.setOnItemClickListener(new OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        IdentificacionDireccionCompletaFragment.this.provinciaTIL.setErrorEnabled(false);
                        IdentificacionDireccionCompletaFragment.this.identificacionViewModel.provinciaSeleccionado = (Provincia) adapterView.getItemAtPosition(i);
                        IdentificacionDireccionCompletaFragment.this.identificacionViewModel.filtrarLocalidades(IdentificacionDireccionCompletaFragment.this.identificacionViewModel.provinciaSeleccionado.getId());
                        IdentificacionDireccionCompletaFragment.this.localidadDropDown.setText("");
                        IdentificacionDireccionCompletaFragment.this.localidadTIL.setEnabled(true);
                        IdentificacionDireccionCompletaFragment.this.calleTie.setEnabled(false);
                        IdentificacionDireccionCompletaFragment.this.numeroTie.setEnabled(false);
                        IdentificacionDireccionCompletaFragment.this.pisoTie.setEnabled(false);
                        IdentificacionDireccionCompletaFragment.this.puertaTie.setEnabled(false);
                        IdentificacionDireccionCompletaFragment.this.codigoPostalTie.setEnabled(false);
                        IdentificacionDireccionCompletaFragment.this.otrosTie.setEnabled(false);
                        InputMethodManager inputMethodManager = (InputMethodManager) IdentificacionDireccionCompletaFragment.this.requireContext().getSystemService("input_method");
                        if (inputMethodManager != null) {
                            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                    }
                });
                IdentificacionDireccionCompletaFragment.this.provinciasDropDown.setOnEditorActionListener(new OnEditorActionListener() {
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        Provincia provincia = (Provincia) IdentificacionDireccionCompletaFragment.this.provinciaAdapter.a();
                        String str = "";
                        if (provincia == null) {
                            IdentificacionDireccionCompletaFragment.this.provinciaTIL.setError("Provincia inválida");
                            IdentificacionDireccionCompletaFragment.this.provinciaTIL.setErrorEnabled(true);
                            IdentificacionDireccionCompletaFragment.this.identificacionViewModel.provinciaSeleccionado = null;
                            IdentificacionDireccionCompletaFragment.this.identificacionViewModel.localidadSeleccionada(null);
                            IdentificacionDireccionCompletaFragment.this.localidadTIL.setEnabled(false);
                            IdentificacionDireccionCompletaFragment.this.localidadDropDown.setText(str);
                            IdentificacionDireccionCompletaFragment.this.calleTie.setEnabled(false);
                            IdentificacionDireccionCompletaFragment.this.numeroTie.setEnabled(false);
                            IdentificacionDireccionCompletaFragment.this.pisoTie.setEnabled(false);
                            IdentificacionDireccionCompletaFragment.this.puertaTie.setEnabled(false);
                            IdentificacionDireccionCompletaFragment.this.codigoPostalTie.setEnabled(false);
                            IdentificacionDireccionCompletaFragment.this.otrosTie.setEnabled(false);
                            return true;
                        }
                        IdentificacionDireccionCompletaFragment.this.provinciasDropDown.setText(provincia.toString());
                        IdentificacionDireccionCompletaFragment.this.provinciasDropDown.dismissDropDown();
                        IdentificacionDireccionCompletaFragment.this.provinciaTIL.setError(null);
                        IdentificacionDireccionCompletaFragment.this.provinciaTIL.setErrorEnabled(false);
                        IdentificacionDireccionCompletaFragment.this.identificacionViewModel.provinciaSeleccionado = provincia;
                        IdentificacionDireccionCompletaFragment.this.identificacionViewModel.filtrarLocalidades(provincia.getId());
                        IdentificacionDireccionCompletaFragment.this.localidadTIL.setEnabled(true);
                        IdentificacionDireccionCompletaFragment.this.localidadDropDown.setText(str);
                        IdentificacionDireccionCompletaFragment.this.calleTie.setEnabled(false);
                        IdentificacionDireccionCompletaFragment.this.numeroTie.setEnabled(false);
                        IdentificacionDireccionCompletaFragment.this.pisoTie.setEnabled(false);
                        IdentificacionDireccionCompletaFragment.this.puertaTie.setEnabled(false);
                        IdentificacionDireccionCompletaFragment.this.codigoPostalTie.setEnabled(false);
                        IdentificacionDireccionCompletaFragment.this.otrosTie.setEnabled(false);
                        return false;
                    }
                });
            }
        });
        this.identificacionViewModel.obtenerLocalidadesParaSpinner().a(getViewLifecycleOwner(), new r<List<Localidad>>() {
            public void onChanged(List<Localidad> list) {
                IdentificacionDireccionCompletaFragment.this.localidadAdapter = new a(IdentificacionDireccionCompletaFragment.this.requireContext(), R.layout.lista_item_dropdown, list);
                if (list.isEmpty()) {
                    IdentificacionDireccionCompletaFragment.this.identificacionViewModel.localidadSeleccionada(null);
                    IdentificacionDireccionCompletaFragment.this.identificacionViewModel.provinciaSeleccionado = null;
                    IdentificacionDireccionCompletaFragment.this.localidadTIL.setEnabled(false);
                    IdentificacionDireccionCompletaFragment.this.localidadDropDown.setText("");
                    IdentificacionDireccionCompletaFragment.this.calleTie.setEnabled(false);
                    IdentificacionDireccionCompletaFragment.this.numeroTie.setEnabled(false);
                    IdentificacionDireccionCompletaFragment.this.pisoTie.setEnabled(false);
                    IdentificacionDireccionCompletaFragment.this.puertaTie.setEnabled(false);
                    IdentificacionDireccionCompletaFragment.this.codigoPostalTie.setEnabled(false);
                    IdentificacionDireccionCompletaFragment.this.otrosTie.setEnabled(false);
                }
                IdentificacionDireccionCompletaFragment.this.localidadDropDown.setAdapter(IdentificacionDireccionCompletaFragment.this.localidadAdapter);
                IdentificacionDireccionCompletaFragment.this.localidadDropDown.setOnItemClickListener(new OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        IdentificacionDireccionCompletaFragment.this.identificacionViewModel.localidadSeleccionada((Localidad) adapterView.getItemAtPosition(i));
                        IdentificacionDireccionCompletaFragment.this.localidadTIL.setErrorEnabled(false);
                        IdentificacionDireccionCompletaFragment.this.calleTie.setEnabled(true);
                        IdentificacionDireccionCompletaFragment.this.numeroTie.setEnabled(true);
                        IdentificacionDireccionCompletaFragment.this.pisoTie.setEnabled(true);
                        IdentificacionDireccionCompletaFragment.this.puertaTie.setEnabled(true);
                        IdentificacionDireccionCompletaFragment.this.codigoPostalTie.setEnabled(true);
                        IdentificacionDireccionCompletaFragment.this.otrosTie.setEnabled(true);
                        InputMethodManager inputMethodManager = (InputMethodManager) IdentificacionDireccionCompletaFragment.this.requireContext().getSystemService("input_method");
                        if (inputMethodManager != null) {
                            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 2);
                        }
                    }
                });
                IdentificacionDireccionCompletaFragment.this.localidadDropDown.setOnEditorActionListener(new OnEditorActionListener() {
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        Localidad localidad = (Localidad) IdentificacionDireccionCompletaFragment.this.localidadAdapter.a();
                        if (localidad == null) {
                            IdentificacionDireccionCompletaFragment.this.localidadTIL.setError("Localidad inválida");
                            IdentificacionDireccionCompletaFragment.this.localidadTIL.setErrorEnabled(true);
                            IdentificacionDireccionCompletaFragment.this.identificacionViewModel.localidadSeleccionada(null);
                            IdentificacionDireccionCompletaFragment.this.localidadDropDown.setText("");
                            IdentificacionDireccionCompletaFragment.this.calleTie.setEnabled(false);
                            IdentificacionDireccionCompletaFragment.this.numeroTie.setEnabled(false);
                            IdentificacionDireccionCompletaFragment.this.pisoTie.setEnabled(false);
                            IdentificacionDireccionCompletaFragment.this.puertaTie.setEnabled(false);
                            IdentificacionDireccionCompletaFragment.this.codigoPostalTie.setEnabled(false);
                            IdentificacionDireccionCompletaFragment.this.otrosTie.setEnabled(false);
                            return true;
                        }
                        IdentificacionDireccionCompletaFragment.this.localidadDropDown.setText(localidad.toString());
                        IdentificacionDireccionCompletaFragment.this.localidadDropDown.dismissDropDown();
                        IdentificacionDireccionCompletaFragment.this.localidadTIL.setError(null);
                        IdentificacionDireccionCompletaFragment.this.localidadTIL.setErrorEnabled(false);
                        IdentificacionDireccionCompletaFragment.this.identificacionViewModel.localidadSeleccionada(localidad);
                        IdentificacionDireccionCompletaFragment.this.calleTie.setEnabled(true);
                        IdentificacionDireccionCompletaFragment.this.numeroTie.setEnabled(true);
                        IdentificacionDireccionCompletaFragment.this.pisoTie.setEnabled(true);
                        IdentificacionDireccionCompletaFragment.this.puertaTie.setEnabled(true);
                        IdentificacionDireccionCompletaFragment.this.codigoPostalTie.setEnabled(true);
                        IdentificacionDireccionCompletaFragment.this.otrosTie.setEnabled(true);
                        return false;
                    }
                });
            }
        });
        this.identificacionViewModel.obtenerActualizarUsuarioLiveData().a(requireActivity(), new r<c.c.a.e.e.a<Boolean>>() {
            public void onChanged(c.c.a.e.e.a<Boolean> aVar) {
                IdentificacionDireccionCompletaFragment.this.loaderDialog.dismiss();
            }
        });
        this.calleTie.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                IdentificacionDireccionCompletaFragment.this.calleTIL.setErrorEnabled(false);
            }
        });
        this.numeroTie.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                IdentificacionDireccionCompletaFragment.this.numeroTIL.setErrorEnabled(false);
            }
        });
        this.codigoPostalTie.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                IdentificacionDireccionCompletaFragment.this.codigoPostalTIL.setErrorEnabled(false);
            }
        });
    }

    private void iniciarViews() {
        this.provinciasDropDown = (AutoCompleteTextView) getView().findViewById(R.id.dropdown_provincia_identificacion_fragment);
        this.provinciaTIL = (TextInputLayout) getView().findViewById(R.id.ti_provincia_selector);
        this.localidadDropDown = (AutoCompleteTextView) getView().findViewById(R.id.dropdown_localidades_identificacion_fragment);
        this.localidadTIL = (TextInputLayout) getView().findViewById(R.id.ti_localidad_selector);
        this.calleTie = (TextInputEditText) getView().findViewById(R.id.tie_calle_identificacion_fragment);
        this.calleTIL = (TextInputLayout) getView().findViewById(R.id.til_calle_identificacion_fragment);
        this.numeroTie = (TextInputEditText) getView().findViewById(R.id.tie_numero_casa_identificacion_fragment);
        this.numeroTIL = (TextInputLayout) getView().findViewById(R.id.til_numero);
        this.codigoPostalTie = (TextInputEditText) getView().findViewById(R.id.tie_codigo_postal_identificacion_fragment);
        this.codigoPostalTIL = (TextInputLayout) getView().findViewById(R.id.til_codigo_postal);
        this.puertaTie = (TextInputEditText) getView().findViewById(R.id.tie_puerta_identificacion_fragment);
        this.pisoTie = (TextInputEditText) getView().findViewById(R.id.tie_piso_identificacion_fragment);
        this.otrosTie = (TextInputEditText) getView().findViewById(R.id.tie_otros_identificacion_fragment);
        this.botonSiguiente = (TextView) getView().findViewById(R.id.btn_siguiente_direccion_completa_identificacion_fragment);
        c activity = getActivity();
        getActivity().getLayoutInflater();
        this.loaderDialog = a.a.a.b.a.a((Context) activity);
        this.calleTie.setEnabled(false);
        this.numeroTie.setEnabled(false);
        this.pisoTie.setEnabled(false);
        this.puertaTie.setEnabled(false);
        this.codigoPostalTie.setEnabled(false);
        this.otrosTie.setEnabled(false);
    }

    private void lanzarDialogoPermisosLocalizacion() {
        this.identificacionViewModel.lanzarDialogoPermisosLocalizacion(213);
    }

    private void llenarProvinciaSpinner() {
        IdentificacionViewModel identificacionViewModel2 = this.identificacionViewModel;
        Provincia provincia = identificacionViewModel2.provinciaSeleccionado;
        if (provincia != null) {
            this.provinciasDropDown.setText(provincia.getNombre());
            Localidad localidad = this.identificacionViewModel.localidad;
            if (localidad != null) {
                this.localidadDropDown.setText(localidad.toString());
                this.calleTie.setEnabled(true);
                this.numeroTie.setEnabled(true);
                this.pisoTie.setEnabled(true);
                this.puertaTie.setEnabled(true);
                this.codigoPostalTie.setEnabled(true);
                this.otrosTie.setEnabled(true);
                return;
            }
            return;
        }
        identificacionViewModel2.localidadSeleccionada(null);
    }

    private r<Boolean> obtenerObservadorDelDialogoCustomDePermisoDeUbicacion() {
        return new r<Boolean>() {
            public void onChanged(Boolean bool) {
                if (bool.booleanValue()) {
                    IdentificacionDireccionCompletaFragment.this.identificacionViewModel.obtenerUbicacionLatLong();
                } else {
                    IdentificacionDireccionCompletaFragment.this.identificacionViewModel.inicializaGeoRemoto();
                }
            }
        };
    }

    /* access modifiers changed from: private */
    public Boolean validarFormulario() {
        boolean z;
        this.provinciaAdapter.a();
        boolean z2 = false;
        if (this.identificacionViewModel.localidad == null) {
            this.localidadTIL.setError("Localidad inválida");
            this.localidadTIL.setErrorEnabled(true);
            z = false;
        } else {
            this.localidadTIL.setErrorEnabled(false);
            z = true;
        }
        if (this.identificacionViewModel.provinciaSeleccionado == null) {
            this.provinciaTIL.setError("Provincia inválida");
            this.provinciaTIL.setErrorEnabled(true);
            z = false;
        } else {
            this.provinciaTIL.setErrorEnabled(false);
        }
        if (this.calleTIL.getEditText().getText().toString().isEmpty() || this.calleTIL.getEditText().getText().toString().length() > 70) {
            this.calleTIL.setError("Calle inválida");
            this.calleTIL.setErrorEnabled(true);
            z = false;
        } else {
            this.calleTIL.setErrorEnabled(false);
        }
        if (this.numeroTIL.getEditText().getText().toString().isEmpty() || this.numeroTIL.getEditText().getText().toString().length() > 8) {
            this.numeroTIL.setError("Número inválido");
            this.numeroTIL.setErrorEnabled(true);
            z = false;
        } else {
            this.numeroTIL.setErrorEnabled(false);
        }
        if (this.codigoPostalTIL.getEditText().getText().toString().isEmpty() || this.codigoPostalTIL.getEditText().getText().toString().length() != 4) {
            this.codigoPostalTIL.setError("Código postal inválido");
            this.codigoPostalTIL.setErrorEnabled(true);
        } else {
            this.codigoPostalTIL.setErrorEnabled(false);
            z2 = z;
        }
        return Boolean.valueOf(z2);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        iniciarViews();
        IdentificacionViewModel identificacionViewModel2 = (IdentificacionViewModel) new z(getActivity()).a(IdentificacionViewModel.class);
        this.identificacionViewModel = identificacionViewModel2;
        identificacionViewModel2.obtenerResultadoDialogoDePermisoDeUbicacionLivaData().a(getViewLifecycleOwner(), obtenerObservadorDelDialogoCustomDePermisoDeUbicacion());
        lanzarDialogoPermisosLocalizacion();
        iniciarObservers();
        iniciarListeners();
        llenarProvinciaSpinner();
        this.identificacionViewModel.inicializaGeoRemoto();
    }

    public void onCreate(Bundle bundle) {
        String str = "IdentificacionDireccionCompletaFragment#onCreate";
        TraceMachine.startTracing("IdentificacionDireccionCompletaFragment");
        try {
            TraceMachine.enterMethod(this._nr_trace, str, null);
        } catch (NoSuchFieldError unused) {
            TraceMachine.enterMethod(null, str, null);
        }
        super.onCreate(bundle);
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        String str = "IdentificacionDireccionCompletaFragment#onCreateView";
        try {
            TraceMachine.enterMethod(this._nr_trace, str, null);
        } catch (NoSuchFieldError unused) {
            TraceMachine.enterMethod(null, str, null);
        }
        View inflate = layoutInflater.inflate(R.layout.identificacion_pregunta_direccion_completa_fragment, viewGroup, false);
        TraceMachine.exitMethod();
        return inflate;
    }

    public void onStart() {
        super.onStart();
    }

    public void onStop() {
        super.onStop();
    }
}
