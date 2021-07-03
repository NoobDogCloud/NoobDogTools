package common.java.Reflect;

import common.java.Reflect.Annotation.MyClassVisitor;
import org.objectweb.asm.ClassReader;

import java.io.IOException;
import java.lang.annotation.Annotation;

import static org.objectweb.asm.Opcodes.ASM9;

public class AnnotationAccess {
    private ClassReader cr;

    private AnnotationAccess(Class<?> cls) {
        try {
            cr = new ClassReader(cls.getName());
            // 扫描类注解
            cr.accept(new MyClassVisitor(ASM9), ClassReader.SKIP_DEBUG);
            // 扫描方法注解
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AnnotationAccess get(Class<?> cls) {
        return new AnnotationAccess(cls);
    }

    // 获得类注解
    public Annotation[] getDeclaredAnnotations() {
        return null;
    }

    // 获得方法注解

    // 获得字段注解(以后再做)
    // 获得方法参数注解(以后再做)
}
