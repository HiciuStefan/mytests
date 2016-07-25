package com.example.hiciu.apptesting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button view = (Button) findViewById(R.id.button);
        view.setOnClickListener(e -> showMessage(e.toString()));
        Observable.just("one", "two", "three", "four", "five")
                .map(s -> "My name is" + s)
                /*.subscribeOn(Schedulers.newThread())*/
                /*.observeOn(Schedulers.)*/
                .subscribe(s -> showMessage(s));

        Observable myObs = Observable.just("one", "two", "three", "four", "five");
        Observable myObservable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                        for (int i = 1; i < 5; i++) {
                            observer.onNext(i);
                        }
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        });
      /*  query().flatMap(urls->Observable.from(urls))
               .map(url->"This is the url"+url)
                .subscribeOn(Schedulers.io())
                *//*.observeOn()*//*
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                     showMessage("Completed");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                    showMessage(s);
                    }
                });
*/
    }

    private void showMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }


    Observable<List<String>> query() {
        List<String> myStrings = new ArrayList<>();
        myStrings.add("url1");
        myStrings.add("url2");
        myStrings.add("url3");
        return Observable.just(myStrings);
    }

}
