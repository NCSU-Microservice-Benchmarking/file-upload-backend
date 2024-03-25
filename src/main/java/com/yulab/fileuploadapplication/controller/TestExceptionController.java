package com.yulab.fileuploadapplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.yulab.fileuploadapplication.utils.LogUtils.logToFile;

/**
 *  test exception log
 */
@RestController
@RequestMapping("/Ex")
public class TestExceptionController {
    /**
     * test aspect log
     * @return
     */
    @GetMapping("/aspect")
    public int aspect() {
        int i = 1 / 0;
        return i;
    }

    /**
     * test util log
     */
    @GetMapping("/util")
    public void util() {
        try {
            System.out.println(1/0);
        } catch (Exception e) {
            logToFile(e);
        }
    }
}
