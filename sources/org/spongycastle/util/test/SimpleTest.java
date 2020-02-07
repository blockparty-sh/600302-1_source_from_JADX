package org.spongycastle.util.test;

import java.io.PrintStream;
import org.spongycastle.util.Arrays;

public abstract class SimpleTest implements Test {
    public abstract String getName();

    public abstract void performTest() throws Exception;

    private TestResult success() {
        return SimpleTestResult.successful(this, "Okay");
    }

    /* access modifiers changed from: protected */
    public void fail(String str) {
        throw new TestFailedException(SimpleTestResult.failed(this, str));
    }

    /* access modifiers changed from: protected */
    public void fail(String str, Throwable th) {
        throw new TestFailedException(SimpleTestResult.failed(this, str, th));
    }

    /* access modifiers changed from: protected */
    public void fail(String str, Object obj, Object obj2) {
        throw new TestFailedException(SimpleTestResult.failed(this, str, obj, obj2));
    }

    /* access modifiers changed from: protected */
    public boolean areEqual(byte[] bArr, byte[] bArr2) {
        return Arrays.areEqual(bArr, bArr2);
    }

    public TestResult perform() {
        try {
            performTest();
            return success();
        } catch (TestFailedException e) {
            return e.getResult();
        } catch (Exception e2) {
            StringBuilder sb = new StringBuilder();
            sb.append("Exception: ");
            sb.append(e2);
            return SimpleTestResult.failed(this, sb.toString(), e2);
        }
    }

    protected static void runTest(Test test) {
        runTest(test, System.out);
    }

    protected static void runTest(Test test, PrintStream printStream) {
        TestResult perform = test.perform();
        printStream.println(perform.toString());
        if (perform.getException() != null) {
            perform.getException().printStackTrace(printStream);
        }
    }
}
