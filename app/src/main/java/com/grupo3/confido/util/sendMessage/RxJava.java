package com.grupo3.confido.util.sendMessage;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.telephony.SmsManager;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxJava {

    private Disposable disposable;
    private final List<Contact> contacts;

    //MENSAJE
    private String messageHelp;
    private Context context;
    private Handler handler = new Handler(Looper.getMainLooper());

    //UBICACIÓN
    private String userURL;

    public RxJava() {
        contacts = new ArrayList<>();
    }


    public void addContacts(Contact c) {
        contacts.add(c);
    }


    public void addContext(Context cont) {
        context = cont;
    }


    public int sizeListContacts() {
        return contacts.size();
    }

    public void startEvent(String url) {
        userURL = url;
        Observable<Contact> observable = Observable.fromIterable(contacts);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(createObserver());

    }

    private Observer<Contact> createObserver() {
        return new Observer<Contact>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(Contact c) {
                sendMessage(c);
            }

            @Override
            public void onError(@NonNull Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        };
    }

    private void sendMessage(Contact c) {
        //Implementar lógica de enviar mensaje
        Log.e("Mensaje", "El mensaje se ha enviado al número: " + c.getNumContact());

        //getLocation();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Esperamos 5 segundos para enviar un mensaje
                messageHelp = "Eres mi contacto de emergencia. ¡NECESITO AYUDA!. Está es mi ubicación actual: " + userURL + "\n" +
                        "Nota: Recuerde que la persona que necesita ayuda posiblemente no pueda contestar o regresar su llamada.";

                smsConfido(String.valueOf(c.getNumContact()));

                Log.e("URL", messageHelp);

            }
        }, 5000);

    }

    private void smsConfido(String numC) {
        //Enviar mensaje SMS
        Log.e("Mensaje", "ENTRE A SMS CONFIDO");

        SmsManager smsManager = SmsManager.getDefault();

        ArrayList<String> largeMessage = smsManager.divideMessage(messageHelp);
        smsManager.sendMultipartTextMessage(numC, null, largeMessage, null, null);
    }

    public void destroyDisposable(){
        disposable.dispose();
    }
}