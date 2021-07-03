package common.java.Reflect.Annotation;

import org.objectweb.asm.AnnotationVisitor;

public class MyArrayAnnotationVisitor extends AnnotationVisitor {

    public MyArrayAnnotationVisitor(int api, AnnotationVisitor av) {
        super(api, av);
    }

    /**
     * 读取数组的内容
     */
    @Override
    public void visit(String name, Object value) {
        super.visit(name, value);
        System.out.println(name + " = " + value);
    }
}

