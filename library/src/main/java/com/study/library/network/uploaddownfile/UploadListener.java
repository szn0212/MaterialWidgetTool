package com.study.library.network.uploaddownfile;


/**
 * Created by only on 16/10/18.
 * Email: onlybeyond99@gmail.com
 */

public interface UploadListener {
    void uploadFinish(FileBean fileBean);
    void uploadProgress(FileBean fileBean);
}
