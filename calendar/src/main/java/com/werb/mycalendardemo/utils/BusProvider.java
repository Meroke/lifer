package com.werb.mycalendardemo.utils;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;


/**
 * 事件总线机制，用于收发消息事件
 */
public class BusProvider {

    public static BusProvider mInstance;

    private final Subject<Object> mBus = PublishSubject.create().toSerialized();


    // region Constructors

    public static BusProvider getInstance() {
        if (mInstance == null) {
            mInstance = new BusProvider();
        }
        return mInstance;
    }

    // endregion

    // region Public methods

    public void send(Object object) {
        mBus.onNext(object);
    }

    public Observable<Object> toObserverable() {
        return mBus;
    }

    // endregion
}
