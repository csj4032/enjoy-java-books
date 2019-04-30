package chapter08.item51;

public class MethodSignatureTest {

    private void constructure() {
        ManyParamConstruct manyParamConstructure = new ManyParamConstruct(1, 2, 3, 4, 5, 6, 7, 8, 9);
        ManyParam manyParam = new ManyParam();
        manyParam.setI(1);
        manyParam.setI1(2);
        manyParam.execute();
        ManyParamConstruct manyParamConstructure1 = new ManyParamConstruct(manyParam);
    }
}
