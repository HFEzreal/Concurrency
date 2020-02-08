package com.liubin.proxy.dynamicProxy;

import com.liubin.proxy.staticProxy.Train;
import com.liubin.proxy.staticProxy.Travel;
import lombok.extern.slf4j.Slf4j;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.FileOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author liubin
 * @description 手写jdk动态代理
 * @date 2020/2/8 16:11
 */
@Slf4j
public class WriteJDKDynamicProxy {

    public static void main(String[] args) throws Exception {
        Travel travel = (Travel)newProxyInstance(Travel.class,new Train());
        travel.travel();
    }

    public static Object newProxyInstance(Class inface,Object o) throws Exception {
        String rn = "\r\n";
        String methodStr = "";
        for (Method method : inface.getMethods()) {
            String methodName = method.getReturnType().getName();
            methodStr += "    @Override" + rn +
                    "    public " + methodName + " " + method.getName() + "() {" + rn +
                    "       log.info(\" 旅行准备工作 \");" + rn;
            if (methodName.equals("void")) {
                methodStr += "       travel." + method.getName() + "();" + rn +
                        "       log.info(\" 旅行总结工作 \");" + rn +
                        "    }" + rn;
            } else {
                methodStr += "       " + methodName + " object = travel." + method.getName() + "();" + rn +
                        "       log.info(\" 旅行总结工作 \");" + rn +
                        "       return object;" + rn +
                        "    }" + rn;
            }

        }
        String str =
                "package com.liubin.proxy.dynamicProxy;" + rn +
                        "import lombok.extern.slf4j.Slf4j;" + rn +
                        "@Slf4j" + rn +
                        "public class $Proxy0 implements " + inface.getName() + " {" + rn +
                        "    private " + inface.getName() + " travel;" + rn +
                        "    public $Proxy0(" + inface.getName() + " travel) {" + rn +
                        "       this.travel = travel;" + rn +
                        "    }" + rn
                        + methodStr +
                        "}";
        //生成$Proxy0.java文件
        String filename = System.getProperty("user.dir") + "\\target\\classes\\com\\liubin\\proxy\\dynamicProxy\\$Proxy0.java";
        FileOutputStream file = new FileOutputStream(filename);
        file.write(str.getBytes());
        file.flush();
        file.close();
        //拿到编译器
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        //文件管理者
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        //获取文件
        Iterable units = fileManager.getJavaFileObjects(filename);
        //编译任务
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, units);
        //进行编译
        task.call();
        fileManager.close();

        ClassLoader cl = ClassLoader.getSystemClassLoader();
        Class c = cl.loadClass("com.liubin.proxy.dynamicProxy.$Proxy0");
        Constructor ctr = c.getConstructor(inface);
        return ctr.newInstance(o);
    }
}