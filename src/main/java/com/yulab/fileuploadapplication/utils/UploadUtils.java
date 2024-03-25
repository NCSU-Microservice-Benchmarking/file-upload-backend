package com.yulab.fileuploadapplication.utils;

import java.util.HashMap;
import java.util.Map;
import static com.yulab.fileuploadapplication.utils.FileUtils.generateFileName;

/**
 * Chunk upload tool class
 */
public class UploadUtils {
    /**
     * Inner class records the information of the chunk upload file
     */
    private static class Value {
        String name;
        boolean[] status;

        Value(int n) {
            this.name = generateFileName();
            this.status = new boolean[n];
        }
    }

    private static Map<String, Value> chunkMap = new HashMap<>();

    /**
     * Check if the (all chunks) file has been uploaded
     * @param key
     * @return
     */
    public static boolean isUploaded(String key) {
        if (isExist(key)) {
            for (boolean b : chunkMap.get(key).status) {
                if (!b) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Check if specific chunk has been uploaded
     * @param key
     * @return
     */
    private static boolean isExist(String key) {
        return chunkMap.containsKey(key);
    }

    /**
     * Add upload chunk record for file
     * @param key
     * @param chunk
     */
    public static void addChunk(String key, int chunk) {
        chunkMap.get(key).status[chunk] = true;
    }

    /**
     * Remove the key-value pair with key from the map
     * @param key
     */
    public static void removeKey(String key) {
        if (isExist(key)) {
            chunkMap.remove(key);
        }
    }

    /**
     * Get the random file name of the chunk upload
     * @param key
     * @param chunks
     * @return
     */
    public static String getFileName(String key, int chunks) {
        if (!isExist(key)) {
            synchronized (UploadUtils.class) {
                if (!isExist(key)) {
                    chunkMap.put(key, new Value(chunks));
                }
            }
        }
        return chunkMap.get(key).name;
    }
}
