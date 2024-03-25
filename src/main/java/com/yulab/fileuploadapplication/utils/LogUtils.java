package com.yulab.fileuploadapplication.utils;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

import java.util.Date;

/**
 * log util class
 */
@Slf4j
public class LogUtils {

    /**
     * write log to file
     * @param ex
     */
    public static void logToFile(Exception ex) {
        StackTraceElement stackTraceElement = ex.getStackTrace()[0];
        //出错行
        int lineNumber = stackTraceElement.getLineNumber();
        //类名
        String className = stackTraceElement.getClassName();
        //方法名
        String methodName = stackTraceElement.getMethodName();
        log.error("方法:" + className + "." + methodName + " | " +
                "参数:" + stackTraceElement + " | " + "错误行：" + lineNumber + " | " +
                "时间:" + " | " + new Date() + " | " + "异常内容:" + ex.toString()
        );
    }

    /**
     * Log output to file, provided to log aspect
     * @param joinPoint
     * @param ex
     */
    public static void logToFile(JoinPoint joinPoint, Throwable ex) {
        //出错行
        int lineNumber = ex.getStackTrace()[0].getLineNumber();
        //方法签名
        Signature signature = joinPoint.getSignature();
        //参数
        Object[] args = joinPoint.getArgs();
        StringBuilder builder = new StringBuilder();
        if (args.length > 0) {
            for (Object o : args) {
                builder.append(o);
            }
        }
        log.error("method:" + signature + " | " + "parameters:" + builder.toString() +
                " | " + "line：" + lineNumber + " | " + "time:" + new Date() +
                " | " + "exception:" + ex.toString()
        );
    }
}
