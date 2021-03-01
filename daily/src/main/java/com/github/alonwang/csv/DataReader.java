package com.github.alonwang.csv;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alonwang
 * @date 2021/2/24 3:03 下午
 */
public class DataReader extends AnalysisEventListener<PlayerData> {
    public List<PlayerData>  datas;
    public DataReader(List<PlayerData> list){
        datas=list;
    }
    @Override
    public void invoke(PlayerData data, AnalysisContext context) {
        datas.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
