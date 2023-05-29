package listners;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.IDataProviderAnnotation;
import java.lang.reflect.Method;

public class testNgRunParallelTransformer implements IAnnotationTransformer {

    @Override
    public void transform(IDataProviderAnnotation annotation, Method method){
        boolean runInParallel = Boolean.getBoolean("parallel.run");
        if(runInParallel){
            annotation.setParallel(true);
        }
    }
}
