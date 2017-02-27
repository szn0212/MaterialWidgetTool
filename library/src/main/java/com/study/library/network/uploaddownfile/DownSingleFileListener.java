package com.study.library.network.uploaddownfile;


/**
 * Created by only on 16/10/19.
 * Email: onlybeyond99@gmail.com
 */
public interface DownSingleFileListener {
    void downSingleFileProgress(FileBean fileBean);

    void downSingleFileComplete(FileBean fileBean);
}

