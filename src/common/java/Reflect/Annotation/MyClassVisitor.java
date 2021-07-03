package common.java.Reflect.Annotation;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;

import java.lang.annotation.Annotation;
import java.util.Map;

public class MyClassVisitor extends ClassVisitor {
    // 注解类型 -> 实际注解
    private transient volatile Map<Class<? extends Annotation>, Annotation> declaredAnnotations;

    public MyClassVisitor(int api) {
        super(api);
    }

    // 类注解
    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        /*
        try {
            Class<?> a = Class.forName(desc);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        */

        AnnotationVisitor r = new MyAnnotationVisitor(api, super.visitAnnotation(desc, visible));

        return r;
    }
}
