package com.kaiback.canteen;

import com.kaiback.canteen.util.LogUtil;
import com.kaiback.canteen.util.TimeUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ruiaa on 2017/10/9.
 */

public class LearnRx {

    public static boolean test() {
        LearnRx learnRx = new LearnRx();
        learnRx.multiSubscribe();
        return true;
    }

    //通用流程 ： 定义被观察者、观察者 ， 订阅
    public void normalCreate() {

        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                for (int i = 0; true; i++) {
                    LogUtil.w("subscribe####  send 线程", Thread.currentThread().getId());
                    e.onNext(i + "  " + TimeUtils.milliseconds2String(System.currentTimeMillis()));
                    try {
                        Thread.sleep(2000);
                    } catch (Exception el) {
                        LogUtil.e("subscribe####", el);
                    }
                }
            }
        });

        Observer<String> observer = new Observer<String>() {
            Disposable disposable;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                LogUtil.w("onSubscribe#### 线程 ", Thread.currentThread().getId());
                disposable = d;
            }

            @Override
            public void onNext(@NonNull String s) {
                LogUtil.w("onNext####", s + "  线程" + Thread.currentThread().getId());
                if (s.substring(0, 1).equals("6")) disposable.dispose();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                LogUtil.e("onError####", e);
            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }

    public void normalCreateLink() {
        Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                        for (int i = 0; true; i++) {
                            LogUtil.w("subscribe####  send 线程", Thread.currentThread().getId());
                            e.onNext(i + "  " + TimeUtils.milliseconds2String(System.currentTimeMillis()));
                            try {
                                Thread.sleep(2000);
                            } catch (Exception e1) {
                                LogUtil.e("subscribe####", e1);
                            }

                            /*if (i > 10) {
                                throw new NullPointerException();
                            }*/
                        }
                    }
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        LogUtil.w("onSubscribe#### 线程 ", Thread.currentThread().getId());
                        disposable = d;
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        LogUtil.w("onNext####", s + "  线程" + Thread.currentThread().getId());
                        if (s.substring(0, 1).equals("6")) disposable.dispose();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogUtil.e("onError####", e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void multiSubscribe() {
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                for (int i = 0; true; i++) {
                    LogUtil.w("subscribe####  send 线程", Thread.currentThread().getId());
                    e.onNext(i + "  " + TimeUtils.milliseconds2String(System.currentTimeMillis()));
                    try {
                        Thread.sleep(2000);
                    } catch (Exception el) {
                        LogUtil.e("subscribe####", el);
                    }
                }
            }
        });

        observable.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        LogUtil.w("onSubscribe#### 线程 ", Thread.currentThread().getId());
                        disposable = d;
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        LogUtil.w("onNext####  1订阅  ", s + "  线程" + Thread.currentThread().getId());
                        if (s.substring(0, 1).equals("6")) disposable.dispose();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogUtil.e("onError####", e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        new Thread(() -> {
            try {
                Thread.sleep(7000);
                observable.subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<String>() {
                            Disposable disposable;

                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                LogUtil.w("onSubscribe#### 线程 ", Thread.currentThread().getId());
                                disposable = d;
                            }

                            @Override
                            public void onNext(@NonNull String s) {
                                LogUtil.w("onNext####  2订阅  ", s + "  线程" + Thread.currentThread().getId());
                                if (s.substring(0, 1).equals("6")) disposable.dispose();
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                LogUtil.e("onError####", e);
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            } catch (Exception e) {
                LogUtil.e("subscribe####", e);
            }
        }).start();
        new Thread(() -> {
            try {
                Thread.sleep(30000);
                observable.subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<String>() {
                            Disposable disposable;

                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                LogUtil.w("onSubscribe#### 线程 ", Thread.currentThread().getId());
                                disposable = d;
                            }

                            @Override
                            public void onNext(@NonNull String s) {
                                LogUtil.w("onNext####  3订阅  ", s + "  线程" + Thread.currentThread().getId());
                                if (s.substring(0, 1).equals("6")) disposable.dispose();
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                LogUtil.e("onError####", e);
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            } catch (Exception e) {
                LogUtil.e("subscribe####", e);
            }
        }).start();
    }
}
