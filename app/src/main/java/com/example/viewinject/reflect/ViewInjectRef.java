package com.example.viewinject.reflect;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.example.viewinject.anotations.OnClickEvent;
import com.example.viewinject.anotations.ViewInject;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/08/14 0014.
 */

public class ViewInjectRef {
    private static final String TAG = "ViewInjectRef";

    public static void init(Activity activity) {
        findViewById(activity);
        setOnClick(activity);
    }

    private static void findViewById(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        View decorView = activity.getWindow().getDecorView();
        for (Field field : aClass.getDeclaredFields()) {
            field.setAccessible(true);
            Annotation[] annotations = field.getDeclaredAnnotations();
            if (annotations.length < 1)
                continue;
            if (annotations[0] instanceof ViewInject) {

                ViewInject inject = (ViewInject) annotations[0];
                int resId = inject.value();
                if (resId == -1) {
                    throw new RuntimeException("ResId Not Found");
                }
                if (field.getGenericType().toString().equals("class android.widget.TextView")) {

                    TextView textView = decorView.findViewById(resId);

                    try {
                        field.set(activity, textView);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static void setOnClick(final Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        View decorView = activity.getWindow().getDecorView();

        for (final Method method : aClass.getDeclaredMethods()) {
            method.setAccessible(true);
            Annotation[] annos = method.getDeclaredAnnotations();
            if (annos.length < 1)
                continue;
            if (annos[0] instanceof OnClickEvent) {
                OnClickEvent onClickAnno = (OnClickEvent) annos[0];
                int[] resIds = onClickAnno.value();
                if (resIds.length < 1)
                    continue;
                for (int resId : resIds) {
                    View view = decorView.findViewById(resId);
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                method.invoke(activity, v);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }
    }

}






















