package com.commerce.platform.handler;

import com.commerce.platform.common.Constance;
import com.commerce.platform.factory.ParamsChange;
import com.commerce.platform.service.ParamChangeService;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/2 0002.
 * 公司为test时的参数转换处理类
 */
@Component
public class TestHandler implements ParamsChange {

    @Autowired
    ParamChangeService pcs;
    @Autowired
    AssemblyHandler ah;



    @Override
    public void paramChange(Map<String, Object> paraMap) {
        try {
            //拿到参数模板
            List<Map<String,Object>> params=pcs.paramChange(paraMap);
            if(params==null||params.size()<0){
                paraMap.clear();
                paraMap.put(Constance.PARAM_EMPTY,"TEST公司未提供参数模板:"+Constance.RESPONSE_PARAM_EMPTY);
                return;
            }
            for(Map<String,Object> param:params){
                String newKey=param.get("dest_param").toString();
                String oldKey=param.get("src_param").toString();
                Object val=paraMap.get(oldKey);
                if(param.containsKey("filling_param")&&param.containsKey("filling_value")){
                    String fillingKey=param.get("filling_param").toString();
                    Object fillingVal=param.get("filling_value").toString();
                    //如果key具备默认值
                    if(fillingKey!=null&&!"".equals(fillingKey)){
                        //如果传的参数为空则需要默认值填充
                        if("".equals(val.toString())){
                            paraMap.put(newKey,fillingVal);
                        }
                    }else{
                        paraMap.put(newKey,val);
                    }
                }else{
                    paraMap.put(newKey,val);
                }
                paraMap.remove(oldKey);
            }
            //调用责任链
            ah.assemblyReqHandler(paraMap);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
