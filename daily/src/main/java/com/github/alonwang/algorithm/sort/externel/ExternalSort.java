package com.github.alonwang.algorithm.sort.externel;

import java.io.File;
import java.util.List;

/**
 * 外部排序掩饰
 * 背景 现有一大文件,每行都是一个字符串,需要将这些字符串排好序输出好一个新文件中.
 * 冲突 机器内存有限,远小于文件大小
 * 问题 怎么完成排序
 * 办法
 * 1.大文件分块
 * 将大文件切分为机器内存能容纳的一批小文件
 * 2. 小文件排序
 * 依次将小文件加载到内存中,直接排序,保存到有序小文件.
 * 3. 有序小文件多路归并
 * 对所有的有序小文件, 从头部开始加载一部分进入内存作为输入缓冲, 对他们进行多路归并,将归并结果存到输出缓冲中.
 * 当某个有序小文件的输入缓冲空了,就从文件中再加载一部分,当输出缓冲满了,输出到结果文件中并清空缓冲.
 *
 *
 */
public interface ExternalSort {
    /**
     * 将大文件切分为小文件
     * @param bigFile
     * @return 小文件集合
     */
    List<File> splitToSmallFiles(File bigFile,long availMemory);

    /**
     * 小文件排序
     * @param smallFiles
     * @return 有序小文件集合
     */
    List<File> sortSmallFiles(List<File> smallFiles);

    /**
     * 归并
     * @param sortedFiles
     * @return
     */
    File mergeSortedSmallFiles(List<File> sortedFiles);
}
