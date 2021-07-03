package common.java.Reflect.Annotation;

import org.objectweb.asm.AnnotationVisitor;

public class MyAnnotationVisitor extends AnnotationVisitor {

    public MyAnnotationVisitor(int api, AnnotationVisitor av) {
        super(api, av);
    }

    /**
     * 读取注解的值
     */
    @Override
    public void visit(String name, Object value) {
        super.visit(name, value);
        System.out.println(name + " = " + value);
    }

    /*
     * 注解枚举的类型的值
     */
    @Override
    public void visitEnum(String name, String desc, String value) {
        super.visitEnum(name, desc, value);
        // name =value, desc=Lcommon/java/InterfaceModel/Type/InterfaceType$type; , value=SessionApi
        System.out.println("name =" + name + ", desc=" + desc + " , value=" + value);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String name, String desc) {
        return super.visitAnnotation(name, desc);
    }

    /**
     * 注解数组类型的值
     */
    @Override
    public AnnotationVisitor visitArray(String name) {
        System.out.println("Array:" + name);
        return new MyArrayAnnotationVisitor(api, super.visitArray(name));
    }

}