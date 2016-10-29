package examples.android.rx.com.org.simplerxandroid;


import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
/**
 * Created by User on 10/29/2016.
 */

public class Basics {
    private static final String TAG = Basics.class.getName();

    public Basics(){
        Log.d(TAG, "Constructor");
        // Your first observable
        Observable<String> myObservable1 = Observable.just("Hello");

        Observer<String> myObserver = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, s);
            }
        };

        Subscription mySubscription = myObservable1.subscribe(myObserver);
        mySubscription.unsubscribe();

        // Using Action1
        Action1<String> myAction1 = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d("My Action", s);
            }
        };

        myObservable1.subscribe(myAction1);

        // Observable from an array
        Observable<Integer> myArrayObservable = Observable.from(new Integer[]{1, 2, 3, 4, 5, 6});
        myArrayObservable.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer i) {
                Log.d("My Action", String.valueOf(i));
            }
        });

        // Map operator
        myArrayObservable.map(new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
                return integer * integer;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer i) {
                Log.d("My Action 1", String.valueOf(i));
            }
        });

        // Chained operators
        myArrayObservable
                .skip(2)
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer % 2 == 0;
                    }
                }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer i) {
                Log.d("My Action 2", String.valueOf(i));
            }
        });

    }
}
